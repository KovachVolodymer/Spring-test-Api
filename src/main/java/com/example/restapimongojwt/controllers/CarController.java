package com.example.restapimongojwt.controllers;


import com.example.restapimongojwt.JwtUtil.response.MessageResponse;
import com.example.restapimongojwt.models.Car;
import com.example.restapimongojwt.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/cars")
public class CarController {

    @Autowired
    CarRepository carRepository;

    @GetMapping
    private ResponseEntity<Object> getAllCars() {
        List<Car> cars = carRepository.findAll();
        return ResponseEntity.ok().body(cars);
    }

    @PostMapping
    private ResponseEntity<Object> addCar(@RequestBody Car car) {
        carRepository.save(car);
        return ResponseEntity.ok().body(new MessageResponse("Car is added"));
    }

    @PatchMapping
    private ResponseEntity<Object> updateCar(Car car) {
        carRepository.save(car);
        return ResponseEntity.ok().body(new MessageResponse("Car is updated"));
    }

}
