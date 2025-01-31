package com.example.datagaze_task.controller;

import com.example.datagaze_task.dto.CountryDto;
import com.example.datagaze_task.dto.DataDto;
import com.example.datagaze_task.service.IWeatherService;
import com.example.datagaze_task.util.ApiConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiConstants.API_VERSION + "/weather")
@RequiredArgsConstructor
public class WeatherController {

    private final IWeatherService service;

    @GetMapping()
    @PreAuthorize(value = "isAuthenticated()")
    public ResponseEntity<DataDto<CountryDto>> getWeather(@RequestParam String country) {
        return ResponseEntity.ok(service.getWeather(country));
    }
}
