package com.kareem.tone.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class StatisticsDto {
    private String type;
    private Double value;
    private Long studentId;
    private Long courseId;
}
