package com.tutorial.employeemanagementbackend.service;

import com.tutorial.employeemanagementbackend.model.Salary;

import java.util.List;
import java.util.Optional;

public interface SalaryServiceInterface {
    public Salary saveSalary(Salary salary);
    public Optional<Salary> getSalaryById(int id);
    List<Salary> getAllSalary();
    Salary updateSalary(int id, Salary salary);
    void deleteSalary(int id);
}
