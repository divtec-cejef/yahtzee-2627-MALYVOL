import java.lang.classfile.attribute.PermittedSubclassesAttribute;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class YahtzeeProcedural {

    static final int MIN = 1;
    static final int MAX = 6;
    static final int MANCHES = 5;

    static final int PAIRE = 5;
    static final int DEUX_PAIRES = 10;
    static final int FULL_HOUSE = 25;
    static final int PETITE_SUITE = 30;
    static final int GRANDE_SUITE = 40;
    static final int YATHZEE = 50;

    static int[] des = new int[5];
    static int[] occurrences = new int[6];
    static ArrayList<String> combinaisonsDisponibles = new ArrayList<>();
    static ArrayList<String> combinaisonsUtilisees = new ArrayList<>();
    static int score = 0;
    static int valeurBrelan = 0;
    static int valeurCarre = 0;

    static int tirerDesAleatoirement() {
        return (int) (Math.floor(Math.random() * (MAX - MIN + 1)) + MIN);
    }

    static void initialiserCombinaisons() {
        combinaisonsDisponibles.add("Paire");
        combinaisonsDisponibles.add("Deux Paires");
        combinaisonsDisponibles.add("Brelan");
        combinaisonsDisponibles.add("Carré");
        combinaisonsDisponibles.add("Full House");
        combinaisonsDisponibles.add("Petite Suite");
        combinaisonsDisponibles.add("Grande Suite");
        combinaisonsDisponibles.add("Yahtzee");
    }

    static void tirerDes() {

        for (int i = 0; i < des.length; i++) {
            des[i] = tirerDesAleatoirement();
        }

    }


    static void affichageDes() {
        System.out.println("\n");
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
            if (occurrences[i] >= 3) {
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

        return maxSuite >= 4;
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
        boolean estFullHouse = false;
        boolean brelan = false;
        boolean paire = false;

        for (int occurrence : occurrences) {
            if (occurrence == 3) {
                brelan = true;
            }

            if (occurrence == 2) {
                paire = true;
            }
        }

        if (brelan && paire) {
            estFullHouse = true;
        }

        return estFullHouse;
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

    static void calculerScore(String combinaisonChoisie) {

        if (combinaisonChoisie.equals("Paire") && estUnPaire()) {
            score += PAIRE;

        } else if (combinaisonChoisie.equals("Deux Paires") && estUnDoublePaire()) {
            score += DEUX_PAIRES;

        } else if (combinaisonChoisie.equals("Brelan") && estUnBrelan()) {
            score += valeurBrelan * 3;

        } else if (combinaisonChoisie.equals("Carré") && estUnCarre()) {
            score += valeurCarre * 4;

        } else if (combinaisonChoisie.equals("Full House") && estUnFullHouse()) {
            score += FULL_HOUSE;

        } else if (combinaisonChoisie.equals("Petite Suite") && estUnePetitSuite()) {
            score += PETITE_SUITE;

        } else if (combinaisonChoisie.equals("Grande Suite") && estUneGrandeSuite()) {
            score += GRANDE_SUITE;

        } else if (combinaisonChoisie.equals("Yahtzee") && estUnYathzee()) {
            score += YATHZEE;
        }
    }

    static void affichageScore() {
        compterOccurrences();
        System.out.println("\n");
        if (combinaisonsUtilisees.contains("Paire")) {
            System.out.println("-");
        } else if (estUnPaire() && !combinaisonsUtilisees.contains("Paire")) {
            System.out.printf("\n%-20s %d%n", "1) Une Paire : ", PAIRE);
            combinaisonsDisponibles.add("Paire");
        } else {
            System.out.printf("%-20s %d%n", "1) Une Paire : ", 0);
            combinaisonsDisponibles.add("Paire");
        }

        if (combinaisonsUtilisees.contains("Deux Paires")) {
            System.out.println("-");
        } else if (estUnDoublePaire() && !combinaisonsUtilisees.contains("Deux Paires")) {
            System.out.printf("%-20s %d%n", "2) Deux Paires : ", DEUX_PAIRES);
            combinaisonsDisponibles.add("Deux Paires");
        } else {
            System.out.printf("%-20s %d%n", "2) Deux Paires : ", 0);
        }

        if (combinaisonsUtilisees.contains("Brelan")) {
            System.out.println("-");
        } else if (estUnBrelan() && !combinaisonsUtilisees.contains("Brelan")) {
            System.out.printf("%-20s %d%n", "3) Brelan : ", valeurBrelan*3);
            combinaisonsDisponibles.add("Brelan");
        } else {
            System.out.printf("%-20s %d%n", "3) Brelan : ", 0);
        }

        if (combinaisonsUtilisees.contains("Carré")) {
            System.out.println("-");
        } else if (estUnCarre() && !combinaisonsUtilisees.contains("Carré")) {
            System.out.printf("%-20s %d%n", "4) Carré : ", valeurCarre*4);
            combinaisonsDisponibles.add("Carré");
        } else {
            System.out.printf("%-20s %d%n", "4) Carré : ", 0);
        }

        if (combinaisonsUtilisees.contains("Full House")) {
            System.out.println("-");
        } else if (estUnFullHouse() && !combinaisonsUtilisees.contains("Full House")) {
            System.out.printf("%-20s %d%n", "5) Full House : ", FULL_HOUSE);
            combinaisonsDisponibles.add("Full House");
        } else {
            System.out.printf("%-20s %d%n", "5) Full House : ", 0);
        }


        if (combinaisonsUtilisees.contains("Petite Suite")) {
            System.out.println("-");
        } else if (estUnePetitSuite() && !combinaisonsUtilisees.contains("Petite Suite")) {
            System.out.printf("%-20s %d%n", "6) Petite Suite : ", PETITE_SUITE);
            combinaisonsDisponibles.add("Petite Suite");
        } else {
            System.out.printf("%-20s %d%n", "6) Petite Suite : ", 0);
        }


        if (combinaisonsUtilisees.contains("Grande Suite")) {
            System.out.println("-");
        } else if (estUneGrandeSuite() && !combinaisonsUtilisees.contains("Grande Suite")) {
            System.out.printf("%-20s %d%n", "7) Grande Suite : ", GRANDE_SUITE);
            combinaisonsDisponibles.add("Grande Suite");
        } else {
            System.out.printf("%-20s %d%n", "7) Grande Suite : ", 0);
        }


        if (combinaisonsUtilisees.contains("Yahtzee")) {
            System.out.println("-");
        } else if (estUnYathzee() && !combinaisonsUtilisees.contains("Yahtzee")) {
            System.out.printf("%-20s %d%n", "8) Yahtzee : ", YATHZEE);
            combinaisonsDisponibles.add("Yahtzee");
        } else {
            System.out.printf("%-20s %d%n", "8) Yahtzee : ", 0);
        }
    }


    static int demanderGarderCombinaisons() {
        boolean valide = true;
        int garder = 0;
        Scanner scanner = new Scanner(System.in);
        do {
            valide = true;
            System.out.println("\nQuelle combinaison voulez-vous garder ?");
            String choix = scanner.nextLine();

            garder = Integer.parseInt(choix);
            ;
            if (garder <= 0 || garder > 8) {
                System.out.println("Error, saisie " + garder + " est invalide.");
                valide = false;
            }

        } while (!valide);

        return garder-1;
    }


    public static void main(String[] args) {
        for (int i = 0; i < MANCHES; i++) {
            tirerDes();
            affichageDes();
            initialiserCombinaisons();

            for (int j = 0; j < 2; j++) {
                int[] positions = demanderDesARelancer();
                if (positions.length == 0) {
                    break;
                } else {
                    reLance(positions);
                }
            }
            affichageScore();

            boolean valide = false;
            String bomboclat = "";
            int choixBomboclat = 0;
            do {
                int choix = demanderGarderCombinaisons();

                String combinaisonChoisie = combinaisonsDisponibles.get(choix);

                if (Objects.equals(combinaisonChoisie, "-")) {
                    System.out.println("\nErreur, cette combinaison a été déjà choisi");
                } else {
                    bomboclat = combinaisonChoisie;
                    choixBomboclat = choix;
                    System.out.println("\nVous avez choisi : " + combinaisonChoisie);
                    valide = true;

                }
            } while (!valide);

            calculerScore(bomboclat);
            combinaisonsUtilisees.add(bomboclat);
            combinaisonsDisponibles.add(choixBomboclat+1, "-");
            combinaisonsDisponibles.remove(choixBomboclat);

            if (i != 4) {
            System.out.println("\nVotre score est : " + score);
            }
        }
        System.out.println("\nVotre score final est : " + score);
    }
}
