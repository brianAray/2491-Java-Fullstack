package org.example.repository.dto;

public class CourseDTO {
    private int id;
    private String title;
    private int credits;

    public CourseDTO() {
    }

    public CourseDTO(int id, String title, int credits) {
        this.id = id;
        this.title = title;
        this.credits = credits;
    }

    public CourseDTO(String title, int credits) {
        this.title = title;
        this.credits = credits;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }
}
