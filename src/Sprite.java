import biuoop.DrawSurface;

/**
 * The interface Sprite.
 */
public interface Sprite {
    /**
     * DrawOn method.
     * Draw the sprite to the screen
     * @param d the surface which is drawn on
     */
    void drawOn(DrawSurface d);

    /**
     * TimePassed method.
     * Notify the sprite that time has passed
     */
    void timePassed();
}
