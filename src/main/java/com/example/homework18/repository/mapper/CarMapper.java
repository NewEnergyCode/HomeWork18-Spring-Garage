package com.example.homework18.repository.mapper;

import com.example.homework18.model.Cars;
import com.example.homework18.model.Users;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CarMapper implements RowMapper<Cars> {


    @Override
    public Cars mapRow(ResultSet rows, int rowNum) throws SQLException {
        return Cars.builder()
                .carId(rows.getInt("car_id"))
                .brand(rows.getString("brand"))
                .model(rows.getString("model"))
                .userId(rows.getInt("user_id"))
                .build();
    }

}
