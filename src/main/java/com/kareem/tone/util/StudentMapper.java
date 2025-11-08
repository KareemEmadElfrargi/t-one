package com.kareem.tone.util;

import com.kareem.tone.dto.StudentDTO;
import com.kareem.tone.dto.StudentRequestDto;
import com.kareem.tone.dto.StudentResponseDto;
import com.kareem.tone.model.Course;
import com.kareem.tone.model.Student;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class StudentMapper {

    // mapping form dto request to entity
    public Student toEntity(StudentRequestDto studentRequestDto) {
        Student student = new Student();
        student.setName(studentRequestDto.getName());
        student.setEmail(studentRequestDto.getEmail());
        student.setAge(studentRequestDto.getAge());
        return student;
    }

    // mapping form entity to dto
    public StudentResponseDto toDTO(Student student) {
        return new StudentResponseDto(
                student.getId(),
                student.getName(),
                student.getEmail(),
                student.getAge()
                ,student.getCourses()
                .stream()
                .map(Course::getName)
                .collect(Collectors.toSet()));
    }
}
