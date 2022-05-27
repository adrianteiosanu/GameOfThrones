package com.example.gameofthrones.controllers;

import com.example.gameofthrones.rest.responses.House;
import com.example.gameofthrones.services.HousesService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HousesController.class)
public class HousesControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private HousesService housesService;

    @Test
    public void getHouse() throws Exception
    {
        ObjectMapper objectMapper = new ObjectMapper();
        House house1 = objectMapper.readValue(new File("src/test/resources/house1.json"), House.class);

        Mockito.when(housesService.getHouseById("1")).thenReturn(house1);

        this.mvc.perform(get("/houses/v1/1")).andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("House Algood")));
    }

    @Test
    public void getAllHouses() throws Exception
    {
        ObjectMapper objectMapper = new ObjectMapper();
        House house1 = objectMapper.readValue(new File("src/test/resources/house1.json"), House.class);
        House house2 = objectMapper.readValue(new File("src/test/resources/house2.json"), House.class);
        List<House> houses= Arrays.asList(house1, house2);

        Mockito.when(housesService.getHouses()).thenReturn(houses);

        this.mvc.perform(get("/houses/v1")).andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$.[0].region", is("The Westerlands")))
                .andExpect(jsonPath("$.[1].words", is("No Foe May Pass")));
    }

    @Test
    public void getHousesWithFilter() throws  Exception
    {
        ObjectMapper objectMapper = new ObjectMapper();
        House house1 = objectMapper.readValue(new File("src/test/resources/house1.json"), House.class);
        List<House> houses= Arrays.asList(house1);

        Mockito.when(housesService.readHousesWithFilter("House Algood",
                "The Westerlands",
                "",
                null,
                null,
                Boolean.FALSE,
                null,
                null))
                .thenReturn(houses);

        this.mvc.perform(get("/houses/v1/filter?name=House Algood&region=The Westerlands&hasSeats=false")).andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$.[0].name", is("House Algood")))
                .andExpect(jsonPath("$.[0].region", is("The Westerlands")))
                .andExpect(jsonPath("$.[0].seats.[0]", is("")));
    }
}
