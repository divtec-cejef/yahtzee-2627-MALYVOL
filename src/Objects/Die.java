package Objects;

public class Die {

    private final int MIN = 1;
    private final int MAX = 6;
    private int value = 0;

    public Die() {
        roll();
    }

    public void roll() {
        value = (int) (Math.floor(Math.random() * (MAX - MIN + 1)) + MIN);
    }

    public int getValue() {
        return value;
    }
}
