package com.tutorial.employeemanagementbackend.repository;

import com.tutorial.employeemanagementbackend.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository // is a Spring annotation that defines the data access layer for managing database operations.
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByFirstNameAndLastName(String firstName, String lastName);
}