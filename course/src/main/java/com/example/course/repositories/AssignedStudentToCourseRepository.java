package com.example.course.repositories;

import com.example.course.entitys.AssignKey;
import com.example.course.entitys.AssignedStudentToCourse;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AssignedStudentToCourseRepository extends CrudRepository<AssignedStudentToCourse, AssignKey> {
    @Query("SELECT asc FROM AssignedStudentToCourse asc WHERE asc.courseId = :courseId")
    Optional<List<AssignedStudentToCourse>> findAllByCourseId(@Param("courseId") Long courseId);

}
