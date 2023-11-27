package org;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "classEmployees")
public class ClassEmployee implements Serializable {
    private static final long serialVersionUID = 2L;

    @Id
    @Column(name = "id", unique = true)
    private int id;

    @Column(name = "className", nullable = false)
    private String className;

    @Column(name = "maxNumberOfEmployees", nullable = false)
    private int maxNum;

    @ManyToMany(mappedBy = "classEmployees")
    private Set<Employee> employees = new HashSet<>();

    public Set<Employee> getEmployees() {
        return this.employees;
    }
}