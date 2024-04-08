package com.example.course.services;

import com.example.course.entitys.Course;
import com.example.course.exceptions.CourseNotFoundException;

import java.util.List;

public interface CourseService {
    Course getCourse(Long id) throws CourseNotFoundException;
    void saveCourse(Course course);
    void deleteCourse(Long id);
    List<Course> getCourses();
}
