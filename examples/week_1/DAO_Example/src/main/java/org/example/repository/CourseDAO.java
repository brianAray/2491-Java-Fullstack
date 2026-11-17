package org.example.repository;

import org.example.repository.dto.CourseDTO;
import org.example.util.DatabaseConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseDAO {
    // CRUD
    public int create(CourseDTO course) throws SQLException {
        String sql = "INSERT INTO courses (title, credits) VALUES (?, ?) RETURNING id";
        try (Connection conn = DatabaseConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, course.getTitle());
            ps.setInt(2, course.getCredits());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt(1);
                    course.setId(id);
                    return id;
                }
                throw new SQLException("Creating course failed, no ID obtained.");
            }
        }
    }

    public CourseDTO findById(int id) throws SQLException {
        String sql = "SELECT id, title, credits FROM courses WHERE id = ?";
        try (Connection conn = DatabaseConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new CourseDTO(rs.getInt("id"), rs.getString("title"), rs.getInt("credits"));
                }
                return null;
            }
        }
    }

    public List<CourseDTO> findAll() throws SQLException {
        List<CourseDTO> list = new ArrayList<>();
        String sql = "SELECT id, title, credits FROM courses ORDER BY id";
        try (Connection conn = DatabaseConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new CourseDTO(rs.getInt("id"), rs.getString("title"), rs.getInt("credits")));
            }
        }
        return list;
    }

    public boolean update(CourseDTO course) throws SQLException {
        String sql = "UPDATE courses SET title = ?, credits = ? WHERE id = ?";
        try (Connection conn = DatabaseConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, course.getTitle());
            ps.setInt(2, course.getCredits());
            ps.setInt(3, course.getId());
            return ps.executeUpdate() > 0;
        }
    }

    public boolean delete(int id) throws SQLException {
        String sql = "DELETE FROM courses WHERE id = ?";
        try (Connection conn = DatabaseConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }
}
