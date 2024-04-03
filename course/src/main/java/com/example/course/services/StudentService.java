package com.example.course.services;

import com.example.course.entitys.Student;
import com.example.course.exceptions.UserNotFoundException;
import com.example.course.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    StudentAtCourseService studentAtCourseService;
    @Autowired
    CourseService courseService;
    public Student getStudent(Long id) {
        return studentRepository.findByPersonId(id);
    }
    public void createStudent(Student student) {
        this.studentRepository.save(student);
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public void deleteStudent(Long id) {
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
        }else {
            throw new UserNotFoundException();
        }
    }
}
