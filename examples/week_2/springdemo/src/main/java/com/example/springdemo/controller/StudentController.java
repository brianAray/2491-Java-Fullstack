package com.example.springdemo.controller;

import com.example.springdemo.controller.dto.CreateStudentRequest;
import com.example.springdemo.controller.dto.StudentResponse;
import com.example.springdemo.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<StudentResponse> createStudent(@Valid @RequestBody CreateStudentRequest request){
        StudentResponse response = studentService.registerStudent(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponse> getStudent(@PathVariable Integer id){
        return ResponseEntity.ok(studentService.getStudent(id));
    }

    @PostMapping("/{studentId}/courses/{courseId}")
    public ResponseEntity<Void> enrollCourse(@PathVariable Integer studentId, @PathVariable Integer courseId){
        studentService.enrollCourse(studentId, courseId);
        return ResponseEntity.noContent().build();
    }

}
