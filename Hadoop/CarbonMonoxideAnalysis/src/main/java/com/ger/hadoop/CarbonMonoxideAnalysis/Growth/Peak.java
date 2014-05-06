package com.ger.hadoop.CarbonMonoxideAnalysis.Growth;

public class Peak {
	
	public String getOriginDate() {
		return originDate;
	}
	
	public void setOriginDate(String originDate) {
		this.originDate = originDate;
	}
	
	public String getEndDate() {
		return endDate;
	}
	
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	public float getOriginValue() {
		return originValue;
	}
	
	public void setOriginValue(float originValue) {
		this.originValue = originValue;
	}
	
	public float getEndValue() {
		return endValue;
	}
	
	public void setEndValue(float endValue) {
		this.endValue = endValue;
	}
	
	public float getTotal() {
		return endValue - originValue;
	}
	
	public String getOriginTime() {
		return originTime;
	}

	public void setOriginTime(String originTime) {
		this.originTime = originTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	private String originDate;
	private String originTime;
	private float originValue;
	private String endDate;
	private String endTime;
	private float endValue;

}
