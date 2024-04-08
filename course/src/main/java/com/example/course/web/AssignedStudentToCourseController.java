package com.example.course.web;

import com.example.course.entitys.Student;
import com.example.course.services.AssignedStudentToCourseService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController()
@RequestMapping("/assignment")
public class AssignedStudentToCourseController {

    AssignedStudentToCourseService assignment;

    @PostMapping("/student/{studentId}/course/{courseId}")
    public ResponseEntity<HttpStatus> assignStudentToCourse(@PathVariable Long studentId, @PathVariable Long courseId) {
        boolean result = assignment.assignStudentToCourse(studentId, courseId);
        if(result) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/student/{studentId}/course/{courseId}" )
    public ResponseEntity<HttpStatus> unassignStudentFromCourse(@PathVariable Long studentId, @PathVariable Long courseId) {
        boolean result = assignment.unassignedStudentFromCourse(studentId, courseId);
        if(result) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<Student>> retrieveStudentsFromCourse(@PathVariable Long courseId) {
        Optional<List<Student>> result = assignment.getStudentAtCourse(courseId);
        return result.map(assignedStudentToCourses -> new ResponseEntity<>(assignedStudentToCourses, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

}
