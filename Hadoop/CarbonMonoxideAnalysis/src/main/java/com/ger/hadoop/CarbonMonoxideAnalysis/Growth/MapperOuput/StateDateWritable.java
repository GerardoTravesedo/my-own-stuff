package com.ger.hadoop.CarbonMonoxideAnalysis.Growth.MapperOuput;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.hadoop.io.WritableComparable;

public class StateDateWritable implements WritableComparable<StateDateWritable> {
	
	public int getState() {
		return state;
	}
	
	public void setState(int state) {
		this.state = state;
	}
	
	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	public int getSiteId() {
		return siteId;
	}

	public void setSiteId(int siteId) {
		this.siteId = siteId;
	}

	public void readFields(DataInput in) throws IOException {			
		state = in.readInt();
		siteId = in.readInt();
		date = in.readUTF();
	}

	public void write(DataOutput out) throws IOException {		
		out.writeInt(state);
		out.writeInt(siteId);
		out.writeUTF(date);
	}

	public int compareTo(StateDateWritable stateDate) {
		Integer thisState = this.getState();
		Integer otherState = stateDate.getState();
		
		int stateComparation = thisState.compareTo(otherState);
		
		if (stateComparation == 0) {
			Integer thisSiteId = this.getSiteId();
			Integer otherSiteId = stateDate.getSiteId();
			
			int siteComparation = thisSiteId.compareTo(otherSiteId);
			
			if (siteComparation == 0) {
				SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd-HH:mm");
				
				Date thisDate = null;
				Date otherDate = null;
				
				String thisDateString = this.getDate();
				String otherDateString = stateDate.getDate();
				
				try {
					thisDate = formatter.parse(thisDateString);
					otherDate = formatter.parse(otherDateString);				
				} 
				catch (ParseException e) {
					return thisDateString.compareTo(otherDateString);
				}
						
				return thisDate.compareTo(otherDate);
			}
			
			return siteComparation;
		}
		
		return stateComparation;	
	}
	
	private int state;
	private int siteId;
	private String date;

}
