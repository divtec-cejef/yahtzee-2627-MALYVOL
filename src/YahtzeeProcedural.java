import java.util.Scanner;

public class YahtzeeProcedural {

    static final int MIN = 1;
    static final int MAX = 6;

    static int[] des = new int[5];

    static int tirerDesAleatoirement() {
        return (int) (Math.floor(Math.random() * (MAX - MIN + 1)) + MIN);
    }

    static void tirerDes() {

        for (int i = 0; i < des.length; i++) {
            des[i] = tirerDesAleatoirement();
        }

    }

    static void affichageDes() {

        for (int i = 0; i < des.length; i++) {
            System.out.println("De " + (i + 1) + " vaut " + des[i]);
        }
    }


    public static void main(String[] args) {
        tirerDes();
        affichageDes();
    }
}
