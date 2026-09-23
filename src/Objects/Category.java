package Objects;

public enum Category {

    PAIRE,
    DEUX_PAIRE,
    BRELAN,
    CARRE,
    FULL_HOUSE,
    PETITE_SUITE,
    GRANDE_SUITE,
    YAHTZEE;

    private Category category;
    private boolean played;

    public String getNom() {
        String nomCombinaison = "";
        switch (this) {
            case PAIRE -> nomCombinaison = "Paire";
            case DEUX_PAIRE -> nomCombinaison = "Deux paire";
            case BRELAN -> nomCombinaison = "Brelan";
            case CARRE -> nomCombinaison = "Carre";
            case FULL_HOUSE -> nomCombinaison = "Full house";
            case PETITE_SUITE -> nomCombinaison = "Petite suite";
            case GRANDE_SUITE -> nomCombinaison = "Grande suite";
            case YAHTZEE -> nomCombinaison = "Yahtzee";
        }
        return nomCombinaison;
    }

    public int getScore(DiceHand diceHand) {
        int score = 0;
        switch (this) {
            case PAIRE:
                for (int occurrence : diceHand.getOccurances()) {
                    if (occurrence >= 2) {
                        score += 5;
                    }
                }
                score += 0;
                break;

            case DEUX_PAIRE:
                int paire = 0;
                for (int occurrence : diceHand.getOccurances()) {
                    if (occurrence >= 2) {
                        paire++;
                    }
                    if (paire >= 2) {
                        score += 10;
                    }
                }
                score += 0;
                break;

            case BRELAN:
                int valeurBrelan = 0;
                for (int i = 0; i < diceHand.getOccurances().length; i++) {
                    if (diceHand.getOccurances()[i] >= 3) {
                        valeurBrelan = i + 1;
                        score = valeurBrelan * 3;
                    }
                }
                score += 0;
                break;

            case CARRE:
                int valeurCarre = 0;
                for (int i = 0; i < diceHand.getOccurances().length; i++) {
                    if (diceHand.getOccurances()[i] == 4) {
                        valeurCarre = i + 1;
                        score = valeurCarre * 4;
                    }
                }
                score += 0;
                break;

            case FULL_HOUSE:
                boolean brelan = false;
                boolean paireBombo = false;

                for (int occurrence : diceHand.getOccurances()) {
                    if (occurrence == 3) {
                        brelan = true;
                    }

                    if (occurrence == 2) {
                        paireBombo = true;
                    }
                }

                if (brelan && paireBombo) {
                    score += 25;
                }

                score += 0;
                break;

            case PETITE_SUITE:
                int suite = 0;
                int maxSuite = 0;
                for (int occurrence : diceHand.getOccurances()) {

                    if (occurrence > 0) {

                        suite++;

                        if (suite > maxSuite) {
                            maxSuite = suite;
                        }

                    } else {
                        suite = 0;
                    }
                }

                if (maxSuite >= 4) {
                    score+=30;
                }
                break;

            case GRANDE_SUITE:
                if (maxSuite == 5) {
                    score += 40;
                }
                break;

            case YAHTZEE:
                boolean estYahtzee = false;
                for (int occurrence : diceHand.getOccurances()) {
                    if (occurrence == 5) {
                        estYahtzee = true; // Un Brelan
                        break;
                    }
                }
                if (estYahtzee) {
                    score += 50;
                }
        }

        return score;
    }
}
