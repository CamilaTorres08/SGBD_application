package edu.eci.sgbd.application.service;

import edu.eci.sgbd.application.exception.ConflictException;
import edu.eci.sgbd.application.exception.NotFoundException;
import edu.eci.sgbd.application.model.Student;
import edu.eci.sgbd.application.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository repo;

    @Autowired
    public StudentService(StudentRepository repo){
        this.repo = repo;
    }

    public List<Student> getAll(){
        return repo.findAll();
    }

    public Student getStudent(int id){
        Optional<Student> student = repo.findById(id);
        if(student.isPresent()){
            return student.get();
        }
        throw new NotFoundException("Student not found.");
    }

    public void create(Student student){
        boolean exist = repo.findByEmail(student.getEmail()).isPresent();
        if(exist){
            throw new ConflictException("Student already exist");
        }
        repo.save(student);
    }

    public void update(int id, Student e){
        getStudent(id);
        repo.update(id, e);
    }

    public void delete(int id){
        getStudent(id);
        repo.deleteById(id);
    }
}
