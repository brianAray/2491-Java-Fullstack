package com.example.springdemo.repository;

import com.example.springdemo.repository.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Integer> {

    @Query("""
        SELECT s from Student s
        JOIN FETCH s.department
        LEFT JOIN FETCH s.profile
        LEFT JOIN FETCH s.courses
        WHERE s.id = :id
    """)
    Optional<Student> findByIdWithDetails(@Param("id") Integer id);
}
