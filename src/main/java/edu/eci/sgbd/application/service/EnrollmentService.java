package edu.eci.sgbd.application.service;

import edu.eci.sgbd.application.exception.NotFoundException;
import edu.eci.sgbd.application.model.Enrollment;
import edu.eci.sgbd.application.repository.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnrollmentService {
    private final EnrollmentRepository repo;

    @Autowired
    public EnrollmentService(EnrollmentRepository repo) {
        this.repo = repo;
    }

    public List<Enrollment> getAll() {
        return repo.findAll();
    }

    public Enrollment getEnrollment(int id) {
        Optional<Enrollment> enrollment = repo.findById(id);
        if (enrollment.isPresent()) {
            return enrollment.get();
        }
        throw new NotFoundException("Enrollment not found.");
    }

    public List<Enrollment> getByStudentId(int studentId) {
        return repo.findByStudentId(studentId);
    }

    public List<Enrollment> getBySubjectId(int subjectId) {
        return repo.findBySubjectId(subjectId);
    }

    public List<Enrollment> getByClassroomId(int classroomId) {
        return repo.findByClassroomId(classroomId);
    }

    public void create(Enrollment enrollment) {
        repo.save(enrollment);
    }

    public void update(int id, Enrollment e) {
        getEnrollment(id);
        repo.update(id, e);
    }

    public void delete(int id) {
        getEnrollment(id);
        repo.deleteById(id);
    }
}
