package com.SpringBoot.EgenLevel2;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Component;

import com.SpringBoot.EgenLevel2.Model.Metrics;
import com.SpringBoot.EgenLevel2.Repository.AlertRepository;
import com.SpringBoot.EgenLevel2.Repository.MetricRepository;


@Component
@EnableMongoRepositories(basePackageClasses=MetricRepository.class)
public class DbSeeder implements CommandLineRunner{
	
	private MetricRepository metricRepository;
	private AlertRepository alertRepository;
	
	
	

	public DbSeeder(MetricRepository metricRepository, AlertRepository alertRepository) {
		
		this.metricRepository = metricRepository;
		this.alertRepository = alertRepository;
	}




	@Override
	public void run(String... arg0) throws Exception {
		// TODO Auto-generated method stub
		
		
		//Metrics metric1 = new Metrics(153, 1111111);
	    //Metrics metric2 = new Metrics(155, 2222222);
		
		//delete all data from the database before starting to accumulate data.
	   	    
	    metricRepository.deleteAll();
	    alertRepository.deleteAll();
	    
		
	}

}
