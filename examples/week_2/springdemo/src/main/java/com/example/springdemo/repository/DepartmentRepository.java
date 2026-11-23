package com.example.springdemo.repository;

import com.example.springdemo.repository.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
}
