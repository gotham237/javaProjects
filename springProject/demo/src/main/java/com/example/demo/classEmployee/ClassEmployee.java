package com.example.demo.classEmployee;

import com.example.demo.employee.Employee;
import com.example.demo.rate.Rate;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "classEmployees")
public class ClassEmployee implements Serializable {
    private static final long serialVersionUID = 2L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private int id;

    @Column(name = "className", nullable = false)
    private String className;

    @Column(name = "maxNumberOfEmployees", nullable = false)
    private int maxNum;

    @ManyToMany(mappedBy = "classEmployees")
    private Set<Employee> employees = new HashSet<>();

    @OneToMany(mappedBy = "classEmployee")
    private List<Rate> rates;

    public double getIsFullPercentage() {
        return ((double)getEmployees().size() / (double)this.maxNum) * 100;
    }
    public Set<Employee> getEmployees() {
        return this.employees;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassName() {
        return className;
    }

    public void setMaxNum(int maxNum) {
        this.maxNum = maxNum;
    }
    public int getMaxNum() {
        return this.maxNum;
    }
}

