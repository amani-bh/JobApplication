package com.job.application.security.services;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.job.application.entities.User;

public class UserDetailsImpl implements UserDetails {
	private static final long serialVersionUID = 1L;

	private String idUser;

	private String email;

	@JsonIgnore
	private String password;
	
	
	  private String firstName;
	  
	  private String lastName;
	  
	  
	  
	  private String phone;
	  
	  
	  
	  private String country;
	  
	  
	  private String address;
	  
	  


	  
	  private Date creationDate;

	private Collection<? extends GrantedAuthority> authorities;

	public UserDetailsImpl(String idUser, String email, String password,
			 String firstName,
			String lastName, String phone,
			 String country, String address, Date creationDate,
			Collection<? extends GrantedAuthority> authorities) {
		this.idUser = idUser;
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.country = country;
		this.address = address;
		this.creationDate = creationDate;
		this.authorities = authorities;
	}

	public static UserDetailsImpl build(User user) {
		List<GrantedAuthority> authorities = user.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getName().name()))
				.collect(Collectors.toList());

		return new UserDetailsImpl(
				user.getIdUser(), 
				user.getEmail(),
				user.getPassword(),  
				user.getFirstName(), 
				user.getLastName(),  
				user.getPhone(), 
				user.getCountry(),
				user.getAddress(), 
			    user.getCreationDate(), 
				authorities);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	

	
	public String getIdUser() {
		return idUser;
	}



	public String getEmail() {
		return email;
	}

	@Override
	public String getUsername() {
		return firstName+" "+lastName;
	} 
	
	
	@Override
	public String getPassword() {
		return password;
	} 
	
	

	

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	

	public String getPhone() {
		return phone;
	}

	
	public String getCountry() {
		return country;
	}

	public String getAddress() {
		return address;
	}

	


	public Date getCreationDate() {
		return creationDate;
	}

	

	

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}


	
	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UserDetailsImpl user = (UserDetailsImpl) o;
		return Objects.equals(idUser, user.idUser);
	}
}
