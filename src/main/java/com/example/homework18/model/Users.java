package com.example.homework18.model;

import lombok.Builder;
import lombok.Data;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Data
@Builder
public class Users implements Serializable {
    private Integer userId;
    private String name;

    private String surname;

}
