package com.job.application.iservices;

import java.util.List;


import com.job.application.entities.JobOffer;



public interface IServiceJobOffer {

	public JobOffer addJob(JobOffer requestJob,String idUser);
	public List<JobOffer> getAllJobOffers();
	public List<JobOffer> getJobOffersByUser(String idUser);
	public JobOffer getJobById(String idjob) ; 
}
