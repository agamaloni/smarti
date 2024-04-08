package com.example.course.entitys;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "TEACHER")
public class Teacher extends Person {

    @JsonIgnore
    @OneToMany(mappedBy = "teacherId", cascade = CascadeType.ALL)
    private List<Course> courses;
}
