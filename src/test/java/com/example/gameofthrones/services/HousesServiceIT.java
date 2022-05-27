package com.example.gameofthrones.services;

import com.example.gameofthrones.rest.responses.House;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class HousesServiceIT {

    @Autowired
    HousesService housesService;

    @Test
    public void getHousesSuccess()
    {
        House house = housesService.getHouseById("1");
        assertNotNull(house);
        assertEquals(house.getRegion(), "The Westerlands");
    }

    @Test
    public void getAllHouses()
    {
        List<House> houses = housesService.getHouses();
        assertNotNull(houses);
        assertEquals(houses.size(), 10);
    }

    @Test
    public void getHousesWithPagination()
    {
        List<House> houses = housesService.readHousesByPage(1, 5);
        assertNotNull(houses);
        assertEquals(houses.size(), 5);
    }

    @Test
    public void getHousesWithFilter()
    {
        List<House> houses = housesService.readHousesWithFilter("House Algood",
                "The Westerlands",
                "",
                null,
                null,
                Boolean.FALSE,
                null,
                null);
        assertNotNull(houses);
        assertEquals(houses.size(), 1);
    }
}
