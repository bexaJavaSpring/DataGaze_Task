package com.example.datagaze_task.cronjob;

import com.example.datagaze_task.dto.CountryDto;
import com.example.datagaze_task.dto.DataDto;
import com.example.datagaze_task.entity.Country;
import com.example.datagaze_task.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class DailyWeather {
    private final WeatherService service;

    @Scheduled(cron = "${cron.job.delay}", zone = "GMT+0")
    public void dailyWeather() {
        DataDto<CountryDto> dto = service.getWeather("Uzbekistan");
        if (!Objects.isNull(dto.getData()) {

        }
        Country country = new Country();
        country.setName();
    }
}
