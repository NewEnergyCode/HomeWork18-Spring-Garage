package com.example.homework18.controller;

import com.example.homework18.model.Users;
import com.example.homework18.service.GarageService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public List<Users> getAllUsers() {
        return garageService.getAllUsers();
    }

    @GetMapping("/{usersId}")
    public Users getUser(@PathVariable Integer usersId) {
        return garageService.getUser(usersId);

    }

    @PutMapping()
    public void creatUser(@RequestBody Users user) {
        garageService.creatUser(user);
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