package com.kareem.tone.util;

import com.kareem.tone.dto.StudentDTO;
import com.kareem.tone.model.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {

    // mapping form dto to entity
    public Student toEntity(StudentDTO studentDTO) {
        Student student = new Student();
        student.setName(studentDTO.getName());
        student.setEmail(studentDTO.getEmail());
        student.setAge(studentDTO.getAge());
        return student;
    }

    // mapping form entity to dto
    public StudentDTO toDTO(Student student) {
        return new StudentDTO(student.getName(), student.getEmail(), student.getAge());
    }
}
