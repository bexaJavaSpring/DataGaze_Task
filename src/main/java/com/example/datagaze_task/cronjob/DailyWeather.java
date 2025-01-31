package com.example.datagaze_task.cronjob;

import com.example.datagaze_task.dto.CountryDto;
import com.example.datagaze_task.dto.DataDto;
import com.example.datagaze_task.entity.Country;
import com.example.datagaze_task.mapper.WeatherColorMapper;
import com.example.datagaze_task.repository.CountryRepository;
import com.example.datagaze_task.service.WeatherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
@Slf4j
public class DailyWeather {
    private final WeatherService service;
    private final CountryRepository repository;
    @Value("${api.key}")
    private String apiKey;

    @Scheduled(cron = "0 52 12 * * ?")
    public void dailyWeather() {
        List<String> list = new ArrayList<>();
        list.add("Uzbekistan");
        list.add("Madrid");
        list.add("Berlin");
        list.add("Germany");
        list.add("India");
        list.add("United States");
        list.add("Canada");
        list.add("France");
        list.add("Portugal");
        list.add("Russia");
        // bu yerda 1 ta free web site dan API olib hamma davlatlarni keltirsa bo'ladi, men list qilib o'zim uchun 10 tachasini yozdim.
        for (String str : list) {
            DataDto<CountryDto> dto = service.getWeather(apiKey, "http", str);
            if (!Objects.isNull(dto.getData())) {
                CountryDto data = dto.getData();
                Country country = new Country();
                country.setName(data.getLocation().getName());
                country.setCountry(data.getLocation().getCountry());
                country.setLon(data.getLocation().getLon());
                country.setWind_kph(data.getCurrent().getWind_kph());
                country.setLat(data.getLocation().getLat());
                country.setCloud(data.getCurrent().getCloud());
                country.setTemp_c(data.getCurrent().getTemp_c());
                country.setCloud_color(WeatherColorMapper.getCloudColor(data.getCurrent().getCloud()));
                country.setWind_color(WeatherColorMapper.getWindColor(data.getCurrent().getWind_kph()));
                country.setTemp_color(WeatherColorMapper.getTempColor(data.getCurrent().getTemp_c()));
                Country save = repository.save(country);
                if (save.getId() != null) {
                    log.info("Country information is successfully saved!...." + save.getId());
                }
                log.info("Country information is not saved.......");
            } else {
                log.info("Country information is empty......");
            }
        }
    }
}
