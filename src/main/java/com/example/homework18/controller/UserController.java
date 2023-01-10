package com.example.homework18.controller;

import com.example.homework18.model.Cars;
import com.example.homework18.model.Users;
import com.example.homework18.service.GarageService;
import com.example.homework18.service.IdGenerator;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
@Controller
public class UserController {

    private final GarageService garageService;

    public UserController(GarageService garageService) {
        this.garageService = garageService;
    }

    @GetMapping()
    public List<Users> getAllUsers() {
        return garageService.getAllUsers();
    }

    @GetMapping("/{usersId}")
    public Users getUser(@PathVariable Integer usersId) {
        return garageService.getUser(usersId);

    }

    @PutMapping()
    public String creatUser(@RequestBody String user) {
        return garageService.creatUser(user);
    }

    @PostMapping("/{id}")
    public Users postUser(@PathVariable Integer id,
                          @RequestBody String name){
        return garageService.postUser(id,name);
    }
    @PutMapping("/{id}")
    public Cars addCarForUser(@PathVariable Integer id,
                              @RequestBody Cars car) {
        return garageService.addCarForUser(id, car);
    }
    @PutMapping("/addById/{id}")
    public Users addCarForUserById(@PathVariable Integer id,
                              @RequestBody Integer carId) {
        return garageService.addCarForUserById(id, carId);
    }
     @DeleteMapping("/{usersId}")
    public Users deleteUser(@PathVariable Integer usersId) {
        return garageService.deleteUser(usersId);
    }
    @DeleteMapping("/delUsersCar/{usersId}")
    public Cars deleteUsersCar(@PathVariable Integer usersId,
                            @RequestBody Integer carsId) {
        return garageService.deleteUsersCar(usersId, carsId);
    }
 }