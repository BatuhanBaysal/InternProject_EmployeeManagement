package com.tutorial.employeemanagementbackend.mapper;

import com.tutorial.employeemanagementbackend.dto.SalaryDTO;
import com.tutorial.employeemanagementbackend.entity.Salary;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryMapper {
    // By transforming data between Entity and DTO, Mapper ensures that database objects are transmitted in a secure, optimized and client-friendly format.
    public static SalaryDTO mapToSalaryDTO(Salary salary) {
        SalaryDTO salaryDTO = new SalaryDTO();
        salaryDTO.setId(salary.getId());
        salaryDTO.setSalaries(salary.getSalaries());

        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE; // store LocalDate as String
        if (salary.getStartDate() != null) {
            salaryDTO.setStartDate(salary.getStartDate().format(formatter));
        }
        if (salary.getFinishDate() != null) {
            salaryDTO.setFinishDate(salary.getFinishDate().format(formatter));
        }

        return salaryDTO;
    }

    public static Salary mapToSalaryEntity(SalaryDTO salaryDTO) {
        Salary salary = new Salary();
        salary.setId(salaryDTO.getId());
        salary.setSalaries(salaryDTO.getSalaries());
        salary.setStartDate(LocalDate.parse(salaryDTO.getStartDate())); // map String from dto to LocalDate in entity

        if (salaryDTO.getFinishDate() != null) {
            salary.setFinishDate(LocalDate.parse(salaryDTO.getFinishDate()));
        }

        return salary;
    }
}