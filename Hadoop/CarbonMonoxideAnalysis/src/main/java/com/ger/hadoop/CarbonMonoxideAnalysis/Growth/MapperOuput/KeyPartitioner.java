package com.ger.hadoop.CarbonMonoxideAnalysis.Growth.MapperOuput;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapred.lib.HashPartitioner;
import org.apache.hadoop.mapreduce.Partitioner;

import com.ger.hadoop.CarbonMonoxideAnalysis.Growth.MeasureWritable;

public class KeyPartitioner extends Partitioner<StateDateWritable, MeasureWritable> {
	
	/*
	 * Since we have a composite key, we need to unsure that all keys
	 * with the same State go to the same reducer. 
	 */
		
	@Override
	public int getPartition(StateDateWritable key, MeasureWritable value,
			int numPartitions) {
		
		IntWritable newKey = new IntWritable(key.getState());
		
		// Default partitioner
		HashPartitioner<IntWritable, MeasureWritable> hashPartitioner = 
				new HashPartitioner<IntWritable, MeasureWritable>();
		
		return hashPartitioner.getPartition(newKey, value, numPartitions);
	}
	
}
