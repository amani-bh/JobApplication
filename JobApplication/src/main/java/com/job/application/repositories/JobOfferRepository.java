package com.job.application.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.job.application.entities.JobOffer;

@Repository
public interface JobOfferRepository extends MongoRepository<JobOffer,String>{
	public List<JobOffer> findByUserIdUser(String idUser);
}
