package com.example.course.exceptions;

public class TeacherNotFoundException extends RuntimeException {
    public TeacherNotFoundException(Long id) {
        super("teacher " + id + ", not found");
    }
}
