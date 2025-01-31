package com.example.datagaze_task.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Setter
public class CountryDto implements Serializable  {
    @JsonProperty("location")
    private LocationDto location;
    @JsonProperty("current")
    private CurrentDto current;
    @JsonProperty("temp_color")
    public String tempColor;
    @JsonProperty("wind_color")
    public String windColor;
    @JsonProperty("cloud_color")
    public String cloudColor;
}
