package com.example.datagaze_task.service;

import com.example.datagaze_task.dto.CountryDto;
import com.example.datagaze_task.dto.CountryRequest;
import com.example.datagaze_task.dto.DataDto;
import com.example.datagaze_task.mapper.CountryMapper;
import com.example.datagaze_task.repository.CountryRepository;
import com.example.datagaze_task.specification.CountrySpecification;
import com.example.datagaze_task.specification.SearchSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CountryService implements ICountryService {

    private final CountryRepository countryRepository;
    private final CountryMapper mapper;

    @Override
    public DataDto<List<CountryDto>> getAll(CountryRequest countryRequest) {
        List<CountryDto> list = countryRepository.findAll(new CountrySpecification(countryRequest),
                        SearchSpecification.getPageable(countryRequest.getPage(), countryRequest.getLimit()))
                .map(mapper::toDto).toList();
        return new DataDto<>(list);
    }
}
