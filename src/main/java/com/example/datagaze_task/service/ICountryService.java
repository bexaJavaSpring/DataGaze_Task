package com.example.datagaze_task.service;

import com.example.datagaze_task.dto.CountryDto;
import com.example.datagaze_task.dto.CountryRequest;
import com.example.datagaze_task.dto.DataDto;

import java.util.List;

public interface ICountryService {
    DataDto<List<CountryDto>> getAll(CountryRequest countryRequest);
}
