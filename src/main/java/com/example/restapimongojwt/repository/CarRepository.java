package com.example.restapimongojwt.repository;

import com.example.restapimongojwt.models.Car;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface CarRepository extends MongoRepository<Car, String> {
}
