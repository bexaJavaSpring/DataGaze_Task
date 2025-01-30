package com.example.datagaze_task.mapper;

import com.example.datagaze_task.dto.CountryDto;
import com.example.datagaze_task.entity.Country;
import org.springframework.stereotype.Component;

@Component
public class CountryMapper {
    public CountryDto toDto(Country country) {
        return CountryDto.builder()
                .id(country.getId())
                .name(country.getName())
                .country(country.getCountry())
                .build();
    }
}
