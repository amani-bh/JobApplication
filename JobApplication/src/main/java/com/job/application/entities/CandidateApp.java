package com.job.application.entities;

import java.io.InputStream;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "CandidateApp")
public class CandidateApp {

	@Id
    private String idCandidateApp;

    private Date applicationDate;
    
    private String firstName;
	  
	  private String lastName;
    private User candidate;
    
    private String email;
    
    
    private JobOffer job;
    
   
    private String  cv;
}
