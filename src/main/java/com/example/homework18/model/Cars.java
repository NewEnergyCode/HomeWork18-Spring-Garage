package com.example.homework18.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Cars {
    private Integer carId;
    private String brand;
    private String model;

}
