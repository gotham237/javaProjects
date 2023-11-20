package com.example.demo;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EmployeeController {
    @FXML
    private TableView<Employee> employeesTable;
    private ObservableList<Employee> originalEmployees;
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
    private ComboBox<String> employeeConditionField;

    @FXML
    private TextField birthYearField;

    @FXML
    private TextField salaryField;

    @FXML
    private TextField lastNameFilterField;

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
    private TextField maxEmployeesField;

    @FXML
    private TextField partialLastNameField;


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

        // Initialize the originalEmployees list
        originalEmployees = FXCollections.observableArrayList(employeesTable.getItems());

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

        //isFull percentage
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

        ClassEmployee classEmployee1 = new ClassEmployee("Klasa1", employeesList, 10);
        classEmployeesTable.getItems().add(classEmployee1);

        // Store the original employees for each class in the map
//        ObservableList<Employee> originalClassEmployees = FXCollections.observableArrayList(employeesList);
//        originalEmployeesMap.put(classEmployee1, originalClassEmployees);
    }

    @FXML
    public void addEmployeeRecord() {
        //System.out.println("Add record");
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String employeeCondition = employeeConditionField.getValue();
        String birthYear = birthYearField.getText();
        String salary = salaryField.getText();

        if (!firstName.isEmpty() && !lastName.isEmpty() && employeeCondition != null &&  !birthYear.isEmpty() && !salary.isEmpty()) {
            EmployeeCondition employeeCondition2 = EmployeeCondition.valueOf(employeeCondition);
            int birthYear2 = Integer.parseInt(birthYear);
            double salary2 = Double.parseDouble(salary);

            // Create a new Employee instance
            Employee newEmployee = new Employee(firstName, lastName, employeeCondition2, birthYear2, salary2);

            // Add the new employee to the table
            employeesTable.getItems().add(newEmployee);
            originalEmployees.add(newEmployee);
            employeesTable.refresh();

            // Clear the input fields after adding
            firstNameField.clear();
            lastNameField.clear();
            employeeConditionField.setValue(null);
            birthYearField.clear();
            salaryField.clear();
        }
        else {
            showAlert(Alert.AlertType.INFORMATION, "Please enter First Name, Last Name, Employee Condition, Birth Year, Salary.");
        }
    }

    @FXML
    public void removeEmployeeRecord() {
        Employee selectedEmployee = employeesTable.getSelectionModel().getSelectedItem();
        if (selectedEmployee != null) {
            employeesTable.getItems().removeAll(employeesTable.getSelectionModel().getSelectedItem());
            originalEmployees.remove(selectedEmployee);
        }
        else {
            showAlert(Alert.AlertType.INFORMATION, "Please select the Employee you want to remove.");
        }
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
            showAlert(Alert.AlertType.INFORMATION, "Please enter group name and max number of employees");
        }
    }

    @FXML
    public void addEmployeeToClassTable() {
        ClassEmployee selectedClassEmployee = classEmployeesTable.getSelectionModel().getSelectedItem();
        Employee selectedEmployee = employeesTable.getSelectionModel().getSelectedItem();

        // Check if a class employee is selected
        if (selectedClassEmployee != null && selectedEmployee != null) {

            // Add the new employee to the selected class employee
            selectedClassEmployee.addEmployee(selectedEmployee);

            classEmployeesTable.refresh();
        } else {
            showAlert(Alert.AlertType.INFORMATION, "Please select the Employee Class and Employee.");
        }
    }

    @FXML
    public void removeClassEmployeeRecord() {
        ClassEmployee selectedClassEmployee = classEmployeesTable.getSelectionModel().getSelectedItem();
        if (selectedClassEmployee != null) {
            classEmployeesTable.getItems().removeAll(classEmployeesTable.getSelectionModel().getSelectedItem());
        }
        else {
            showAlert(Alert.AlertType.INFORMATION, "Please select the Employee Class you want to remove.");
        }
    }

    public void showAlert(Alert.AlertType type, String message) {
        Alert alert = new Alert(type);
        alert.setContentText(message);
        alert.show();
    }

    @FXML
    public void filterEmployeesByLastName() {
        String filterText = lastNameFilterField.getText().trim().toLowerCase();

        if (!filterText.isEmpty()) {
            // Create a new filtered list
            List<Employee> filteredEmployees = originalEmployees.stream()
                    .filter(employee -> employee.getNazwisko().toLowerCase().equals(filterText))
                    .collect(Collectors.toList());

            // Clear the current items in the table
            employeesTable.getItems().clear();

            // Add the filtered employees to the table
            employeesTable.getItems().addAll(filteredEmployees);
        } else {
            // If the filter field is empty, create a new list and set it to the table
            List<Employee> allEmployees = new ArrayList<>(originalEmployees);
            employeesTable.setItems(FXCollections.observableArrayList(allEmployees));
            employeesTable.refresh();
        }
    }

    @FXML
    public void editEmployeeRecord() {
        // Get the selected employee from the table
        Employee selectedEmployee = employeesTable.getSelectionModel().getSelectedItem();

        if (selectedEmployee != null) {
            // Retrieve values from the text fields
            String newFirstName = firstNameField.getText();
            String newLastName = lastNameField.getText();
            String newEmployeeCondition = employeeConditionField.getValue();
            String newBirthYear = birthYearField.getText();
            String newSalary = salaryField.getText();

            // Check which fields are not empty and update the selected employee
            if (!newFirstName.isEmpty()) {
                selectedEmployee.setImie(newFirstName);
            }
            if (!newLastName.isEmpty()) {
                selectedEmployee.setNazwisko(newLastName);
            }
            if (newEmployeeCondition != null) {
                selectedEmployee.setCondition(EmployeeCondition.valueOf(newEmployeeCondition));
            }
            if (!newBirthYear.isEmpty()) {
                selectedEmployee.setRokUrodzenia(Integer.parseInt(newBirthYear));
            }
            if (!newSalary.isEmpty()) {
                selectedEmployee.setWynagrodzenie(Double.parseDouble(newSalary));
            }

            // Update the corresponding employee in the original list
            int indexInOriginalList = originalEmployees.indexOf(selectedEmployee);
            if (indexInOriginalList != -1) {
                originalEmployees.set(indexInOriginalList, selectedEmployee);
            }

            // Refresh the table to reflect the changes
            employeesTable.refresh();

            // Clear the input fields after editing
            firstNameField.clear();
            lastNameField.clear();
            employeeConditionField.setValue(null);
            birthYearField.clear();
            salaryField.clear();
        } else {
            showAlert(Alert.AlertType.INFORMATION, "Please select the employee you want to edit.");
        }
    }

//    @FXML
//    public void filterEmployeesByPartialLastName() {
//        String partialLastName = partialLastNameField.getText().toLowerCase();
//
//        // Check if the partial last name is provided
//        if (!partialLastName.isEmpty()) {
//            // Create a list to store filtered class employees
//            List<ClassEmployee> filteredClassEmployees = new ArrayList<>();
//
//            // Iterate through class employees and filter employees based on the partial last name
//            for (ClassEmployee classEmployee : classEmployeesTable.getItems()) {
//                List<Employee> employeesInClass = classEmployee.getListaPracownikow();
//                List<Employee> filteredEmployeesInClass = employeesInClass.stream()
//                        .filter(e -> e.getNazwisko().toLowerCase().contains(partialLastName))
//                        .collect(Collectors.toList());
//
//                // Create a new ClassEmployee with the filtered employees
//                ClassEmployee filteredClassEmployee = new ClassEmployee(
//                        classEmployee.getNazwaGrupy(),
//                        filteredEmployeesInClass,
//                        classEmployee.getMaxIloscPracownikow()
//                );
//
//                // Add the filtered ClassEmployee to the list
//                filteredClassEmployees.add(filteredClassEmployee);
//            }
//
//            // Clear the classEmployeesTable and add the filtered class employees
//            classEmployeesTable.getItems().clear();
//            classEmployeesTable.getItems().addAll(filteredClassEmployees);
//        } else {
//            // If no partial last name is provided, restore the original state of classEmployeesTable
//            classEmployeesTable.getItems().clear();
//            classEmployeesTable.getItems().addAll(originalClassEmployees);
//        }
//    }

}

