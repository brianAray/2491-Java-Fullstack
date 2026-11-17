package org.example.service;

import org.example.repository.DepartmentDAO;
import org.example.repository.dto.DepartmentDTO;

import java.sql.SQLException;

public class DepartmentService {
    private final DepartmentDAO departmentDAO = new DepartmentDAO();

    public DepartmentDTO createDepartment(String name) throws SQLException {
        DepartmentDTO departmentDTO = new DepartmentDTO(name);
        int id = departmentDAO.create(departmentDTO);
        departmentDTO.setId(id);
        return departmentDTO;
    }
}
