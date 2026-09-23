package Objects;

public class Die {

    private int value = 0;


    public Die() {
        roll();
    }


    public void roll() {
        value = (int) (Math.floor(Math.random() * (6 - 1 + 1)) + 1);
    }


    public int getValue() {
        return value;
    }
}
