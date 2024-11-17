package com.tutorial.employeemanagementbackend.controller;

import com.tutorial.employeemanagementbackend.dto.SalaryDTO;
import com.tutorial.employeemanagementbackend.exception.ResourceNotFoundException;
import com.tutorial.employeemanagementbackend.service.ServiceImpl.SalaryServiceImpl;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // It is a Spring annotation that defines it as a web controller that can process HTTP requests and return a response in JSON format.
@AllArgsConstructor
@RequestMapping("/salary") // In Spring MVC, this class or method is accessible via /salary. This means that HTTP requests to /salary are redirected to this class or method.
@CrossOrigin("*") // It is used in theSpring Framework to indicate that a resource is open to requests from different origins (domains).
public class SalaryController {
    private SalaryServiceImpl salaryService;
    private static final Logger logger = LoggerFactory.getLogger(SalaryController.class);

    @PostMapping("createSalary")
    // e.g. (for Postman) http://localhost:8080/salary/createSalary -> POST
    // ResponseEntity<> is a class used in Spring MVC to structure HTTP responses and return them to the client. This class allows you to customize the status code, headers and response content (body) along with the body of the HTTP response.
    // @RequestBody is used in Spring MVC to convert the data in the body of an incoming HTTP request directly into a Java object.
    public ResponseEntity<SalaryDTO> saveSalary(@RequestBody SalaryDTO salaryDTO) {
        logger.info("Received request to create salary: {}", salaryDTO);

        try {
            SalaryDTO saveSalary = salaryService.saveSalary(salaryDTO);
            logger.info("Successfully created salary: {}", saveSalary);
            return new ResponseEntity<>(saveSalary, HttpStatus.CREATED); // 201 Created status
        } catch (Exception e) {
            logger.error("Error occurred while creating salary: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // 500 Internal Server Error
        }
    }

    @GetMapping("listSalaries")
    // e.g. (for Postman) http://localhost:8080/salary/listSalaries -> GET
    public ResponseEntity<List<SalaryDTO>> getAllSalaries() {
        logger.info("getAllSalaries method started");

        try {
            List<SalaryDTO> salaries = salaryService.getAllSalaries();
            logger.info("Successfully retrieved {} salaries", salaries.size());
            return new ResponseEntity<>(salaries, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error occurred while retrieving salaries: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // Return 500 for any error
        }
    }

    @GetMapping("/{id}")
    // e.g. (for Postman) http://localhost:8080/salary/1 -> GET
    // @PathVariable is an annotation used in Spring MVC to bind a variable value in a URL path directly to a method parameter. This allows us to capture and use a specific value in the URL when dealing with dynamic URL paths.
    public ResponseEntity<SalaryDTO> getSalaryById(@PathVariable Long id) {
        logger.info("Received request to retrieve salary with ID: {}", id);

        try {
            SalaryDTO salaryById = salaryService.getSalaryById(id);
            logger.info("Successfully retrieved salary for ID: {}", id);
            return new ResponseEntity<>(salaryById, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            logger.error("Salary with ID: {} not found: {}", id, e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Return 404 if salary not found
        } catch (Exception e) {
            logger.error("Error occurred while retrieving salary with ID: {}: {}", id, e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // Return 500 in case of any other error
        }
    }

    // PATCH works with fewer data by updating only the modified fields, which results in data efficiency and performance, while PUT updates the entire record again.
    @PatchMapping("updateSalary/{id}")
    // e.g. (for Postman) http://localhost:8080/salary/updateSalary/1 -> PATCH
    public ResponseEntity<SalaryDTO> updateSalary(@PathVariable Long id, @RequestBody SalaryDTO salaryDTO) {
        logger.info("Received request to update salary with ID: {}", id);

        try {
            salaryDTO.setId(id);
            SalaryDTO updateSalary = salaryService.updateSalary(salaryDTO);
            logger.info("Salary with ID: {} updated successfully", id);
            return new ResponseEntity<>(updateSalary, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            logger.error("Salary with ID: {} not found: {}", id, e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Return 404 if salary not found
        } catch (Exception e) {
            logger.error("Error occurred while updating salary with ID: {}: {}", id, e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // Return 500 for any other error
        }
    }

    @DeleteMapping("deleteSalary/{id}")
    // e.g. (for Postman) http://localhost:8080/salary/deleteSalary/1 -> DELETE
    public ResponseEntity<String> deleteSalary(@PathVariable Long id) {
        logger.info("Received request to delete salary with ID: {}", id);

        try {
            salaryService.deleteSalary(id);
            logger.info("Salary with ID: {} deleted successfully", id);
            return new ResponseEntity<>("Salary successfully deleted.", HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            logger.error("Salary with ID: {} not found: {}", id, e.getMessage());
            return new ResponseEntity<>("Salary not found.", HttpStatus.NOT_FOUND); // Return 404 if salary not found
        } catch (Exception e) {
            logger.error("Error occurred while deleting salary with ID: {}: {}", id, e.getMessage());
            return new ResponseEntity<>("Internal server error.", HttpStatus.INTERNAL_SERVER_ERROR); // Return 500 for any other error
        }
    }
}