package com.ger.hadoop.CarbonMonoxideAnalysis.Mean;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

import com.ger.hadoop.CarbonMonoxideAnalysis.Common.StateSiteWritable;

public class MeanJob {
	
	public static class Map 
		extends Mapper<LongWritable, Text, StateSiteWritable, FloatWritable> {

		private StateSiteWritable stateSite = new StateSiteWritable(); 
		
		public void map(
				LongWritable key, Text value, Context context) 
			throws IOException, InterruptedException {
			
			String line = value.toString();
			
			String[] data = line.split("\\|");
			
			if (data.length < 12 || data[12].isEmpty()) {
				return;
			}
			
			stateSite.setState(Integer.parseInt(data[2]));
			stateSite.setSiteId(Integer.parseInt(data[4]));
			
			FloatWritable measure = new FloatWritable(
					Float.parseFloat(data[12]));
			
			context.write(stateSite, measure);
		}
	}

	public static class Reduce 
		extends Reducer<StateSiteWritable, FloatWritable, Text, FloatWritable> {
	
		public void reduce(
				StateSiteWritable key, Iterable<FloatWritable> values, Context context) 
			throws IOException, InterruptedException {
			
			int numberOfMeasures = 0;
			
			float sum = 0.0f;
			
			for(FloatWritable val : values) {
				float floatVal = val.get();
				
				sum += floatVal;
				numberOfMeasures++;
			}
			
			context.write(
					new Text(key.getState() + " " + key.getSiteId()), 
					new FloatWritable(sum / numberOfMeasures));
		}
	}

	public static void main(String[] args) throws Exception {
		
		Configuration configuration = new Configuration();
		
		Job job = new Job(configuration, "MeanJob");
		
		// Since output key and value are different for mapper
		// and reducer, we specify those for the mapper only
		job.setMapOutputKeyClass(StateSiteWritable.class);
		job.setMapOutputValueClass(FloatWritable.class);
		
		// We specify the mappers and reducers to use
		job.setMapperClass(Map.class);
		job.setReducerClass(Reduce.class);
		
		// The input will come from a file and we'll leave
		// the output in a file as well
		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);
		
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		job.setJarByClass(MeanJob.class);
		job.waitForCompletion(true);
	}

}
