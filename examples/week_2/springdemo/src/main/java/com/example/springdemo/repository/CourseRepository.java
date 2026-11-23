package com.example.springdemo.repository;

import com.example.springdemo.repository.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Integer> {
}
