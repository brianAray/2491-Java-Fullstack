package org.example.repository;

import org.example.repository.dto.StudentProfileDTO;
import org.example.util.DatabaseConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentProfileDAO {
    // CRUD

    public int create(StudentProfileDTO profile) throws SQLException {
        String sql = "INSERT INTO student_profiles (student_id, bio) VALUES (?, ?) RETURNING id";
        try (Connection conn = DatabaseConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, profile.getStudentId());
            ps.setString(2, profile.getBio());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt(1);
                    profile.setId(id);
                    return id;
                }
                throw new SQLException("Creating profile failed, no ID obtained.");
            }
        }
    }

    public StudentProfileDTO findByStudentId(int studentId) throws SQLException {
        String sql = "SELECT id, student_id, bio FROM student_profiles WHERE student_id = ?";
        try (Connection conn = DatabaseConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, studentId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new StudentProfileDTO(rs.getInt("id"), rs.getInt("student_id"), rs.getString("bio"));
                }
                return null;
            }
        }
    }

    public boolean update(StudentProfileDTO profile) throws SQLException {
        String sql = "UPDATE student_profiles SET bio = ? WHERE student_id = ?";
        try (Connection conn = DatabaseConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, profile.getBio());
            ps.setInt(2, profile.getStudentId());
            return ps.executeUpdate() > 0;
        }
    }

    public boolean deleteByStudentId(int studentId) throws SQLException {
        String sql = "DELETE FROM student_profiles WHERE student_id = ?";
        try (Connection conn = DatabaseConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, studentId);
            return ps.executeUpdate() > 0;
        }
    }
}
