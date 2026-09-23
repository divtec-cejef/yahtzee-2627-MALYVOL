package Objects;

public enum Category {

    PAIR,
    TWO_PAIRS,
    THREE_OF_A_KIND,
    FOUR_OF_A_KIND,
    FULL_HOUSE,
    SMALL_STRAIGHT,
    LARGE_STRAIGHT,
    YAHTZEE;

    public String getNom() {
        String nomCombinaison = "";
        switch (this) {
            case PAIR -> nomCombinaison = "Pair";
            case TWO_PAIRS -> nomCombinaison = "Two pairs";
            case THREE_OF_A_KIND -> nomCombinaison = "Three of a kind";
            case FOUR_OF_A_KIND -> nomCombinaison = "Four of a kind";
            case FULL_HOUSE -> nomCombinaison = "Full house";
            case SMALL_STRAIGHT -> nomCombinaison = "Small straight";
            case LARGE_STRAIGHT -> nomCombinaison = "Large straight";
            case YAHTZEE -> nomCombinaison = "Yahtzee";
        }
        return nomCombinaison;
    }

    public int getScore(DiceHand diceHand) {
        return switch (this) {
            case PAIR -> diceHand.hasPair() ? 5 : 0;
            case TWO_PAIRS -> diceHand.hasTwoPairs() ? 10 : 0;
            case THREE_OF_A_KIND -> diceHand.hasThreeOfAKind() ? diceHand.getValueThree() * 3 : 0;
            case FOUR_OF_A_KIND -> diceHand.hasFourOfAKind() ? diceHand.getValueFour() * 4 : 0;
            case FULL_HOUSE -> diceHand.hasFullHouse() ? 25 : 0;
            case SMALL_STRAIGHT -> diceHand.hasSmallStraight() ? 30 : 0;
            case LARGE_STRAIGHT -> diceHand.hasLargeStraight() ? 40 : 0;
            case YAHTZEE -> diceHand.hasYathzee() ? 50 : 0;
        };
    }
}
