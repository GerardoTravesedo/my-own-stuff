package com.ger.hadoop.wordcount;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;
import org.apache.hadoop.mrunit.mapreduce.MapReduceDriver;
import org.apache.hadoop.mrunit.mapreduce.ReduceDriver;
import org.junit.Before;
import org.junit.Test;

import com.ger.hadoop.wordcount.Wordcount.Map;
import com.ger.hadoop.wordcount.Wordcount.Reduce;

public class WordCountTest {
	
	MapDriver<LongWritable, Text, Text, IntWritable> mapDriver;
    ReduceDriver<Text, IntWritable, Text, IntWritable> reduceDriver;
    MapReduceDriver<LongWritable, Text, Text, IntWritable, Text, IntWritable> mapReduceDriver;
 
    @Before
    public void setUp() {
    	
      Map mapper = new Wordcount.Map();
      Reduce reducer = new Wordcount.Reduce();
      
      mapDriver = MapDriver.newMapDriver(mapper);
      reduceDriver = ReduceDriver.newReduceDriver(reducer);
      mapReduceDriver = MapReduceDriver.newMapReduceDriver(mapper, reducer);
    }
    
    @Test
    public void testMapper() throws IOException {
    	
      mapDriver.withInput(new LongWritable(), new Text(
          "Test one"));
      
      mapDriver.withOutput(new Text("Test"), new IntWritable(1));
      mapDriver.withOutput(new Text("one"), new IntWritable(1));
      
      mapDriver.runTest();
    }
    
    @Test
    public void testReducer() throws IOException {
    	
      List<IntWritable> values = new ArrayList<IntWritable>();
      
      values.add(new IntWritable(1));
      values.add(new IntWritable(1));
      
      reduceDriver.withInput(new Text("Test"), values);
      
      reduceDriver.withOutput(new Text("Test"), new IntWritable(2));
      
      reduceDriver.runTest();
    }
    
    @Test
    public void testMaperReducer() throws IOException {
    	
    	mapReduceDriver.withInput(new LongWritable(), new Text(
    	          "Test two Test"));
    	
    	mapReduceDriver.withOutput(new Text("Test"), new IntWritable(2));
    	mapReduceDriver.withOutput(new Text("two"), new IntWritable(1));
   
    	mapReduceDriver.runTest();
    }
	
}