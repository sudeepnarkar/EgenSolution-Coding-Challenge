package com.SpringBoot.EgenLevel2.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.easyrules.api.RulesEngine;
import org.easyrules.core.RulesEngineBuilder;
import org.mongodb.morphia.Datastore;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.SpringBoot.EgenLevel2.Model.Metrics;
import com.SpringBoot.EgenLevel2.Repository.AlertRepository;
import com.SpringBoot.EgenLevel2.Repository.MetricRepository;
import com.SpringBoot.EgenLevel2.Rules.OverWeight;
import com.SpringBoot.EgenLevel2.Rules.UnderWeight;
import com.google.common.net.MediaType;


@RestController
@RequestMapping("/metrics")
public class MetricController {
		
	private int baseWeight = 0;
	private int reqCount = 0;
	MetricRepository metricRepository;
	AlertRepository alertRepository;
	
	public  MetricController(MetricRepository metricRepository, AlertRepository alertRepository) {
		this.metricRepository = metricRepository;
		this.alertRepository  =alertRepository;
	}
	
	
	@RequestMapping(value ="/read",
			method = RequestMethod.GET)
	public List<Metrics> readAll(){
		List<Metrics> metrics = metricRepository.findAll();
		return metrics;
	}
	
	@RequestMapping(value ="/create",
			method = RequestMethod.POST)
	public void create(@RequestBody Metrics metric) {
		
		if(reqCount==0) {
			baseWeight = metric.getValue();
			System.out.println("baseWeight: "+baseWeight);
			
		}
		
		reqCount++;
		Metrics metric1  = metricRepository.save(metric);
		
		//check weight
		int weight = metric.getValue();
		long timeStamp = metric.getTimeStamp();
		
		checkWeight(baseWeight,weight,timeStamp);	
		
	}
	

	@RequestMapping(value ="/readByTimeRange",
			method = RequestMethod.GET)
	public List<Metrics> readByTimeRange (@RequestParam long start, @RequestParam long end){
		
		List<Metrics> allMetrics = readAll();
		List<Metrics> resultMetricsList = new ArrayList<Metrics>();
		
		for(Metrics metric:allMetrics) {
			if(metric.getTimeStamp()>=start &&  metric.getTimeStamp()<=end) {
				resultMetricsList.add(metric);
			}
		}
		return resultMetricsList;		
	}
	
	@RequestMapping(value ="/checkWeight",
			method = RequestMethod.GET)
	public void checkWeight(int baseWeight, int weight, long timeStamp) {
	
		UnderWeight underWeight = new UnderWeight(baseWeight,weight,timeStamp,alertRepository);
		OverWeight overWeight = new OverWeight(baseWeight,weight,timeStamp,alertRepository);
		
        /**
         * Create a rules engine and register the business rule
         */
        
        RulesEngine rulesEngine = RulesEngineBuilder.aNewRulesEngine().build();
        
        rulesEngine.registerRule(underWeight);
        rulesEngine.registerRule(overWeight);
        

        /**
         * Fire rules
         */
        rulesEngine.fireRules();
		
	}
	
}

