package edu.eci.sgbd.application.controller;

import edu.eci.sgbd.application.DTO.SubjectDTO;
import edu.eci.sgbd.application.mapper.Mapper;
import edu.eci.sgbd.application.model.Subject;
import edu.eci.sgbd.application.service.SubjectService;
import jakarta.validation.Valid;
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
    public ResponseEntity<String> create(@Valid @RequestBody SubjectDTO s){
        Subject subject = Mapper.toSubject(s);
        subjectService.create(subject);
        return ResponseEntity.ok("Subject created successfully");
    }

    @PutMapping("{id}")
    public ResponseEntity<String> update(@PathVariable int id, @Valid @RequestBody SubjectDTO s){
        Subject subject = Mapper.toSubject(s);
        subjectService.update(id,subject);
        return ResponseEntity.ok("Subject updated successfully");
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable int id){
        subjectService.delete(id);
        return ResponseEntity.ok("Subject deleted sucessfully");
    }
}
