package com.example.course.services;

import com.example.course.entitys.Student;
import com.example.course.exceptions.StudentNotFoundException;
import com.example.course.repositories.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@AllArgsConstructor
@Service
public class StudentServiceImpl implements PersonService<Student> {
    private StudentRepository studentRepository;

    @Override
    public Student getPerson(Long id) {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isPresent()) {
            return student.get();
        }
        throw new StudentNotFoundException(id);
    }

    @Override
    public void save(Student person) {
        studentRepository.save(person);
    }

    @Override
    public void deletePerson(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public List<Student> getAllPersons() {
        return studentRepository.findAll();
    }
}
