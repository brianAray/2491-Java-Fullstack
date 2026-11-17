package org.example;

import org.example.controller.DepartmentController;
import org.example.repository.CourseDAO;
import org.example.repository.DepartmentDAO;
import org.example.repository.StudentDAO;
import org.example.repository.StudentProfileDAO;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {


    private final Scanner scanner = new Scanner(System.in);

    private final DepartmentController departmentController = new DepartmentController(scanner);



    static void main() {
        new Main().start();
    }

    public void start() {
        boolean active = true;
        while (active) {
            System.out.printf("Database management system");
            System.out.printf("1. create department");
            System.out.printf("8. Exit");
            String input = scanner.nextLine().trim();

            switch (input) {
                case "1" -> departmentController.handleCreateDepartment();
                case "8" -> active = false;
                default -> System.out.printf("Invalid selection. Try again");
            }
        }
    }
}
