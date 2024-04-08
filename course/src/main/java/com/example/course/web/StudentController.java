package com.example.course.web;

import com.example.course.entitys.Student;
import com.example.course.exceptions.StudentNotFoundException;
import com.example.course.services.PersonService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@AllArgsConstructor
@RestController()
@RequestMapping("/student")
public class StudentController {
    PersonService<Student> studentService;
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable Long id){
        try {
            return new ResponseEntity<>(studentService.getPerson(id), HttpStatus.OK);
        } catch (StudentNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Student>> getStudents(){
        return new ResponseEntity<>(studentService.getAllPersons(), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<HttpStatus> createStudent(@Valid @RequestBody Student student){
        studentService.save(student);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("{id}/courses")
    public ResponseEntity<HttpStatus> AssignToCourse(@PathVariable Long id, @Valid @RequestBody List<Long> courses){

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteStudent(@PathVariable Long id){
        try {
            studentService.deletePerson(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (StudentNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
