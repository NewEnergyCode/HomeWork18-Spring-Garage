package com.example.homework18.repository.dao;

import com.example.homework18.model.Cars;
import com.example.homework18.model.Users;
import org.apache.catalina.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface GarageRepository {

    List<Cars> getAllCars();

    Cars getCar(Integer carsId);

    void creatCar(Cars car, Integer id);

    void postCar(Integer carId, Integer userId, Cars car);

    void deleteCar(Integer carsId);


    List<Users> getAllUsers();

    Users getUser(Integer usersId);

    void creatUser(Users user);

    void postUser(Integer id, Users user);


    void deleteUser(Integer usersId);


}