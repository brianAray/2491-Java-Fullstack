package com.example.springdemo.controller.dto;

import java.util.ArrayList;
import java.util.List;

public class StudentResponse {

    private Integer id;
    private String name;
    private String departmentName;
    private String bio;
    private List<String> enrolledCourseTitles = new ArrayList<>();

    public StudentResponse() {
    }

    public StudentResponse(Integer id, String name, String departmentName, String bio, List<String> enrolledCourseTitles) {
        this.id = id;
        this.name = name;
        this.departmentName = departmentName;
        this.bio = bio;
        this.enrolledCourseTitles = enrolledCourseTitles != null ? enrolledCourseTitles : new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public List<String> getEnrolledCourseTitles() {
        return enrolledCourseTitles;
    }

    public void setEnrolledCourseTitles(List<String> enrolledCourseTitles) {
        this.enrolledCourseTitles = enrolledCourseTitles;
    }
}
