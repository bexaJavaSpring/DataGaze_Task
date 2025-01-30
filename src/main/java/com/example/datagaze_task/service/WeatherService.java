package com.example.datagaze_task.service;

import com.example.datagaze_task.dto.CountryDto;
import com.example.datagaze_task.dto.DataDto;
import com.example.datagaze_task.exception.GenericRuntimeException;
import com.example.datagaze_task.mapper.WeatherColorMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class WeatherService implements IWeatherService {
    private final RestTemplate restTemplate;
    private static final String API_KEY = "b0a8e50f92194b3992d135455252901";
    private static final String API_URL = "https://www.weatherapi.com/";
    @Override
    public DataDto<CountryDto> getWeather(String country) {
        String url = String.format(API_URL, country, API_KEY);
        Map<String, Object> response = restTemplate.getForObject(url, Map.class);

        if (response == null) throw new GenericRuntimeException("weather.data.not.found");

        double temp = (double) ((Map<String, Object>) response.get("main")).get("temp");
        double windSpeed = (double) ((Map<String, Object>) response.get("wind")).get("speed");
        int cloud = (int) ((Map<String, Object>) response.get("clouds")).get("all");
        return new DataDto<>(new CountryDto((String) response.get("name"),
                (String) ((Map<String, Object>) response.get("sys")).get("country"),
                (double) ((Map<String, Object>) response.get("coord")).get("lat"),
                (double) ((Map<String, Object>) response.get("coord")).get("lon"),
                temp, WeatherColorMapper.getTempColor(temp),
                windSpeed, WeatherColorMapper.getWindColor(windSpeed),
                cloud, WeatherColorMapper.getCloudColor(cloud)));
    }
}
