import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Employee employee1 = new Employee("Test1", "test1", EmployeeCondition.DELEGACJA, 2002, 6000);
        Employee employee2 = new Employee("Test2", "Tym", EmployeeCondition.OBECNY, 2000, 8000);
        Employee employee3 = new Employee("Test3", "test3", EmployeeCondition.NIEOBECNY, 1999, 10000);
        Employee employee4 = new Employee("Test4", "test4", EmployeeCondition.NIEOBECNY, 1999, 12000);
        Employee employee5 = new Employee("Test5", "test5", EmployeeCondition.NIEOBECNY, 1999, 4000);
        employee1.printing();
        employee2.printing();
        employee3.printing();

        ClassEmployee classEmployee = new ClassEmployee("programisci", 2);
        System.out.println("addEmployee: ");
        classEmployee.addEmployee(employee1);
        classEmployee.addEmployee(employee2);
        classEmployee.addEmployee(employee3);
        classEmployee.summary();

        System.out.print("CompareTo: ");
        System.out.println(employee1.compareTo(employee2));

        System.out.println("addSalary: ");
        classEmployee.addSalary(employee1, 1000);
        employee1.printing();

        System.out.println("removeEmployee: ");
        //classEmployee.removeEmployee(employee2);
        //classEmployee.summary();

        System.out.println("changeCondition: ");
        classEmployee.changeCondition(employee1, EmployeeCondition.OBECNY);
        employee1.printing();

        System.out.println("search: ");
        classEmployee.search("test1").printing();
        classEmployee.search("Tym").printing();
        //System.out.println(classEmployee.search("Tym"));

        System.out.println("searchPartial: ");
        classEmployee.addEmployee(employee2);
        ArrayList<Employee> em = classEmployee.searchPartial("te");
        for(Employee e : em){
            e.printing();
        }

        System.out.println("countByCondition: ");
        System.out.println(classEmployee.countByCondition(EmployeeCondition.OBECNY));

        System.out.println("summary: ");
        classEmployee.summary();

        ClassEmployee classEmployee2 = new ClassEmployee();
        classEmployee2.addEmployee(employee1);
        classEmployee2.addEmployee(employee2);
        classEmployee2.addEmployee(employee3);
        classEmployee2.addEmployee(employee4);
        classEmployee2.addEmployee(employee5);
        System.out.println("sortByName: ");
        ArrayList<Employee> ce = classEmployee2.sortByName();
        for(Employee e : ce){
            e.printing();
        }

        System.out.println("sortBySalary: ");
        ArrayList<Employee> ce2 = classEmployee2.sortBySalary();
        for(Employee e : ce2){
            e.printing();
        }

        System.out.println("max: ");
        System.out.println(classEmployee2.max());

        System.out.println("Grupy pracownicze: ");
        ClassContainer classContainer = new ClassContainer();
        System.out.println("addClass: ");
        classContainer.addClass("grupa1", 10);
        classContainer.addClass("grupa2", 15);
        classContainer.summary();

        System.out.println("removeClass: ");
        classContainer.removeClass("grupa1");
        classContainer.summary();

        classContainer.addClass("grupa3", 5);
        System.out.println("findEmpty: ");
        ArrayList<String> ar = classContainer.findEmpty();
        for(String x : ar){
            System.out.println(x);
        }
    }
}