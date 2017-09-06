package com.SpringBoot.EgenLevel2.Repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.SpringBoot.EgenLevel2.Model.Alerts;

@Repository
public interface AlertRepository extends MongoRepository<Alerts,String> {

}