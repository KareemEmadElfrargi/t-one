package com.kareem.tone.controller;


import com.kareem.tone.model.Student;
import com.kareem.tone.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getAllStudents(){
        return studentService.getAllStudents();
    }
    @PostMapping
    public Student createStudent(@Valid @RequestBody Student student){
        return studentService.addStudent(student);
    }
    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable Long id , @RequestBody Student updatedStudent){
        return studentService.updateStudent(id , updatedStudent);
    }
    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Long id){
        studentService.deleteStudent(id);
    }
    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable Long id){
        return studentService.getStudentById(id);
    }
}
