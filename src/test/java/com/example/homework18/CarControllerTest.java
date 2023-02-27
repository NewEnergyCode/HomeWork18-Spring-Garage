package com.example.homework18;

import com.example.homework18.model.Cars;
import com.example.homework18.repository.dao.GarageRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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


        Cars cars1 = Cars.builder().carId(123).brand("Reno").model("Meg").build();

        when(garageRepository.getCar(43)).thenReturn(cars1);

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

        Cars car = Cars.builder().carId(99).userId(1).brand("Opel").model("Astra").build();
        when(garageRepository.getCar(car.getCarId())).thenReturn(car);

        MvcResult mvcResult = mockMvc.perform(get("/cars/" + car.getCarId()))
                .andExpect(status().isOk()).andDo(print())
                .andReturn();

        String responseString = mvcResult.getResponse().getContentAsString();
        Cars returnedCar = new ObjectMapper().readValue(responseString, new TypeReference<>() {
        });
        assertEquals(car, returnedCar);
    }

    @Test
    public void postCarTest() throws Exception {
        Cars car = Cars.builder().carId(99).userId(1).brand("Opel").model("Astra").build();
        String requestJson = objectMapper.writeValueAsString(car);
        MvcResult mvcResult = mockMvc.perform(post("/cars/" + car.getCarId() + "/" + car.getUserId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk())
                .andDo(print()).andReturn();

    }


    @Test
    void creatCarTest() throws Exception {

        Cars car = Cars.builder().carId(99).userId(1).brand("Opel").model("Astra").build();
        String requestJson = objectMapper.writeValueAsString(car);

        when(garageRepository.creatCar(any(), anyInt())).thenReturn(car);

        MvcResult mvcResult = mockMvc.perform(put("/cars/" + car.getCarId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        String responseString = mvcResult.getResponse().getContentAsString();

        // Конвертируем JSON-строку ответа в объект машины
        Cars createdCar = objectMapper.readValue(responseString, Cars.class);

        // Проверяем, что созданная машина соответствует отправленной в запросе машине
        assertEquals(car.getBrand(), createdCar.getBrand());
        assertEquals(car.getBrand(), createdCar.getBrand());
        assertEquals(car.getModel(), createdCar.getModel());

    }

    @Test
    public void deleteCarTest() throws Exception {

        MvcResult mvcResult = mockMvc.perform(delete("/cars/" + anyInt()))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

    }

}
