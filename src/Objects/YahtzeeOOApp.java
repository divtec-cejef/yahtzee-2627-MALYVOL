package Objects;

import java.util.ArrayList;

public class YahtzeeOOApp {
    public static void main(String[] args) {
        ConsoleIO consoleIO = new ConsoleIO();
        Game game = new Game(consoleIO);

        game.play();
    }

}
