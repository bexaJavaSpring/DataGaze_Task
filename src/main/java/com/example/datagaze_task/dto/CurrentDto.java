package com.example.datagaze_task.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CurrentDto {
    private Double temp_c;
    private Double wind_kph;
    private Integer cloud;
}
