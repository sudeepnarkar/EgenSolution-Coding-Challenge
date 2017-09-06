package com.SpringBoot.EgenLevel2.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.SpringBoot.EgenLevel2.Model.Metrics;


@Repository
public interface MetricRepository extends MongoRepository<Metrics,String> {

}
