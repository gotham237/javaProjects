import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.sqrt;

public class Triangle extends Figure {
    private double a, b, c;

    public Triangle(double a, double b, double c) {
        if (a + b <= c || b + c <= a || a + c <= b)
        {
            throw(new IllegalArgumentException("Boki nie spelniaja warunkow powstawania trojkata"));
        }
        else if(a <= 0 || b <= 0 || c <= 0)
        {
            throw(new IllegalArgumentException("Bok trojkata musi byc wiekszy od 0"));
        }
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public void print() {
        System.out.println("Boki trojkata: " + a + " " + b + " " + c);
        System.out.println("Pole trojkata: " + calculateArea());
        System.out.println("Obwod trojkata: " + calculatePerimeter());
        System.out.println();
    }

    @Override
    public double calculateArea() {
        double s = (a + b + c) / 2;
        double pole = sqrt(s * (s - a) * (s - b) * (s - c));
        return pole;
    }

    @Override
    public double calculatePerimeter() {
        return a + b + c;
    }

    public List getSides() {
        List list = new ArrayList();
        list.add(a);
        list.add(b);
        list.add(c);
        return list;
    }
}
