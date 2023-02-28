package com.example.homework18.controller;

import com.example.homework18.model.Cars;
import com.example.homework18.service.GarageService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cars")
@Component
public class CarController {

    private final GarageService garageService;
    private final Map<Integer, Cars> carsMap = new HashMap<>();

    public CarController(GarageService garageService) {
        this.garageService = garageService;
    }

    @GetMapping()
    @Cacheable(value = "cars")
    public List<Cars> getAllCars() {
        System.out.println("cars");
        return garageService.getAllCars();
    }

    @GetMapping("/{carsId}")
    @Cacheable(value = "cars", key = "#carsId")
    public Cars getCar(@PathVariable Integer carsId) {
//        return carsMap.computeIfAbsent(carsId, key -> garageService.getCar(key));
        return garageService.getCar(carsId);
    }

    @PutMapping("/{id}")
    public Cars creatCar(@RequestBody Cars car, @PathVariable Integer id) {
        return this.garageService.creatCar(car, id);
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