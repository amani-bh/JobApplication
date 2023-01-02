package com.job.application.entities;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "JobOffer")
public class JobOffer {

	    @Id
	    private String idJob;
    
	    private String title;
	    
	    private String description;
	    	    
	    private Date creationDate;
	    
	    private String jobType;
	    
	    private String employer;
	    
	    @NotBlank
	    @Size(max = 50)
	    @Email
	    private String email;
	    
	    private String ville;
	    
	    private User user;
	    
	    private float vuNumber;
	   
	    
}
