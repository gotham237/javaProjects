package com.example.demo.classEmployee;

import com.example.demo.employee.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Service
public class ClassEmployeeService {
    private final ClassEmployeeRepository classEmployeeRepository;

    @Autowired
    public ClassEmployeeService(ClassEmployeeRepository classEmployeeRepository) {
        this.classEmployeeRepository = classEmployeeRepository;
    }


    public List<ClassEmployee> getGroups() {
        return classEmployeeRepository.findAll();
    }

    public Set<Employee> getEmployeesFromGroup(Integer groupId) {
        ClassEmployee ce = classEmployeeRepository.findById(groupId)
                .orElseThrow(() -> new IllegalStateException(
                        "Group with id " + groupId + " does not exist"));

        return ce.getEmployees();
    }

    public double getFill(Integer groupId) {
        ClassEmployee classEmployee = classEmployeeRepository.findById(groupId)
                .orElseThrow(() -> new IllegalStateException(
                        "Group with id " + groupId + " does not exist"));

        return classEmployee.getIsFullPercentage();
    }

    public void addGroup(ClassEmployee classEmployee) {
        boolean exists = classEmployeeRepository.existsByClassName(classEmployee.getClassName());

        if(!exists){
            classEmployeeRepository.save(classEmployee);
        }
        else {
            throw new IllegalStateException(
                    "Group with name: " + classEmployee.getClassName() + " already exists");
        }
    }

    public void deleteGroup(Integer groupId) {
        ClassEmployee classEmployee = classEmployeeRepository.findById(groupId)
                .orElseThrow(() -> new IllegalStateException(
                        "Group with id " + groupId + " does not exist"));

        // Remove relationships in the join table before deleting
        classEmployee.getEmployees().forEach(employee -> employee.getClassEmployees().remove(classEmployee));

        classEmployeeRepository.deleteById(groupId);
    }

    @Transactional
    public void updateGroup(Integer groupId, ClassEmployee updatedClassEmployee) {
        ClassEmployee existingClassEmployee = classEmployeeRepository.findById(groupId)
                .orElseThrow(() -> new IllegalStateException(
                    "group with id " + groupId + " does not exist"));

        String className = updatedClassEmployee.getClassName();
        Integer maxNum = updatedClassEmployee.getMaxNum();
        if (className != null && !className.isEmpty() && !Objects.equals(existingClassEmployee.getClassName(), className)) {
            existingClassEmployee.setClassName(className);
        }
        if (maxNum != null && maxNum > 0 && !Objects.equals(existingClassEmployee.getMaxNum(), maxNum)) {
            existingClassEmployee.setMaxNum(maxNum);
        }
    }
}
