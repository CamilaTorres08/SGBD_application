package edu.eci.sgbd.application.repository;

import edu.eci.sgbd.application.model.Subject;

import java.util.List;
import java.util.Optional;

public interface SubjectRepository {
    void save(Subject subject);
    void update(int id, Subject e);
    List<Subject> findAll();
    Optional<Subject> findById(int id);
    void deleteById(int id);
}
