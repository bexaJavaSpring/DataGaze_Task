package com.example.datagaze_task.service;

import com.example.datagaze_task.dto.CountryDto;
import com.example.datagaze_task.dto.DataDto;

public interface IWeatherService {

    DataDto<CountryDto> getWeather(String country);
}
