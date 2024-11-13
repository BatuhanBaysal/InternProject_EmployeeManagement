package com.tutorial.employeemanagementbackend.dto;

import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class EmployeeDTO {
    // Wrapper classes provide additional functionality such as collections, null value support, and helper methods because they allow the underlying data types to be used as objects.
    private Long id;
    private String firstName;
    private String lastName;
    @Email // Email format.
    private String email;
    private SalaryDTO salary; // When entering employee information, we call from salaryDTO for salary information
}