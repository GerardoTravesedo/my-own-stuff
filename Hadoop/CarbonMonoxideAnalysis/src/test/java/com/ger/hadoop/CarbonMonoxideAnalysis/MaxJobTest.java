package com.ger.hadoop.CarbonMonoxideAnalysis;

import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;
import org.apache.hadoop.mrunit.mapreduce.MapReduceDriver;
import org.apache.hadoop.mrunit.mapreduce.ReduceDriver;
import org.apache.hadoop.mrunit.types.Pair;
import org.junit.Before;
import org.junit.Test;

import com.ger.hadoop.CarbonMonoxideAnalysis.Common.StateSiteWritable;
import com.ger.hadoop.CarbonMonoxideAnalysis.Max.MaxJob;
import com.ger.hadoop.CarbonMonoxideAnalysis.Max.MaxJob.Map;
import com.ger.hadoop.CarbonMonoxideAnalysis.Max.MaxJob.Reduce;

public class MaxJobTest {
	
	MapDriver<LongWritable, Text, StateSiteWritable, FloatWritable> mapDriver;
    ReduceDriver<StateSiteWritable, FloatWritable, Text, FloatWritable> reduceDriver;
    MapReduceDriver<LongWritable, Text, StateSiteWritable, FloatWritable, Text, FloatWritable> mapReduceDriver;

    @Before
    public void setUp() {
    	
	    Map mapper = new MaxJob.Map();
	    Reduce reducer = new MaxJob.Reduce();
	  
	    mapDriver = MapDriver.newMapDriver(mapper);
	    reduceDriver = ReduceDriver.newReduceDriver(reducer);
	    mapReduceDriver = MapReduceDriver.newMapReduceDriver(mapper, reducer);
    }
    
    @Test
    public void testMapper() throws IOException {
    	
    	String line = "RD|I|01|073|0023|42101|2|1|007|554|20130101|02:00|0.35";
    	
        mapDriver.withInput(new LongWritable(), new Text(line));
        
        StateSiteWritable stateSite = new StateSiteWritable();
        stateSite.setState(1);
        stateSite.setSiteId(23);
        
        FloatWritable value = new FloatWritable(0.35f);
        
        Pair<StateSiteWritable, FloatWritable> output = mapDriver.run().get(0);
        
        assertTrue(stateSite.compareTo(output.getFirst()) == 0);
        assertTrue(value.compareTo(output.getSecond()) == 0);
    }
    
    @Test
    public void testReducer() throws Exception {
    	
    	StateSiteWritable stateSite = new StateSiteWritable();
    	
    	stateSite.setSiteId(23);
    	stateSite.setState(1);
	    	
	    List<FloatWritable> values = generateFakeMeasureWritables();
	  
	    reduceDriver.withInput(stateSite, values);
	  
	    reduceDriver.withOutput(
	    		new Text("1 23"), 
	    		new FloatWritable(2.75f));
	  
	    reduceDriver.runTest();
    } 
    
    @Test
    public void testMaperReducer() throws IOException {
    	
    	mapReduceDriver.withInput(new LongWritable(), new Text(
    	          "RD|I|01|073|0023|42101|2|1|007|554|20130101|02:00|0.35"));
    	
    	mapReduceDriver.withInput(new LongWritable(), new Text(
    	          "RD|I|01|073|0023|42101|2|1|007|554|20130101|03:00|0.45"));
    	
    	mapReduceDriver.withInput(new LongWritable(), new Text(
  	          	  "RD|I|01|073|0023|42101|2|1|007|554|20130101|03:00|0.15"));
    	
    	mapReduceDriver.withOutput(new Text("1 23"), new FloatWritable(0.45f));
   
    	mapReduceDriver.runTest();
    }
    
    private List<FloatWritable> generateFakeMeasureWritables() throws Exception {
    	
    	List<FloatWritable> values = new ArrayList<FloatWritable>();
    	
    	Class clazz = Class.forName("com.ger.hadoop.CarbonMonoxideAnalysis.PeakDetectionTest");
		
		InputStream inputStream = 
			    clazz.getResourceAsStream("PeakDetectionInput.txt");
		
        BufferedReader reader = 
        		new BufferedReader(new InputStreamReader(inputStream));
        
        String line = null;
               
        while((line = reader.readLine()) != null) {
        	String[] lineValues = line.split(" ");
        	
        	FloatWritable value = new FloatWritable();
        	
        	value.set(Float.parseFloat(lineValues[2]));
        	
        	values.add(value);
        }
        
        return values;
    }
}
