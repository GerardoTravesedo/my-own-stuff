package com.ger.hadoop.CarbonMonoxideAnalysis.Growth.MapperOuput;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class CompositeGroupingComparator extends WritableComparator {

	/*
	 * We need to guarantee that all keys with the same State
	 * come in the same input group.
	 */
	
	public CompositeGroupingComparator() {
		super(StateDateWritable.class, true);
	}
	
	@Override
	public int compare(WritableComparable value1, WritableComparable value2) {
		
		StateDateWritable key1 = (StateDateWritable) value1;
		StateDateWritable key2 = (StateDateWritable) value2;
		
		Integer state1 = key1.getState();
		Integer state2 = key2.getState();
		
		return state1.compareTo(state2);
	}
	
}
