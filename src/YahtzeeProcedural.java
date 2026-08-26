import java.util.Scanner;
import java.util.concurrent.StructureViolationException;

public class YahtzeeProcedural {

    static final int MIN = 1;
    static final int MAX = 6;

    static int[] des = new int[5];
    static int[] occurrences = new int[6];

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


    static void reLance(int[] positions) {

        for (int j = 0; j < positions.length; j++) {
            des[positions[j]] = tirerDesAleatoirement();
        }
        affichageDes();
    }


    static int[] demanderDesARelancer() {
        int[] positions;
        boolean valide = true;
        Scanner scanner = new Scanner(System.in);
        do {
            valide = true;
            System.out.println("\nQuels des voulez-vous relancer ? (0 pour arreter)");
            String choix = scanner.nextLine();

            if (choix.equals("0") || choix.isEmpty()) {
                return new int[0];
            }

            String[] morceaux = choix.split(" ");
            positions = new int[morceaux.length];

            for (int j = 0; j < morceaux.length; j++) {
                positions[j] = Integer.parseInt(morceaux[j]) - 1;
                if (positions[j] < 0 || positions[j] > 4) {
                    System.out.println("Error, saisie " + morceaux[j] + " est invalide.");
                    valide = false;
                }
            }

        } while (!valide);

        return positions;
    }

    static void compterOccurrences() {

        for (int i = 0; i < des.length; i++) {
            occurrences[ des[i] - 1 ] ++;
            }

    }


    static void affichageOccurences() {

        for (int i = 0; i < occurrences.length; i++) {
            System.out.println("[Numero " + (i+1) + "] : " + occurrences[i]);
        }
    }


    static void detecterCombinaison() {
        int nombrePaires = 0;
        boolean unBrelan = false;
        boolean unCarre = false;
        boolean unYanthzee = false;
        for (int i = 0; i < occurrences.length; i++) {
            if (occurrences[i] == 2) {
                nombrePaires++; // Un paire
            } else if (occurrences[i] == 3) {
                unBrelan = true; // Un brelan
            } else if (occurrences[i] == 4) {
                unCarre = true; // Un carré
            } else if (occurrences[i] == 5) {
                unYanthzee = true; // Yanthzee
            }
        }
        if (nombrePaires == 2) {
            // Deux paires
        }

        if (nombrePaires == 1 && unBrelan) {
        // Full house
        }
    }


    public static void main(String[] args) {
        tirerDes();
        affichageDes();
        compterOccurrences();
        affichageOccurences();
        for (int i = 0; i < 2; i++) {
            int[] positions = demanderDesARelancer();
            if (positions.length == 0) {
                break;
            } else {
                reLance(positions);
            }
        }
    }
}
