package com.ger.hadoop.CarbonMonoxideAnalysis.Growth;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.Lists;

public class PeakDetection {
	
	// WHAT HAPPENS TO LITTLE DECREASE AND INMEDIATELY GROWING AGAIN?
	
	public List<Peak> getPeaks(List<MeasureWritable> listValues) {
		
		List<Peak> peaks = new ArrayList<Peak>();
		
		float gradient = 0;		
		MeasureWritable previousMeasure = listValues.get(0);
		MeasureWritable originMeasure = null;
		
		for (MeasureWritable measure : listValues) {
			float currentValue = measure.getValue();
			float previousValue = previousMeasure.getValue();
			
			if (previousValue < currentValue) {
				gradient += currentValue - previousValue;
				
				if (originMeasure == null) {
					originMeasure = previousMeasure;
				}
			}
			else {
				if (gradient > THRESHOLD) {
					Peak peak = new Peak();
					
					peak.setOriginDate(originMeasure.getDate());
					peak.setOriginTime(originMeasure.getTime());
					peak.setOriginValue(originMeasure.getValue());
					peak.setEndDate(previousMeasure.getDate());
					peak.setEndTime(previousMeasure.getTime());
					peak.setEndValue(previousMeasure.getValue());
					
					peaks.add(peak);
				}
				
				originMeasure = null;
				
				gradient = 0;
			}
			
			previousMeasure = measure;
		}
		
		return peaks;
	}
	
	public final static float THRESHOLD = 2.0F;
}
