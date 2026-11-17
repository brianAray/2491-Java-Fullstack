package org.example.controller;

import org.example.repository.DepartmentDAO;
import org.example.repository.dto.DepartmentDTO;
import org.example.service.DepartmentService;

import java.sql.SQLException;
import java.util.Scanner;

public class DepartmentController {

    private final DepartmentService departmentService = new DepartmentService();
    private Scanner scanner;

    public DepartmentController(Scanner scanner){
        this.scanner = scanner;
    }
    public void handleCreateDepartment() {
        System.out.printf("Enter Department name:");
        String name = scanner.nextLine().trim();
        try{
            DepartmentDTO departmentDTO = departmentService.createDepartment(name);
            System.out.printf("Department created");
            System.out.printf(departmentDTO.toString());
        }catch(SQLException e){
            System.out.printf(e.getMessage());
        }
    }
}
