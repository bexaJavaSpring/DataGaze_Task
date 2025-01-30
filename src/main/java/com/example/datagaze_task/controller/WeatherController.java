package com.example.datagaze_task.controller;

import com.example.datagaze_task.dto.CountryDto;
import com.example.datagaze_task.dto.DataDto;
import com.example.datagaze_task.service.IWeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weather")
@RequiredArgsConstructor
public class WeatherController {

    private final IWeatherService service;

    @GetMapping("/{country}")
    @PreAuthorize(value = "isAuthenticated()")
    public ResponseEntity<DataDto<CountryDto>> getWeather(@PathVariable String country) {
        return ResponseEntity.ok(service.getWeather(country));
    }
}
