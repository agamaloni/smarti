package com.example.course.entitys;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;
import lombok.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "STUDENT")
public class Student extends Person {

    @JsonIgnore
    @OneToMany(mappedBy = "studentId", cascade = CascadeType.ALL)
    List<AssignedStudentToCourse> assignedCourses;

}



