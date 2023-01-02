package com.job.application.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.job.application.entities.JobOffer;
import com.job.application.iservices.IServiceJobOffer;
import com.job.application.repositories.JobOfferRepository;
import com.job.application.repositories.UserRepository;

@Service
public class JobOfferServiceImpl implements IServiceJobOffer{
	
	@Autowired
    private  JobOfferRepository jobRepository;
	
	@Autowired
    private  UserRepository userRepository;
	
	@Override
	public JobOffer addJob(JobOffer requestJob, String idUser) {
		requestJob.setUser(userRepository.findById(idUser).get());
		requestJob.setCreationDate(new Date());
		return jobRepository.save(requestJob);
	}

	@Override
	public List<JobOffer> getAllJobOffers() {
		return jobRepository.findAll(Sort.by(Sort.Direction.DESC, "creationDate"));
	}

	@Override
	public List<JobOffer> getJobOffersByUser(String idUser) {
		return jobRepository.findByUserIdUser(idUser);
	}

	@Override
	public JobOffer getJobById(String idjob) {
		JobOffer job=jobRepository.findById(idjob).get();
		job.setVuNumber(job.getVuNumber()+0.5F);
		jobRepository.save(job);
		return job;
	}

}
