package Objects;

import java.util.ArrayList;

public class Scorecard {
    private final ArrayList<ScoreEntry> entries = new ArrayList<>();

    public boolean isUsed(Category category) {
        for (ScoreEntry entry : entries) {
            if (entry.getCategory() == category) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Category> getAvailableCategories() {
        ArrayList<Category> available = new ArrayList<>();

        for (Category category : Category.values()) {
            if (!isUsed(category)) {
                available.add(category);
            }
        }
        return available;
    }

    public ScoreEntry add(Category category, DiceHand hand) {
        if (isUsed(category)) {
            throw new IllegalArgumentException("Category already used");
        }

        int score = category.getScore(hand);
        ScoreEntry entry = new ScoreEntry(category, score, hand.getValues());

        entries.add(entry);
        return entry;
    }


    public ArrayList<ScoreEntry> getEntries() {
        return new ArrayList<>(entries);
    }

    public int getTotal() {
        int total = 0;
        for (ScoreEntry entry : entries) {
                total += entry.getScore();
            }
        return total;

        }
    }

