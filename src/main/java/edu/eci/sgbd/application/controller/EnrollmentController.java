package edu.eci.sgbd.application.controller;

import edu.eci.sgbd.application.model.Enrollment;
import edu.eci.sgbd.application.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {

    private EnrollmentService enrollmentService;

    @Autowired
    public EnrollmentController(EnrollmentService service) {
        this.enrollmentService = service;
    }

    @GetMapping
    public ResponseEntity<List<Enrollment>> getAll() {
        return ResponseEntity.ok(enrollmentService.getAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Enrollment> get(@PathVariable int id) {
        return ResponseEntity.ok(enrollmentService.getEnrollment(id));
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<Enrollment>> getByStudent(@PathVariable int studentId) {
        return ResponseEntity.ok(enrollmentService.getByStudentId(studentId));
    }

    @GetMapping("/subject/{subjectId}")
    public ResponseEntity<List<Enrollment>> getBySubject(@PathVariable int subjectId) {
        return ResponseEntity.ok(enrollmentService.getBySubjectId(subjectId));
    }

    @GetMapping("/classroom/{classroomId}")
    public ResponseEntity<List<Enrollment>> getByClassroom(@PathVariable int classroomId) {
        return ResponseEntity.ok(enrollmentService.getByClassroomId(classroomId));
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody Enrollment enrollment) {
        enrollmentService.create(enrollment);
        return ResponseEntity.ok("Enrollment created successfully");
    }

    @PutMapping("{id}")
    public ResponseEntity<String> update(@PathVariable int id, @RequestBody Enrollment enrollment) {
        enrollmentService.update(id, enrollment);
        return ResponseEntity.ok("Enrollment updated successfully");
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        enrollmentService.delete(id);
        return ResponseEntity.ok("Enrollment deleted successfully");
    }
}
