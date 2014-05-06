package com.ger.hadoop.CarbonMonoxideAnalysis.Growth.MapperOuput;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class CompositeSortComparator extends WritableComparator {

	/*
	 * We need to specify how the secondary sort must be done.
	 * All elements with the same state will be ordered by
	 * date.
	 */
	
	public CompositeSortComparator() {
		super(StateDateWritable.class, true);
	}
	
	@Override
	public int compare(WritableComparable value1, WritableComparable value2) {
		
		StateDateWritable key1 = (StateDateWritable) value1;
		StateDateWritable key2 = (StateDateWritable) value2;
		
		return key1.compareTo(key2);
	}
	
}
