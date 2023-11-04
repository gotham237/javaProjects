import java.util.ArrayList;
import java.util.List;

public class Rectangle extends Figure{
    private double a, b;

    public Rectangle(double a, double b){
        if (a <= 0 || b <= 0)
        {
            throw(new IllegalArgumentException("Bok prostokata musi byc wiekszy od zera"));
        }
        this.a = a;
        this.b = b;
    }

    @Override
    public double calculateArea() {
        return a * b;
    }

    @Override
    public double calculatePerimeter() {
        return 2*a + 2*b;
    }

    @Override
    public List getSides() {
        List list = new ArrayList();
        list.add(a);
        list.add(b);
        list.add(a);
        list.add(b);
        return list;
    }

    @Override
    public void print() {
        System.out.println("Boki prostokata: " + a + b);
        System.out.println("Pole prostokata: " + calculateArea());
        System.out.println("Obwod prostokata: " + calculatePerimeter());
        System.out.println();
    }
}
