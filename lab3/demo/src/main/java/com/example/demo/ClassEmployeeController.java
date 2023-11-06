package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class ClassEmployeeController {
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
    public void initialize() {
        groupNameColumn.setCellValueFactory(new PropertyValueFactory<>("nazwaGrupy"));
        employeesColumn.setCellValueFactory(new PropertyValueFactory<>("listaPracownikow"));
        maxEmployeesColumn.setCellValueFactory(new PropertyValueFactory<>("maxIloscPracownikow"));
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
}

