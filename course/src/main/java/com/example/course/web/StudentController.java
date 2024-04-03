package com.example.course.web;

import com.example.course.entitys.Student;
import com.example.course.exceptions.UserNotFoundException;
import com.example.course.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentService studentService;

    @GetMapping(value = "/{id}" , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Student> getStudent(@PathVariable Long id){
        System.out.println("id = " + id);
        Student student = studentService.getStudent(id);
        System.out.println("student = " + student);
        return new ResponseEntity<>(studentService.getStudent(id), HttpStatus.OK);
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Student>> getStudents(){
        List<Student> students = studentService.getStudents();
        System.out.println("all students = " + students);
        return new ResponseEntity<>(studentService.getStudents(), HttpStatus.OK);
    }
/*
    @PostMapping("/{id)")
    public ResponseEntity<Student> createStudent(@Valid @RequestBody Student student, @PathVariable Long courseId){

    } */

    @DeleteMapping(value = "/{id}" , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> deleteStudent(@PathVariable Long id){
        try {
            studentService.deleteStudent(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
