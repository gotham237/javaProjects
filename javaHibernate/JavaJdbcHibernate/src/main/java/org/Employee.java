package org;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name = "employees")
public class Employee implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private int id;

    @Column(name = "firstName", nullable = false)
    private String firstName;

    @Column(name = "lastName", nullable = false)
    private String lastName;

    @Column(name = "salary", nullable = false)
    private int salary;

    @Column(name = "employeeCondition", nullable = false)
    @Enumerated(EnumType.STRING)
    private EmployeeCondition employeeCondition;

    @Column(name = "birthYear", nullable = false)
    private int birthYear;

    @ManyToMany(
            cascade = {CascadeType.PERSIST, CascadeType.MERGE}
    )
    @JoinTable(
            name = "employee_classes",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "classEmployee_id")
    )
    private Set<ClassEmployee> classEmployees = new HashSet<>();

    public void addClassEmployee(ClassEmployee ce) {
        this.classEmployees.add(ce);
        ce.getEmployees().add(this);
    }

    public void removeClassEmployee(ClassEmployee ce) {
        this.classEmployees.remove(ce);
        ce.getEmployees().remove(this);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setEmployeeCondition(EmployeeCondition ec) {
        this.employeeCondition = ec;
    }
    public EmployeeCondition getEmployeeCondition(){
        return this.employeeCondition;
    }
    public void setBirthYear(int b) {
        this.birthYear = b;
    }
    public int getBirthYear() {
        return this.birthYear;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
}
