package com.kareem.tone.util.mapper;

import com.kareem.tone.dto.TeacherRequestDto;
import com.kareem.tone.dto.TeacherResponseDto;
import com.kareem.tone.model.Course;
import com.kareem.tone.model.Teacher;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class TeacherMapper {
    public Teacher toEntity(TeacherRequestDto dto) {
        Teacher teacher = new Teacher();
        teacher.setName(dto.getName());
        teacher.setEmail(dto.getEmail());
        teacher.setSpecialization(dto.getSpecialization());
        return teacher;
    }

    public TeacherResponseDto toDto(Teacher teacher) {
        return new TeacherResponseDto(
                teacher.getId(),
                teacher.getName(),
                teacher.getEmail(),
                teacher.getSpecialization(),
                teacher.getCourses().stream().map(Course::getName).collect(Collectors.toSet())

        );
    }
}
