package com.kareem.tone.service;


import com.kareem.tone.dto.CourseRequestDto;
import com.kareem.tone.dto.CourseResponseDto;
import com.kareem.tone.dto.CourseWithStudentsDto;
import com.kareem.tone.model.Course;
import com.kareem.tone.repository.CourseRepository;
import com.kareem.tone.util.mapper.CourseMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseService {
    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    public CourseService(CourseRepository courseRepository, CourseMapper courseMapper) {
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
    }

    public List<CourseResponseDto> getAllCourses() {

        return courseRepository.findAll()
                .stream()
                .map(courseMapper::toDto)
                .collect(Collectors.toList());
    }

    public CourseResponseDto getCourseById(Long id) {
        Course course = courseRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Course with id: " + id + " not found"));
        return courseMapper.toDto(course);
    }

    public CourseResponseDto addCourse(CourseRequestDto courseRequestDto) {
        Course course = courseMapper.toEntity(courseRequestDto);
        courseRepository.save(course);
        return courseMapper.toDto(course);
    }

    public CourseResponseDto updateCourse(Long id, CourseRequestDto courseRequestDto) {
        Course existing = courseRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Course with id: " + id + " not found")
        );

        existing.setName(courseRequestDto.getName());
        existing.setDescription(courseRequestDto.getDescription());
        courseRepository.save(existing);
        return courseMapper.toDto(existing);
    }

    public CourseResponseDto deleteCourse(Long id) {
        Course existing = courseRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Course with id: " + id + " not found")
        );
        return courseMapper.toDto(existing);
    }
    /**
     * @param idCourse --> give me unique id of course
     * @return CourseWithStudentsDto to get details about course
     * **/
    public CourseWithStudentsDto getCourseWithStudents(Long idCourse) {
        Course course = courseRepository.findById(idCourse).orElseThrow(
                () -> new EntityNotFoundException("Course with id: " + idCourse + " not found")
        );
        return courseMapper.toDtoWithStudents(course);
    }





}
