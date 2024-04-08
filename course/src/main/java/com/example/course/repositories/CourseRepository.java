package com.example.course.repositories;

import com.example.course.entitys.Course;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends CrudRepository<Course, Long> {
    Optional<Course> findByCourseId(Long id);
    List<Course> findAll();

    @Transactional
    void deleteByCourseId(Long id);
}
