package com.example.course.services;

import com.example.course.entitys.AssignedStudentToCourse;
import com.example.course.entitys.Course;
import com.example.course.entitys.Student;
import com.example.course.exceptions.CourseNotFoundException;
import com.example.course.exceptions.StudentNotFoundException;
import com.example.course.repositories.AssignedStudentToCourseRepository;
import com.example.course.repositories.CourseRepository;
import com.example.course.repositories.StudentRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class AssignedStudentToCourseServiceImpl implements AssignedStudentToCourseService {
    private StudentRepository studentRepository;
    private CourseRepository courseRepository;
    private AssignedStudentToCourseRepository assignedStudentToCourseRepository;


    @Override
    public boolean assignStudentToCourse(Long studentId, Long courseId) {
        Optional<Student> studentOptional = studentRepository.findById(studentId);
        Optional<Course> courseOptional = courseRepository.findById(courseId);

        if (studentOptional.isPresent() && courseOptional.isPresent()) {
            Student student = studentOptional.get();
            Course course = courseOptional.get();

            courseRepository.save(course);
            studentRepository.save(student);

            return true;
        } else {
            return false;
        }
    }

    @Override
    public Optional<List<Student>> getStudentAtCourse(Long courseId) {
        List<AssignedStudentToCourse> assignedStudentsToCourse = assignedStudentToCourseRepository.findAllByCourseId(courseId)
                .orElseThrow(() -> new CourseNotFoundException(courseId));

        List<Student> studentsAtCourse = new ArrayList<>();

        for (AssignedStudentToCourse assigned : assignedStudentsToCourse) {
            Long studentId = assigned.getStudentId();
            studentsAtCourse.add(studentRepository.findById(assigned.getStudentId())
                    .orElseThrow(() -> new StudentNotFoundException(studentId)));
        }
        return Optional.of(studentsAtCourse);
    }

    @Override
    @Transactional
    public boolean unassignedStudentFromCourse(Long studentId, Long courseId) {
        Optional<Student> studentOptional = studentRepository.findById(studentId);
        Optional<Course> courseOptional = courseRepository.findById(courseId);

        if (studentOptional.isPresent() && courseOptional.isPresent()) {
            Student student = studentOptional.get();
            Course course = courseOptional.get();

            course.getAssignedStudents().remove(student);
            student.getAssignedCourses().remove(course);

            courseRepository.save(course);
            studentRepository.save(student);

            return true;
        } else {
            return false;
        }
    }
}

