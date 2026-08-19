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

    static void reLance() {
        Scanner scanner = new Scanner(System.in);
        String choix;

        for (int i= 0; i < 2; i++){
        System.out.println("\nQuels des voulez-vous relancer ? (0 pour arreter)");
        choix = scanner.nextLine();

        if (choix.equals("0") || choix.isEmpty()) {
            affichageDes();
            break;
        } else {
            for (int j = 0; j < choix.split(" ").length; j++) {
                des[(Integer.parseInt(choix.split(" ")[j]) - 1)] = tirerDesAleatoirement();
            }
            affichageDes();
        }

        }


    }

    static String demandeReLance() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nQuels des voulez-vous relancer ? (0 pour arreter)");
        String choix = scanner.nextLine();

        if (choix.isEmpty() || choix.equals("0")) {

        }

        return choix;
    }



    public static void main(String[] args) {
        tirerDes();
        affichageDes();
        reLance();
    }
}
