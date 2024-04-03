package com.example.course.entitys;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Table(name = "STUDENT_AT_COURSE")
@Entity
public class StudentAtCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @Column(name = "course_id")
    private Long courseId;
   /* @Column(name = "student_id")
    private Long studentId;*/
}
