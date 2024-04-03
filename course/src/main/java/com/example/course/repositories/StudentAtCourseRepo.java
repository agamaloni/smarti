package com.example.course.repositories;

import com.example.course.entitys.StudentAtCourse;
import org.springframework.data.repository.CrudRepository;

public interface StudentAtCourseRepo extends CrudRepository<StudentAtCourse,Long> {
}
