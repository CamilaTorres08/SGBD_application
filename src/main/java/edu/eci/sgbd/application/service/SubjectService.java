package edu.eci.sgbd.application.service;

import edu.eci.sgbd.application.exception.NotFoundException;
import edu.eci.sgbd.application.model.Subject;
import edu.eci.sgbd.application.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubjectService {
    private final SubjectRepository repo;

    @Autowired
    public SubjectService(SubjectRepository repo){
        this.repo = repo;
    }

    public List<Subject> getAll(){
        return repo.findAll();
    }

    public Subject getSubject(int id){
        Optional<Subject> subject = repo.findById(id);
        if(subject.isPresent()){
            return subject.get();
        }
        throw new NotFoundException("Subject not found.");
    }

    public void create(Subject subject){
        repo.save(subject);
    }

    public void update(int id, Subject s){
        getSubject(id);
        repo.update(id, s);
    }

    public void delete(int id){
        getSubject(id);
        repo.deleteById(id);
    }
}
