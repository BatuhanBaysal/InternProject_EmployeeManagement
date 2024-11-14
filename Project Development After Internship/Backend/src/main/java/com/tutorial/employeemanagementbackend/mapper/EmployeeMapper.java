package com.tutorial.employeemanagementbackend.mapper;

import com.tutorial.employeemanagementbackend.dto.EmployeeDTO;
import com.tutorial.employeemanagementbackend.entity.Employee;
import com.tutorial.employeemanagementbackend.entity.Salary;

public class EmployeeMapper {
    // By transforming data between Entity and DTO, Mapper ensures that database objects are transmitted in a secure, optimized and client-friendly format.
    public static EmployeeDTO mapToEmployeeDTO(Employee employee) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(employee.getId());
        employeeDTO.setFirstName(employee.getFirstName());
        employeeDTO.setLastName(employee.getLastName());
        employeeDTO.setEmail(employee.getEmail());

        if (employee.getSalary() != null) {
            employeeDTO.setSalary(SalaryMapper.mapToSalaryDTO(employee.getSalary()));
        }

        return employeeDTO;
    }

    public static Employee mapToEmployeeEntity(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setId(employeeDTO.getId());
        employee.setFirstName(employeeDTO.getFirstName());
        employee.setLastName(employeeDTO.getLastName());
        employee.setEmail(employeeDTO.getEmail());

        if (employeeDTO.getSalary() != null) {
            employee.setSalary(new Salary());
        }

        return employee;
    }
}