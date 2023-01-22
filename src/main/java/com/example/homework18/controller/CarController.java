package com.example.homework18.controller;

import com.example.homework18.model.Cars;
import com.example.homework18.service.GarageService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/{id}")
    public void creatCar(@RequestBody Cars car, @PathVariable Integer id) {
        this.garageService.creatCar(car, id);
    }

    @PostMapping("/{car_id}/{user_id}")
    public void postCar(@PathVariable Integer car_id,
                        @PathVariable Integer user_id,
                        @RequestBody Cars car) {
        garageService.postCar(car_id, user_id, car);
    }

    @DeleteMapping("/{carsId}")
    public void deleteCar(@PathVariable Integer carsId) {
        garageService.deleteCar(carsId);
    }
}