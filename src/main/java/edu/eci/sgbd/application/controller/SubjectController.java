package edu.eci.sgbd.application.controller;

import edu.eci.sgbd.application.model.Subject;
import edu.eci.sgbd.application.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subjects")
public class SubjectController {
    private SubjectService subjectService;

    @Autowired
    public SubjectController(SubjectService service){
        this.subjectService = service;
    }

    @GetMapping
    public ResponseEntity<List<Subject>> getAll(){
        return ResponseEntity.ok(subjectService.getAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Subject> get(@PathVariable int id){
        return ResponseEntity.ok(subjectService.getSubject(id));
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody Subject subject){
        subjectService.create(subject);
        return ResponseEntity.ok("Subject created successfully");
    }

    @PutMapping("{id}")
    public ResponseEntity<String> update(@PathVariable int id, @RequestBody Subject subject){
        subjectService.update(id,subject);
        return ResponseEntity.ok("Subject updated successfully");
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable int id){
        subjectService.delete(id);
        return ResponseEntity.ok("Subject deleted sucessfully");
    }
}
