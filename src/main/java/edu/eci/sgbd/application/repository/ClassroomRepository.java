package edu.eci.sgbd.application.repository;

import edu.eci.sgbd.application.model.Classroom;

import java.util.List;
import java.util.Optional;

public interface ClassroomRepository {
    void save(Classroom classroom);

    void update(int id, Classroom classroom);

    List<Classroom> findAll();

    Optional<Classroom> findById(int id);

    void deleteById(int id);
}
