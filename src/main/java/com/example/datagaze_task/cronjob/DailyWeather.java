package com.example.datagaze_task.cronjob;

import com.example.datagaze_task.dto.CountryDto;
import com.example.datagaze_task.dto.DataDto;
import com.example.datagaze_task.entity.Country;
import com.example.datagaze_task.repository.CountryRepository;
import com.example.datagaze_task.service.WeatherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
@Slf4j
public class DailyWeather {
    private final WeatherService service;
    private final CountryRepository repository;

    @Scheduled(cron = "${cron.job.delay}", zone = "GMT+0")
    public void dailyWeather() {
//        DataDto<CountryDto> dto = service.getWeather("Uzbekistan");
//        if (!Objects.isNull(dto.getData())) {
//            CountryDto data = dto.getData();
//            Country country = new Country();
//            country.setName(data.getName());
//            country.setCountry(data.getCountry());
//            country.setLon(data.getLon());
//            country.setLat(data.getLat());
//            country.setCloud(data.getCloud());
//            country.setTemp_c(data.getTemp_c());
//            Country save = repository.save(country);
//            if (save.getId() != null) {
//                log.info("Country information is successfully saved!...." + save.getId());
//            }
//            log.info("Country information is not saved.......");
//        }

    }
}
