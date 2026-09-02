import java.lang.classfile.attribute.PermittedSubclassesAttribute;
import java.util.Scanner;

public class YahtzeeProcedural {

    static final int MIN = 1;
    static final int MAX = 6;

    static final int PAIRE = 5;
    static final int DEUX_PAIRES = 10;
    static final int FULL_HOUSE = 25;
    static final int PETITE_SUITE = 30;
    static final int GRANDE_SUITE = 40;
    static final int YATHZEE = 50;

    static int[] des = new int[5];
    static int[] occurrences = new int[6];

    static int score = 0;
    static int valeurBrelan = 0;
    static int valeurCarre = 0;

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

        for (int position : positions) {
            des[position] = tirerDesAleatoirement();
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
        for (int i = 0; i < occurrences.length; i++) {
            occurrences[i] = 0;
        }

        for (int de : des) {
            occurrences[de - 1]++;
        }
    }


    static boolean estUnPaire() {
        for (int occurrence : occurrences) {
            if (occurrence >= 2) {
                return true;
            }
        }
        return false;
    }


    static boolean estUnDoublePaire() {
        int paire = 0;
        for (int occurrence : occurrences) {
            if (occurrence >= 2) {
                paire++;
            }
            if (paire >= 2) {
                return true;
            }
        }
        return false;
    }


    static boolean estUnBrelan() {
        for (int i = 0; i < occurrences.length; i++) {
            if (occurrences[i] == 3) {
                valeurBrelan = i + 1;
                return true;
            }
        }
        return false;
    }


    static boolean estUnCarre() {
        for (int i = 0; i < occurrences.length; i++) {
            if (occurrences[i] == 4) {
                valeurCarre = i + 1;
                return true;
            }
        }
        return false;
    }


    static boolean estUnePetitSuite() {
        int suite = 0;
        int maxSuite = 0;
        for (int occurrence : occurrences) {

            if (occurrence > 0) {

                suite++;

                if (suite > maxSuite) {
                    maxSuite = suite;
                }

            } else {
                suite = 0;
            }
        }

        return maxSuite == 4;
    }


    static boolean estUneGrandeSuite() {
        int suite = 0;
        int maxSuite = 0;
        for (int occurrence : occurrences) {

            if (occurrence > 0) {

                suite++;

                if (suite > maxSuite) {
                    maxSuite = suite;
                }

            } else {
                suite = 0;
            }
        }

        return maxSuite == 5;
    }

    static boolean estUnFullHouse() {
        return estUnPaire() && estUnBrelan();
    }

    static boolean estUnYathzee() {
        boolean estYahtzee = false;
        for (int occurrence : occurrences) {
            if (occurrence == 5) {
                estYahtzee = true; // Un Brelan
                break;
            }
        }
            return estYahtzee;
        }

    static void calculerScore() {
        compterOccurrences();
        if (estUnFullHouse()) {
            score += FULL_HOUSE;
        }

        if (estUneGrandeSuite()) {
            score += GRANDE_SUITE;
        }

        if (estUnePetitSuite()) {
            score += PETITE_SUITE;
        }

        if (estUnDoublePaire()) {
            score += DEUX_PAIRES;
        }

        if (estUnPaire()) {
            score += PAIRE;
        }

        if (estUnYathzee()) {
            score += YATHZEE;
        }

        if (estUnCarre()) {
            score += valeurCarre * 4;
        }

        if (estUnBrelan()) {
            score += valeurBrelan * 3;
        }
    }

    static void affichageScore() {
        calculerScore();

        if (estUnPaire()) {
            System.out.printf("\n%-20s %d%n", "1) Une Paire : ", PAIRE);
        } else {
            System.out.printf("%-20s %d%n", "1) Une Paire : ", 0);
        }

        if (estUnDoublePaire()) {
            System.out.printf("%-20s %d%n", "2) Deux Paires : ", DEUX_PAIRES);
        } else {
            System.out.printf("%-20s %d%n", "2) Deux Paires : ", 0);
        }

        if (estUnBrelan()) {
            System.out.printf("%-20s %d%n", "3) Brelan : ", valeurBrelan*3);
        } else {
            System.out.printf("%-20s %d%n", "3) Brelan : ", 0);
        }

        if (estUnCarre()) {
            System.out.printf("%-20s %d%n", "4) Carré : ", valeurCarre*4);
        } else {
            System.out.printf("%-20s %d%n", "4) Carré : ", 0);
        }

        if (estUnFullHouse()) {
            System.out.printf("%-20s %d%n", "5) Full House : ", FULL_HOUSE);
        } else {
            System.out.printf("%-20s %d%n", "5) Full House : ", 0);
        }

        if (estUnePetitSuite()) {
            System.out.printf("%-20s %d%n", "6) Petite Suite : ", PETITE_SUITE);
        } else {
            System.out.printf("%-20s %d%n", "6) Petite Suite : ", 0);
        }

        if (estUneGrandeSuite()) {
            System.out.printf("%-20s %d%n", "7) Grande Suite : ", GRANDE_SUITE);
        } else {
            System.out.printf("%-20s %d%n", "7) Grande Suite : ", 0);
        }

        if (estUnYathzee()) {
            System.out.printf("%-20s %d%n", "8) Yahtzee : ", YATHZEE);
        } else {
            System.out.printf("%-20s %d%n", "8) Yahtzee : ", 0);
        }
    }

    public static void main(String[] args) {
        tirerDes();
        affichageDes();

        for (int i = 0; i < 2; i++) {
            int[] positions = demanderDesARelancer();
            if (positions.length == 0) {
                break;
            } else {
                reLance(positions);
            }
        }
        affichageScore();
        System.out.printf("\n%-20s %d%n", "Votre score est : ", score);
    }
}
