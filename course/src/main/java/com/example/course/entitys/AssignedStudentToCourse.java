package com.example.course.entitys;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@IdClass(AssignKey.class)
@Table(name = "STUDENT_AT_COURSE")
public class AssignedStudentToCourse {
    @Id
    @ManyToOne(optional = false,cascade = CascadeType.ALL,targetEntity = Course.class)
    private Long courseId;
    @Id
    @ManyToOne(optional = false,cascade = CascadeType.ALL,targetEntity = Student.class)
    private Long studentId;

    @CreatedDate
    @Column(name = "created_date",nullable = false, updatable = false)
    protected LocalDateTime createdDate;

    @LastModifiedDate
    @Column(name = "last_modified_date")
    protected LocalDateTime lastModifiedDate;
}
