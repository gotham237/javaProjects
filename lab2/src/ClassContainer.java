import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ClassContainer {
    private Map<String, ClassEmployee> grupyPracownicze;

    public ClassContainer() {
        this.grupyPracownicze = new HashMap<>();
    }

    public void addClass(String nazwaGrupy, int maxIloscPracownikow) {
        ClassEmployee grupa = new ClassEmployee();
        Employee employee1 = new Employee("Test1", "test1", EmployeeCondition.DELEGACJA, 2002, 6000);
        Employee employee2 = new Employee("Test2", "test2", EmployeeCondition.OBECNY, 2000, 8000);
        grupa.addEmployee(employee1);
        grupa.addEmployee(employee2);
        grupa.setMaxIloscPracownikow(maxIloscPracownikow);
        grupyPracownicze.put(nazwaGrupy, grupa);
    }

    public void removeClass(String nazwaGrupy) {
        grupyPracownicze.remove(nazwaGrupy);
    }

    public ArrayList<String> findEmpty() {
        ArrayList<String> pusteGrupy = new ArrayList<>();

        for (Map.Entry<String, ClassEmployee> entry : grupyPracownicze.entrySet()) {
            String nazwaGrupy = entry.getKey();
            ClassEmployee grupa = entry.getValue();

            if (grupa.getListaPracownikow().isEmpty()) {
                pusteGrupy.add(nazwaGrupy);
            }
        }

        return pusteGrupy;
    }

    public void summary() {
        for (Map.Entry<String, ClassEmployee> entry : grupyPracownicze.entrySet()) {
            String nazwaGrupy = entry.getKey();
            ClassEmployee grupa = entry.getValue();

            double procentoweZapelnienie = (double)(grupa.getListaPracownikow().size() / (double) grupa.getMaxIloscPracownikow()) * 100;

            System.out.println("Grupa: " + nazwaGrupy);
            System.out.println("Procentowe zapełnienie: " + procentoweZapelnienie + "%");
            System.out.println();
        }
    }
}

