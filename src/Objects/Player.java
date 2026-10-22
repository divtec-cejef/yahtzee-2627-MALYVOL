package Objects;

public class Player {

    private String name;
    private Scorecard scorecard;

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
