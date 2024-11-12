package com.tutorial.employeemanagementbackend.service;

import com.tutorial.employeemanagementbackend.model.Salary;
import com.tutorial.employeemanagementbackend.repository.SalaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SalaryService implements SalaryServiceInterface {
    @Autowired
    private SalaryRepository salaryRepository;

    @Override
    public Salary saveSalary(Salary salary) { return salaryRepository.save(salary); }

    @Override
    public Optional<Salary> getSalaryById(int id) { return salaryRepository.findById(id); }

    @Override
    public List<Salary> getAllSalary() { return salaryRepository.findAll(Sort.by(Sort.Direction.DESC, "id")); }

    @Override
    public Salary updateSalary(int id, Salary salary) {
        Salary salaryToUpdate = salaryRepository.findById(id).orElseThrow();
        salaryToUpdate.setSalary(salary.getSalary());
        salaryToUpdate.setStartDate(salary.getStartDate());
        salaryToUpdate.setFinishDate(salary.getFinishDate());
        return salaryRepository.save(salaryToUpdate);
    }

    @Override
    public void deleteSalary(int id) {
        salaryRepository.deleteById(id);
    }
}