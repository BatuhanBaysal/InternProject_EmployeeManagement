package com.tutorial.employeemanagementbackend.service.ServiceImpl;

import com.tutorial.employeemanagementbackend.dto.EmployeeDTO;
import com.tutorial.employeemanagementbackend.dto.SalaryDTO;
import com.tutorial.employeemanagementbackend.entity.Employee;
import com.tutorial.employeemanagementbackend.entity.Salary;
import com.tutorial.employeemanagementbackend.exception.ResourceNotFoundException;
import com.tutorial.employeemanagementbackend.mapper.EmployeeMapper;
import com.tutorial.employeemanagementbackend.mapper.SalaryMapper;
import com.tutorial.employeemanagementbackend.repository.EmployeeRepository;
import com.tutorial.employeemanagementbackend.repository.SalaryRepository;
import com.tutorial.employeemanagementbackend.service.ServiceInterface.EmployeeServiceInterface;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service // is a service layer annotation marked by Spring to manage business logic.
@AllArgsConstructor // Creates a constructor method for all fields in the class.
public class EmployeeServiceImpl implements EmployeeServiceInterface {
    private EmployeeRepository employeeRepository;
    private SalaryRepository salaryRepository;
    private SalaryServiceImpl salaryService;
    private EntityManager entityManager;

    private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);
    // logger.info: Records important events in the normal flow of the application.
    // logger.debug: Records detailed, debugging information.
    // logger.warn: Indicates potential problems or situations that need attention.

    @Override // indicates that a method is written to override a method defined in a superclass or interface.
    @Transactional // is an annotation used to maintain data consistency by treating all operations of a method or class as a whole, rolling back all changes when one of the operations fails.
    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) {
        logger.info("Started saving employee with ID: {}", employeeDTO.getId());

        SalaryDTO salaryDTO = employeeDTO.getSalary();
        logger.info("Mapping SalaryDTO to Salary entity for Employee ID: {}", employeeDTO.getId());
        Salary salary = SalaryMapper.mapToSalaryEntity(salaryDTO);
        salary = salaryRepository.save(salary);
        logger.info("Saved Salary entity for Employee ID: {}", employeeDTO.getId());

        logger.info("Mapping EmployeeDTO to Employee entity for Employee ID: {}", employeeDTO.getId());
        Employee employee = EmployeeMapper.mapToEmployeeEntity(employeeDTO);
        employee.setSalary(salary);
        employee = employeeRepository.save(employee);
        logger.info("Successfully saved Employee entity with ID: {}", employeeDTO.getId());

        return EmployeeMapper.mapToEmployeeDTO(employee);
    }

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        logger.info("Fetching all employees from the database.");
        List<Employee> employees = employeeRepository.findAll();

        if (employees.isEmpty()) {
            logger.info("No employees found in the database.");
        } else {
            logger.info("Found {} employees in the database.", employees.size());
        }

        return employees.stream()
                .map(EmployeeMapper::mapToEmployeeDTO)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDTO getEmployeeById(Long id) {
        // Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        // Employee employee = optionalEmployee.get();

        logger.info("Fetching employee with ID: {}", id);
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Employee with ID: {} not found", id);
                    return new ResourceNotFoundException("Employee", "ID", id);
                });

        logger.info("Employee with ID: {} successfully found.", id);
        return EmployeeMapper.mapToEmployeeDTO(employee);
    }

    @Override
    public EmployeeDTO updateEmployee(EmployeeDTO employeeDTO) {
        logger.info("Attempting to update employee with ID: {}", employeeDTO.getId());
        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeDTO.getId());

        // Employee employeeToUpdate = optionalEmployee.get();
        Employee employeeToUpdate = optionalEmployee.orElseThrow(
                () -> {
                    logger.warn("Employee with ID: {} not found", employeeDTO.getId());
                    return new ResourceNotFoundException("Employee", "ID", employeeDTO.getId());
                });

        logger.info("Updating employee with ID: {}", employeeDTO.getId());
        updateEmployeeEntityFromDTO(employeeToUpdate, employeeDTO);
        employeeRepository.save(employeeToUpdate);
        logger.info("Employee with ID: {} successfully updated.", employeeDTO.getId());
        return EmployeeMapper.mapToEmployeeDTO(employeeToUpdate);
    }

    @Override
    public void deleteEmployee(Long id) {
        logger.info("Attempting to delete employee with ID: {}", id);

        if (!employeeRepository.existsById(id)) {
            logger.error("Employee with ID: {} not found for deletion", id);
            throw new ResourceNotFoundException("Employee", "ID", id);
        }

        employeeRepository.deleteById(id);
        logger.info("Employee with ID: {} successfully deleted.", id);
    }

    @Override
    public List<EmployeeDTO> findEmployeeByFirstNameAndLastName(String firstName, String lastName) {
        logger.info("Searching for employees with firstName: '{}' and lastName: '{}'.", firstName, lastName);
        List<Employee> employees = employeeRepository.findByFirstNameAndLastName(firstName, lastName);

        if (employees.isEmpty()) {
            logger.info("No employees found with firstName: '{}' and lastName: '{}'.", firstName, lastName);
        } else {
            logger.info("Found {} employees with firstName: '{}' and lastName: '{}'.", employees.size(), firstName, lastName);
        }

        return employees.stream()
                .map(EmployeeMapper::mapToEmployeeDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<EmployeeDTO> findEmployeeByCriteria(String firstName, String lastName, String email) {
        logger.info("Building dynamic query for employees based on provided criteria.");
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);
        Root<Employee> employeeRoot = cq.from(Employee.class);
        List<Predicate> predicates = new ArrayList<>();

        if (firstName != null && !firstName.isEmpty()) {
            logger.info("Adding firstName filter: '{}'", firstName);
            predicates.add(cb.like(cb.lower(employeeRoot.get("firstName")),"%" + firstName.toLowerCase() + "%"));
        }

        if (lastName != null && !lastName.isEmpty()) {
            logger.info("Adding lastName filter: '{}'", lastName);
            predicates.add(cb.like(cb.lower(employeeRoot.get("lastName")),"%" + lastName.toLowerCase() + "%"));
        }

        if (email != null && !email.isEmpty()) {
            logger.info("Adding email filter: '{}'", email);
            predicates.add(cb.like(cb.lower(employeeRoot.get("email")),"%" + email.toLowerCase() + "%"));
        }

        cq.where(cb.and(predicates.toArray(new Predicate[0])));
        logger.info("Executing query with {} predicates.", predicates.size());
        List<Employee> result = entityManager.createQuery(cq).getResultList();

        if (result.isEmpty()) {
            logger.info("No employees found for the given criteria.");
        } else {
            logger.info("Found {} employees matching the criteria.", result.size());
        }

        return result.stream()
                .map(EmployeeMapper::mapToEmployeeDTO)
                .collect(Collectors.toList());
    }

    public void updateEmployeeEntityFromDTO(Employee employeeToUpdate, EmployeeDTO employeeDTO) {
        logger.info("Updating employee entity from DTO with ID: {}", employeeDTO.getId());

        if (employeeDTO.getFirstName() != null) {
            logger.info("Updating first name for employee ID: {}", employeeDTO.getId());
            employeeToUpdate.setFirstName(employeeDTO.getFirstName());
        }

        if (employeeDTO.getLastName() != null) {
            logger.info("Updating last name for employee ID: {}", employeeDTO.getId());
            employeeToUpdate.setLastName(employeeDTO.getLastName());
        }

        if (employeeDTO.getEmail() != null) {
            logger.info("Updating email for employee ID: {}", employeeDTO.getId());
            employeeToUpdate.setEmail(employeeDTO.getEmail());
        }

        if (employeeDTO.getSalary() != null) {
            logger.info("Updating salary for employee ID: {}", employeeDTO.getId());
            Salary salaryToUpdate;

            if (employeeToUpdate.getSalary() != null) {
                logger.debug("Found existing salary for employee ID: {}", employeeDTO.getId());
                salaryToUpdate = employeeToUpdate.getSalary();
            } else {
                logger.debug("No existing salary found. Creating new salary for employee ID: {}", employeeDTO.getId());
                salaryToUpdate = new Salary();
            }

            salaryService.UpdateSalaryEntityFromDTO(salaryToUpdate, employeeDTO.getSalary());
            logger.debug("Updated salary details for employee ID: {}", employeeDTO.getId());
            salaryRepository.save(salaryToUpdate);
            employeeToUpdate.setSalary(salaryToUpdate);
        }

        logger.info("Employee entity with ID: {} successfully updated from DTO.", employeeDTO.getId());
    }
}