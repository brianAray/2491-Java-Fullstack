package org.example.repository;

import org.example.repository.dto.DepartmentDTO;
import org.example.util.DatabaseConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDAO {
    //CRUD
    // CREATE
    public int create(DepartmentDTO department) throws SQLException{
        String sql = "INSERT INTO departments (name) VALUES (?) RETURNING id;";
        try (Connection conn = DatabaseConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, department.getName());
            try (ResultSet rs = ps.executeQuery()){
                if (rs.next()){
                    int id = rs.getInt(1);
                    department.setId(id);
                    return id;
                }
            }throw new SQLException("Creating department failed, no ID Obtained");
        }
    }

    // READ (find by id)
    public DepartmentDTO findById(int id) throws SQLException{
        String sql = "SELECT id, name FROM departments WHERE id = ?;";
        try (Connection conn = DatabaseConnectionFactory.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()){
                if (rs.next()){
                    return new DepartmentDTO(rs.getInt("id"), rs.getString("name)"));
                }
                return null;
            }
        }
    }

    public List<DepartmentDTO> findAll() throws SQLException {
        List<DepartmentDTO> list = new ArrayList<>();
        String sql = "SELECT id, name FROM departments ORDER BY id";
        try (Connection conn = DatabaseConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new DepartmentDTO(rs.getInt("id"), rs.getString("name")));
            }
        }
        return list;
    }

    // UPDATE
    public boolean update(DepartmentDTO department) throws SQLException {
        String sql = "UPDATE departments SET name = ? WHERE id = ?";
        try (Connection conn = DatabaseConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, department.getName());
            ps.setInt(2, department.getId());
            return ps.executeUpdate() > 0;
        }
    }

    // DELETE
    public boolean delete(int id) throws SQLException {
        String sql = "DELETE FROM departments WHERE id = ?";
        try (Connection conn = DatabaseConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }
}
