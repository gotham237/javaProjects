package com.example.demo.classEmployee;

import com.example.demo.employee.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(path = "/api/group")
public class ClassEmployeeController {
    private final ClassEmployeeService classEmployeeService;

    @Autowired
    public ClassEmployeeController(ClassEmployeeService classEmployeeService) {
        this.classEmployeeService = classEmployeeService;
    }

    @GetMapping
    public ResponseEntity<?> getGroups() {
        try {
            List<ClassEmployee> groups = classEmployeeService.getGroups();
            return ResponseEntity.ok().body(groups);
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "{groupId}/employee")
    public ResponseEntity<?> getEmployeesFromGroup(@PathVariable("groupId") Integer groupId) {
        try {
            Set<Employee> employees = classEmployeeService.getEmployeesFromGroup(groupId);
            return ResponseEntity.ok().body(employees);
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "{groupId}/fill")
    public ResponseEntity<?> getFill(@PathVariable("groupId") Integer groupId) {
        try {
            double fill = classEmployeeService.getFill(groupId);
            return ResponseEntity.ok().body(fill);
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<String> addGroup(@RequestBody ClassEmployee classEmployee) {
        try {
            classEmployeeService.addGroup(classEmployee);
            return ResponseEntity.status(HttpStatus.CREATED).body("Group added successfully");
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @DeleteMapping(path = "{groupId}")
    public ResponseEntity<String> deleteGroup(@PathVariable("groupId") Integer groupId) {
        try {
            classEmployeeService.deleteGroup(groupId);
            return ResponseEntity.ok().body("Group deleted successfully");
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(path = "{groupId}")
    public ResponseEntity<String> updateGroup(
            @PathVariable("groupId") Integer groupId,
            @RequestBody ClassEmployee updatedClassEmployee) {
        try {
            classEmployeeService.updateGroup(groupId, updatedClassEmployee);
            return ResponseEntity.ok().body("Group updated successfully");
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
