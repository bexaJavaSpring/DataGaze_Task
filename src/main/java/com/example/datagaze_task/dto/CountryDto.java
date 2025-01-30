package com.example.datagaze_task.dto;

import lombok.*;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Setter
public class CountryDto {
    public String name;
    public String country;
    public double lat;
    public double lon;
    public double temp_c;
    public String temp_color;
    public double wind_kph;
    public String wind_color;
    public int cloud;
    public String cloud_color;
}
