package com.example.course.entitys;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "TEACHER")
public class Teacher extends Person {

}
