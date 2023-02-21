package com.example.homework18.repository;

import com.example.homework18.model.Cars;
import com.example.homework18.model.Users;
import com.example.homework18.repository.dao.GarageRepository;
import com.example.homework18.repository.mapper.CarMapper;
import com.example.homework18.repository.mapper.UserMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GarageRepositoryImplement implements GarageRepository {

    private final JdbcTemplate jdbcTemplate;

    private static final String addCar =
            """
                    INSERT INTO cars(
                    brand, model, user_id) 
                    VALUES (?, ?, ?)
                    """;
    private static final String addUser =
            """
                    INSERT INTO users(
                    name, surname) 
                    VALUES (?, ?)
                    """;

    public GarageRepositoryImplement(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<Cars> getAllCars() {
        return jdbcTemplate.query("Select * from cars", new CarMapper());
    }

    @Override
    public Cars getCar(Integer carsId) {
            String sql = "SELECT * FROM cars WHERE car_id = ?";
            return jdbcTemplate.queryForObject(sql, new Object[] { carsId }, new CarMapper());
//        return jdbcTemplate.queryForObject("Select * from cars" + carsId, new CarMapper());
    }

    @Override
    public void creatCar(Cars car, Integer id) {
        jdbcTemplate.update(addCar, car.getBrand(), car.getModel(), id);

    }

    @Override
    public void postCar(Integer carId, Integer userId, Cars car) {
        jdbcTemplate.update("UPDATE cars SET "
                + "brand ='" + car.getBrand()
                + "', model ='" + car.getModel()
                + "', user_id ='" + userId
                + "' WHERE car_id ='"
                + carId + "';");
    }

    @Override
    public void deleteCar(Integer carsId) {
        jdbcTemplate.update("DELETE FROM cars WHERE car_id = '" + carsId + "';");
    }

    @Override
    public List<Users> getAllUsers() {
        return jdbcTemplate.query("Select * from users", new UserMapper());
    }

    @Override
    public Users getUser(Integer usersId) {
        return jdbcTemplate.queryForObject("Select * from users" + usersId, new UserMapper());
    }

    @Override
    public void creatUser(Users user) {
        jdbcTemplate.update(addUser, user.getName(), user.getSurname());
    }

    @Override
    public void postUser(Integer id, Users user) {
        jdbcTemplate.update("UPDATE users SET name ='"
                + user.getName() + "', surname ='"
                + user.getSurname() + "' WHERE user_id ='"
                + id + "';");
    }

    @Override
    public void deleteUser(Integer usersId) {
        jdbcTemplate.update("DELETE FROM users WHERE user_id = '" + usersId + "';");
    }


}
