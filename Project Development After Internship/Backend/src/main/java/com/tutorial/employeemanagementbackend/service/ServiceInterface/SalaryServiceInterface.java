package com.tutorial.employeemanagementbackend.service.ServiceInterface;

import com.tutorial.employeemanagementbackend.dto.SalaryDTO;

import java.util.List;

public interface SalaryServiceInterface {
    SalaryDTO saveSalary(SalaryDTO salaryDTO);
    List<SalaryDTO> getAllSalaries();
    SalaryDTO getSalaryById(Long id);
    SalaryDTO updateSalary(SalaryDTO salaryDTO);
    void deleteSalary(Long id);
}