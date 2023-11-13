package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

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

    //class employee
    @FXML
    private TableView<ClassEmployee> classEmployeesTable;
    @FXML
    private TableColumn<Employee, String> groupNameColumn;
    @FXML
    private TableColumn<Employee, Integer> employeesColumn;
    @FXML
    private TableColumn<Employee, Integer> maxEmployeesColumn;
    @FXML
    private TextField groupNameField;
    @FXML
    private TextField employeesField;
    @FXML
    private TextField maxEmployeesField;
    @FXML
    private Button addEmployeeToClassButton;

    @FXML
    public void initialize() {
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("imie"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("nazwisko"));
        employeeConditionColumn.setCellValueFactory(new PropertyValueFactory<>("employeeCondition"));
        birthYearColumn.setCellValueFactory(new PropertyValueFactory<>("rokUrodzenia"));
        salaryColumn.setCellValueFactory(new PropertyValueFactory<>("wynagrodzenie"));

        // add default employees
        employeesTable.getItems().add(new Employee("Szymon", "Tymula", EmployeeCondition.OBECNY, 2002, 8000));
        employeesTable.getItems().add(new Employee("Piotr", "Nowak", EmployeeCondition.NIEOBECNY, 2000, 10000));
        employeesTable.getItems().add(new Employee("Michal", "Kowalski", EmployeeCondition.CHORY, 1999, 6000));

        // class employee table
        groupNameColumn.setCellValueFactory(new PropertyValueFactory<>("nazwaGrupy"));
        employeesColumn.setCellValueFactory(new PropertyValueFactory<>("listaPracownikow"));
        maxEmployeesColumn.setCellValueFactory(new PropertyValueFactory<>("maxIloscPracownikow"));

        //add default classes
        classEmployeesTable.getItems().add(new ClassEmployee("Klasa1",  10));

        addEmployeeToClassButton.setOnAction(event -> addEmployeeToClassTable());
    }

    @FXML
    public void addEmployeeRecord() {
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

    @FXML
    public void removeEmployeeRecord() {
        employeesTable.getItems().removeAll(employeesTable.getSelectionModel().getSelectedItem());
    }

    @FXML
    public void addClassRecord() {
        String groupName = groupNameField.getText();
        int maxEmployees = Integer.parseInt(maxEmployeesField.getText());

        // Create a new ClassEmployee instance
        ClassEmployee newClassEmployee = new ClassEmployee(groupName, maxEmployees);

        // Add the new class employee to the table
        classEmployeesTable.getItems().add(newClassEmployee);

        // Clear the input fields after adding
        groupNameField.clear();
        maxEmployeesField.clear();
    }

    @FXML
    public void addEmployeeToClassTable() {
        ClassEmployee selectedClassEmployee = classEmployeesTable.getSelectionModel().getSelectedItem();
        Employee selectedEmployee = employeesTable.getSelectionModel().getSelectedItem();

        // Check if a class employee is selected
        if (selectedClassEmployee != null) {

            // Add the new employee to the selected class employee
            selectedClassEmployee.addEmployee(selectedEmployee);

            classEmployeesTable.refresh();
        } else {
            System.out.println("Please select a class employee to add the employee to.");
        }
    }

    @FXML
    public void removeClassEmployeeRecord() {
        classEmployeesTable.getItems().removeAll(classEmployeesTable.getSelectionModel().getSelectedItem());
    }

}
