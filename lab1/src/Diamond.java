import java.util.ArrayList;
import java.util.List;

public class Diamond extends Figure{
    private double a, h;

    public Diamond(double a, double b, double h) {
        if (a <= 0 || h <= 0)
        {
            throw(new IllegalArgumentException("Kazdy bok musi byc wiekszy od zera"));
        }
        this.a = a;
        this.h = h;
    }

    @Override
    public double calculateArea() {
        return a * h;
    }

    @Override
    public double calculatePerimeter() {
        return 4*a;
    }

    @Override
    public List getSides() {
        List list = new ArrayList();
        list.add(a);
        list.add(a);
        list.add(a);
        list.add(a);
        return list;
    }

    @Override
    public void print() {
        System.out.println("Boki rombu: " + a );
        System.out.println("Wysokosc rombu: " + h);
        System.out.println("Pole rombu: " + calculateArea());
        System.out.println("Obwod rombu: " + calculatePerimeter());
        System.out.println();
    }

}
