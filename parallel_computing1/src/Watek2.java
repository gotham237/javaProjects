public class Watek2 implements Runnable {
    private Obraz obraz;
    private int id;
    private int liczba_znakow;
    private int liczba_watkow;

    Watek2(Obraz obraz, int id, int liczba_znakow, int liczba_watkow) {
        this.obraz = obraz;
        this.id = id;
        this.liczba_watkow = liczba_watkow;
        this.liczba_znakow = liczba_znakow;
    }

    @Override
    public void run() {
        int start = (int) Math.ceil((double) liczba_znakow / (double) liczba_watkow) * id;
        int end = (int) Math.ceil((double) liczba_znakow / (double) liczba_watkow) * (id + 1);
        end = Math.min(end, liczba_znakow);

        obraz.calculate_histogram(start, end);
        obraz.show(start, end, id);
    }
}
