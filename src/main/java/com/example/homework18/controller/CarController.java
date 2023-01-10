package com.example.homework18.controller;

import com.example.homework18.model.Cars;
import com.example.homework18.model.Users;
import com.example.homework18.service.GarageService;
import com.example.homework18.service.IdGenerator;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/cars")
@Component
public class CarController {

    private final GarageService garageService;

    public CarController(GarageService garageService) {
        this.garageService = garageService;
    }

    @GetMapping()
    public List<Cars> getAllCars() {
        return garageService.getAllCars();
    }

    @GetMapping("/{carsId}")
    public Cars getCar(@PathVariable Integer carsId) {
        return garageService.getCar(carsId);

    }

    @PutMapping()
    public Cars creatCar(@RequestBody Cars car) {
        return garageService.creatCar(car);
    }

    @PostMapping("/{id}")
    public Cars postCar(@PathVariable Integer id,
                          @RequestBody Cars car) {
        return garageService.postCar(id, car);
    }
    @DeleteMapping("/{carsId}")
    public Cars deleteCar(@PathVariable Integer carsId) {
                return garageService.deleteCar(carsId);
    }
}