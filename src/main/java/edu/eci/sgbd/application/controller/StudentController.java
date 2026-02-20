package edu.eci.sgbd.application.controller;

import edu.eci.sgbd.application.model.Student;
import edu.eci.sgbd.application.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private StudentService studentService;

    @Autowired
    public StudentController(StudentService service){
        this.studentService = service;
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAll(){
        return ResponseEntity.ok(studentService.getAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Student> get(@PathVariable int id){
        return ResponseEntity.ok(studentService.getStudent(id));
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody Student student){
        studentService.create(student);
        return ResponseEntity.ok("Student created successfully");
    }

    @PutMapping("{id}")
    public ResponseEntity<String> update(@PathVariable int id, @RequestBody Student student){
        studentService.update(id,student);
        return ResponseEntity.ok("Student updated successfully");
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable int id){
        studentService.delete(id);
        return ResponseEntity.ok("Student deleted sucessfully");
    }

}
