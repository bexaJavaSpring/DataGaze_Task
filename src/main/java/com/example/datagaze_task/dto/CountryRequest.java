package com.example.datagaze_task.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CountryRequest extends BaseFilter {
    private UUID id;
    private String name;
    private String country;
    private Double lat;
    private Double lon;
    private String temp_color;
    private String wind_color;
    private String cloud_color;
    private Double temp_c;
    private Double wind_kph;
    private Long cloud;
}
