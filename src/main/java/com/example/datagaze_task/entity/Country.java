package com.example.datagaze_task.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "countries")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    private String country;

    private Double lat;

    private Double lon;

    private Double temp_c;

    private String temp_color;

    private Double wind_kph;

    private String wind_color;

    private Long cloud;

    private String cloud_color;
}
