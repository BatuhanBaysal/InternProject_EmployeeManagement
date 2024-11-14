package com.tutorial.employeemanagementbackend.dto;

import lombok.Data;

@Data
public class SalaryDTO {
    // Wrapper classes provide additional functionality such as collections, null value support, and helper methods because they allow the underlying data types to be used as objects.
    private Long id;
    private Double salaries;
    private String startDate;
    private String finishDate;
}