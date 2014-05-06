package com.ger.hadoop.CarbonMonoxideAnalysis.Growth;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.WritableComparable;

public class MeasureWritable implements WritableComparable<MeasureWritable>, Cloneable {
	
	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	public String getTime() {
		return time;
	}
	
	public void setTime(String time) {
		this.time = time;
	}
	
	public float getValue() {
		return value;
	}
	
	public void setValue(float value) {
		this.value = value;
	}

	public void readFields(DataInput in) throws IOException {			
		date = in.readUTF();
		time = in.readUTF();
		value = in.readFloat();
	}

	public void write(DataOutput out) throws IOException {		
		out.writeUTF(date);
		out.writeUTF(time);
		out.writeFloat(value);
	}

	public int compareTo(MeasureWritable mesure) {
		Float thisValue = this.getValue();
		Float otherValue = mesure.getValue();
		
		return thisValue.compareTo(otherValue);		
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	private String date;
	private String time;
	private float value;
	
}
