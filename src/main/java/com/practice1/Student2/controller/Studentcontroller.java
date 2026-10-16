package com.practice1.Student2.controller;

import com.practice1.Student2.entity.Student;
import com.practice1.Student2.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class Studentcontroller {

    private final StudentService studentService;

    public Studentcontroller(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @GetMapping
    public List<Student> getAll() {
        return studentService.getAll();
    }

    @GetMapping("/{id}")
    public Student getById(@PathVariable Long id) {
        return studentService.getById(id);
    }

    @PutMapping("/{id}")
    public Student updateById(@PathVariable Long id, @RequestBody Student student) {
        return studentService.updateById(id, student);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        studentService.deleteById(id);
    }
}
