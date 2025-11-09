package com.kareem.tone.service;

import com.kareem.tone.dto.EnrollmentRequestDto;
import com.kareem.tone.dto.EnrollmentResponseDto;
import com.kareem.tone.model.Course;
import com.kareem.tone.model.Enrollment;
import com.kareem.tone.model.Student;
import com.kareem.tone.repository.CourseRepository;
import com.kareem.tone.repository.EnrollmentRepository;
import com.kareem.tone.repository.StudentRepository;
import com.kareem.tone.util.mapper.EnrollmentMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EnrollmentService {
    private final EnrollmentRepository enrollmentRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final EnrollmentMapper enrollmentMapper;

    public EnrollmentService(EnrollmentRepository enrollmentRepository
            , StudentRepository studentRepository
            , CourseRepository courseRepository
            , EnrollmentMapper enrollmentMapper) {

        this.enrollmentRepository = enrollmentRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.enrollmentMapper = enrollmentMapper;
    }

    public EnrollmentResponseDto addGrade(EnrollmentRequestDto requestDto) {
        Student student = studentRepository.findById(requestDto.getStudentId()).orElseThrow(
                ()-> new RuntimeException("Student not found")
        );
        Course course = courseRepository.findById(requestDto.getCourseId()).orElseThrow(
                ()-> new RuntimeException("Course not found")
        );
        Enrollment enrollment = enrollmentMapper.toEntity(requestDto, student,course);
        enrollmentRepository.save(enrollment);
        return enrollmentMapper.toDto(enrollment);
    }
    public List<EnrollmentResponseDto> getGradesByStudent(Long studentId) {
        return enrollmentRepository.findByStudentId(studentId)
                .stream()
                .map(enrollmentMapper::toDto)
                .collect(Collectors.toList());
    }
    public List<EnrollmentResponseDto> getGradesByCourse(Long courseId) {
        return enrollmentRepository.findByCourseId(courseId)
                .stream()
                .map(enrollmentMapper::toDto)
                .collect(Collectors.toList());
    }
    //Reporting ....
    public double getStudentAverageGrade(Long studentId) {
        List<Enrollment> enrollments = enrollmentRepository.findByStudentId(studentId);
        if (enrollments.isEmpty()) {
            return 0.0;
        }
        double total = enrollments.stream().mapToDouble(Enrollment::getGrade).sum();
        return total / enrollments.size();
    }
    public double getHighestGradeInCourse(Long courseId) {
        List<Enrollment> enrollments = enrollmentRepository.findByCourseId(courseId);
        if (enrollments.isEmpty()) {
            return 0.0;
        }
        return enrollments.stream().mapToDouble(Enrollment::getGrade).max().orElse(0.0);
    }
    public int getStudentRankInCourse(Long studentId, Long courseId) {
        List<Enrollment> enrollments = enrollmentRepository.findByCourseId(courseId);

        enrollments.sort((element1 , element2) ->
                element2.getGrade().compareTo(element1.getGrade()));

        for (int index =0; index<enrollments.size(); index++) {
            if (enrollments.get(index).getStudent().getId().equals(studentId)) {
                return index + 1;
            }
        }
        return -1;
    }
}
