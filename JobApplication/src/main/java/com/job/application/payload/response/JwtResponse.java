package com.job.application.payload.response;

import java.util.List;

public class JwtResponse {
	private String token;
	private String type = "Bearer";
	private String idUser;
	private String username;
	private boolean active;
	private String email;
	private List<String> roles;

	public JwtResponse(String accessToken, String id, String username, String email,boolean active, List<String> roles) {
		this.token = accessToken;
		this.idUser = id;
		this.username = username;
		this.email = email;
		this.roles = roles;
		this.active=active;
	}

	public String getAccessToken() {
		return token;
	}

	public void setAccessToken(String accessToken) {
		this.token = accessToken;
	}

	public String getTokenType() {
		return type;
	}

	public void setTokenType(String tokenType) {
		this.type = tokenType;
	}

	
	public String getidUser() {
		return idUser;
	}

	public void setidUser(String idUser) {
		this.idUser = idUser;
	}

	public String getUserName() {
		return username;
	}

	public void setUserName(String userName) {
		this.username = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	

	public boolean getActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public List<String> getRoles() {
		return roles;
	}
}
