package com.kareem.tone.controller;


import com.kareem.tone.dto.StudentDTO;
import com.kareem.tone.model.Student;
import com.kareem.tone.service.StudentService;
import com.kareem.tone.util.StudentMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {

    private final StudentService studentService;
    private final StudentMapper studentMapper;

    public StudentController(StudentService studentService , StudentMapper studentMapper) {
        this.studentService = studentService;
        this.studentMapper = studentMapper;
    }

    @GetMapping
    public List<StudentDTO> getAllStudents(){
        return studentService.getAllStudents().stream().map(studentMapper::toDTO).collect(Collectors.toList());
    }
    @PostMapping
    public StudentDTO createStudent(@Valid @RequestBody StudentDTO studentDTO){
        Student student = studentMapper.toEntity(studentDTO);
        Student saved = studentService.addStudent(student);
        return studentMapper.toDTO(saved);
    }
    @PutMapping("/{id}")
    public StudentDTO updateStudent(@PathVariable Long id , @RequestBody StudentDTO updatedStudentDto){
        Student student = studentMapper.toEntity(updatedStudentDto);
        Student updated = studentService.updateStudent(id, student);
        return studentMapper.toDTO(updated);
    }
    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Long id){
        studentService.deleteStudent(id);
    }
    @GetMapping("/{id}")
    public StudentDTO getStudentById(@PathVariable Long id){
        Student student = studentService.getStudentById(id);
        return studentMapper.toDTO(student);
    }
}
