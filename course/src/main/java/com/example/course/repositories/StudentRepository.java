package com.example.course.repositories;

import com.example.course.entitys.Student;
import org.springframework.stereotype.Repository;
@Repository
public interface StudentRepository extends PersonRepository<Student> {
}
