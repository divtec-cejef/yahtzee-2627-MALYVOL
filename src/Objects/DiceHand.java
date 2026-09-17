package Objects;

public class DiceHand {

    private Die[] dice = new Die[5];


    public DiceHand() {
        for (int i = 0; i < dice.length; i++) {
            dice[i] = new Die();
        }

        rollAll();
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

    public Die getDie(int index) {
        return dice[index];
    }

    public int[] getValues() {
        int[] values = new int[5];
        for (int i = 0; i < dice.length; i++) {
            values[i] = getDie(i).getValue();
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


}
