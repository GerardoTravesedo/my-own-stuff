package com.ger.hadoop.CarbonMonoxideAnalysis.Common;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.WritableComparable;

public class StateSiteWritable implements WritableComparable<StateSiteWritable> {
	
	public int getState() {
		return state;
	}
	
	public void setState(int state) {
		this.state = state;
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
	}

	public void write(DataOutput out) throws IOException {		
		out.writeInt(state);
		out.writeInt(siteId);
	}
	
	public int compareTo(StateSiteWritable stateSite) {
		
		Integer thisState = this.getState();
		Integer otherState = stateSite.getState();
		
		int stateComparation = thisState.compareTo(otherState);
		
		if (stateComparation == 0) {
			Integer thisSiteId = this.getSiteId();
			Integer otherSiteId = stateSite.getSiteId();
			
			return thisSiteId.compareTo(otherSiteId);
		}
		
		return stateComparation;
	}
	
	private int state;
	private int siteId;

}
