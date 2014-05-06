package com.ger.hadoop.CarbonMonoxideAnalysis;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.ger.hadoop.CarbonMonoxideAnalysis.Growth.MapperOuput.StateDateWritable;

public class StateDateWritableTest {
	
	@Before
    public void oneTimeSetUp() throws Exception{
		writable1 = new StateDateWritable();
		writable1.setState(1);
		
		writable2 = new StateDateWritable();
		writable2.setState(1);
	}
	
	@Test
    public void testHourComparison() {
             
        writable1.setDate("20130101-03:00");
        writable1.setSiteId(1);
        
        writable2.setDate("20130101-04:00");
        writable2.setSiteId(1);
        
        int output = writable1.compareTo(writable2);        
        assertTrue(output < 0);
    }
	
	@Test
    public void testDayComparison() {
             
        writable1.setDate("20130102-03:00");
        writable1.setSiteId(1);
        
        writable2.setDate("20130101-04:00");
        writable2.setSiteId(1);
        
        int output = writable1.compareTo(writable2);        
        assertTrue(output > 0);
    }
	
	@Test
    public void testSiteComparison() {
             
        writable1.setDate("20130102-03:00");
        writable1.setSiteId(2);
        
        writable2.setDate("20130101-04:00");
        writable2.setSiteId(1);
        
        int output = writable1.compareTo(writable2);        
        assertTrue(output > 0);
    }
	
	private StateDateWritable writable1;
	private StateDateWritable writable2;

}
