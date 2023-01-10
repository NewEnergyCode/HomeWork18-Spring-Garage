package com.example.homework18.service;

import com.example.homework18.model.Cars;
import com.example.homework18.model.Users;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class GarageService {
    private final List<Users> users = new ArrayList<>() {
        {
            add(Users.builder().userId(1).name("Max").garage(new ArrayList<>()).build());
            add(Users.builder().userId(2).name("Vadim").garage(new ArrayList<>()).build());

        }
    };
    private final List<Cars> cars = new ArrayList<>() {
        {
            add(Cars.builder().carId(1).brand("Renault").model("Megane").build());
            add(Cars.builder().carId(2).brand("Mitsubishi").model("Colt").build());
            add(Cars.builder().carId(3).brand("BMW").model("i318").build());

        }
    };
    private final IdGenerator idGenerator;

    public GarageService(IdGenerator idGenerator) {
        this.idGenerator = idGenerator;
    }

    public List<Users> getAllUsers() {
        return users;
    }

    public Users getUser(Integer usersId) {
        for (Users user : users) {
            if (user.getUserId().equals(usersId)) return user;
        }
        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "User id is not found");
    }

    public String creatUser(String user) {
        users.add(Users.builder().
                userId(idGenerator.getRandomUserId(users)).
                name(user).
                garage(new ArrayList<>()).
                build());
        return user;
    }

    public Cars addCarForUser(Integer id, Cars car) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUserId().equals(id)) {
                users.get(i).getGarage().add(car);
                return car;
            }
        }
        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND);
    }

    public Users addCarForUserById(Integer userId, Integer carId) {
        List<Integer> carsId = new ArrayList<>();
        int ui = 0;
        int ci = 0;
        for (int u = 0; u < users.size(); u++) {
            if (users.get(u).getUserId().equals(userId)) {
                ui = u;
            }
        }
        for (int c = 0; c < cars.size(); c++) {
            if (cars.get(c).getCarId().equals(carId)) {
                ci = c;
            }
        }
        for (Cars car : cars) {
            carsId.add(car.getCarId());
        }
        if (carsId.contains(carId)) {
            users.get(ui).getGarage().
                    add(Cars.builder().
                            carId(cars.get(ci).getCarId()).
                            brand(cars.get(ci).getBrand()).
                            model(cars.get(ci).getModel()).
                            build());
            return users.get(ui);
        }
        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND);
    }

    public Users postUser(Integer userId, String name) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUserId().equals(userId)) {
                return users.set(i, Users.builder().
                        userId(users.get(i).getUserId()).
                        name(name).
                        garage(users.get(i).getGarage()).
                        build());
            }
        }
        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND);
    }

    public Cars postCar(Integer carId, Cars car) {
        for (int i = 0; i < cars.size(); i++) {
            if (cars.get(i).getCarId().equals(carId)) {
                return cars.set(i, Cars.builder().
                        carId(cars.get(i).getCarId()).
                        brand(car.getBrand()).
                        model(car.getModel()).
                        build());
            }
        }
        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND);
    }
    public Users deleteUser(Integer usersId) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUserId().equals(usersId)) {
                return users.remove(i);
            }
        }
        throw new ResponseStatusException(
                HttpStatus.I_AM_A_TEAPOT);
    }

    public Cars deleteUsersCar(Integer usersId, Integer carId) {
        int ci = 0;
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUserId().equals(usersId)) {
                for (int c = 0; c < users.get(i).getGarage().size(); c++) {
                    if (users.get(i).getGarage().get(c).getCarId().equals(carId)) {
                        ci = c;
                    }
                }
                return users.get(i).getGarage().remove(ci);
            }
        }
        throw new ResponseStatusException(
                HttpStatus.I_AM_A_TEAPOT);
    }

    public List<Cars> getAllCars() {
        return cars;
    }

    public Cars getCar(Integer carsId) {
        for (Cars car : cars) {
            if (car.getCarId().equals(carsId)) return car;
        }
        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Car id is not found");
    }

    public Cars creatCar(Cars car) {
        cars.add(Cars.builder().
                carId(idGenerator.getRandomCarId(cars)).
                brand(car.getBrand()).
                model(car.getModel()).
                build());
        return car;
    }

    public Cars deleteCar(Integer carsId) {
        for (int i = 0; i < cars.size(); i++) {
            if (cars.get(i).getCarId().equals(carsId)) {
                return cars.remove(i);
            }
        }
        throw new ResponseStatusException(
                HttpStatus.I_AM_A_TEAPOT);

    }


}
