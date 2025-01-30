package com.example.datagaze_task.specification;

import com.example.datagaze_task.dto.CountryRequest;
import com.example.datagaze_task.entity.Country;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
public class CountrySpecification implements Specification<Country> {

    private final CountryRequest request;
    @Override
    public Predicate toPredicate(Root<Country> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        List<Predicate> predicates = new ArrayList<>();
        if (!Objects.isNull(request.getName())) {
            predicates.add(builder.like(builder.lower(root.get("name")), "%" + request.getName().toLowerCase() + "%"));
        }
        if (!Objects.isNull(request.getCountry())) {
            predicates.add(builder.like(builder.lower(root.get("country")), "%" + request.getCountry().toLowerCase() + "%"));
        }

        return builder.and(predicates.toArray(new Predicate[0]));
    }
}
