package com.SpringBoot.EgenLevel2.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="Metrics")
public class Metrics {
	@Id
	String id;
	
	
	private int value;
	private long timeStamp;
	
	public Metrics() {
		
	}
	
	public Metrics(int value, long timeStamp) {
		this.value = value;
		this.timeStamp = timeStamp;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public long getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	
	

}
