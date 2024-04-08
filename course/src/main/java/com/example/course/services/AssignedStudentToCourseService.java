package com.example.course.services;

import com.example.course.entitys.Student;

import java.util.List;
import java.util.Optional;

public interface AssignedStudentToCourseService {
    boolean assignStudentToCourse(Long studentId, Long courseId);

    boolean unassignedStudentFromCourse(Long studentId, Long courseId);

    Optional<List<Student>> getStudentAtCourse(Long courseId);
}
