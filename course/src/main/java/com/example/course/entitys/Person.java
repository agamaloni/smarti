package com.example.course.entitys;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serializable;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
public abstract class Person implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long personId;

    @Column(name = "first_name")
    String firstName;


    @Column(name = "last_name")
    String lastName;


    /*@Enumerated(EnumType.STRING)
    @Column(name = "person_type")
    PersonTypeEnum personTypeEnum;*/


    @Column(name = "email_address")
    String emailAddress;

    @CreatedDate
    @Column(name = "created_date",nullable = false, updatable = false)
    protected LocalDateTime createdDate;

    @LastModifiedDate
    @Column(name = "last_modified_date")
    protected LocalDateTime lastModifiedDate;
}
