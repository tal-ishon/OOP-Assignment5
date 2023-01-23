import biuoop.DrawSurface;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Tal Ishon.
 * SpriteCollection class.
 * this class is a collection of sprite object.
 */
public class SpriteCollection {
    private List<Sprite> spriteCollection;
    /**
     * construct a SpriteCollection with list of sprite.
     */
    public SpriteCollection() {
        this.spriteCollection = new ArrayList<Sprite>();
    }
    /**
     * construct a SpriteCollection with list of sprite.
     * @param spriteCollection the collection of sprites.
     */
    public SpriteCollection(List<Sprite> spriteCollection) {
        this.spriteCollection = spriteCollection;
    }
    /**
     * addSprite method.
     * add a new Sprite to game.
     * @param s the Sprite that is added.
     */
    public void addSprite(Sprite s) {
        this.spriteCollection.add(s);
    }
    /**
     * notifyAllTimePassed method.
     * call timePassed() on all sprites
     */
    public void notifyAllTimePassed() {
        if (!this.spriteCollection.isEmpty()) { // make sure list is not empty
           List<Sprite> sprites = new ArrayList<>(this.spriteCollection);
            for (Sprite sprite : sprites) {
                sprite.timePassed();
            }
        }
    }
    /**
     * drawAllOn method.
     * call drawOn(d) on all sprites.
     * @param d the surface which is drawn on
     */
    public void drawAllOn(DrawSurface d) {
        if (!this.spriteCollection.isEmpty()) { // make sure list is not empty
            for (Sprite sprite : this.spriteCollection) {
                sprite.drawOn(d);
            }
        }
    }
    /**
     * removeSprite method.
     * Remove the given sprite from sprite's collection.
     *
     * @param s the sprite which is removed from sprite collection.
     */
    public void removeSprite(Sprite s) {
        this.spriteCollection.remove(s);
    }
}
