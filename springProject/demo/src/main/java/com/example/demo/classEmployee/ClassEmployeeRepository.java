package com.example.demo.classEmployee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ClassEmployeeRepository extends JpaRepository<ClassEmployee, Integer> {
    @Query("SELECT COUNT(ce) > 0 FROM ClassEmployee ce WHERE ce.className = ?1")
    boolean existsByClassName(String className);
}
