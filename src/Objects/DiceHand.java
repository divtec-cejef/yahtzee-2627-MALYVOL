package Objects;

public class DiceHand {

    private final Die[] dice = new Die[5];

    private int valueThree = 0;
    private int valueFour = 0;

    public DiceHand() {
        for (int i = 0; i < dice.length; i++) {
            dice[i] = new Die();
        }
    }

    public void rollAll() {
        for (int i = 0; i < dice.length; i++) {
            dice[i].roll();
        }
    }

    public void reRoll(int[] positions) {

        for (int position : positions) {
            dice[position].roll();
        }
    }

    public int[] getValues() {
        int[] values = new int[5];
        for (int i = 0; i < dice.length; i++) {
            values[i] = dice[i].getValue();
        }
        return values;
    }

    public int[] getOccurances() {
        int[] occurrences = new int[6];

        for (Die die : dice) {
            occurrences[die.getValue() - 1]++;
        }

        return occurrences;
    }

    public boolean hasPair() {
        int[] occurrences = getOccurances();
        for (int occurrence : occurrences) {
            if (occurrence >= 2) {
                return true;
            }
        }
        return false;
    }


    public boolean hasTwoPairs() {
        int[] occurrences = getOccurances();
        int pair = 0;
        for (int occurrence : occurrences) {
            if (occurrence >= 2) {
                pair++;
            }
            if (pair >= 2) {
                return true;
            }
        }
        return false;
    }


    public boolean hasThreeOfAKind() {
        int[] occurrences = getOccurances();
        for (int i = 0; i < occurrences.length; i++) {
            if (occurrences[i] >= 3) {
                valueThree = i + 1;
                return true;
            }
        }
        return false;
    }


    public boolean hasFourOfAKind() {
        int[] occurrences = getOccurances();
        for (int i = 0; i < occurrences.length; i++) {
            if (getOccurances()[i] >= 4) {
                valueFour = i + 1;
                return true;
            }
        }
        return false;
    }


    public boolean hasSmallStraight() {
        int straight = 0;
        int maxStraight = 0;
        int[] occurrences = getOccurances();
        for (int occurrence : occurrences) {

            if (occurrence > 0) {

                straight++;

                if (straight > maxStraight) {
                    maxStraight = straight;
                }

            } else {
                straight = 0;
            }
        }

        return maxStraight >= 4;
    }


    public boolean hasLargeStraight() {
        int straight = 0;
        int maxStraight = 0;
        int[] occurrences = getOccurances();
        for (int occurrence : occurrences) {

            if (occurrence > 0) {

                straight++;

                if (straight > maxStraight) {
                    maxStraight = straight;
                }

            } else {
                straight = 0;
            }
        }

        return maxStraight == 5;
    }

    public boolean hasFullHouse() {
        boolean isFullHouse = false;
        boolean isThreeOfAKind = false;
        boolean isPair = false;
        int[] occurrences = getOccurances();

        for (int occurrence : occurrences) {
            if (occurrence == 3) {
                isThreeOfAKind = true;
            }

            if (occurrence == 2) {
                isPair = true;
            }
        }

        if (isThreeOfAKind && isPair) {
            isFullHouse = true;
        }

        return isFullHouse;
    }

    public boolean hasYathzee() {
        boolean isYahtzee = false;
        int[] occurrences = getOccurances();

        for (int occurrence : occurrences) {
            if (occurrence == 5) {
                isYahtzee = true;
                break;
            }
        }
        return isYahtzee;
    }

    public int getSum() {
       int sum = 0;
       for (Die die : dice) {
           sum += die.getValue();
       }
       return sum;
    }

    public int getValueThree() {
        return valueThree;
    }

    public int getValueFour() {
        return valueFour;
    }
}
