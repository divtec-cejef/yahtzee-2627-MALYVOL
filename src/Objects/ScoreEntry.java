package Objects;

public class ScoreEntry {

    private final Category category;
    private final int score;
    private final int[] diceValues;

    public ScoreEntry(Category category, int score, int[] diceValues) {
        this.category = category;
        this.score = score;
        this.diceValues = diceValues.clone();
    }

    public Category getCategory() {
        return category;
    }

    public int getScore() {
        return score;
    }

    public int[] getDiceValues() {
        return diceValues.clone();
    }
}
