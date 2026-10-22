package Objects;

import java.util.Scanner;
import java.util.ArrayList;

public class ConsoleIO {
    private final Scanner scanner = new Scanner(System.in);

    public void showMessage(String messsage) {
        System.out.println(messsage);
    }

    public String readLine() {
        return scanner.nextLine();
    }

    public int[] readDicePositions() {
        String input = readLine().trim();

        if (input.isEmpty() || input.equals("0")) {
            return new int[0];
        }

        String[] parts = input.split("\\s+");
        int[] positions = new int[parts.length];

        for (int j = 0; j < parts.length; j++) {
            positions[j] = Integer.parseInt(parts[j]) - 1;
            }
        return positions;
    }

    public void showDice(DiceHand hand) {
        int[] dice = hand.getValues();

        for (int i = 0; i < dice.length; i++) {
            System.out.println("Die " + (1 + i) + " : " + dice[i]);
        }
    }

    public void showScores(ArrayList<Category> categories, DiceHand hand) {
        for (Category category : categories) {
            System.out.println(category.getNom() + " : " + category.getScore(hand));
        }
    }

    public void showScorecard(Scorecard scorecard) {
        for (ScoreEntry entry : scorecard.getEntries()) {
            System.out.println(entry.getCategory().getNom() + " : " + entry.getScore());
        }
        System.out.println("Total : " + scorecard.getTotal());
    }
}
