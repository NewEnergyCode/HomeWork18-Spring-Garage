package com.example.homework18;

import com.example.homework18.model.Cars;
import com.example.homework18.model.Users;
import com.example.homework18.repository.dao.GarageRepository;
import com.example.homework18.repository.mapper.UserMapper;
import com.example.homework18.service.GarageService;
import org.apache.catalina.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.plugins.MockMaker;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;

@ExtendWith(MockitoExtension.class)
public class GarageServiceTests {
    GarageRepository garageRepository = Mockito.mock(GarageRepository.class);

    @Test
    public void getAllUsersTest() {
        GarageService garageService = new GarageService(garageRepository);
        List<Users> usersListMock = new ArrayList<>();
        usersListMock.add(Users.builder()
                .userId(1)
                .name("name")
                .surname("surname").
                build());
        Mockito.when(garageRepository.getAllUsers()).thenReturn(usersListMock);
        Assertions.assertEquals(usersListMock, garageService.getAllUsers());
    }

    @Test
    public void getUserTest() {
        GarageService garageService = new GarageService(garageRepository);
        Users usersListMock = Users.builder()
                .userId(1)
                .name("name")
                .surname("surname").
                build();
        Mockito.when(garageRepository.getUser(anyInt())).thenReturn(usersListMock);
        Assertions.assertEquals(usersListMock, garageService.getUser(anyInt()));
    }

    @Test
    public void creatUserTest() {
        GarageService garageService = new GarageService(garageRepository);
        Users usersListMock = Users.builder()
                .userId(1)
                .name("name")
                .surname("surname").
                build();

        garageService.creatUser(usersListMock);
        Mockito.verify(garageRepository).creatUser(usersListMock);

    }

    @Test
    public void postUserTest() {

        GarageService garageService = new GarageService(garageRepository);
        Users userMock = Mockito.mock(Users.class);
        garageService.postUser(1, userMock);
        Mockito.verify(garageRepository).postUser(1, userMock);

    }

    @Test
    void deletedUserTest() {
        GarageService garageService = new GarageService(garageRepository);
        Users userMock = Mockito.mock(Users.class);
        garageService.deleteUser(anyInt());
        Mockito.verify(garageRepository).deleteUser(anyInt());
    }

    @Test
    public void getAllCarsTest() {
        GarageService garageService = new GarageService(garageRepository);
        Cars car = Mockito.mock(Cars.class);
        List<Cars> carsList = new ArrayList<>();
        carsList.add(car);
        Mockito.when(garageRepository.getAllCars()).thenReturn(carsList);
        Assertions.assertEquals(carsList, garageService.getAllCars());
    }

    @Test
    public void getCarTest(){

        GarageService garageService = new GarageService(garageRepository);
        Cars car = Mockito.mock(Cars.class);
        Mockito.when(garageRepository.getCar(anyInt())).thenReturn(car);
        Assertions.assertEquals(car,garageService.getCar(1));
    }

    @Test
    public void postCarTest(){
        GarageService garageService = new GarageService(garageRepository);
        Cars car = Mockito.mock(Cars.class);
        garageService.postCar(1,1,car);
        Mockito.verify(garageRepository).postCar(1,1,car);



    }

    @Test
    public void creatCarTest(){
        GarageService garageService = new GarageService(garageRepository);
        Cars car = Mockito.mock(Cars.class);
        garageService.creatCar(car,1);
        Mockito.verify(garageRepository).creatCar(car,1);

    }

    @Test
    public void deleteCarTest(){
        GarageService garageService = new GarageService(garageRepository);
        Cars car = Mockito.mock(Cars.class);
        garageService.deleteCar(1);
        Mockito.verify(garageRepository).deleteCar(1);


    }

}
