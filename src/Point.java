/**
 * @author Tal Ishon
 * class Point.
 * creates new points.
 * checks if 2 points are equal.
 * calculets distance between 2 points.
 */
public class Point {

    private double x;
    private double y;
    /**
     * constructor Point.
     *
     * @param x value of point.
     * @param y value of point.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Distance method.
     * calculates the distance between this point to other point
     *
     * @param other the other
     * @return the distance of this point to the other point
     */
    public double distance(Point other) {

        double otherX = other.getX();
        double otherY = other.getY();
        double distanceX = this.x - otherX;
        double distanceY = this.y - otherY;

        return Math.sqrt((distanceX * distanceX + distanceY * distanceY));

    }

    /**
     * Equals method.
     * checks if 2 points are the same
     *
     * @param other the other point checked if the same as this point
     * @return true is the points are equal, false otherwise
     */
    public boolean equals(Point other) {

        if (other == null) { // check if point exists. if not - return false
            return false;
        }

        return (this.x == other.getX()) && (this.y == other.getY());

    }

    /**
     * getX method.
     *
     * @return x value of this point
     */
    public double getX() {
        return this.x;
    }

    /**
     * getY method.
     *
     * @return y value of this point
     */
    public double getY() {
        return this.y;
    }

}
