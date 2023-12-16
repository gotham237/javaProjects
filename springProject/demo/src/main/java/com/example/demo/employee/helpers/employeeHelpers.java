package com.example.demo.employee.helpers;

import com.example.demo.employee.Employee;

import java.util.List;

public class employeeHelpers {

    public static String convertEmployeesToCSV(List<Employee> employees) {

        StringBuilder csvData = new StringBuilder();
        csvData.append("firstName,lastName,birthYear,salary,employeeCondition\n");
        for (Employee employee : employees) {
            csvData.append(String.format("%s,%s,%d,%d,%s\n",
                    employee.getFirstName(),
                    employee.getLastName(),
                    employee.getBirthYear(),
                    employee.getSalary(),
                    employee.getEmployeeCondition()));
        }
        return csvData.toString();
    }
}
