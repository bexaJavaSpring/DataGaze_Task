package com.example.datagaze_task.dto;

import lombok.*;

import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Setter
public class CountryDto {
    private UUID id;
    private String name;
    private String country;
}
