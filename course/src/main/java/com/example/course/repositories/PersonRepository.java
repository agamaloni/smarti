package com.example.course.repositories;

import com.example.course.entitys.Person;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Optional;
@NoRepositoryBean
public interface PersonRepository<T extends Person> extends CrudRepository<T ,Long> {
    Optional<T> findById(Long id);
    <S extends T> S save(S person);
    List<T> findAll();
    @Transactional
    void deleteById(Long id);
}
