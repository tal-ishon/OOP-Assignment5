import biuoop.DrawSurface;
import java.awt.Color;
import java.util.Random;
/**
 * @author Tal Ishon.
 * Paddle class.
 * This class represents paddle.
 */
public class Paddle implements Sprite, Collidable {
    private biuoop.KeyboardSensor keyboard;
    private Rectangle rectangle;
    private Color color;
    private int screenWidth;
    private int screenHeight;

    /**
     * construct a paddle from a upperLeft, with, height ,color and gui.
     * @param rect the rectangle given.
     * @param width the given with of rectangle.
     * @param height the given height of rectangle.
     * @param keyboard the given KeyboardSensor.
     */
    public Paddle(Rectangle rect, int width, int height, biuoop.KeyboardSensor keyboard) {
        this.rectangle = rect;
        this.screenWidth = width;
        this.screenHeight = height;
        this.keyboard = keyboard;
        int myColor = new Random().nextInt();
        this.color = new Color(myColor);
    }
    /**
     * construct a paddle from a upperLeft, with, height ,color and gui.
     * @param rect the rectangle given.
     * @param width the given with of rectangle.
     * @param height the given height of rectangle.
     * @param keyboard the given KeyboardSensor.
     * @param c the given color.
     */
    public Paddle(Rectangle rect, Color c, int width, int height, biuoop.KeyboardSensor keyboard) {
        this.rectangle = rect;
        this.color = c;
        this.screenWidth = width;
        this.screenHeight = height;
        this.keyboard = keyboard;
    }
    /**
     * moveLeft method.
     * This method move left the block.
     */
    public void moveLeft() {
        this.rectangle = new Rectangle(new Point(
                this.rectangle.getUpperLeft().getX() - 5, this.rectangle.getUpperLeft().getY()),
                    this.rectangle.getWidth(), this.rectangle.getHeight());
    }
    /**
     * moveRight method.
     * This method move right the block.
     */
    public void moveRight() {
        this.rectangle = new Rectangle(new Point(
                this.rectangle.getUpperLeft().getX() + 5, this.rectangle.getUpperLeft().getY()),
                this.rectangle.getWidth(), this.rectangle.getHeight());
    }
    // Sprites
    @Override
    public void timePassed() {
        if (this.keyboard.isPressed(this.keyboard.LEFT_KEY)
                && this.rectangle.getLeftLine().start().getX() > 20) { // including frame's block
            moveLeft();
        }
        if (this.keyboard.isPressed(this.keyboard.RIGHT_KEY)
                && this.rectangle.getRightLine().start().getX() < this.screenWidth - 20) { // including frame's block
            moveRight();
        }
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

    // Collidables
    @Override
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    @Override
    public Velocity hit(Ball b, Point collisionPoint, Velocity currentVelocity) {
        Line collisionLine = new Line(collisionPoint, collisionPoint);
        Velocity v = currentVelocity;
        // case ball hits paddle's top
        if (this.rectangle.getUpperLine().isIntersecting(collisionLine)) {
            v = this.hitTop(collisionLine, currentVelocity);
        }
        // case ball hits paddle's left or right
        if (this.rectangle.getLeftLine().isIntersecting(collisionLine)
                || this.rectangle.getRightLine().isIntersecting(collisionLine)) {
            v = new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
        }
        return v;
    }
    /**
     * hitTop method.
     * This method changes the angle of the ball by the position it hits on paddle's top.
     * @param collisionLine the point which represent the position the ball hits the paddle.
     * @param currentVelocity represents ball's current velocity.
     * @return the new velocity.
     */
    public Velocity hitTop(Line collisionLine, Velocity currentVelocity) {
        double speed = currentVelocity.getSpeed();
        // split paddle into 5 regions
        double splitPaddle = this.rectangle.getWidth() / 5;
        double x = this.rectangle.getUpperLeft().getX();
        double y = this.rectangle.getUpperLeft().getY();
        // generating 5 lines
        Line[] region = new Line[5];
        for (int i = 0; i < region.length; i++) {
            region[i] = new Line(new Point(x, y), new Point(x + splitPaddle, y));
            x = x + splitPaddle;
        }
        if (collisionLine.isIntersecting(region[0])) {
            currentVelocity = Velocity.fromAngleAndSpeed(300, speed);
        }
        if (collisionLine.isIntersecting(region[1])) {
            currentVelocity = Velocity.fromAngleAndSpeed(330, speed);
        }
        if (collisionLine.isIntersecting(region[2])) {
            currentVelocity = new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
        }
        if (collisionLine.isIntersecting(region[3])) {
            currentVelocity = Velocity.fromAngleAndSpeed(30, speed);
        }
        if (collisionLine.isIntersecting(region[4])) {
            currentVelocity = Velocity.fromAngleAndSpeed(60, speed);
        }
        return currentVelocity;
    }
    /**
     * addToGame method.
     * This method add this paddle to the game.
     * @param g a game.
     */
    public void addToGame(Game g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
}
