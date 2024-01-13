package com.example.restapimongojwt.repository;

import com.example.restapimongojwt.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

    Boolean existsByEmail(String email);

    Boolean existsByName(String name);

    User findByEmail(String email);

}
