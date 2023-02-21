package com.example.homework18;

import com.example.homework18.model.Cars;
import com.example.homework18.repository.dao.GarageRepository;
import com.example.homework18.service.GarageService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc

public class CarControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    GarageRepository garageRepository;
    @Autowired
    ObjectMapper objectMapper;

    @Mock
    Cars cars;

    @Test
    public void getAllCarsTest() throws Exception {

        MvcResult mvcResult = mockMvc.perform(get("/cars"))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        String responseString = mvcResult.getResponse().getContentAsString();
        List<Cars> carsList = objectMapper.readValue(responseString, new TypeReference<List<Cars>>() {
        });
        assertNotNull(carsList);

    }

    @Test
    public void getCarTest() throws Exception {

        GarageService garageService = new GarageService(garageRepository);
        Cars cars1 = Cars.builder().carId(123).brand("Reno").model("Meg").build();

        Mockito.when(garageRepository.getCar(43)).thenReturn(cars1);

        MvcResult mvcResult = mockMvc.perform(get("/cars/" + 43))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        String responseString = mvcResult.getResponse().getContentAsString();
        Cars returnedCar = new ObjectMapper().readValue(responseString, new TypeReference<>() {
        });
        assertEquals(cars1, returnedCar);


    }

    @Test
    void getCarTests() throws Exception {
        GarageService garageService = new GarageService(garageRepository);
        Cars car = Cars.builder().carId(99).userId(1).brand("Opel").model("Astra").build();
        Mockito.when(garageService.getCar(car.getCarId())).thenReturn(car);

        MvcResult mvcResult = mockMvc.perform(get("/cars/" + car.getCarId()))
                .andExpect(status().isOk())
                .andReturn();

        String responseString = mvcResult.getResponse().getContentAsString();
        Cars returnedCar = new ObjectMapper().readValue(responseString, new TypeReference<>() {
        });
        assertEquals(car, returnedCar);
    }


}
