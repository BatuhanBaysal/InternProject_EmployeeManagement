package com.tutorial.employeemanagementbackend.service.ServiceImpl;

import com.tutorial.employeemanagementbackend.dto.SalaryDTO;
import com.tutorial.employeemanagementbackend.entity.Salary;
import com.tutorial.employeemanagementbackend.exception.ResourceNotFoundException;
import com.tutorial.employeemanagementbackend.mapper.SalaryMapper;
import com.tutorial.employeemanagementbackend.repository.SalaryRepository;
import com.tutorial.employeemanagementbackend.service.ServiceInterface.SalaryServiceInterface;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service // is a service layer annotation marked by Spring to manage business logic.
@AllArgsConstructor // Creates a constructor method for all fields in the class.
public class SalaryServiceImpl implements SalaryServiceInterface {
    private SalaryRepository salaryRepository;

    private static final Logger logger = LoggerFactory.getLogger(SalaryServiceImpl.class);
    // logger.info: Records important events in the normal flow of the application.
    // logger.debug: Records detailed, debugging information.
    // logger.warn: Indicates potential problems or situations that need attention.

    @Override // indicates that a method is written to override a method defined in a superclass or interface.
    public SalaryDTO saveSalary(SalaryDTO salaryDTO) {
        logger.info("Attempting to save salary with ID: {}", salaryDTO.getId());
        Salary salary = SalaryMapper.mapToSalaryEntity(salaryDTO);
        logger.debug("Mapped Salary entity: {}", salary);
        salary = salaryRepository.save(salary);
        logger.info("Salary successfully saved with ID: {}", salary.getId());
        return SalaryMapper.mapToSalaryDTO(salary);
    }

    @Override
    public List<SalaryDTO> getAllSalaries() {
        logger.info("getAllSalaries method started");
        List<Salary> salaries = salaryRepository.findAll();

        if (salaries.isEmpty()) {
            logger.info("No salaries found in the database.");
        } else {
            logger.info("Fetched {} salary records from the database.", salaries.size());
        }

        logger.info("Mapping Salary entities to SalaryDTO objects");
        return salaries.stream()
                .map(SalaryMapper::mapToSalaryDTO)
                .collect(Collectors.toList());
    }

    @Override
    public SalaryDTO getSalaryById(Long id) {
        // Optional<Salary> optionalSalaryId = salaryRepository.findById(id);
        // Salary salary = optionalSalaryId.get();

        logger.info("getSalaryById method started for salary ID: {}", id);
        Salary salary = salaryRepository.findById(id)
                .orElseThrow(
                    () -> {
                        logger.error("Salary with ID: {} not found", id);
                        return new ResourceNotFoundException("Salary", "ID", id);
                });

        logger.info("Salary found for ID: {}", id);
        return SalaryMapper.mapToSalaryDTO(salary);
    }

    @Override
    public SalaryDTO updateSalary(SalaryDTO salaryDTO) {
        logger.info("updateSalary method started for salary ID: {}", salaryDTO.getId());
        Optional<Salary> OptionalSalaryUpdate = salaryRepository.findById(salaryDTO.getId());

        // Salary updateSalary = OptionalSalaryUpdate.get();
        Salary updateSalary = OptionalSalaryUpdate.orElseThrow(
                () -> {
                    logger.warn("Salary with ID: {} not found", salaryDTO.getId());
                    return new ResourceNotFoundException("Salary", "ID", salaryDTO.getId());
                });

        logger.info("Salary found for update with ID: {}", salaryDTO.getId());
        UpdateSalaryEntityFromDTO(updateSalary, salaryDTO);
        updateSalary = salaryRepository.save(updateSalary);
        logger.info("Salary updated successfully for ID: {}", salaryDTO.getId());
        return SalaryMapper.mapToSalaryDTO(updateSalary);
    }

    @Override
    public void deleteSalary(Long id) {
        logger.info("deleteSalary method started for salary ID: {}", id);

        if (!salaryRepository.existsById(id)) {
            logger.warn("Salary with ID: {} not found for deletion", id);
            throw new ResourceNotFoundException("Salary", "ID", id);
        }

        salaryRepository.deleteById(id);
        logger.info("Salary with ID: {} deleted successfully", id);
    }

    public void UpdateSalaryEntityFromDTO(Salary updateSalary, SalaryDTO salaryDTO) {
        logger.info("Updating Salary entity from DTO for Salary ID: {}", updateSalary.getId());

        if (salaryDTO.getSalaries() != null) {
            logger.info("Setting salaries value for Salary ID: {}", updateSalary.getId());
            updateSalary.setSalaries(salaryDTO.getSalaries());
        }

        if (salaryDTO.getStartDate() != null) {
            logger.info("Setting start date for Salary ID: {}", updateSalary.getId());
            updateSalary.setStartDate(LocalDate.parse(salaryDTO.getStartDate()));
        }

        if (salaryDTO.getFinishDate() != null) {
            logger.info("Setting finish date for Salary ID: {}", updateSalary.getId());
            updateSalary.setFinishDate(LocalDate.parse(salaryDTO.getFinishDate()));
        }

        logger.info("Salary entity updated for Salary ID: {}", updateSalary.getId());
    }
}