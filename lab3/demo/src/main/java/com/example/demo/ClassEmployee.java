package com.example.demo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ClassEmployee implements Comparator<String>{
    private String nazwaGrupy;
    private ArrayList<Employee> listaPracownikow;
    private int maxIloscPracownikow;

    public ClassEmployee() {
        this.nazwaGrupy = "";
        this.listaPracownikow = new ArrayList<>();
        this.maxIloscPracownikow = 10;
    }
    public ClassEmployee(String nazwaGrupy, int maxIloscPracownikow) {
        this.nazwaGrupy = nazwaGrupy;
        this.maxIloscPracownikow = maxIloscPracownikow;
        this.listaPracownikow = new ArrayList<>();
    }

    public ClassEmployee(String nazwaGrupy, ArrayList<Employee> listaPracownikow, int maxIloscPracownikow) {
        this.nazwaGrupy = nazwaGrupy;
        this.maxIloscPracownikow = maxIloscPracownikow;
        this.listaPracownikow = listaPracownikow;
    }

    public void addEmployee(Employee pracownik) {

        for (Employee e : listaPracownikow) {
            if (e.compareTo(pracownik) == 0) {
                System.out.println("Pracownik o tym imieniu i nazwisku juz istnieje w grupie.");
                return;
            }
        }

        // Sprawdź, czy pojemność grupy nie została przekroczona
        if (listaPracownikow.size() >= maxIloscPracownikow) {
            System.out.println("Przekroczono maksymalna liczbe pracownikow w grupie.");
            return;
        }

        listaPracownikow.add(pracownik);
    }

    public void addSalary(Employee e, double kwota) {
        e.setWynagrodzenie(kwota);
    }

    public void removeEmployee(Employee e) {
        listaPracownikow.remove(e);
    }

    public void changeCondition(Employee e, EmployeeCondition employeeCondition) {
        e.setCondition(employeeCondition);
    }

    public Employee search(String nazwisko) {
        for (Employee e: listaPracownikow) {
            if (compare(nazwisko, e.getNazwisko()) == 0) {
                return e;
            }
        }
        return null;
    }

    @Override
    public int compare(String o1, String o2) {
        return o1.compareTo(o2);
    }

    public ArrayList<Employee> searchPartial(String fragment) {
        ArrayList<Employee> matches = new ArrayList<>();

        for (Employee e : listaPracownikow) {
            if (e.getImie().contains(fragment) || e.getNazwisko().contains(fragment)) {
                matches.add(e);
            }
        }

        return matches;
    }

    public int countByCondition(EmployeeCondition employeeCondition) {
        int count = 0;
        for (Employee e: listaPracownikow) {
            if (e.getEmployeeCondition() == employeeCondition) {
                count++;
            }
        }
        return count;
    }

    public void summary() {
        for (Employee e: listaPracownikow) {
            e.printing();
        }
    }

    public ArrayList<Employee> sortByName() {
        ArrayList<Employee> sortedList = new ArrayList<>(listaPracownikow);

        Collections.sort(sortedList, new Comparator<Employee>() {
            @Override
            public int compare(Employee e1, Employee e2) {
                return e1.getNazwisko().compareTo(e2.getNazwisko());
            }
        });

        return sortedList;
    }

    public ArrayList<Employee> sortBySalary() {
        ArrayList<Employee> sortedList = new ArrayList<>(listaPracownikow);

        Collections.sort(sortedList, new Comparator<Employee>() {
            @Override
            public int compare(Employee e1, Employee e2) {
                return Double.compare(e1.getWynagrodzenie(), e2.getWynagrodzenie());
            }
        });

        return sortedList;
    }

    public double max() {
        Employee e = Collections.max(listaPracownikow, new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                return Double.compare(o1.getWynagrodzenie(), o2.getWynagrodzenie());
            }
        });
        return e.getWynagrodzenie();
    }


    public void setMaxIloscPracownikow(int maxIloscPracownikow) {
        this.maxIloscPracownikow = maxIloscPracownikow;
    }
    public String getNazwaGrupy() {
        return nazwaGrupy;
    }

    public int getMaxIloscPracownikow() {
        return maxIloscPracownikow;
    }

    public ArrayList<Employee> getListaPracownikow() {
        return listaPracownikow;
    }

    public double getIsFullPercentage() {
        return ((double)listaPracownikow.size() / (double) maxIloscPracownikow) * 100;
    }
}
