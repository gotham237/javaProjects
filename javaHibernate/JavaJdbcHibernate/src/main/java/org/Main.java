package org;

import java.util.Optional;

import static org.EmployeeCondition.OBECNY;

public class Main {
    public static void main(String[] args) {
        EmployeeController employeeController = new EmployeeController();
        ClassEmployeeController classEmployeeController = new ClassEmployeeController();
        RateController rateController = new RateController();

        // You can now call methods from EmployeeController and ClassEmployeeController
        //employeeController.addEmployee("Bob10", "bob1", 1000, OBECNY, 2000);
        //classEmployeeController.addClassEmployee("Class10", 10);
        //classEmployeeController.addClassEmployee("Class2", 5);
        //classEmployeeController.addClassEmployee("Class3", 4);
        //employeeController.getEmployee(27);

        //addEmployee("Bob", "bob", 1000);
        //employeeController.addEmployee("Rob", "rob", 2000);
        //employeeController.addEmployee("Tob", "tob", 3000);
        //employeeController.getEmployee(26);
        //employeeController.getEmployees();
        //deleteEmployee(4);
        //updateEmployee(1, Optional.of("Szym_tym"), Optional.empty(), Optional.empty());
        //System.out.println(OBECNY);
        //employeeController.addEmployeeToClass("Szym4", "tym4", 10000, OBECNY, "Class10");
        //employeeController.addEmployeeToClass("Szym3", "tym3", 10000, "UpdatedClass");
        //employeeController.addEmployeeToClass("Szym3", "tym3", 10000, "Class1");
        //classEmployeeController.deleteClassEmployee(13);
        //classEmployeeController.getClassEmployees();
        //classEmployeeController.getClassEmployee(2);
        //classEmployeeController.updateClassEmployee(2, Optional.of("UpdatedClass"), Optional.empty());
        //EmployeeController.deleteEmployeeFromClass(20, 2);
        //rateController.addRate(3, "not bad", "Class1");
        //rateController.updateRate(1, Optional.of(4.0), Optional.empty());
        //rateController.deleteRate(2);
        //rateController.getRate(1);
        //rateController.getRates();
        //employeeController.search("tym");

        rateController.closeEntityManagerFactory();
        employeeController.closeEntityManagerFactory();
        classEmployeeController.closeEntityManagerFactory();
    }
}
