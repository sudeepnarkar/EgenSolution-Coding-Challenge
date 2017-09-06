package com.SpringBoot.EgenLevel2.Rules;

import org.easyrules.annotation.Action;
import org.easyrules.annotation.Condition;
import org.easyrules.annotation.Rule;

import com.SpringBoot.EgenLevel2.Model.Alerts;
import com.SpringBoot.EgenLevel2.Repository.AlertRepository;

@Rule(name="OverWeight Rule", description="To fire alerts when weight "
		+ "goes above 10 percent of Base Weight")
public class OverWeight {
	
	
private int weight;
private int baseWeight;
private long timeStamp;
AlertRepository alertRepository;

	
	public OverWeight(int baseWeight, int weight, long timeStamp,AlertRepository alertRepository ) {
		this.weight = weight;
		this.baseWeight = baseWeight;
		this.timeStamp = timeStamp;
		this.alertRepository = alertRepository;
		
	}

	@Condition
	 public boolean checkifOverWeight() {
		
		boolean res  = false;
		int checkWeight = (int) ((0.10*baseWeight) + baseWeight); 
		//System.out.println("checkWeight :"+checkWeight);
		if(weight>checkWeight)
			res = true;
		
        return res; 
    }
	
	@Action
	public void fireAlert() {
		
		System.out.println(" OverWeight - Base Weight is "+baseWeight +" Weight is "+weight);
		String alertMsg = "OverWeight alert msg fired for weight "+weight;
		Alerts alert = new Alerts(weight,alertMsg,timeStamp);
		alertRepository.save(alert);
			
	}
	
}
