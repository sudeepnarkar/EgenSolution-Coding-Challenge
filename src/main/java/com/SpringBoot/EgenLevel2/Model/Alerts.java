package com.SpringBoot.EgenLevel2.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document (collection="Alerts")
public class Alerts {
	
	@Id
	String id;
	
	int weight;
	String alertMsg;
	long timeStamp;
	
	public Alerts(){
		
	}
	
	
	public Alerts(int weight, String alertMsg, long timeStamp) {
		this.weight = weight;
		this.alertMsg = alertMsg;
		this.timeStamp = timeStamp;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public String getAlertMsg() {
		return alertMsg;
	}
	public void setAlertMsg(String alertMsg) {
		this.alertMsg = alertMsg;
	}
	public long getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	
	
	
	
		
}
