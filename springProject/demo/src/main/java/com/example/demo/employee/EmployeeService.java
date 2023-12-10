package com.example.demo.employee;

import com.example.demo.EmployeeCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    public void addEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    public void deleteEmployee(Integer employeeId) {
        boolean exists = employeeRepository.existsById(employeeId);

        if (!exists) {
            throw new IllegalStateException("employee with id " + employeeId + " does not exist");
        }

        employeeRepository.deleteById(employeeId);
    }

    @Transactional
    public void updateEmployee(Integer employeeId, EmployeeDTO employeeDTO) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new IllegalStateException(
                        "employee with id " + employeeId + " does not exist"));

        String newFirstName = employeeDTO.getFirstName();
        String newLastName = employeeDTO.getLastName();
        Integer newBirthYear = employeeDTO.getBirthYear();
        Integer newSalary = employeeDTO.getSalary();
        EmployeeCondition newEmployeeCondition = employeeDTO.getEmployeeCondition();

        if (newFirstName != null && !newFirstName.isEmpty() && !Objects.equals(employee.getFirstName(), newFirstName)) {
            employee.setFirstName(newFirstName);
        }
        if (newLastName != null && !newLastName.isEmpty() && !Objects.equals(employee.getLastName(), newLastName)) {
            employee.setLastName(newLastName);
        }
        if (newBirthYear != null && newBirthYear > 0 && !Objects.equals(employee.getBirthYear(), newBirthYear)) {
            employee.setBirthYear(newBirthYear);
        }
        if (newSalary != null && newSalary > 0 && !Objects.equals(employee.getSalary(), newSalary)) {
            employee.setSalary(newSalary);
        }
        if (newEmployeeCondition != null && !Objects.equals(employee.getEmployeeCondition(), newEmployeeCondition)) {
            employee.setEmployeeCondition(newEmployeeCondition);
        }
    }
}
