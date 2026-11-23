package com.example.springdemo.service;

import com.example.springdemo.controller.dto.CreateStudentRequest;
import com.example.springdemo.controller.dto.StudentResponse;
import com.example.springdemo.repository.CourseRepository;
import com.example.springdemo.repository.DepartmentRepository;
import com.example.springdemo.repository.StudentRepository;
import com.example.springdemo.repository.entity.Course;
import com.example.springdemo.repository.entity.Department;
import com.example.springdemo.repository.entity.Student;
import com.example.springdemo.repository.entity.StudentProfile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
public class StudentService {

    private final StudentRepository studentRepo;
    private final DepartmentRepository departmentRepo;
    private final CourseRepository courseRepo;

    public StudentService(StudentRepository studentRepo, DepartmentRepository departmentRepo, CourseRepository courseRepo) {
        this.studentRepo = studentRepo;
        this.departmentRepo = departmentRepo;
        this.courseRepo = courseRepo;
    }

    @Transactional
    public StudentResponse registerStudent(CreateStudentRequest request){
        Department department = departmentRepo.findById(request.getDepartmentId())
                .orElseThrow(() -> new IllegalArgumentException("Department not found: " + request.getDepartmentId()));

        Student student = new Student();
        student.setName(request.getName());
        student.setDepartment(department);

        if (request.getBio() != null && !request.getBio().isBlank()){
            StudentProfile profile = new StudentProfile();
            profile.setBio(request.getBio());
            student.setProfile(profile);
        }

        Student saved = studentRepo.save(student);

        return new StudentResponse(
                saved.getId(),
                saved.getName(),
                department.getName(),
                saved.getProfile() != null ? saved.getProfile().getBio() : null,
                Collections.emptyList()
        );
    }

    @Transactional
    public void enrollCourse(Integer studentId, Integer courseId){
        Student student = studentRepo.findByIdWithDetails(studentId)
                .orElseThrow(() -> new IllegalArgumentException("Student not found: " + studentId));

        Course course = courseRepo.findById(courseId)
                .orElseThrow(() -> new IllegalArgumentException("Course not found: " + courseId));

        student.enrollCourse(course);
        studentRepo.save(student);
    }

    @Transactional(readOnly = true)
    public StudentResponse getStudent(Integer id){
        Student student = studentRepo.findByIdWithDetails(id)
                .orElseThrow(() -> new IllegalArgumentException("Student not found: " + id));

        return new StudentResponse(
                student.getId(),
                student.getName(),
                student.getDepartment().getName(),
                student.getProfile() != null ? student.getProfile().getBio() : null,
                student.getCourses().stream().map(Course::getTitle).toList()
        );
    }
}
