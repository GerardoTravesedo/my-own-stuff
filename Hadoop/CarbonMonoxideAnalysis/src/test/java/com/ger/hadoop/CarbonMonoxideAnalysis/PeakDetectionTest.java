package com.ger.hadoop.CarbonMonoxideAnalysis;

import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.ger.hadoop.CarbonMonoxideAnalysis.Growth.MeasureWritable;
import com.ger.hadoop.CarbonMonoxideAnalysis.Growth.Peak;
import com.ger.hadoop.CarbonMonoxideAnalysis.Growth.PeakDetection;

public class PeakDetectionTest {
	
	@BeforeClass
    public static void oneTimeSetUp() throws Exception{
		
		Class clazz = Class.forName("com.ger.hadoop.CarbonMonoxideAnalysis.PeakDetectionTest");
		
		InputStream inputStream = 
			    clazz.getResourceAsStream("PeakDetectionInput.txt");
		
        BufferedReader reader = 
        		new BufferedReader(new InputStreamReader(inputStream));
        
        String line = null;
               
        while((line = reader.readLine()) != null) {
        	String[] lineValues = line.split(" ");
        	
        	MeasureWritable measureWritable = new MeasureWritable();
        	
        	measureWritable.setDate(lineValues[0]);
        	measureWritable.setTime(lineValues[1]);
        	measureWritable.setValue(Float.parseFloat(lineValues[2]));
        	
        	values.add(measureWritable);
        }
        
        reader.close();
    }

	@Test
    public void testPeakDetection() {
        
        List<Peak> peaks = peakDetection.getPeaks(values);
        
        for (Peak peak : peaks) {
        	assertTrue(peak.getTotal() > PeakDetection.THRESHOLD);
        }
    }
	
	private PeakDetection peakDetection = new PeakDetection();
	private static List<MeasureWritable> values = new ArrayList<MeasureWritable>();
}
