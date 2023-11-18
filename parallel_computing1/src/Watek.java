
public class Watek extends Thread {
    private Obraz obraz;
    private char znak;
    private int id;

    Watek(Obraz obraz, char znak, int id) {
        this.obraz = obraz;
        this.znak = znak;
        this.id = id;
    }

    @Override
    public void run(){
        int liczba_powtorzen = obraz.calculate_histogram(znak);
        String info = "Watek: " + id + ", znak: " + znak;

        obraz.show(info, liczba_powtorzen);
    }
}
