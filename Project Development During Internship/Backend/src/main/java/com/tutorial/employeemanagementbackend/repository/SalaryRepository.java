package com.tutorial.employeemanagementbackend.repository;

import com.tutorial.employeemanagementbackend.model.Salary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalaryRepository extends JpaRepository<Salary, Integer> {
}
