package org;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class ClassEmployeeController {
    private static EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
            .createEntityManagerFactory("Persistence");

    public static void closeEntityManagerFactory() {
        ENTITY_MANAGER_FACTORY.close();
    }

    public static void getClassEmployee(int id) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        String query = "SELECT ce FROM ClassEmployee ce Where ce.id = :id";

        TypedQuery<ClassEmployee> tq = em.createQuery(query, ClassEmployee.class);
        tq.setParameter("id", id);

        ClassEmployee ce;

        try {
            ce = tq.getSingleResult();
            System.out.println(ce.getClassName() + ", " + ce.getMaxNum());
        } catch(NoResultException e) {
            System.out.println("Class with that id not found.\n");
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public static void getClassEmployees() {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        String query = "SELECT ce FROM ClassEmployee ce";

        TypedQuery<ClassEmployee> tq = em.createQuery(query, ClassEmployee.class);

        List<ClassEmployee> ce;

        try {
            ce = tq.getResultList();
            ce.forEach(c -> System.out.println(c.getClassName() + ", " + c.getMaxNum()));
        } catch(NoResultException e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public static void addClassEmployee(String className, int maxNum) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;

        try {
            et = em.getTransaction();
            et.begin();

            ClassEmployee ce = new ClassEmployee();
            ce.setClassName(className);
            ce.setMaxNum(maxNum);

            em.persist(ce);
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

    public static void deleteClassEmployee(int id) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        ClassEmployee ce = null;

        try {
            et = em.getTransaction();
            et.begin();

            ce = em.find(ClassEmployee.class, id);

            if (ce != null){
                Set<Employee> employeesCopy = new HashSet<>(ce.getEmployees());
                for (Employee employee : employeesCopy) {
                    employee.removeClassEmployee(ce);
                }

                em.remove(ce);
            }
            else {
                System.out.println("Class with that id not found.\n");
            }
            // Create a copy of the employee collection to avoid ConcurrentModificationException

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

    public static void updateClassEmployee(int id, Optional<String> newClassName, Optional<Integer> newMaxNum) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;

        try {
            et = em.getTransaction();
            et.begin();

            ClassEmployee ce = em.find(ClassEmployee.class, id);

            if (ce != null) {
                // Update the classEmployee details if the corresponding optional values are present
                newClassName.ifPresent(ce::setClassName);
                newMaxNum.ifPresent(ce::setMaxNum);

                em.persist(ce);
                et.commit();

                System.out.println("ClassEmployee updated successfully.");
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
