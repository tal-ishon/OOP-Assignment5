import biuoop.DrawSurface;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The type Block.
 *
 * @author Tal Ishon.
 * Block class.
 * This class represents block.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle rectangle;
    private Color color;
    private List<HitListener> hitListeners;

    /**
     * construct a block from a rectangle.
     *
     * @param rect the rectangle that defines the block.
     */
    public Block(Rectangle rect) {
        this.rectangle = rect;
        int myColor = new Random().nextInt();
        this.color = new Color(myColor);
        this.hitListeners = new ArrayList<>();
    }

    /**
     * construct a block from a rectangle and color.
     *
     * @param rect  the rectangle that defines the block.
     * @param color the rectangle color.
     */
    public Block(Rectangle rect, Color color) {
        this.rectangle = rect;
        this.color = color;
        this.hitListeners = new ArrayList<>();
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Velocity v = currentVelocity;
        // collision point collides with horizontal line
        if (this.rectangle.getUpperLine().isInRange(collisionPoint)
            || this.rectangle.getBottomLine().isInRange(collisionPoint)) {
            v = new Velocity(currentVelocity.getDx(), -1 * currentVelocity.getDy());
        }
        // collision point collides with vertical line
        if (this.rectangle.getLeftLine().isInRange(collisionPoint)
            || this.rectangle.getRightLine().isInRange(collisionPoint)) {
            v = new Velocity(-1 * currentVelocity.getDx(), currentVelocity.getDy());
        }
        this.notifyHit(hitter);
        return v;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle((int) this.rectangle.getUpperLeft().getX(), (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
        d.setColor(Color.black);
        d.drawRectangle((int) this.rectangle.getUpperLeft().getX(), (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
    }

    @Override
    public void timePassed() {

    }

    /**
     * addToGame method.
     * Adds the ball to the game's sprite and Collidable list.
     *
     * @param g the game that played.
     */
    public void addToGame(Game g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * RemoveFromGame method.
     *
     * @param game the game we want to remove the block from
     */
    public void removeFromGame(Game game) {
        game.removeSprite(this);
        game.removeCollidable(this);
    }
    /**
     * NotifyHit method.
     * the method notify all gamemanagement.listeners about a hit event.
     * this method gets a ball that is hitting the block and notify
     * all the hit gamemanagement.listeners about the hit.
     *
     * @param hitter the game we want to remove the block from
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<>(this.hitListeners);
        // Notify all gamemanagement.listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }
}
