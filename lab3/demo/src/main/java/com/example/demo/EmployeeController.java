package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class EmployeeController {
    @FXML
    private TableView<Employee> employeesTable;
    @FXML
    private TableColumn<Employee, String> firstNameColumn;
    @FXML
    private TableColumn<Employee, String> lastNameColumn;
    @FXML
    private TableColumn<Employee, EmployeeCondition> employeeConditionColumn;
    @FXML
    private TableColumn<Employee, Integer> birthYearColumn;
    @FXML
    private TableColumn<Employee, Double> salaryColumn;
    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField employeeConditionField;

    @FXML
    private TextField birthYearField;

    @FXML
    private TextField salaryField;

    @FXML
    public void initialize() {
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("imie"));
        //TableColumn<Employee, String> lastNameColumn = new TableColumn<>("Last Name");
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("nazwisko"));
        employeeConditionColumn.setCellValueFactory(new PropertyValueFactory<>("employeeCondition"));
        birthYearColumn.setCellValueFactory(new PropertyValueFactory<>("rokUrodzenia"));
        salaryColumn.setCellValueFactory(new PropertyValueFactory<>("wynagrodzenie"));

        // add default employees
        employeesTable.getItems().add(new Employee("Szymon", "Tymula", EmployeeCondition.OBECNY, 2002, 8000));
        employeesTable.getItems().add(new Employee("Piotr", "Nowak", EmployeeCondition.NIEOBECNY, 2000, 10000));
        employeesTable.getItems().add(new Employee("Michal", "Kowalski", EmployeeCondition.CHORY, 1999, 6000));
    }

    @FXML
    public void addRecord() {
        //System.out.println("Add record");
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        EmployeeCondition employeeCondition = EmployeeCondition.valueOf(employeeConditionField.getText());
        int birthYear = Integer.parseInt(birthYearField.getText());
        double salary = Double.parseDouble(salaryField.getText());

        // Create a new Employee instance
        Employee newEmployee = new Employee(firstName, lastName, employeeCondition, birthYear, salary);

        // Add the new employee to the table
        employeesTable.getItems().add(newEmployee);
        employeesTable.refresh();

        // Clear the input fields after adding
        firstNameField.clear();
        lastNameField.clear();
        employeeConditionField.clear();
        birthYearField.clear();
        salaryField.clear();
    }
}
