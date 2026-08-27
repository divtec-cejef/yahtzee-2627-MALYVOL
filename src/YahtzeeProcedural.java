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

    static int nombrePaires = 0;
    static int score = 0;
    static int valeurBrelan = 0;
    static int valeurCarre = 0;
    static int suite = 0;
    static int maxSuite = 0;
    static boolean unBrelan = false;
    static boolean unCarre = false;
    static boolean unYanthzee = false;

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


    static void detecterCombinaison() {
        for (int i = 0; i < des.length; i++) {
            occurrences[des[i] - 1]++;
        }

        for (int i = 0; i < occurrences.length; i++) {
            if (occurrences[i] == 2) {
                nombrePaires++; // Un paire
            } else if (occurrences[i] == 3) {
                unBrelan = true; // Un brelan
                valeurBrelan = i + 1;
            } else if (occurrences[i] == 4) {
                unCarre = true; // Un carré
                valeurCarre = i + 1;
            } else if (occurrences[i] == 5) {
                unYanthzee = true; // Yanthzee
            }
        }

        for (int i = 0; i < occurrences.length; i++) {

            if (occurrences[i] > 0) {

                suite++;

                if (suite > maxSuite) {
                    maxSuite = suite;
                }

            } else {
                suite = 0;
            }
        }
    }

    static void calculerScore() {
        if (nombrePaires == 1 && unBrelan) {
            score += FULL_HOUSE;
        } else if (maxSuite == 5) {
            score += GRANDE_SUITE;
        } else if (maxSuite == 4) {
            score += PETITE_SUITE;
        } else if (nombrePaires == 2) {
            score += DEUX_PAIRE;
        } else if (nombrePaires == 1) {
            score += PAIRE;
        } else if (unYanthzee) {
            score += YATHZEE;
        } else if (unCarre) {
            score += valeurCarre * 4;
        } else if (unBrelan) {
            score += valeurBrelan * 3;
        }
    }

    static int estUnPaire() {
        int paire = 0;
        for (int i = 0; i < des.length; i++) {
            occurrences[des[i] - 1]++;
        }
        for (int i = 0; i < occurrences.length; i++) {
            if (occurrences[i] == 2) {
                return true; // Un paire
                paire++;
            } else {
                return false;
            }
        }
        return paire;
    }

    static boolean estUnDoublePaire() {

    }

    static boolean estUnBrelan() {
            for (int i = 0; i < des.length; i++) {
                occurrences[des[i] - 1]++;
            }
            for (int i = 0; i < occurrences.length; i++) {
                if (occurrences[i] == 3) {
                    return true; // Un Brelan
                } else {
                    return false;
                }
        }


    static boolean estUnCarre() {

    }

    static boolean estUnFullHouse() {

    }

    static boolean estUnePetiteSuite() {

    }

    static boolean estUneGrandeSuite() {

    }

    static boolean estUnYathzee() {

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
        detecterCombinaison();
        calculerScore();
        System.out.println("Votre score est : " + score);
    }
}
