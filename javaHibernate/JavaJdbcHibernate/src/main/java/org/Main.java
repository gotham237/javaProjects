package org;

public class Main {
    public static void main(String[] args) {
        EmployeeController employeeController = new EmployeeController();
        ClassEmployeeController classEmployeeController = new ClassEmployeeController();

        // You can now call methods from EmployeeController and ClassEmployeeController
        //employeeController.addEmployee("Bob", "bob", 1000);
        //classEmployeeController.addClassEmployee("ClassA", 20);

        //addEmployee("Bob", "bob", 1000);
//        addEmployee("Rob", "rob", 2000);
//        addEmployee("Tob", "tob", 3000);
//        getEmployee(3);
//        getEmployees();
        //deleteEmployee(4);
        //updateEmployee(1, Optional.of("Szym_tym"), Optional.empty(), Optional.empty());
        //employeeController.addEmployeeToClass("Szym3", "tym3", 10000, "ClassB");
        //classEmployeeController.deleteClassEmployee(1);
        //classEmployeeController.getClassEmployees();
        //classEmployeeController.getClassEmployee(2);

        employeeController.closeEntityManagerFactory();
        classEmployeeController.closeEntityManagerFactory();
    }
}
