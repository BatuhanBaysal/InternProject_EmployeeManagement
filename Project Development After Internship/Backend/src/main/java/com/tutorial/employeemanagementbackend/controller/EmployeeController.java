package com.tutorial.employeemanagementbackend.controller;

import com.tutorial.employeemanagementbackend.dto.EmployeeDTO;
import com.tutorial.employeemanagementbackend.exception.ResourceNotFoundException;
import com.tutorial.employeemanagementbackend.service.ServiceImpl.EmployeeServiceImpl;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // It is a Spring annotation that defines it as a web controller that can process HTTP requests and return a response in JSON format.
@AllArgsConstructor
@RequestMapping("/employee") // In Spring MVC, this class or method is accessible via /employee. This means that HTTP requests to /employee are redirected to this class or method.
@CrossOrigin("*") // It is used in theSpring Framework to indicate that a resource is open to requests from different origins (domains).
public class EmployeeController {
    private EmployeeServiceImpl employeeService;
    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @PostMapping("createEmployee")
    // e.g. (for Postman) http://localhost:8080/employee/createEmployee -> POST
    // ResponseEntity<> is a class used in Spring MVC to structure HTTP responses and return them to the client. This class allows you to customize the status code, headers and response content (body) along with the body of the HTTP response.
    // @RequestBody is used in Spring MVC to convert the data in the body of an incoming HTTP request directly into a Java object.
    public ResponseEntity<EmployeeDTO> saveEmployee(@RequestBody EmployeeDTO employeeDTO){
        logger.info("Received request to create employee with ID: {}", employeeDTO.getId());

        try {
            EmployeeDTO saveEmployee = employeeService.saveEmployee(employeeDTO);
            logger.info("Employee with ID: {} successfully created.", employeeDTO.getId());
            return new ResponseEntity<>(saveEmployee, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error occurred while creating employee with ID: {}: {}", employeeDTO.getId(), e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // Return 500 in case of error
        }
    }

    @GetMapping("listEmployees")
    // e.g. (for Postman) http://localhost:8080/employee/listEmployees -> GET
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees(){
        logger.info("Received request to get all employees.");

        try {
            List<EmployeeDTO> listEmployees = employeeService.getAllEmployees();
            logger.info("Successfully retrieved {} employees.", listEmployees.size());
            return new ResponseEntity<>(listEmployees, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error occurred while retrieving employees: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // Return 500 in case of error
        }
    }

    @GetMapping("/{id}")
    // e.g. (for Postman) http://localhost:8080/employee/1 -> GET
    // @PathVariable is an annotation used in Spring MVC to bind a variable value in a URL path directly to a method parameter. This allows us to capture and use a specific value in the URL when dealing with dynamic URL paths.
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long id){
        logger.info("Received request to get employee with ID: {}", id);

        try {
            EmployeeDTO listEmployeeById = employeeService.getEmployeeById(id);
            logger.info("Successfully retrieved employee with ID: {}", id);
            return new ResponseEntity<>(listEmployeeById, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            logger.error("Employee with ID: {} not found: {}", id, e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Return 404 if employee not found
        } catch (Exception e) {
            logger.error("Error occurred while retrieving employee with ID: {}: {}", id, e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // Return 500 if any other error occurs
        }
    }

    // PATCH works with fewer data by updating only the modified fields, which results in data efficiency and performance, while PUT updates the entire record again.
    @PatchMapping("updateEmployee/{id}")
    // e.g. (for Postman) http://localhost:8080/employee/updateEmployee/1 -> PATCH
    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable Long id, @RequestBody EmployeeDTO employeeDTO){
        logger.info("Received request to update employee with ID: {}", id);

        try {
            employeeDTO.setId(id); // ID'yi DTO'ya set ediyoruz
            EmployeeDTO updateEmployee = employeeService.updateEmployee(employeeDTO);
            logger.info("Successfully updated employee with ID: {}", id);
            return new ResponseEntity<>(updateEmployee, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            logger.error("Employee with ID: {} not found for update: {}", id, e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Return 404 if employee not found
        } catch (Exception e) {
            logger.error("Error occurred while updating employee with ID: {}: {}", id, e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // Return 500 for any other errors
        }
    }

    @DeleteMapping("deleteEmployee/{id}")
    // e.g. (for Postman) http://localhost:8080/employee/deleteEmployee/1 -> DELETE
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id){
        logger.info("Received request to delete employee with ID: {}", id);

        try {
            employeeService.deleteEmployee(id);
            logger.info("Successfully deleted employee with ID: {}", id);
            return new ResponseEntity<>("Employee successfully deleted.", HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            logger.error("Employee with ID: {} not found for deletion: {}", id, e.getMessage());
            return new ResponseEntity<>("Employee not found.", HttpStatus.NOT_FOUND); // Return 404 if employee not found
        } catch (Exception e) {
            logger.error("Error occurred while deleting employee with ID: {}: {}", id, e.getMessage());
            return new ResponseEntity<>("An error occurred while deleting the employee.", HttpStatus.INTERNAL_SERVER_ERROR); // Return 500 if any other errors
        }
    }

    @GetMapping("search-name-surname")
    // e.g. (for Postman) http://localhost:8080/employee/search-name-surname?firstName=Batuhan&lastName=Baysal -> GET
    public ResponseEntity<List<EmployeeDTO>> searchEmployeeByNameAndSurname(@RequestParam String firstName,
                                                                            @RequestParam String lastName) {
        try {
            logger.info("Searching for employees with first name: {} and last name: {}", firstName, lastName);
            List<EmployeeDTO> searchNameAndSurname = employeeService.findEmployeeByFirstNameAndLastName(firstName, lastName);

            if (searchNameAndSurname.isEmpty()) {
                logger.info("No employees found with first name: {} and last name: {}", firstName, lastName);
            } else {
                logger.info("Found {} employees with first name: {} and last name: {}", searchNameAndSurname.size(), firstName, lastName);
            }

            return new ResponseEntity<>(searchNameAndSurname, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("An error occurred while searching for employees by name and surname: {}", e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("search")
    // e.g. (for Postman) http://localhost:8080/employee/search?firstName=Bat&lastName=Bay&email=ba -> GET
    public ResponseEntity<List<EmployeeDTO>> searchEmployee(@RequestParam(required = false) String firstName,
                                                            @RequestParam(required = false) String lastName,
                                                            @RequestParam(required = false) String email) {
        try {
            logger.info("Searching for employees with criteria - First Name: {}, Last Name: {}, Email: {}", firstName, lastName, email);
            List<EmployeeDTO> searchEmployee = employeeService.findEmployeeByCriteria(firstName, lastName, email);

            if (searchEmployee.isEmpty()) {
                logger.info("No employees found with the given search criteria.");
            } else {
                logger.info("Found {} employees with the given search criteria.", searchEmployee.size());
            }

            return new ResponseEntity<>(searchEmployee, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("An error occurred while searching for employees: {}", e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}