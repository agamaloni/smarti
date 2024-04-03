package com.example.course.exceptions;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(){
        super("user not found");
    }
}
