package com.example.homework18;

import com.example.homework18.model.Users;
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

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc

public class UserControllerTests {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    GarageRepository garageRepository;
    @Autowired
    ObjectMapper objectMapper;

    @Mock
    Users users;

    @Test
    public void getAllUsersTest() throws Exception {

        MvcResult mvcResult = mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andReturn();

        String responseString = mvcResult.getResponse().getContentAsString();
        List<Users> usersList = objectMapper.readValue(responseString, new TypeReference<List<Users>>() {
        });
        assertNotNull(usersList);
    }

    @Test
    public void getUserTest() throws Exception {

        Users user = Users.builder().userId(5).surname("Benson").name("Ben").build();

        when(garageRepository.getUser(anyInt())).thenReturn(user);

        MvcResult mvcResult = mockMvc.perform(get("/users/" + user.getUserId()))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        String responseString = mvcResult.getResponse().getContentAsString();

        Users userJson = objectMapper.readValue(responseString, Users.class);
        assertEquals(user, userJson);

    }

    @Test
    public void creatUserTest() throws Exception {

        Users user = Users.builder().userId(5).name("Ben").surname("Benson").build();

        String requestJson = objectMapper.writeValueAsString(user);
        when(garageRepository.creatUser(user)).thenReturn(user);

        MvcResult mvcResult = mockMvc.perform(put("/users")
                        .contentType(MediaType.APPLICATION_JSON).content(requestJson))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        String mvcResultString = mvcResult.getResponse().getContentAsString();

        Users responseString = objectMapper.readValue(mvcResultString, Users.class);
        assertEquals(user.getName(), responseString.getName());
        assertEquals(user.getUserId(), responseString.getUserId());
        assertEquals(user.getSurname(), responseString.getSurname());

    }

    @Test
    public void postUserTest() throws Exception {

        Users user = Users.builder().userId(5).name("Ben").surname("Benson").build();
        String userJson = objectMapper.writeValueAsString(user);

        MvcResult mvcResult = mockMvc.perform(post("/users/" + user.getUserId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson))
                .andExpect(status().isOk())
                .andDo(print()).andReturn();

    }

    @Test
    public void deleteUserTest() throws Exception {

        MvcResult mvcResult = mockMvc.perform(delete("/users/" + anyInt()))
                .andExpect(status().isOk())
                .andDo(print()).andReturn();

    }

}
