package Objects;

public class Round {

    private final int number;
    private final DiceHand hand;
    private final ConsoleIO console;

    public Round(int number, ConsoleIO console) {
        this.number = number;
        this.console = console;
        this.hand = new DiceHand();
    }

    public DiceHand play() {
        console.showMessage("Round: " + number);
        hand.rollAll();
        console.showDice(hand);
        console.showMessage("Which dice do you want to reroll? (1-5, 0 to stop)");
        for (int i = 0; i < 2; i++) {
            int[] positions = console.readDicePositions();
            if (positions.length == 0) {
                break;
            } else {
                hand.reRoll(positions);
                console.showDice(hand);
            }
        }
        return hand;
    }
}
