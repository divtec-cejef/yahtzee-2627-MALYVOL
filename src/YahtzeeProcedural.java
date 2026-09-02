import java.util.Scanner;

public class YahtzeeProcedural {

    static final int MIN = 1;
    static final int MAX = 6;

    static final int PAIRE = 5;
    static final int DEUX_PAIRE = 10;
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
            if (occurrence == 2) {
                return true;
            }
        }
        return false;
    }


    static boolean estUnDoublePaire() {
        int paire = 0;
        for (int occurrence : occurrences) {
            if (occurrence == 2) {
                paire++;
            }
            if (paire == 2) {
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
        } else if (estUneGrandeSuite()) {
            score += GRANDE_SUITE;
        } else if (estUnePetitSuite()) {
            score += PETITE_SUITE;
        } else if (estUnDoublePaire()) {
            score += DEUX_PAIRE;
        } else if (estUnPaire()) {
            score += PAIRE;
        } else if (estUnYathzee()) {
            score += YATHZEE;
        } else if (estUnCarre()) {
            score += valeurCarre * 4;
        } else if (estUnBrelan()) {
            score += valeurBrelan * 3;
        }
    }

    static void affichageScore() {
        calculerScore();

        System.out.print("\n1) Une Paire : ") ;
        if (score == PAIRE) {
            System.out.println(score);
        } else {
            System.out.println("0");
        }

        System.out.print("2) Deux Paire : ");
        if (score == DEUX_PAIRE) {
            System.out.println(score);
        } else {
            System.out.println("0");
        }

        System.out.print("3) Brelan : ");
        if (score == (valeurBrelan * 3)) {
            System.out.println(score);
        } else {
            System.out.println("0");
        }

        System.out.print("4) Carré : ");
        if (score == (valeurCarre * 4)) {
            System.out.println(score);
        } else {
            System.out.println("0");
        }

        System.out.print("5) Full House : ");
        if (score == FULL_HOUSE) {
            System.out.println(score);
        } else {
            System.out.println("0");
        }

        System.out.print("6) Petite suite : ");
        if (score == PETITE_SUITE) {
            System.out.println(score);
        } else {
            System.out.println("0");
        }

        System.out.print("7) Grande suite : ");
        if (score == GRANDE_SUITE) {
            System.out.println(score);
        } else {
            System.out.println("0");
        }

        System.out.print("8) Yahtzee : ");
        if (score == YATHZEE) {
            System.out.println(score);
        } else {
            System.out.println("0");
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
        System.out.println("Votre score est : " + score);
    }
}
