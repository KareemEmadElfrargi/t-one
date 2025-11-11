package com.kareem.tone.controller;

import com.kareem.tone.dto.EnrollmentRequestDto;
import com.kareem.tone.dto.EnrollmentResponseDto;
import com.kareem.tone.dto.StatisticsDto;
import com.kareem.tone.service.EnrollmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/enrollments")
public class EnrollmentController {
    private final EnrollmentService enrollmentService;


    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @PostMapping("/grade")
    public ResponseEntity<EnrollmentResponseDto> addGrade(@RequestBody EnrollmentRequestDto enrollmentRequestDto) {
        return ResponseEntity.ok(enrollmentService.addGrade(enrollmentRequestDto));
    }
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<EnrollmentResponseDto>> getGradesByStudent(@PathVariable("studentId") Long studentId) {
        return ResponseEntity.ok(enrollmentService.getGradesByStudent(studentId));
    }
    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<EnrollmentResponseDto>> getGradesByCourse(@PathVariable("courseId") Long courseId) {
        return ResponseEntity.ok(enrollmentService.getGradesByCourse(courseId));
    }
    @GetMapping("/student/{studentId}/average")
    public ResponseEntity<StatisticsDto> getStudentAverageGrade(@PathVariable("studentId") Long studentId) {
        double average = enrollmentService.getStudentAverageGrade(studentId);
        return ResponseEntity.ok(new StatisticsDto("average",average,studentId,null));
    }
    @GetMapping("/course/{courseId}/highest")
    public ResponseEntity<StatisticsDto> getCourseHighestGrade(@PathVariable("courseId") Long courseId) {
        double highest = enrollmentService.getHighestGradeInCourse(courseId);
        return ResponseEntity.ok(new StatisticsDto("highest",highest,null,courseId));
    }
    @GetMapping("/course/{courseId}/student/{studentId}/rank")
    public ResponseEntity<StatisticsDto> getStudentRank(@PathVariable("courseId") Long courseId,
                                                  @PathVariable("studentId") Long studentId) {
        int rank = enrollmentService.getStudentRankInCourse(courseId, studentId);
        return ResponseEntity.ok(new StatisticsDto("rank",(double)rank,studentId,courseId));
    }
}
