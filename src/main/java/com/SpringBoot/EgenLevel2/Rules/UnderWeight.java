package com.SpringBoot.EgenLevel2.Rules;

import org.easyrules.annotation.Action;
import org.easyrules.annotation.Condition;
import org.easyrules.annotation.Rule;

import com.SpringBoot.EgenLevel2.Model.Alerts;
import com.SpringBoot.EgenLevel2.Repository.AlertRepository;

@Rule(name="UnderWeight Rule", description="To fire alerts when weight "
		+ "falls below 10 percent of Base Weight")
public class UnderWeight {
	
	private int weight;
	private int baseWeight;
	private long timeStamp;
	AlertRepository alertRepository;
	
	public UnderWeight(int baseWeight, int weight,long timeStamp,AlertRepository alertRepository) {
		this.baseWeight = baseWeight;
		this.weight = weight;
		this.timeStamp = timeStamp;
		this.alertRepository = alertRepository;
	}

	@Condition
	 public boolean checkifUnderWeight() {
		
		boolean res  = false;
		int checkWeight = (int)0.10*baseWeight;
		
		if(weight<checkWeight)
			res = true;
		
        return res; 
    }
	
	@Action
	public void fireAlert() {
		System.out.println(" UnderWeight - Base Weight is "+baseWeight + "Weight is "+weight);
		String alertMsg = "UnderWeight alert message fired for weight "+weight;
		Alerts alert = new Alerts(weight,alertMsg,timeStamp);
		alertRepository.save(alert);

	}
	

}
