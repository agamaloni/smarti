package com.example.course.web;

import com.example.course.entitys.Course;
import com.example.course.exceptions.CourseNotFoundException;
import com.example.course.services.CourseService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController()
@RequestMapping("/course")
public class CourseController {
    private CourseService courseService;

    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourse(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(courseService.getCourse(id), HttpStatus.OK);
        }catch (CourseNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Course>> getCourses() {
        return new ResponseEntity<>(courseService.getCourses(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> saveCourse(@Valid @RequestBody Course course) {
        courseService.saveCourse(course);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
