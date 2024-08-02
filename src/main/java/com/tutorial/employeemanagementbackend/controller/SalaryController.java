package com.tutorial.employeemanagementbackend.controller;

import com.tutorial.employeemanagementbackend.model.Salary;
import com.tutorial.employeemanagementbackend.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/salary")
@CrossOrigin("*")
public class SalaryController {

    @Autowired
    private SalaryService salaryService;

    @PostMapping
    public Salary saveSalary(@RequestBody Salary salary) {
        return salaryService.saveSalary(salary);
    }

    @GetMapping
    public List<Salary> getAllEmployee() {
        return salaryService.getAllSalary();
    }

    @GetMapping("/{id}")
    public Optional<Salary> getSalaryById(@PathVariable int id) {
        return salaryService.getSalaryById(id);
    }

    @PutMapping("/{id}")
    public void updateSalary(@PathVariable int id, @RequestBody Salary salary) { salaryService.updateSalary(id, salary); }

    @DeleteMapping("/{id}")
    public void deleteSalary(@PathVariable int id) {
        salaryService.deleteSalary(id);
    }
}