package com.example.demo;

import java.util.Comparator;

public class Employee implements Comparable<Employee> {
    private String imie;
    private String nazwisko;
    private EmployeeCondition employeeCondition;
    private int rokUrodzenia;
    private double wynagrodzenie;

    public Employee(String imie, String nazwisko, EmployeeCondition employeeCondition, Integer rokUrodzenia, double wynagrodzenie) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.employeeCondition = employeeCondition;
        this.rokUrodzenia = rokUrodzenia;
        this.wynagrodzenie = wynagrodzenie;
    }

    public void printing() {
        System.out.println(
                "Imie: " + imie
                        + " " + nazwisko +
                        ", " + employeeCondition +
                        ", " + rokUrodzenia +
                        ", " + wynagrodzenie
        );
    }

    public String getImie() {
        return this.imie;
    }

    public String getNazwisko() {
        return this.nazwisko;
    }
    public int getRokUrodzenia() { return this.rokUrodzenia; }

    public double getWynagrodzenie() {
        return this.wynagrodzenie;
    }

    public EmployeeCondition getEmployeeCondition() {
        return this.employeeCondition;
    }
    public void setImie(String imie) {
        this.imie = imie;
    }
    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public void setRokUrodzenia(int rokUrodzenia) {
        this.rokUrodzenia = rokUrodzenia;
    }
    public void setWynagrodzenie(double wynagrodzenie) {
        this.wynagrodzenie += wynagrodzenie;
    }

    public void setCondition(EmployeeCondition employeeCondition) {
        this.employeeCondition = employeeCondition;
    }

    @Override
    public int compareTo(Employee e) {
        //zwraca 0 jesli sa rowne
        // -1 jesli nie sa
        int lastNameComparison = this.nazwisko.compareTo(e.nazwisko);
        if (lastNameComparison != 0) {
            return lastNameComparison;
        }

        return this.imie.compareTo(e.imie);
    }

    @Override
    public String toString() {
        return this.imie + " " + this.nazwisko;
    }
}
