package Objects;

public class Player {

    private final String name;
    private final Scorecard scorecard;

    public Player (String name) {
        this.name = name;
        this.scorecard = new Scorecard();
    }

    public String getName() {
        return name;
    }

    public Scorecard getScorecard() {
        return scorecard;
    }
}
