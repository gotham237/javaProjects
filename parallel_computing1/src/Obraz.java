import  java.util.Random;

class Obraz {

    private int size_n;
    private int size_m;
    private char[][] tab;
    private char[] tab_symb;
    private int[] histogram;
    private int[] histogram_parallel;
    private int[] histogram_parallel2;

    public Obraz(int n, int m) {

        this.size_n = n;
        this.size_m = m;
        tab = new char[n][m];
        tab_symb = new char[94];

        final Random random = new Random();

        // for general case where symbols could be not just integers
        for(int k=0;k<94;k++) {
            tab_symb[k] = (char)(k+33); // substitute symbols
        }

        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                tab[i][j] = tab_symb[random.nextInt(94)];  // ascii 33-127
                //tab[i][j] = (char)(random.nextInt(94)+33);  // ascii 33-127
                System.out.print(tab[i][j]+" ");
            }
            System.out.print("\n");
        }
        System.out.print("\n\n");

        histogram = new int[94];
        histogram_parallel = new int[94];
        histogram_parallel2 = new int[94];
        clear_histogram();
    }

    public void clear_histogram(){

        for(int i=0;i<94;i++)
        {
            histogram[i]=0;
            histogram_parallel[i] = 0;
            histogram_parallel2[i] = 0;
        }

    }

    public void calculate_histogram(){

        for(int i=0;i<size_n;i++) {
            for(int j=0;j<size_m;j++) {

                // optymalna wersja obliczania histogramu, wykorzystujÄca fakt, Ĺźe symbole w tablicy
                // moĹźna przeksztaĹciÄ w indeksy tablicy histogramu
                // histogram[(int)tab[i][j]-33]++;

                // wersja bardziej ogĂłlna dla tablicy symboli nie utoĹźsamianych z indeksami
                // tylko dla tej wersji sensowne jest zrĂłwnoleglenie w dziedzinie zbioru znakĂłw ASCII
                for(int k=0;k<94;k++) {
                    if(tab[i][j] == tab_symb[k]) histogram[k]++;
                    //if(tab[i][j] == (char)(k+33)) histogram[k]++;
                }

            }
        }

    }

    public int calculate_histogram(char c) {
        for(int i = 0; i < size_n; i++){
            for(int j = 0; j < size_m; j++) {
                if(tab[i][j] == c)
                    histogram_parallel[(int) c - 33]++;
            }
        }

//        if (histogram[(int) c - 33] != histogram_parallel[(int) c - 33]) {
//            System.out.println("Brak zgodności dla znaku: " + c);
//        }

        return histogram_parallel[(int) c - 33];
    }

    //wersja blokowa
    public void calculate_histogram(int start, int end) {
        for(int i = start; i < end; i++) {
            for(int j = 0; j < size_n; j++) {
                for(int k = 0; k < size_m; k++) {
                    if (tab_symb[i] == tab[j][k])
                        histogram_parallel2[i]++;
                }
            }
        }
    }

    public synchronized void show(String info, int liczba_powtorzen) {
        System.out.print(info);
        for(int i = 0; i < liczba_powtorzen; i++){
            System.out.print("=");
        }
        System.out.println();
    }

    public synchronized void show(int start, int end, int id) {
        for(int i = start; i < end; i++) {
            System.out.print("Watek " + id + " znak " + (char)(i + 33));
            for(int j = 0; j < histogram_parallel2[i]; j++) {
                System.out.print("=");
            }
            System.out.println();
        }
    }

// uniwersalny wzorzec dla rĂłĹźnych wariantĂłw zrĂłwnoleglenia - moĹźna go modyfikowaÄ dla
// rĂłĹźnych wersji dekompozycji albo stosowaÄ tak jak jest zapisane poniĹźej zmieniajÄc tylko
// parametry wywoĹania w wÄtkach
//
//calculate_histogram_wzorzec(start_wiersz, end_wiersz, skok_wiersz,
//                            start_kol, end_kol, skok_kol,
//                            start_znak, end_znak, skok_znak){
//
//  for(int i=start_wiersz;i<end_wiersz;i+=skok_wiersz) {
//     for(int j=start_kol;j<end_kol;j+=skok_kol) {
//        for(int k=start_znak;k<end_znak;k+=skok_znak) {
//           if(tab[i][j] == tab_symb[k]) histogram[k]++;
//


    public void print_histogram(){

        for(int i=0;i<94;i++) {
            System.out.print(tab_symb[i]+" "+histogram[i]+"\n");
            //System.out.print((char)(i+33)+" "+histogram[i]+"\n");
        }

    }

    public void validate() {
        boolean condition = true;
        for(int i = 0; i < histogram.length; i++) {
            if(histogram[i] != histogram_parallel2[i]) {
                condition = false;
                System.out.println("Brak zgodności");
                break;
            }
        }
        if(condition) {
            System.out.println("Tablice sa zgodne");
        }
    }

}
