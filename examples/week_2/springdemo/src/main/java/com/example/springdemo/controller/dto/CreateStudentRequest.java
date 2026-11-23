package com.example.springdemo.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateStudentRequest {

    @NotBlank(message = "Name is required")
    private String name;

    @NotNull(message = "Department ID is required")
    private Integer departmentId;

    private String bio;

    public CreateStudentRequest() {
    }

    public CreateStudentRequest(String name, Integer departmentId, String bio) {
        this.name = name;
        this.departmentId = departmentId;
        this.bio = bio;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}
