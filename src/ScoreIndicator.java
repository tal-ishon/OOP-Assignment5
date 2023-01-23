import biuoop.DrawSurface;
import java.awt.Color;

/**
 * ScoreIndicator class.
 * a score indicator is in charge of displaying the current score.
 */
public class ScoreIndicator implements Sprite {
    private Counter scoreCounter;
    private int width;
    private int height;
    /**
     * ScoreIndicator constructor.
     * @param counter counts the current score.
     * @param width the width of the indicator.
     * @param height the height of the indicator.
     */
    public ScoreIndicator(Counter counter, int width, int height) {
        this.scoreCounter = counter;
        this.width = width;
        this.height = height;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.orange);
        d.fillRectangle(0, 0, this.width, 20);
        d.setColor(Color.black);
        d.drawRectangle(0, 0, this.width, 20);
        d.setColor(Color.black);
        d.drawText((this.width / 2) - 20, 15, "Score: " + this.scoreCounter.getValue(), 12);
    }

    @Override
    public void timePassed() {
    }
    /**
     * addToGame method.
     * This method add this ScoreIndicator to the game.
     * @param g a game.
     */
    public void addToGame(Game g) {
        g.addSprite(this);
    }
}

