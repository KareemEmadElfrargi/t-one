package com.kareem.tone.controller;

import com.kareem.tone.dto.TeacherRequestDto;
import com.kareem.tone.dto.TeacherResponseDto;
import com.kareem.tone.service.TeacherService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/teachers")
public class TeacherController {
    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }
    @GetMapping
    public List<TeacherResponseDto> getAllTeachers() {
        return teacherService.getAllTeachers();
    }
    @GetMapping("/{id}")
    public TeacherResponseDto getTeacherById(@PathVariable Long id) {
        return teacherService.getTeacherById(id);
    }
    @PostMapping
    public TeacherResponseDto addTeacher(@RequestBody TeacherRequestDto dto) {
        return teacherService.addTeacher(dto);
    }

    @PutMapping("/{id}")
    public TeacherResponseDto updateTeacher(@PathVariable Long id, @RequestBody TeacherRequestDto dto) {
        return teacherService.updateTeacher(id, dto);
    }
    @DeleteMapping("/{id}")
    public void deleteTeacher(@PathVariable Long id) {
        teacherService.deleteTeacher(id);
    }

}
