package com.job.application.iservices;

import java.io.IOException;

import com.job.application.entities.User;
import com.job.application.payload.request.LoginRequest;
import com.job.application.payload.request.SignupRequest;
import com.job.application.payload.response.JwtResponse;



public interface IServiceUser {

	public JwtResponse authenticateUser(LoginRequest loginRequest) ;
	public User registerUser(SignupRequest signUpRequest) throws IOException ;
}
