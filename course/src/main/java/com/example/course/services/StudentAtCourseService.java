package com.example.course.services;

import com.example.course.repositories.StudentAtCourseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentAtCourseService {

    @Autowired
    private StudentAtCourseRepo studentAtCourseRepo;
}
