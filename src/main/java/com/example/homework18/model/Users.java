package com.example.homework18.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@Builder
public class Users {
    private Integer userId;
    private String name;
    private List<Cars> garage;

}
