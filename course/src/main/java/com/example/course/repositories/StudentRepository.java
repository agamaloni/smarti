package com.example.course.repositories;

import com.example.course.entitys.Student;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StudentRepository extends CrudRepository<Student,Long> {
    Student findByPersonId(Long id);
    List<Student> findAll();
}
