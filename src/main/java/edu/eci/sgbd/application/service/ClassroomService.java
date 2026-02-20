package edu.eci.sgbd.application.service;

import edu.eci.sgbd.application.exception.NotFoundException;
import edu.eci.sgbd.application.model.Classroom;
import edu.eci.sgbd.application.repository.ClassroomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClassroomService {
    private final ClassroomRepository repo;

    @Autowired
    public ClassroomService(ClassroomRepository repo) {
        this.repo = repo;
    }

    public List<Classroom> getAll() {
        return repo.findAll();
    }

    public Classroom getClassroom(int id) {
        Optional<Classroom> classroom = repo.findById(id);
        if (classroom.isPresent()) {
            return classroom.get();
        }
        throw new NotFoundException("Classroom not found.");
    }

    public void create(Classroom classroom) {
        repo.save(classroom);
    }

    public void update(int id, Classroom c) {
        getClassroom(id);
        repo.update(id, c);
    }

    public void delete(int id) {
        getClassroom(id);
        repo.deleteById(id);
    }
}
