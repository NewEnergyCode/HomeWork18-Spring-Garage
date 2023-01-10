package com.example.homework18.controller;

import com.example.homework18.service.GarageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@RequestMapping("/view")
@Controller
public class ViewController {
    private final GarageService garageService;

    public ViewController(GarageService garageService) {
        this.garageService = garageService;
    }

    @RequestMapping(value = "/userPage",method = RequestMethod.GET)
    public String usersPage (Model model){
        model.addAttribute("users", garageService.getAllUsers());
        model.addAttribute("cars", garageService.getAllCars());
        return "usersPage";

    }
}
