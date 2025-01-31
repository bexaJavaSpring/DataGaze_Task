package com.example.datagaze_task.service;

import com.example.datagaze_task.dto.CountryDto;
import com.example.datagaze_task.dto.CurrentDto;
import com.example.datagaze_task.dto.DataDto;
import com.example.datagaze_task.dto.LocationDto;
import com.example.datagaze_task.exception.GenericRuntimeException;
import com.example.datagaze_task.mapper.WeatherColorMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class WeatherService implements IWeatherService {
    private final RestTemplate restTemplate;
    @Value("${api.url}")
    private String apiUrl;

    @Override
    public DataDto<CountryDto> getWeather(String apiKey, String protocol, String country) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        String url = String.format(protocol.toLowerCase() + apiUrl, apiKey, country);
        ResponseEntity<CountryDto> responseEntity = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(headers), CountryDto.class);
        if (!responseEntity.getStatusCode().is2xxSuccessful())
            throw new GenericRuntimeException("weather.data.not.found");
        CountryDto response = responseEntity.getBody();
        Double temp = response.getCurrent().getTemp_c();
        Double windKph = response.getCurrent().getWind_kph();
        Integer cloud = response.getCurrent().getCloud();
        return new DataDto<>(new CountryDto(LocationDto.builder()
                .name(response.getLocation().getName())
                .lat(response.getLocation().getLat())
                .lon(response.getLocation().getLon())
                .region(response.getLocation().getRegion())
                .country(response.getLocation().getCountry())
                .build(), CurrentDto.builder()
                .cloud(cloud)
                .temp_c(temp)
                .wind_kph(windKph)
                .build(), WeatherColorMapper.getTempColor(temp),
                WeatherColorMapper.getWindColor(windKph),
                WeatherColorMapper.getCloudColor(cloud)));
    }
}
