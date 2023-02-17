package com.example.homework18.repository.mapper;

import com.example.homework18.model.Users;
import org.springframework.jdbc.core.RowMapper;

import javax.swing.tree.TreePath;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<Users> {

    @Override
    public Users mapRow(ResultSet rows, int rowNum) throws SQLException {
        return Users.builder()
                .userId(rows.getInt("user_id"))
                .name(rows.getString("name"))
                .surname("surname")
                .build();
    }
}
