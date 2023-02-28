package com.example.homework18.controller;

import com.example.homework18.model.Users;
import com.example.homework18.service.GarageService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    @Cacheable(value = "users")
    public List<Users> getAllUsers() {
        System.out.println("user`s cache");
        return garageService.getAllUsers();
    }

    @GetMapping("/{usersId}")
    @Cacheable(value = "getCars", key = "#usersId")
    public Users getUser(@PathVariable Integer usersId) {
        System.out.println("user id " + usersId);
        return garageService.getUser(usersId);
    }

    @PutMapping()
    public Users creatUser(@RequestBody Users user) {
        return garageService.creatUser(user);
    }

    @PostMapping("/{id}")
    public void postUser(@PathVariable Integer id,
                         @RequestBody Users user) {
        garageService.postUser(id, user);
    }

    @DeleteMapping("/{usersId}")
    public void deleteUser(@PathVariable Integer usersId) {
        garageService.deleteUser(usersId);
    }

}