package com.example.gameofthrones.services;

import com.example.gameofthrones.client.ApiClient;
import com.example.gameofthrones.rest.responses.Book;
import com.example.gameofthrones.rest.responses.Character;
import com.example.gameofthrones.rest.responses.House;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HousesService {
    private final ApiClient apiClient;

    public HousesService(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public List<House> getHouses()
    {
        return this.apiClient.readHouses();
    }

    public House getHouseById(String houseId)
    {
        return this.apiClient.readHouseById(houseId);
    }

    public List<House> readHousesByPage(int page, int pageSize)
    {
        return apiClient.readHousesByPage(page, pageSize);
    }

    public List<House> readHousesWithFilter(String name,
                                            String region,
                                            String words,
                                            Boolean hasWords,
                                            Boolean hasTitles,
                                            Boolean hasSeats,
                                            Boolean hasDiedOut,
                                            Boolean hasAncestralWeapons )
    {
        return apiClient.readHousesWithFilter(name, region, words, hasWords, hasTitles, hasSeats, hasDiedOut, hasAncestralWeapons);
    }
}
