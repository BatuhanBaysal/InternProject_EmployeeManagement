package com.tutorial.employeemanagementbackend.service.ServiceInterface;

import com.tutorial.employeemanagementbackend.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeServiceInterface {
    EmployeeDTO saveEmployee(EmployeeDTO employeeDTO);
    List<EmployeeDTO> getAllEmployees();
    EmployeeDTO getEmployeeById(Long id);
    EmployeeDTO updateEmployee(EmployeeDTO employeeDTO);
    void deleteEmployee(Long id);

    // Performs a search based on the employee's first name (firstName) and last name (lastName).
    List<EmployeeDTO> findEmployeeByFirstNameAndLastName(String firstName, String lastName);
    // It performs a search based on both first name (firstName), last name (lastName) and email address (email) criteria to further filter employee information.
    List<EmployeeDTO> findEmployeeByCriteria(String firstName, String lastName, String email);
}