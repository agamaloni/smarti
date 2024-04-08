package com.example.course.exceptions;

public class CourseNotFoundException extends RuntimeException {
    public CourseNotFoundException(Long id){
        super("course " +id+ ", not found");
    }
}
