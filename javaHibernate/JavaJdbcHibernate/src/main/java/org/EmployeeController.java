package org;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

public class EmployeeController {
    private static EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
            .createEntityManagerFactory("Persistence");

    public static void closeEntityManagerFactory() {
        ENTITY_MANAGER_FACTORY.close();
    }

    public static void addEmployee(String fname, String lname, int salary, EmployeeCondition employeeCondition, int birthYear) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;

        try {
            et = em.getTransaction();
            et.begin();

            Employee employee = new Employee();
            employee.setFirstName(fname);
            employee.setLastName(lname);
            employee.setSalary(salary);
            employee.setEmployeeCondition(employeeCondition);
            employee.setBirthYear(birthYear);

            em.persist(employee);
            et.commit();
        } catch (Exception e) {
            if (et != null) {
                et.rollback();
            }
            e.printStackTrace();
        } finally {
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
                    + ", " + employee.getEmployeeCondition() + ", " + employee.getBirthYear()+ ", " + employee.getSalary());
        } catch(NoResultException e) {
            System.out.println("Employee with ID '" + id + "' does not exist.");
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

            if (employee != null) {
                em.remove(employee);
                et.commit();
            } else {
                System.out.println("Employee with ID '" + id + "' does not exist.");
            }
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

    public static void addEmployeeToClass(String fname, String lname, int salary,EmployeeCondition employeeCondition, String classEmployeeName) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;

        try {
            et = em.getTransaction();
            et.begin();

            // Query to find the ClassEmployee by name
            String findClassEmployeeQuery = "SELECT ce FROM ClassEmployee ce WHERE ce.className = :className";
            TypedQuery<ClassEmployee> findClassEmployeeTypedQuery = em.createQuery(findClassEmployeeQuery, ClassEmployee.class);
            findClassEmployeeTypedQuery.setParameter("className", classEmployeeName);

            // Execute the query to find the ClassEmployee
            ClassEmployee ce = findClassEmployeeTypedQuery.getSingleResult();

            if (ce != null) {
                Employee employee = new Employee();
                employee.setFirstName(fname);
                employee.setLastName(lname);
                employee.setSalary(salary);
                employee.setEmployeeCondition(employeeCondition);

                em.persist(employee);
                em.persist(ce);
                employee.addClassEmployee(ce);

                et.commit();
            }
        }
        catch(NoResultException e) {
            System.out.println("ClassEmployee with name '" + classEmployeeName + "' does not exist.");
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

    public static void deleteEmployeeFromClass(int employeeId, int classId) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;

        try {
            et = em.getTransaction();
            et.begin();

            // Execute the query to find the ClassEmployee
            Employee e = em.find(Employee.class, employeeId);
            ClassEmployee ce = em.find(ClassEmployee.class, classId);

            if (ce != null && e != null) {
                e.removeClassEmployee(ce);

                et.commit();
            }
            else {
                System.out.println("Class or Employee with that id do not exist.\n");
            }
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

    public static void search(String s) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        String query = "SELECT e FROM Employee e WHERE LOWER(e.lastName) LIKE LOWER(:lastName)";

        TypedQuery<Employee> tq = em.createQuery(query, Employee.class);
        tq.setParameter("lastName", "%" + s.toLowerCase() + "%");

        List<Employee> employees;

        try {
            employees = tq.getResultList();
            if(!employees.isEmpty()) {
                employees.forEach(emp -> System.out.println(emp.getFirstName() + ", " + emp.getLastName()));
            }
            else {
                System.out.println("No employees found that match this filter");
            }
        } catch(NoResultException e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}
