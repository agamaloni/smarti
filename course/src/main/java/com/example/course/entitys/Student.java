package com.example.course.entitys;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Table(name = "STUDENT")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
public class Student extends Person {

    @Column(name = "courses")
    String courses;

}
