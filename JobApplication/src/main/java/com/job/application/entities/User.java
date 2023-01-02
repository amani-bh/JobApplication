package com.job.application.entities;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "User")
public class User {

	@Id
	  private String idUser;

	  

	  
	  private String email;

	  private String password;
	  
	  private boolean is_deleted;
	  
	  private String firstName;
	  
	  private String lastName;
	  
	  private String phone;
	  
	  private String country;	  
	  
	  private String address;
	  
	  private Date creationDate;

	  private Set<Role> roles = new HashSet<>();

}
