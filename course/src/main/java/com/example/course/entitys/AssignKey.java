package com.example.course.entitys;

import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode
public class AssignKey implements Serializable {
    private Long courseId;
    private Long studentId;
}
