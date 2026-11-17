package org.example.repository.dto;

public class StudentProfileDTO {
    private int id;
    private int studentId;
    private String bio;

    public StudentProfileDTO() {
    }

    public StudentProfileDTO(int id, int studentId, String bio) {
        this.id = id;
        this.studentId = studentId;
        this.bio = bio;
    }

    public StudentProfileDTO(int studentId, String bio) {
        this.studentId = studentId;
        this.bio = bio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}
