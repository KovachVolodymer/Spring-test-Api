package com.example.restapimongojwt.repository;

import com.example.restapimongojwt.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface UserRepository extends MongoRepository<User, String> {

    Boolean existsByEmail(String email);

    User findByEmail(String email);

}
