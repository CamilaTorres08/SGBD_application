package edu.eci.sgbd.application.repository;

import edu.eci.sgbd.application.model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentRepository {
    void save(Student student);
    void update(int id, Student e);
    List<Student> findAll();
    Optional<Student> findById(int id);
    void deleteById(int id);
    Optional<Student> findByEmail(String email);
}
