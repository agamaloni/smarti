package com.example.course.entitys;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@Getter
@Setter
public abstract class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @NonNull
    @Column(name = "first_name")
    String firstName;

    @NonNull
    @Column(name = "last_name")
    String lastName;

    /**
     * This comment out for reference and desiccation
     * reference: PersonTypeEnum.java
     * TODO: Decide if it better to use one table as PERSON instead of (TEACHER / STUDENT)
     *
    * */
    /*
    @Enumerated(EnumType.STRING)
    @Column(name = "person_type")
    PersonTypeEnum personTypeEnum;
    */

    @Column(name = "email_address")
    String emailAddress;

    @CreatedDate
    @Column(name = "created_date",nullable = false, updatable = false)
    protected LocalDateTime createdDate;

    @LastModifiedDate
    @Column(name = "last_modified_date")
    protected LocalDateTime lastModifiedDate;
}
