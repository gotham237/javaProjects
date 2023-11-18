import java.util.Scanner;

class Histogram_test {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Set image size: n (#rows), m(#kolumns)");
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        Obraz obraz_1 = new Obraz(n, m);

        obraz_1.calculate_histogram();
        obraz_1.print_histogram();

        System.out.println("Set number of threads");
        int num_threads = scanner.nextInt();

        Watek[] NewThr = new Watek[num_threads];
        //Thread[] NewThr2 = new Thread[num_threads];

        for (int i = 0; i < num_threads; i++) {
            (NewThr[i] = new Watek(obraz_1, (char)(i + 33), i)).start();
        }

//        for (int i = 0; i < num_threads; i++) {
//            (NewThr2[i] = new Thread(
//                    new Watek2(obraz_1, i, 15, num_threads)
//            )).start();
//        }

        for (int i = 0; i < num_threads; i++) {
            try {
        	    NewThr[i].join();
                //[i].join();

            } catch (InterruptedException e) {}
        }

        obraz_1.validate();
    }

}

