package com.SpringBoot.EgenLevel2.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.SpringBoot.EgenLevel2.Model.Alerts;
import com.SpringBoot.EgenLevel2.Repository.AlertRepository;
import com.SpringBoot.EgenLevel2.Repository.MetricRepository;


@RestController
@RequestMapping("/alerts")
public class AlertController {
	
	MetricRepository metricRepository;
	AlertRepository alertRepository;
	
	public  AlertController(MetricRepository metricRepository, AlertRepository alertRepository) {
		this.metricRepository = metricRepository;
		this.alertRepository  =alertRepository;
	}
	
	
	@RequestMapping(value ="/read",
			method = RequestMethod.POST)
	public List<Alerts> readAll(){
		List<Alerts> alerts = alertRepository.findAll();
		return alerts;
	}
	
	@RequestMapping(value ="/readByTimeRange",
			method = RequestMethod.POST)
	public List<Alerts> readByTimeRange (@RequestParam long start, @RequestParam long end){
		
		List<Alerts> allAlerts = readAll();
		List<Alerts> resultAlertsList = new ArrayList<Alerts>();
		
		for(Alerts alert:allAlerts) {
			if(alert.getTimeStamp()>=start &&  alert.getTimeStamp()<=end) {
				resultAlertsList.add(alert);
			}
		}
		return resultAlertsList;		
	}
	
}
