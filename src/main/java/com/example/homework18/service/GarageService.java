package com.example.homework18.service;

import com.example.homework18.model.Cars;
import com.example.homework18.model.Users;
import com.example.homework18.repository.dao.GarageRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class GarageService {

    private final GarageRepository garageRepository;

    public GarageService(GarageRepository garageRepository) {
        this.garageRepository = garageRepository;
    }

    public List<Users> getAllUsers() {
        return this.garageRepository.getAllUsers();
    }

    public Users getUser(Integer usersId) {
        return this.garageRepository.getUser(usersId);
    }

    public void creatUser(Users user) {
        this.garageRepository.creatUser(user);
    }

    public void postUser(Integer userId, Users user) {
        this.garageRepository.postUser(userId, user);
    }

    public void postCar(Integer carId, Integer userId, Cars car) {
        this.garageRepository.postCar(carId, userId, car);
    }

    public void deleteUser(Integer usersId) {
        this.garageRepository.deleteUser(usersId);
    }


    public List<Cars> getAllCars() {
        return this.garageRepository.getAllCars();
    }

    public Cars getCar(Integer carsId) {
        return this.garageRepository.getCar(carsId);

    }

    public void creatCar(Cars car, Integer id) {
        this.garageRepository.creatCar(car, id);
    }

    public void deleteCar(Integer carsId) {
        this.garageRepository.deleteCar(carsId);

    }


}
