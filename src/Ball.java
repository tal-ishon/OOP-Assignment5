import biuoop.DrawSurface;
/**
 * @author Tal Ishon
 * Ball class.
 * creates new balls.
 */
public class Ball implements Sprite {

    private Point center;
    private int radius;
    private java.awt.Color color;
    private Velocity velocity;
    private GameEnvironment gameEnvironment;
    private Line trajectory;
    private Paddle paddle;

    /**
     * constructor of a  Ball.
     *
     * @param center the center point of the ball
     * @param r      the radius of the ball
     * @param color  the color of the ball
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.radius = r;
        this.color = color;
    }

    /**
     * constructor of a  Ball.
     *
     * @param x     the x value of the center point
     * @param y     the y value of the center point
     * @param r     the radius of the ball
     * @param color the color of the ball
     */
    public Ball(int x, int y, int r, java.awt.Color color) {
        this.center = new Point(x, y);
        this.radius = r;
        this.color = color;
    }

    /**
     * constructor of a  Ball.
     *
     * @param center      the center point of the ball
     * @param r           the radius of the ball
     * @param color       the color of the ball
     * @param environment the environment of the ball
     */
    public Ball(Point center, int r, java.awt.Color color, GameEnvironment environment) {
        this.center = center;
        this.radius = r;
        this.color = color;
        this.gameEnvironment = environment;
    }

    /**
     * SetPaddle method.
     * @param p the paddle we set in ball's environment
     */
    public void setPaddle(Paddle p) {
        this.paddle = p;
    }

    /**
     * GetPaddle method.
     * @return the paddle in ball's environment
     */
    public Paddle getPaddle() {
        return this.paddle;
    }

    /**
     * Add to game.
     *
     * @param g the g
     */
    public void addToGame(Game g) {
        g.addSprite(this);
    }

    /**
     * GetX method.
     *
     * @return the x of the center point
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * GetY method.
     *
     * @return the y of the center point
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * GetSize method.
     *
     * @return the radius of the ball
     */
    public int getSize() {
        return this.radius;
    }

    /**
     * GetColor method.
     *
     * @return the color of the ball
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * DrawOn method.
     * draw the ball on the given DrawSurface
     *
     * @param surface the DrawSurface to draw the ball on
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(getColor());
        surface.fillCircle(getX(), getY(), getSize());

    }

    @Override
    public void timePassed() {
        moveOneStep();
    }

    /**
     * SetVelocity method.
     * set the ball's velocity
     *
     * @param v the object velocity we want to set for the ball
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * SetVelocity method.
     * set the ball's velocity
     *
     * @param dx the x value's direction the ball's velocity
     * @param dy the y value's direction the ball's velocity
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * GetVelocity method.
     *
     * @return the ball's velocity
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * getGameEnvironment method.
     *
     * @return the ball's game environment
     */
    public GameEnvironment getGameEnvironment() {
        return this.gameEnvironment;
    }

    /**
     * Sets game environment.
     *
     * @param environment the environment
     */
    public void setGameEnvironment(GameEnvironment environment) {
        this.gameEnvironment = environment;
    }

    /**
     * SetTrajectory method.
     * Set ball's path (trajectory) on board.
     * Start point from ball's radius and end point according to ball's velocity.
     */
    public void setTrajectory() {
        double x;
        double y;
        if (this.velocity.getDx() < 0) {
            x = this.center.getX() + this.velocity.getDx() - this.getSize();
        } else {
            x = this.center.getX() + this.velocity.getDx() + this.getSize();
        }
        if (this.velocity.getDy() < 0) {
            y = this.center.getY() + this.velocity.getDy() - this.getSize();
        } else {
            y = this.center.getY() + this.velocity.getDy() + this.getSize();
        }
        Point endPoint = new Point(x, y);
        this.trajectory = new Line(this.center, endPoint);
    }

    /**
     * GetTrajectory.
     *
     * @return the ball's trajectory.
     */
    public Line getTrajectory() {
        return this.trajectory;
    }
    /**
     * RemoveFromGame method.
     *
     * @param game the game we want to remove the block from
     */
    public void removeFromGame(Game game) {
        game.removeSprite(this);
    }
    /**
     * MoveOneStep method.
     * moves the ball while making sure ball hit the collidables.
     * checks if ball's next step is in or out of frame.
     * if it doesn't hit collidables - ball continues in same direction,
     * otherwise, changes the ball's direction.
     */
    public void moveOneStep() {
        setTrajectory();
        CollisionInfo collisionInfo = getGameEnvironment().getClosestCollision(getTrajectory());
        Rectangle rectPaddle = this.paddle.getCollisionRectangle();
        if (collisionInfo != null) {
            // move the ball to almost collision point
            this.center = new Point(collisionInfo.collisionPoint().getX() - this.velocity.getDx(),
                    collisionInfo.collisionPoint().getY() - this.velocity.getDy());
            // check if ball hits paddle and if it does - make sure it doesn't get stuck inside
            if (collisionInfo.collisionObject().getCollisionRectangle() == rectPaddle) {
                this.center = new Point(this.center.getX(), rectPaddle.getUpperLeft().getY() - this.radius);
            }
            this.setVelocity(collisionInfo.collisionObject().hit(this,
                    collisionInfo.collisionPoint(), this.getVelocity()));
        } else {
            this.center = this.getVelocity().applyToPoint(this.center); // move the ball to next step
        }
    }
}
