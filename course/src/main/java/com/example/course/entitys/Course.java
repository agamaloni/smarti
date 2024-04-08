package com.example.course.entitys;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.List;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "COURSE")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    Long courseId;

    @Column(name = "course_name", nullable = false)
    String courseName;

    @Column(name = "teacher_id", nullable = false)
    Long teacherId;

    @OneToMany(mappedBy = "courseId",cascade = CascadeType.ALL)
    private List<AssignedStudentToCourse> assignedStudents;

    @CreatedDate()
    @Column(name = "created_date",nullable = false, updatable = false)
    protected LocalDateTime createdDate;

    @LastModifiedDate
    @Column(name = "last_modified_date")
    protected LocalDateTime lastModifiedDate;
}
