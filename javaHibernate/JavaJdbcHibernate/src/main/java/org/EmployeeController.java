package org;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

public class EmployeeController {
    private static EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
            .createEntityManagerFactory("Persistence");

    public static void main(String[] args) {
        //addEmployee("Bob", "bob", 1000);
//        addEmployee("Rob", "rob", 2000);
//        addEmployee("Tob", "tob", 3000);
//        getEmployee(3);
//        getEmployees();
        //deleteEmployee(4);
        //updateEmployee(1, Optional.of("Szym_tym"), Optional.empty(), Optional.empty());


        ENTITY_MANAGER_FACTORY.close();
    }

//    public static void addEmployeeToClass(String fname, String lname, int salary, ClassEmployee classEmployee) {
//        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
//        EntityTransaction et = null;
//
//        try {
//            et = em.getTransaction();
//            et.begin();
//
//        }
//    }
    public static void addClassEmployee(String className, int maxNumOfEmployees) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;

        try {
            et = em.getTransaction();
            et.begin();

            ClassEmployee ce = new ClassEmployee();
            ce.setClassName(className);
            ce.setMaxNumOfEmployees(salary);

            em.persist(employee);
            et.commit();
        }
        catch(Exception e) {
            if (et != null) {
                et.rollback();
            }
            e.printStackTrace();
        }
        finally {
            em.close();
        }
    }

    public static void addEmployee(String fname, String lname, int salary) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;

        try {
            et = em.getTransaction();
            et.begin();

            Employee employee = new Employee();
            employee.setFirstName(fname);
            employee.setLastName(lname);
            employee.setSalary(salary);

            em.persist(employee);
            et.commit();
        }
        catch(Exception e) {
            if (et != null) {
                et.rollback();
            }
            e.printStackTrace();
        }
        finally {
            em.close();
        }
    }

    public static void getEmployee(int id) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        String query = "SELECT e FROM Employee e WHERE e.id = :id";

        TypedQuery<Employee> tq = em.createQuery(query, Employee.class);
        tq.setParameter("id", id);

        Employee employee = null;

        try{
            employee = tq.getSingleResult();
            System.out.println(employee.getFirstName() + ", " + employee.getLastName()
                    + ", " + employee.getSalary());
        } catch(NoResultException e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public static void getEmployees() {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        String query = "SELECT e FROM Employee e";

        TypedQuery<Employee> tq = em.createQuery(query, Employee.class);

        List<Employee> employees;

        try {
            employees = tq.getResultList();
            employees.forEach(emp -> System.out.println(emp.getFirstName() + ", " + emp.getLastName()
                    + ", " + emp.getSalary()));
        } catch(NoResultException e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public static void deleteEmployee(int id) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        Employee employee = null;
        try {
            et = em.getTransaction();
            et.begin();
            employee = em.find(Employee.class, id);
            em.remove(employee);

            et.commit();
        }
        catch(Exception e) {
            if (et != null) {
                et.rollback();
            }
            e.printStackTrace();
        }
        finally {
            em.close();
        }
    }

    public static void updateEmployee(int id, Optional<String> newFirstName, Optional<String> newLastName, Optional<Integer> newSalary) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;

        try {
            et = em.getTransaction();
            et.begin();

            Employee employee = em.find(Employee.class, id);

            if (employee != null) {
                // Update the employee details if the corresponding optional values are present
                newFirstName.ifPresent(employee::setFirstName);
                newLastName.ifPresent(employee::setLastName);
                newSalary.ifPresent(employee::setSalary);

                em.persist(employee);
                et.commit();

                System.out.println("Employee updated successfully.");
            } else {
                System.out.println("Employee with ID " + id + " not found.");
            }
        } catch (Exception e) {
            if (et != null) {
                et.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

}
