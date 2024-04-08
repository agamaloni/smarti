package com.example.course.services;

import com.example.course.entitys.Teacher;
import com.example.course.exceptions.TeacherNotFoundException;
import com.example.course.repositories.TeacherRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class TeacherServiceImpl implements PersonService<Teacher> {

    private TeacherRepository teacherRepository;

    @Override
    public Teacher getPerson(Long id) {
        Optional<Teacher> teacher = teacherRepository.findById(id);
        if (teacher.isPresent()) {
            return teacher.get();
        }
        throw new TeacherNotFoundException(id);
    }

    @Override
    public void save(Teacher person) {
        teacherRepository.save(person);
    }

    @Override
    public void deletePerson(Long id) {
        Optional<Teacher> teacher = teacherRepository.findById(id);
        if (teacher.isPresent()) {
            teacherRepository.deleteById(id);
        } else {
            throw new TeacherNotFoundException(id);
        }
    }

    @Override
    public List<Teacher> getAllPersons() {
        return teacherRepository.findAll();
    }
}
