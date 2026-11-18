package org.example.entity;

public class UserEntity {
    private int id;
    private String email;
    private boolean active;

    public UserEntity() {
    }

    public UserEntity(int id, String email, boolean active) {
        this.id = id;
        this.email = email;
        this.active = active;
    }

    public UserEntity(String email, boolean active) {
        this.email = email;
        this.active = active;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
