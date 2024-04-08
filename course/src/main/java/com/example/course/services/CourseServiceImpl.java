package com.example.course.services;

import com.example.course.entitys.Course;
import com.example.course.exceptions.CourseNotFoundException;
import com.example.course.repositories.CourseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class CourseServiceImpl implements CourseService {

    CourseRepository courseRepository;
    @Override
    public Course getCourse(Long id) {
        Optional<Course> course = courseRepository.findByCourseId(id);
        if(course.isPresent()) {
           return course.get();
        }
        throw new CourseNotFoundException(id);
    }

    @Override
    public void saveCourse(Course course) {
        courseRepository.save(course);
    }

    @Override
    public void deleteCourse(Long id) {
        Optional<Course> course = courseRepository.findById(id);
        if(course.isPresent()) {
            courseRepository.deleteByCourseId(id);
        } throw new CourseNotFoundException(id);
    }

    @Override
    public List<Course> getCourses() {
        return courseRepository.findAll();
    }
}
