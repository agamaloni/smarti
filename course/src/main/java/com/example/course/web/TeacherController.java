package com.example.course.web;

import com.example.course.entitys.Teacher;
import com.example.course.exceptions.TeacherNotFoundException;
import com.example.course.services.PersonService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController()
@RequestMapping("/teacher")
public class TeacherController {
    PersonService<Teacher> teacherService;

    @GetMapping("/all")
    public List<Teacher> getAllTeachers() {
        return teacherService.getAllPersons();
    }

    @GetMapping("/{id}")
    public Teacher getTeacher(@PathVariable Long id) {
        return teacherService.getPerson(id);
    }

    @PostMapping()
    public ResponseEntity<HttpStatus> createTeacher(@Valid Teacher teacher) {
         teacherService.save(teacher);
         return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteTeacher(@PathVariable Long id) {
        try {
            teacherService.deletePerson(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (TeacherNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
