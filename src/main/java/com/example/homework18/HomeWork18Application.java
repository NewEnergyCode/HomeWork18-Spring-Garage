package com.example.homework18;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class HomeWork18Application {

    public static void main(String[] args) {
        SpringApplication.run(HomeWork18Application.class, args);
    }

}
