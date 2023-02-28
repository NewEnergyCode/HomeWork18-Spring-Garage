package com.example.homework18.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cars implements Serializable {
    private Integer carId;
    private String brand;
    private String model;
    private Integer userId;

}
