import java.util.ArrayList;
import java.util.List;

public class ThreeDim extends Figure{
    private Figure figure;
    private double H;

    public ThreeDim(Figure figure, double H) {
        this.figure = figure;
        this.H = H;
    }

    @Override
    public double calculateArea() {
        double result = 0.0;
        List<Double> list;
        list = figure.getSides();
        result += 2 * figure.calculateArea();
        for (Double x : list){
            result += x * H;
        }
        return result;
    }

    @Override
    public double calculatePerimeter() {
        return 0;
    }

    @Override
    public List getSides() {
        return null;
    }

    public double calculateVolume(){
        return figure.calculateArea() * H;
    }

    @Override
    public void print() {
        System.out.println("Wysokosc: " + H);
        System.out.println("Pole powierzchni: " + calculateArea());
        System.out.println("Objetosc: " + calculateVolume());
        System.out.println();
    }
}
