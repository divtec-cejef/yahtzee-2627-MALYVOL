package Objects;

import java.io.Console;
import java.util.ArrayList;

public class Game {
    private final Player player;
    private final ConsoleIO console;

    public Game(ConsoleIO console) {
        this.console = console;

        console.showMessage("Enter player's name: ");
        String name = console.readLine();

        this.player = new Player(name);
    }

    public void play() {
        for (int i = 0; i < 5; i++) {
            Round round = new Round(i+1, console);
            DiceHand hand = round.play();

            Scorecard scorecard = player.getScorecard();
            console.showScores(scorecard.getAvailableCategories(), hand);

            Category chosen = choseCategory(scorecard.getAvailableCategories(), hand);
            scorecard.add(chosen, hand);
            console.showScorecard(scorecard);
        }

    }

    private Category choseCategory(ArrayList<Category> categories, DiceHand hand) {
        boolean valid;
        Category selectedCategory = null;

        do {
            valid=true;
            console.showMessage("\nChose a category ('Enter' for the best score):");
            String input = console.readLine().trim();

            if (input.isEmpty()) {
                Category bestCategory = categories.getFirst();

                for (Category category : categories) {
                    if (category.getScore(hand) > bestCategory.getScore(hand)) {
                        bestCategory = category;
                    }
                }
                selectedCategory = bestCategory;
            } else {
                try {
                    int chosenCategory = Integer.parseInt(input);

                    if (chosenCategory < 1 || chosenCategory > categories.size()) {
                        console.showMessage("Error, chosen category is invalid.");
                        valid = false;
                    } else {
                        selectedCategory = categories.get(chosenCategory - 1 );
                    }

                } catch (NumberFormatException e) {
                    System.out.println("Error, chosen category is invalid.");
                    valid = false;
                }
            }

        }while (!valid);

        return selectedCategory;

        }
    }

