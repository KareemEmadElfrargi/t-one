package com.kareem.tone.service;

import com.kareem.tone.dto.CourseResponseDto;
import com.kareem.tone.dto.TeacherRequestDto;
import com.kareem.tone.dto.TeacherResponseDto;
import com.kareem.tone.model.Course;
import com.kareem.tone.model.Teacher;
import com.kareem.tone.repository.CourseRepository;
import com.kareem.tone.repository.TeacherRepository;
import com.kareem.tone.util.mapper.CourseMapper;
import com.kareem.tone.util.mapper.TeacherMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeacherService {
    private final TeacherRepository teacherRepository;
    private final CourseRepository courseRepository;
    private final TeacherMapper teacherMapper;
    private final CourseMapper courseMapper;

    public TeacherService(TeacherRepository teacherRepository, CourseRepository courseRepository, TeacherMapper teacherMapper, CourseMapper courseMapper) {
        this.teacherRepository = teacherRepository;
        this.courseRepository = courseRepository;
        this.teacherMapper = teacherMapper;
        this.courseMapper = courseMapper;
    }

    public List<TeacherResponseDto> getAllTeachers() {
        return teacherRepository.findAll().stream().map(teacherMapper::toDto).collect(Collectors.toList());
    }

    public TeacherResponseDto getTeacherById(Long id) {
        return teacherRepository.findById(id).map(teacherMapper::toDto)
                .orElseThrow(()-> new RuntimeException("Teacher not found"));
    }
    public TeacherResponseDto addTeacher(TeacherRequestDto teacherRequestDto) {
        Teacher teacher = teacherMapper.toEntity(teacherRequestDto);
        teacherRepository.save(teacher);
        return teacherMapper.toDto(teacher);
    }
    public TeacherResponseDto updateTeacher(Long id , TeacherRequestDto teacherRequestDto) {
        Teacher existing = teacherRepository.findById(id).orElseThrow(()-> new RuntimeException("Teacher not found"));
        existing.setName(teacherRequestDto.getName());
        existing.setEmail(teacherRequestDto.getEmail());
        existing.setSpecialization(teacherRequestDto.getSpecialization());
        teacherRepository.save(existing);
        return teacherMapper.toDto(existing);
    }

    public void deleteTeacher(Long id) {
        teacherRepository.deleteById(id);
    }

    public TeacherResponseDto assignCoursesToTeacher(Long teacherId, List<Long> courseIds) {
        Teacher teacher = teacherRepository.findById(teacherId).orElseThrow(()-> new RuntimeException("Teacher not found"));

        List<Course> courses = courseRepository.findAllById(courseIds);

        courses.forEach(course -> {
            course.setTeacher(teacher);

        });
        courseRepository.saveAll(courses);
        return teacherMapper.toDto(teacher);
    }

    public List<CourseResponseDto> getCoursesForTeacher(Long teacherId) {
        return courseRepository.findByTeacherId(teacherId)
                .stream()
                .map(courseMapper::toDto)
                .collect(Collectors.toList());
    }

}
