package com.ger.hadoop.CarbonMonoxideAnalysis.Growth;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

import com.ger.hadoop.CarbonMonoxideAnalysis.Growth.MapperOuput.CompositeGroupingComparator;
import com.ger.hadoop.CarbonMonoxideAnalysis.Growth.MapperOuput.KeyPartitioner;
import com.ger.hadoop.CarbonMonoxideAnalysis.Growth.MapperOuput.StateDateWritable;

public class GrowthJob {
	
	public static class Map 
		extends Mapper<LongWritable, Text, StateDateWritable, MeasureWritable> {
		
		private StateDateWritable stateDate = new StateDateWritable();
		
		private MeasureWritable measure = new MeasureWritable();
		
		public void map(
				LongWritable key, Text value, Context context) 
			throws IOException, InterruptedException {
			
			String line = value.toString();
			
			String[] data = line.split("\\|");
			
			if (data.length < 12 || data[12].isEmpty()) {
				return;
			}
			
			stateDate.setState(Integer.parseInt(data[2]));
			stateDate.setSiteId(Integer.parseInt(data[4]));
			stateDate.setDate(data[10] + "-" + data[11]);
			
			measure.setDate(data[10]);
			measure.setTime(data[11]);
			measure.setValue(Float.parseFloat(data[12]));
			
			context.write(stateDate, measure);
		}
	}
	
	public static class Reduce 
		extends Reducer<StateDateWritable, MeasureWritable, IntWritable, Text> {
	
		public void reduce(
				StateDateWritable key, Iterable<MeasureWritable> values, Context context) 
			throws IOException, InterruptedException {
			
			PeakDetection detector = new PeakDetection();
			
			List<MeasureWritable> measures;
			
			try {
				measures = iterableToListClone(values, key.getState());
			} catch (Exception e) {
				throw new InterruptedException(e.getMessage());
			}
			
			List<Peak> peaks = detector.getPeaks(measures);
			
			for (Peak peak : peaks) {
				IntWritable keyState = new IntWritable(key.getState());
				Text value = generateValueFromPeak(peak);
				
				context.write(keyState, value);
			}
		}
		
		private Text generateValueFromPeak(Peak peak) {
			
			String value = "(" + peak.getOriginDate() + " " +
					peak.getOriginTime() + " - " +
					peak.getEndDate() + " " + peak.getEndTime() + 
					" - " + peak.getTotal() + ")";
			
			return new Text(value);
		}
		
		private List<MeasureWritable> iterableToListClone(
				Iterable<MeasureWritable> values, int state) throws Exception {
			
			// We need to clone the elements since values.next always
			// provides the same instance with updated values. If we don't
			// clone, each previous element added to the list is going to 
			// be updated to the values of the last element inserted (same instance).
			
			List<MeasureWritable> measures = new ArrayList<MeasureWritable>();
			
			for (MeasureWritable measure : values) {
				MeasureWritable clone = (MeasureWritable) measure.clone();
				
				measures.add(clone);				
			}
			
			return measures;
		}
	}
	
	public static void main(String[] args) throws Exception {
		
		Configuration configuration = new Configuration();
		
		Job job = new Job(configuration, "growthJob");
		
		// Configuring the composite key
		job.setPartitionerClass(KeyPartitioner.class);
		job.setGroupingComparatorClass(CompositeGroupingComparator.class);
		//job.setSortComparatorClass(CompositeSortComparator.class);
		
		// Since output key and value are different for mapper
		// and reducer, we specify those for the mapper only
		job.setMapOutputKeyClass(StateDateWritable.class);
		job.setMapOutputValueClass(MeasureWritable.class);
		
		// We specify the mappers and reducers to use
		job.setMapperClass(Map.class);
		job.setReducerClass(Reduce.class);
		
		// The input will come from a file and we'll leave
		// the output in a file as well
		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);
		
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		job.setJarByClass(GrowthJob.class);
		job.waitForCompletion(true);
    }

}
