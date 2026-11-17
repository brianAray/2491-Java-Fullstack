package org.example.repository;

import org.example.repository.dto.CourseDTO;
import org.example.repository.dto.StudentDTO;
import org.example.util.DatabaseConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    // CRUD

    public int create(StudentDTO student) throws SQLException {
        String sql = "INSERT INTO students (name, department_id) VALUES (?, ?) RETURNING id";
        try (Connection conn = DatabaseConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, student.getName());
            ps.setInt(2, student.getDepartmentId());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt(1);
                    student.setId(id);
                    return id;
                }
                throw new SQLException("Creating student failed, no ID obtained.");
            }
        }
    }

    public StudentDTO findById(int id) throws SQLException {
        String sql = "SELECT id, name, department_id FROM students WHERE id = ?";
        try (Connection conn = DatabaseConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new StudentDTO(rs.getInt("id"), rs.getString("name"), rs.getInt("department_id"));
                }
                return null;
            }
        }
    }

    public List<StudentDTO> findAll() throws SQLException {
        List<StudentDTO> list = new ArrayList<>();
        String sql = "SELECT id, name, department_id FROM students ORDER BY id";
        try (Connection conn = DatabaseConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new StudentDTO(rs.getInt("id"), rs.getString("name"), rs.getInt("department_id")));
            }
        }
        return list;
    }

    public boolean update(StudentDTO student) throws SQLException {
        String sql = "UPDATE students SET name = ?, department_id = ? WHERE id = ?";
        try (Connection conn = DatabaseConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, student.getName());
            ps.setInt(2, student.getDepartmentId());
            ps.setInt(3, student.getId());
            return ps.executeUpdate() > 0;
        }
    }

    public boolean delete(int id) throws SQLException {
        String sql = "DELETE FROM students WHERE id = ?";
        try (Connection conn = DatabaseConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    // --- Many-to-Many Junction Operations (student_courses) ---

    public void enrollCourse(int studentId, int courseId) throws SQLException {
        String sql = "INSERT INTO student_courses (student_id, course_id) VALUES (?, ?) ON CONFLICT DO NOTHING";
        try (Connection conn = DatabaseConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, studentId);
            ps.setInt(2, courseId);
            ps.executeUpdate();
        }
    }

    public boolean unenrollCourse(int studentId, int courseId) throws SQLException {
        String sql = "DELETE FROM student_courses WHERE student_id = ? AND course_id = ?";
        try (Connection conn = DatabaseConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, studentId);
            ps.setInt(2, courseId);
            return ps.executeUpdate() > 0;
        }
    }

    public List<CourseDTO> getEnrolledCourses(int studentId) throws SQLException {
        List<CourseDTO> list = new ArrayList<>();
        String sql = """
            SELECT c.id, c.title, c.credits
            FROM courses c
            JOIN student_courses sc ON c.id = sc.course_id
            WHERE sc.student_id = ?
            ORDER BY c.id
        """;
        try (Connection conn = DatabaseConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, studentId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new CourseDTO(rs.getInt("id"), rs.getString("title"), rs.getInt("credits")));
                }
            }
        }
        return list;
    }
}
