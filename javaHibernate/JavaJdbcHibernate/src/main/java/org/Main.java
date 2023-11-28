package org;

import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        EmployeeController employeeController = new EmployeeController();
        ClassEmployeeController classEmployeeController = new ClassEmployeeController();
        RateController rateController = new RateController();

        // You can now call methods from EmployeeController and ClassEmployeeController
        //employeeController.addEmployee("Bob", "bob", 1000);
        //classEmployeeController.addClassEmployee("Class1", 10);
        //classEmployeeController.addClassEmployee("Class2", 5);
        //classEmployeeController.addClassEmployee("Class3", 4);
        //employeeController.getEmployee(2);

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
        //classEmployeeController.updateClassEmployee(2, Optional.of("UpdatedClass"), Optional.empty());
        //EmployeeController.deleteEmployeeFromClass(20, 2);
        //rateController.addRate(3, "not bad", "Class1");\
        //rateController.updateRate(1, Optional.of(4.0), Optional.empty());
        //rateController.deleteRate(2);
        //rateController.getRate(1);
        rateController.getRates();

        rateController.closeEntityManagerFactory();
        employeeController.closeEntityManagerFactory();
        classEmployeeController.closeEntityManagerFactory();
    }
}
