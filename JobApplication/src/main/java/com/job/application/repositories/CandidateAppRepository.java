package com.job.application.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.job.application.entities.CandidateApp;

@Repository
public interface CandidateAppRepository extends MongoRepository<CandidateApp,String>{

}
