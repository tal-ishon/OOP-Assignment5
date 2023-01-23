/**
 * @author Tal Ishon.
 * Ass5Game class.
 * this class include the main.
 */
public class Ass5Game {
    /**
     * Main method.
     * runs the game.
     * @param args do nothing
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.initialize();
        game.run();
    }
}
