/**
 * The type Velocity.
 *
 * @author Tal Ishon  class Velocity.  creates new Velocity of balls.
 */
public class Velocity {

    private double dx;
    private double dy;

    /**
     * constructor of a new ball's Velocity.
     *
     * @param dx the x value's direction the ball's velocity
     * @param dy the y value's direction the ball's velocity
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * GetDx method.
     *
     * @return the dx of the ball's velocity
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * GetDy method.
     *
     * @return the dy of the ball's velocity
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * FromAngleAndSpeed method.
     * calculates the dx and dy of the ball's velocity
     * according to the angle and the speed method receives
     *
     * @param angle the angle of the vector's direction
     * @param speed the speed of the ball
     * @return the velocity of the ball
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double radian = Math.toRadians(angle);
        double dx = Math.sin(radian) * speed;
        double dy = Math.cos(radian) * -speed;
        return new Velocity(dx, dy);
    }

    /**
     * GetsSpeed method.
     * Calculates ball's speed and return it.
     * @return the speed of the ball.
     */
    public double getSpeed() {
        return Math.sqrt(getDx() * getDx() + getDy() * getDy());
    }

    /**
     * ApplyToPoint method.
     * Take a point with position (x,y)
     * and return a new point with position (x+dx, y+dy)
     *
     * @param p the point the ball is at right now
     * @return the point on the next step
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + this.dx, p.getY() + this.dy);
    }
}

