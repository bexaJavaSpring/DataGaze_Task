package com.example.datagaze_task.controller;

import com.example.datagaze_task.dto.CountryDto;
import com.example.datagaze_task.dto.CountryRequest;
import com.example.datagaze_task.dto.DataDto;
import com.example.datagaze_task.service.ICountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/countries")
@RequiredArgsConstructor
public class CountryController {

    private final ICountryService service;

    @GetMapping("/filter")
    public ResponseEntity<DataDto<List<CountryDto>>> getAll(CountryRequest request) {
        return ResponseEntity.ok(service.getAll(request));
    }

}
