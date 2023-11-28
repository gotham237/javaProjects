package org;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class RateController {
    private static EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("Persistence");

    public static void closeEntityManagerFactory() {
        ENTITY_MANAGER_FACTORY.close();
    }

    public static void addRate(double rating, String comment, String classEmployeeName) {
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
                Rate rate = new Rate();
                rate.setRating(rating);
                rate.setComment(comment);
                rate.setClassEmployee(ce);

                em.persist(rate);
                et.commit();
            } else {
                System.out.println("ClassEmployee with name '" + classEmployeeName + "' does not exist.");
            }
        } catch (NoResultException e) {
            System.out.println("ClassEmployee with name '" + classEmployeeName + "' does not exist.");
            e.printStackTrace();
        } catch (Exception e) {
            if (et != null) {
                et.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public static void getRate(int id) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        String query = "SELECT r FROM Rate r WHERE r.id = :id";

        TypedQuery<Rate> tq = em.createQuery(query, Rate.class);
        tq.setParameter("id", id);

        Rate rate = null;

        try {
            rate = tq.getSingleResult();
            System.out.println("Rating: " + rate.getRating() + ", Comment: " + rate.getComment()
                    + ", ClassEmployee: " + rate.getClassEmployee().getClassName()
                    + ", Created At: " + rate.getCreatedAt());
        } catch (NoResultException e) {
            System.out.println("Rate with ID '" + id + "' does not exist.");
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public static void getRates() {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        String query = "SELECT r FROM Rate r";

        TypedQuery<Rate> tq = em.createQuery(query, Rate.class);

        List<Rate> rates;

        try {
            rates = tq.getResultList();
            rates.forEach(rate -> System.out.println("Rating: " + rate.getRating() + ", Comment: " + rate.getComment()
                    + ", ClassEmployee: " + rate.getClassEmployee().getClassName()
                    + ", Created At: " + rate.getCreatedAt()));
        } catch (NoResultException e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public static void deleteRate(int id) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        Rate rate = null;
        try {
            et = em.getTransaction();
            et.begin();

            rate = em.find(Rate.class, id);

            if (rate != null) {
                em.remove(rate);
                et.commit();
            } else {
                System.out.println("Rate with ID '" + id + "' does not exist.");
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

    public static void updateRate(int id, Optional<Double> newRating, Optional<String> newComment) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;

        try {
            et = em.getTransaction();
            et.begin();

            Rate rate = em.find(Rate.class, id);

            if (rate != null) {
                // Update the rate details if the corresponding optional values are present
                newRating.ifPresent(rate::setRating);
                newComment.ifPresent(rate::setComment);

                em.persist(rate);
                et.commit();

                System.out.println("Rate updated successfully.");
            } else {
                System.out.println("Rate with ID " + id + " not found.");
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

