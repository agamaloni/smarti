package com.example.course.services;
import java.util.List;

public interface PersonService<T>{

    T getPerson(Long id);
    void save(T person);
    void deletePerson(Long id);
    List<T> getAllPersons();
}
