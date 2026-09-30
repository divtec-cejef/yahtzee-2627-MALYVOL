package Objects;

import java.util.ArrayList;

public class Scorecard {
    private ArrayList<ScoreEntry> entries;

    public boolean isUsed(Category category) {
        for (ScoreEntry entry : entries) {
            if (entry.getCategory() == category) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Category> getAvailableCategories() {

    }

    public ScoreEntry add(category, hand) {

    }


    public ArrayList<ScoreEntry> getEntries() {
        return entries;
    }

    public int getTotal() {
        int total = 0;
        for (ScoreEntry entry : entries) {
                total += entry.getScore();
            }
        return total;

        }
    }

