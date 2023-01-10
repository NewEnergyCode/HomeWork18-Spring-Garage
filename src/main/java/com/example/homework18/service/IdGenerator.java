package com.example.homework18.service;

import com.example.homework18.model.Cars;
import com.example.homework18.model.Users;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component

public class IdGenerator {

    public int getRandomCarId(List<Cars> list) {
        Random random = new Random();
        List<Integer> carsId = new ArrayList<>();
        for (Cars car : list) {
            carsId.add(car.getCarId());
        }
        int x = 1;
        while (carsId.contains(x)) {
            x = random.nextInt(99 - x);
        }
        return x;
    }

    public int getRandomUserId(List<Users> list) {
        Random random = new Random();
        List<Integer> userId = new ArrayList<>();
        for (Users user : list) {
            userId.add(user.getUserId());
        }
        int x = 1;
        while (userId.contains(x)) {
            x = random.nextInt(99 - x);
        }
        return x;
    }

}
