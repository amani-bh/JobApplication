package com.job.application.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.job.application.entities.ERole;
import com.job.application.entities.Role;


public interface RoleRepository extends MongoRepository<Role, String> {
  Optional<Role> findByName(ERole name);
}
