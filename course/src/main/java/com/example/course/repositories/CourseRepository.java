package com.example.course.repositories;

import com.example.course.entitys.Course;
import org.springframework.data.repository.CrudRepository;

public interface CourseRepository extends CrudRepository<Course, Long> {
}
