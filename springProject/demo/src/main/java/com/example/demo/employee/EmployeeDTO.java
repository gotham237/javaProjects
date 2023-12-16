package com.example.demo.employee;

public class EmployeeDTO {
    private String firstName;
    private String lastName;
    private Integer birthYear;
    private Integer salary;
    private EmployeeCondition employeeCondition;

    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }

    public Integer getBirthYear() {
        return birthYear;
    }

    public Integer getSalary() {
        return salary;
    }

    public EmployeeCondition getEmployeeCondition() {
        return employeeCondition;
    }
}
