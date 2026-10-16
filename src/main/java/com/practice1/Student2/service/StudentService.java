package com.practice1.Student2.service;

import com.practice1.Student2.entity.Student;
import com.practice1.Student2.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    public Student getById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id " + id));
    }

    public Student updateById(Long id, Student newData) {
        Student existing = getById(id);
        existing.setName(newData.getName());
        existing.setAge(newData.getAge());
        existing.setEmail(newData.getEmail());
        existing.setCourse(newData.getCourse());
        return studentRepository.save(existing);
    }

    public void deleteById(Long id) {
        studentRepository.deleteById(id);
    }
}
