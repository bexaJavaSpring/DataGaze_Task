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
}
