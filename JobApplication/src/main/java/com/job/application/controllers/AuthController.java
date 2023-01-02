package com.job.application.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.job.application.iservices.IServiceUser;
import com.job.application.payload.request.LoginRequest;
import com.job.application.payload.request.SignupRequest;
import com.job.application.security.services.UserDetailsImpl;

@CrossOrigin(origins = "*")
@RequestMapping( "/api/auth/" )
@RestController
public class AuthController {

	@Autowired
	private IServiceUser authService;
	
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		try {
			return ResponseEntity.ok(authService.authenticateUser(loginRequest));
		}
		catch(Exception e)
		{  
		   return new ResponseEntity<>( "Error accurred! "+ e.getMessage() , HttpStatus.BAD_REQUEST);
		}
		   
	}
	
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest)  {
		

		
		try {
			return ResponseEntity.ok(authService.registerUser(signUpRequest));
		}
		catch(Exception e)
		{  
		   return new ResponseEntity<>( "Error accurred! "+ e.getMessage() , HttpStatus.BAD_REQUEST);
		}
		   

	}
	
	 @GetMapping("/profile")
	    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_COMPANY') or hasRole('ROLE_ADMIN')")
	    public UserDetailsImpl profile() {
	        SecurityContext context = SecurityContextHolder.getContext();
	        Authentication authentication = context.getAuthentication();
	        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
	        return userDetails;
	    }
}
