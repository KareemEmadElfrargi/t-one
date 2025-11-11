package com.kareem.tone.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
public class TeacherResponseDto {
    private Long id;
    private String name;
    private String email;
    private String specialization;
    private Set<String> courses;
}
