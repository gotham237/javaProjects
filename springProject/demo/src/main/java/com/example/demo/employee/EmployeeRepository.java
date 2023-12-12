package com.example.demo.employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    @Query("SELECT e FROM Employee e WHERE e.firstName = ?1 AND e.lastName = ?2")
    Optional<Employee> findEmployeeByFirstNameAndLastName(String firstName, String lastName);
}
