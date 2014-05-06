package com.ger.hadoop.CarbonMonoxideAnalysis;

import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.RawComparator;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;
import org.apache.hadoop.mrunit.mapreduce.MapReduceDriver;
import org.apache.hadoop.mrunit.mapreduce.ReduceDriver;
import org.apache.hadoop.mrunit.types.Pair;
import org.junit.Before;
import org.junit.Test;

import com.ger.hadoop.CarbonMonoxideAnalysis.Growth.GrowthJob;
import com.ger.hadoop.CarbonMonoxideAnalysis.Growth.GrowthJob.Map;
import com.ger.hadoop.CarbonMonoxideAnalysis.Growth.GrowthJob.Reduce;
import com.ger.hadoop.CarbonMonoxideAnalysis.Growth.MeasureWritable;
import com.ger.hadoop.CarbonMonoxideAnalysis.Growth.MapperOuput.CompositeGroupingComparator;
import com.ger.hadoop.CarbonMonoxideAnalysis.Growth.MapperOuput.StateDateWritable;

public class GrowthJobTest {
	
	MapDriver<LongWritable, Text, StateDateWritable, MeasureWritable> mapDriver;
    ReduceDriver<StateDateWritable, MeasureWritable, IntWritable, Text> reduceDriver;
    MapReduceDriver<LongWritable, Text, StateDateWritable, MeasureWritable, IntWritable, Text> mapReduceDriver;

    @Before
    public void setUp() {
    	
	    Map mapper = new GrowthJob.Map();
	    Reduce reducer = new GrowthJob.Reduce();
	  
	    mapDriver = MapDriver.newMapDriver(mapper);
	    reduceDriver = ReduceDriver.newReduceDriver(reducer);
	    mapReduceDriver = MapReduceDriver.newMapReduceDriver(mapper, reducer);
    }
    
    @Test
    public void testMapper() throws IOException {
    	
    	String line = "RD|I|01|073|0023|42101|2|1|007|554|20130101|02:00|0.35";
    	
        mapDriver.withInput(new LongWritable(), new Text(line));
        
        StateDateWritable stateDate = parseLineToStateDateWritable(line);
        MeasureWritable measure = parseLineToMeasureWritable(line);    
      
        Pair<StateDateWritable, MeasureWritable> output = mapDriver.run().get(0);
        
        assertTrue(stateDate.compareTo(output.getFirst()) == 0);
        assertTrue(measure.compareTo(output.getSecond()) == 0);
    }
    
    @Test
    public void testReducer() throws Exception {
    	
    	StateDateWritable stateDate = new StateDateWritable();
    	
    	stateDate.setDate("20130101-02:00");
    	stateDate.setSiteId(1);
    	stateDate.setState(1);
	    	
	    List<MeasureWritable> values = generateFakeMeasureWritables();
	  
	    reduceDriver.withInput(stateDate, values);
	  
	    reduceDriver.withOutput(
	    		new IntWritable(stateDate.getState()), 
	    		new Text("(20130101 01:00 - 20130101 06:00 - 2.4)"));
	    
	    reduceDriver.withOutput(
	    		new IntWritable(stateDate.getState()), 
	    		new Text("(20130101 13:00 - 20130101 15:00 - 2.1)"));
	  
	    reduceDriver.runTest();
    } 
    
    // MapReduce test: MRUNIT-88 and MRUNIT-128
    
    private List<MeasureWritable> generateFakeMeasureWritables() throws Exception {
    	
    	List<MeasureWritable> values = new ArrayList<MeasureWritable>();
    	
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
        
        return values;
    }
    
    private MeasureWritable parseLineToMeasureWritable(String line) {
    	
    	MeasureWritable measure = new MeasureWritable();
    	
    	String[] values = line.split("\\|");
    	
    	measure.setDate(values[10]);
    	measure.setTime(values[11]);
    	measure.setValue(Float.parseFloat(values[12]));
    	
    	return measure;
    }
    
    private StateDateWritable parseLineToStateDateWritable(String line) {
    	
    	StateDateWritable stateDate = new StateDateWritable();
    	
    	String[] values = line.split("\\|");
    	  	
    	stateDate.setState(Integer.parseInt(values[2]));
    	stateDate.setSiteId(Integer.parseInt(values[4]));
    	stateDate.setDate(values[10] + "-" + values[11]);
    	
    	return stateDate;
    }
}
