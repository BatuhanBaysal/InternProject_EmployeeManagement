package com.tutorial.employeemanagementbackend.repository;

import com.tutorial.employeemanagementbackend.entity.Salary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // is a Spring annotation that defines the data access layer for managing database operations.
public interface SalaryRepository extends JpaRepository<Salary, Long> {

}