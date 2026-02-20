package edu.eci.sgbd.application.repository;

import edu.eci.sgbd.application.model.Enrollment;

import java.util.List;
import java.util.Optional;

public interface EnrollmentRepository {
    void save(Enrollment enrollment);

    void update(int id, Enrollment enrollment);

    List<Enrollment> findAll();

    Optional<Enrollment> findById(int id);

    void deleteById(int id);

    List<Enrollment> findByStudentId(int studentId);

    List<Enrollment> findBySubjectId(int subjectId);

    List<Enrollment> findByClassroomId(int classroomId);
}
