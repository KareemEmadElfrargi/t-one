package com.kareem.tone.service;

import com.kareem.tone.model.Student;
import com.kareem.tone.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
    public Student addStudent(Student student){
        return studentRepository.save(student);
    }

    public Student updateStudent(Long id , Student updatedStudent){
        Student student = studentRepository.findById(id).orElseThrow();
        student.setName(updatedStudent.getName());
        student.setEmail(updatedStudent.getEmail());
        student.setAge(updatedStudent.getAge());
        return studentRepository.save(student);
    }
    public void deleteStudent(long id){
        studentRepository.deleteById(id);
    }

    public Student getStudentById(long id){
        boolean exist = studentRepository.existsById(id);

        if (!exist){
            throw new RuntimeException("Student with id: " + id + " not found");
        }

        return studentRepository.findById(id).orElseThrow();
    }
}
