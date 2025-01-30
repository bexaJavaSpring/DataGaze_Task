package com.example.datagaze_task.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class BaseFilter {
    private Integer page;
    private Integer limit;
}
