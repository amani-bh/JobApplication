package com.job.application.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.job.application.entities.ERole;
import com.job.application.entities.Role;
import com.job.application.entities.User;
import com.job.application.iservices.IServiceUser;
import com.job.application.payload.request.LoginRequest;
import com.job.application.payload.request.SignupRequest;
import com.job.application.payload.response.JwtResponse;
import com.job.application.repositories.RoleRepository;
import com.job.application.repositories.UserRepository;
import com.job.application.security.jwt.JwtUtils;
import com.job.application.security.services.UserDetailsImpl;

@Service
public class UserServiceImpl implements IServiceUser{

	@Autowired
    private  UserRepository userRepository;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private JwtUtils jwtUtils;

	@Override
	public JwtResponse authenticateUser(LoginRequest loginRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());
       
		return new JwtResponse(jwt, 
												 userDetails.getIdUser(), 
												 userDetails.getUsername(), 
												 userDetails.getEmail(), 
												 userDetails.isEnabled(),
												 roles);
	}

	@Override
	public User registerUser(SignupRequest signUpRequest) throws IOException {
		

		 if (Boolean.TRUE.equals(userRepository.existsByEmail(signUpRequest.getEmail()))) {
			throw new IOException("User with email " + signUpRequest.getEmail() + " already exist");
		}

		List<String> list=new ArrayList<>();
		// Create new user's account
		User user = new User();
		user.setAddress(signUpRequest.getAddress());
		user.setFirstName(signUpRequest.getFirstName());
		user.setLastName(signUpRequest.getLastName());
		user.setCountry(signUpRequest.getCountry());
		user.setPhone(signUpRequest.getPhone());
		user.setCreationDate(new Date());
		user.setEmail(signUpRequest.getEmail());
		user.setPassword(encoder.encode(signUpRequest.getPassword()));
		user.set_deleted(false);

		Set<String> strRoles = signUpRequest.getRoles();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role userRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);

					break;
				case "comp":
					Role modRole = roleRepository.findByName(ERole.ROLE_COMPANY)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(modRole);

					break;
				default:
					Role userRole = roleRepository.findByName(ERole.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
				}
			});
		}

		user.setRoles(roles);
		userRepository.save(user);

		return user;
	}
}
