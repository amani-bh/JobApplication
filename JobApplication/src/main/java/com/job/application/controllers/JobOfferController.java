package com.job.application.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.job.application.entities.JobOffer;
import com.job.application.iservices.IServiceJobOffer;

@CrossOrigin(origins = "*")
@RequestMapping( "/JobOffers" )
@RestController
public class JobOfferController {
	@Autowired
	private IServiceJobOffer iJob;
	
	@GetMapping(path = "")
	public List<JobOffer> getAllJobs() {
		return iJob.getAllJobOffers(); 
	}
	
	@PostMapping(path = "/{idUser}")
    public ResponseEntity<JobOffer> addJob(@RequestBody  @Valid  JobOffer job,@PathVariable(name = "idUser") String idUser) {
		
		JobOffer jobResponse = iJob.addJob(job,idUser);
		
        return new ResponseEntity<JobOffer>(jobResponse, HttpStatus.CREATED);
    }
	
	@GetMapping(path = "/{idJob}")
	public JobOffer getById(@PathVariable(name = "idJob") String idJob) {
		return iJob.getJobById(idJob); 
	}
	
	@GetMapping(path = "user/{idUser}")
	public List<JobOffer> getByIdUser(@PathVariable(name = "idUser") String idUser) {
		return iJob.getJobOffersByUser(idUser); 
	}
}
