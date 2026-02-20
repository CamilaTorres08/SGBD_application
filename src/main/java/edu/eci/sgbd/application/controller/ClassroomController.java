package edu.eci.sgbd.application.controller;

import edu.eci.sgbd.application.model.Classroom;
import edu.eci.sgbd.application.service.ClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/classrooms")
public class ClassroomController {

    private ClassroomService classroomService;

    @Autowired
    public ClassroomController(ClassroomService service) {
        this.classroomService = service;
    }

    @GetMapping
    public ResponseEntity<List<Classroom>> getAll() {
        return ResponseEntity.ok(classroomService.getAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Classroom> get(@PathVariable int id) {
        return ResponseEntity.ok(classroomService.getClassroom(id));
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody Classroom classroom) {
        classroomService.create(classroom);
        return ResponseEntity.ok("Classroom created successfully");
    }

    @PutMapping("{id}")
    public ResponseEntity<String> update(@PathVariable int id, @RequestBody Classroom classroom) {
        classroomService.update(id, classroom);
        return ResponseEntity.ok("Classroom updated successfully");
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        classroomService.delete(id);
        return ResponseEntity.ok("Classroom deleted successfully");
    }
}
