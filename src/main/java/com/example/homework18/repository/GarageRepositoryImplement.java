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
                    INSERT INTO public."Cars"(
                    brand, model, userid) 
                    VALUES (?, ?, ?)
                    """;
    private static final String addUser =
            """
                    INSERT INTO public."Users"(
                    name, surname) 
                    VALUES (?, ?)
                    """;

    public GarageRepositoryImplement(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<Cars> getAllCars() {
        return jdbcTemplate.query("""
                Select * from public."Cars"
                """, new CarMapper());
    }

    @Override
    public Cars getCar(Integer carsId) {
        String sql = """
                SELECT * FROM public."Cars" WHERE carid =?
                """;
        return jdbcTemplate.queryForObject(sql, new Object[]{carsId}, new CarMapper());

    }

    @Override
    public Cars creatCar(Cars car, Integer id) {
        jdbcTemplate.update(addCar, car.getBrand(), car.getModel(), id);
        return car;
    }

    @Override
    public void postCar(Integer carId, Integer userId, Cars car) {
        jdbcTemplate.update("""
                UPDATE public."Cars" SET """
                + " brand ='" + car.getBrand()
                + "', model ='" + car.getModel()
                + "', userid =" + userId
                + " WHERE carid ="
                + carId + ";");
    }

    @Override
    public void deleteCar(Integer carsId) {
        jdbcTemplate.update("""
                DELETE FROM public."Cars" WHERE carid = ?
                """ + carsId + ";");
    }

    @Override
    public List<Users> getAllUsers() {
        return jdbcTemplate.query("""
                Select * from public."Users"
                """, new UserMapper());
    }

    @Override
    public Users getUser(Integer usersId) {
        return jdbcTemplate.queryForObject("""
                Select * from public."Users" WHERE userid =?
                """, new Object[]{usersId}, new UserMapper());
    }

    @Override
    public Users creatUser(Users user) {
        jdbcTemplate.update(addUser, user.getName(), user.getSurname());
        return user;
    }

    @Override
    public void postUser(Integer id, Users user) {
        jdbcTemplate.update("""
                UPDATE public."Users" SET name ='"""
                + user.getName() + "', surname ='"
                + user.getSurname() + "' WHERE userid ='"
                + id + "';");
    }

    @Override
    public void deleteUser(Integer usersId) {
        jdbcTemplate.update("""
                DELETE FROM public."Users" WHERE userid = '"""
                + usersId + "';");
    }


}
