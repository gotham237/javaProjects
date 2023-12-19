package com.example.demo.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<?> getEmployees() {
        try {
            List<Employee> employees = employeeService.getEmployees();
            return ResponseEntity.ok().body(employees);
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/csv", produces = "text/csv")
    public ResponseEntity<byte[]> getEmployeesAsCSV() {
        return employeeService.getEmployeesAsCsv();
    }

    @PostMapping
    public ResponseEntity<String> addEmployee(@RequestBody Employee employee) {
        try {
            employeeService.addEmployee(employee);
            return ResponseEntity.status(HttpStatus.CREATED).body("Employee added successfully");
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("An error occurred while adding the employee");
        }
    }

    @PostMapping(path = "/group/{groupId}")
    public ResponseEntity<String> addEmployeeToGroup(
            @PathVariable("groupId") Integer groupId,
            @RequestBody Employee employee
    ) {
        try {
            employeeService.addEmployeeToGroup(groupId, employee);
            return ResponseEntity.status(HttpStatus.CREATED).body("Employee added to group successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Group NOT FOUND.");
        }
    }

    @DeleteMapping(path = "{employeeId}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("employeeId") Integer employeeId) {
        try {
            employeeService.deleteEmployee(employeeId);
            return ResponseEntity.ok().body("Employee deleted successfully");
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while deleting the employee");
        }
    }

    @PutMapping(path = "{employeeId}")
    public ResponseEntity<String> updateEmployee(
            @PathVariable("employeeId") Integer employeeId,
            @RequestBody EmployeeDTO employeeDTO) {
        try {
            employeeService.updateEmployee(employeeId, employeeDTO);
            return ResponseEntity.ok().body("Employee updated successfully");
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while updating the employee");
        }
    }
}
