import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        double a, b, c, H;
        while (true) {
            System.out.println("Wybierz figure: (5 aby wyjsc)");
            System.out.println("1: trojkat");
            System.out.println("2: prostokat");
            System.out.println("3: romb");
            System.out.println("4: figura 3D");
            System.out.print("Podaj numer: ");
            Scanner scanner = new Scanner(System.in);
            int input = scanner.nextInt();
            if (input == 5)
                break;
            switch (input) {
                case (1) -> {
                    System.out.println("Podaj 3 boki trojkata: ");
                    a = scanner.nextDouble();
                    b = scanner.nextDouble();
                    c = scanner.nextDouble();
                    Triangle triangle = new Triangle(a, b, c);
                    triangle.print();
                }
                case (2) -> {
                    System.out.println("Podaj 2 boki prostokata: ");
                    a = scanner.nextDouble();
                    b = scanner.nextDouble();
                    Rectangle rectangle = new Rectangle(a, b);
                    rectangle.print();
                }
                case (3) -> {
                    System.out.println("Podaj 2 boki rombu: ");
                    a = scanner.nextDouble();
                    b = scanner.nextDouble();
                    System.out.print("Podaj wysokosc rombu: ");
                    c = scanner.nextDouble();
                    Diamond diamond = new Diamond(a, b, c);
                    diamond.print();
                }
                case (4) -> {
                    while (true) {
                        System.out.println("Wybierz podstawe: (5 aby cofnac sie do figur 2D)");
                        System.out.println("1: trojkat");
                        System.out.println("2: prostokat");
                        System.out.println("3: romb");
                        System.out.print("Podaj numer: ");
                        int input2 = scanner.nextInt();
                        if (input2 == 5)
                            break;
                        switch (input2) {
                            case (1) -> {
                                System.out.println("Podaj 3 boki trojkata: ");
                                a = scanner.nextDouble();
                                b = scanner.nextDouble();
                                c = scanner.nextDouble();
                                Triangle triangle = new Triangle(a, b, c);
                                System.out.print("Podaj wysokosc graniastoslupa: ");
                                H = scanner.nextDouble();
                                ThreeDim threeDim = new ThreeDim(triangle, H);
                                threeDim.print();
                            }
                            case (2) -> {
                                System.out.println("Podaj 2 boki prostokata: ");
                                a = scanner.nextDouble();
                                b = scanner.nextDouble();
                                Rectangle rectangle = new Rectangle(a, b);
                                System.out.print("Podaj wysokosc prostopadloscianu: ");
                                H = scanner.nextDouble();
                                ThreeDim threeDim = new ThreeDim(rectangle, H);
                                threeDim.print();
                            }
                            case (3) -> {
                                System.out.println("Podaj 2 boki rombu: ");
                                a = scanner.nextDouble();
                                b = scanner.nextDouble();
                                System.out.print("Podaj wysokosc rombu: ");
                                c = scanner.nextDouble();
                                Diamond diamond = new Diamond(a, b, c);
                                System.out.print("Podaj wysokosc prostopadloscianu: ");
                                H = scanner.nextDouble();
                                ThreeDim threeDim = new ThreeDim(diamond, H);
                                threeDim.print();
                            }
                            default -> {
                                System.out.println("Podano zly argument. Sprobuj ponownie.");
                            }
                        }
                    }
                }
                default -> {
                    System.out.println("Podano zly argument. Sprobuj ponownie.");
                }
            }
        }
    }
}