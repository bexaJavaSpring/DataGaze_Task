package com.example.datagaze_task.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LocationDto implements Serializable {
    private String name;
    private String region;
    private String country;
    private Double lat;
    private Double lon;
}
