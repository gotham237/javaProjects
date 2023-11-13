package com.example.demo;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

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
    private TableColumn<Employee, List<Employee>> employeesColumn;
    @FXML
    private TableColumn<Employee, Integer> maxEmployeesColumn;
    @FXML
    private TableColumn<ClassEmployee, Double> isFull;
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
        Employee e1 = new Employee("Szymon", "Tymula", EmployeeCondition.OBECNY, 2002, 8000);
        Employee e2 = new Employee("Piotr", "Nowak", EmployeeCondition.NIEOBECNY, 2000, 10000);
        Employee e3 = new Employee("Michal", "Kowalski", EmployeeCondition.CHORY, 1999, 6000);
        Employee e4 = new Employee("Karol", "Kot", EmployeeCondition.DELEGACJA, 1997, 7000);
        employeesTable.getItems().add(e1);
        employeesTable.getItems().add(e2);
        employeesTable.getItems().add(e3);
        employeesTable.getItems().add(e4);

        //scrollPane in employeesColumn
        employeesColumn.setCellFactory(column -> {
            return new TableCell<Employee, List<Employee>>() {
                private final ScrollPane scrollPane = new ScrollPane();
                private final VBox content = new VBox();
                {
                    scrollPane.setContent(content);
                    scrollPane.setFitToWidth(true);
                }

                @Override
                protected void updateItem(List<Employee> employees, boolean empty) {
                    super.updateItem(employees, empty);

                    if (employees == null || empty) {
                        setGraphic(null);
                    } else {
                        // Display employee information in the VBox
                        content.getChildren().clear();
                        for (Employee employee : employees) {
                            Label label = new Label(employee.toString()); // Use toString() or format as needed
                            content.getChildren().add(label);
                        }

                        setGraphic(scrollPane);
                    }
                }
            };
        });

        // class employee table
        groupNameColumn.setCellValueFactory(new PropertyValueFactory<>("nazwaGrupy"));
        employeesColumn.setCellValueFactory(new PropertyValueFactory<>("listaPracownikow"));
        maxEmployeesColumn.setCellValueFactory(new PropertyValueFactory<>("maxIloscPracownikow"));

        isFull.setCellValueFactory((data) -> {
            ClassEmployee ce = data.getValue();
            double isFullPercentage = ce.getIsFullPercentage();
            DecimalFormat decimalFormat = new DecimalFormat("#.##");
            double roundedIsFullPercentage = Double.parseDouble(decimalFormat.format(isFullPercentage));
            return new SimpleDoubleProperty(roundedIsFullPercentage).asObject();
        });

        //add default classes
        ArrayList<Employee> employeesList = new ArrayList<Employee>();
        employeesList.add(e1);
        employeesList.add(e2);
        employeesList.add(e3);
        classEmployeesTable.getItems().add(new ClassEmployee("Klasa1", employeesList, 10));

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
        String maxEmployeesText = maxEmployeesField.getText();

        if (!groupName.isEmpty() && !maxEmployeesText.isEmpty()) {
            int maxEmployees = Integer.parseInt(maxEmployeesText);

            if (maxEmployees >= 1) {
                ClassEmployee newClassEmployee = new ClassEmployee(groupName, maxEmployees);

                // Add the new class employee to the table
                classEmployeesTable.getItems().add(newClassEmployee);

                groupNameField.clear();
                maxEmployeesField.clear();
            } else {
                showAlert(Alert.AlertType.ERROR, "Max number of employees must be at least 1");
            }
        } else {
            showAlert(Alert.AlertType.ERROR, "Please enter group name and max number of employees");
        }
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
            showAlert(Alert.AlertType.INFORMATION, "Please select the Employee Class to add the employee to.");
        }
    }

    @FXML
    public void removeClassEmployeeRecord() {
        classEmployeesTable.getItems().removeAll(classEmployeesTable.getSelectionModel().getSelectedItem());
    }

    public void showAlert(Alert.AlertType type, String message) {
        Alert alert = new Alert(type);
        alert.setContentText(message);
        alert.show();
    }

}

