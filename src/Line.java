/**
 * The type Line.
 *
 * @author Tal Ishon  class Line.
 * creates new lines.
 * finds middle point in line.
 * finds line's length.  checks if 2 lines intersect.
 * checks if 2 lines are equal.
 */
public class Line {

    private Point start;
    private Point end;

    /**
     * constructor Line - Instantiates a new Line from 2 points.
     *
     * @param start the start
     * @param end   the end
     */
// constructors
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * constructor Line - Instantiates a new Line from 4 coordinates.
     *
     * @param x1 the x coordinate of first point
     * @param y1 the y coordinate of first point
     * @param x2 the x coordinate of second point
     * @param y2 the y coordinate of second point
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * Length method.
     * measures the length of this line
     *
     * @return length of this line
     */
    public double length() {
        return this.start.distance(this.end);
    }

    /**
     * Middle method.
     * calculates the middle point of this line
     *
     * @return middle point of this line
     */
    public Point middle() {
        double midXPoint = (this.start.getX() + this.end.getX()) / 2;
        double midYPoint = (this.start.getY() + this.end.getY()) / 2;
        return new Point(midXPoint, midYPoint); // return middle point
    }

    /**
     * Start method.
     *
     * @return start point of this line
     */
    public Point start() {
        return this.start;
    }

    /**
     * End method.
     *
     * @return end point of this line
     */
    public Point end() {
        return this.end;
    }

    /**
     * isIntersecting method.
     * checks if 2 lines intersect each other
     *
     * @param other the line to check intersection with
     * @return true if lines intersect, false otherwise
     */
    public boolean isIntersecting(Line other) {

        int orient1 = this.getOrient(other.start);
        int orient2 = this.getOrient(other.end);
        int orient3 = other.getOrient(start());
        int orient4 = other.getOrient(end());

        // 2 points
        if (this.start.equals(this.end) && other.start.equals(other.end)) {
            return this.equals(other);
        }
        // line and point
        if (this.start.equals(this.end) || other.start.equals(other.end)) {
            // check if line and point on same vector
            if (orient1 == 0 && orient2 == 0 && orient3 == 0 && orient4 == 0) {
                return this.isInRange(other.start) || other.isInRange(this.start);
            }
            return false;
        }
        // 2 lines

        // equals
        if (this.equals(other)) {
            return true;
        }

        // verticals
        if (this.start.getX() == this.end.getX() && other.start.getX() == other.end.getX()) {
            if (this.start.getX() == other.end.getX()) {
                return this.isInRange(other.start) || this.isInRange(other.end);
            }
            return false; // verticals not on same x values don't meet
        }

        // same slope
        if (this.m() == other.m()) {
            // check if line and point on same vector
            if (orient1 == 0 && orient2 == 0 && orient3 == 0 && orient4 == 0) {
                return !this.isInRange(other.start) && !this.isInRange(other.end);
            }
            return false;
        }

        // intersect
        // lines cross each other according to orientation formula
        return (orient1 != orient2) && (orient3 != orient4);
    }


    /**
     * getOrient method.
     * Calculate the orientation (see below) of a given point relative to this line.
     * This is used in order to test whether a line intersect with this line.
     * We need to examine the two edge points from the other line relative to this line.
     * We check whether the point is clockwise or anti-clockwise
     * relative to the vector direction of this line (or directly on the vector).
     * The same thing needs to be done with both points.
     * (Actual direction doesn't matter, as long as we use the same direction
     * when evaluating both points of the other line.)
     * By calculating an equation relating to the determinants of vectors,
     * we find that when one point is relative to the vector
     * clockwise and the other is relative anti-clockwise then the other line intersects with this line.
     *
     * @param p the point that is checked with this line
     * @return a value which symbolise if vectors go clockwise, anti-clockwise or on same vector
     * @see <a href="https://www.youtube.com/watch?v=bbTqI0oqL5U">Find if Lines Intersect</a>
     */
    public int getOrient(Point p) {
        if ((start().getX() - p.getX()) * (end().getY() - p.getY())
                - (end().getX() - p.getX()) * (start().getY() - p.getY()) > 0) {
            return 2;
        }
        if ((start().getX() - p.getX()) * (end().getY() - p.getY())
                - (end().getX() - p.getX()) * (start().getY() - p.getY()) < 0) {
            return 1;
        }
        return 0; // if we got here equation equals 0
    }

    /**
     * M method.
     * find the slope of line
     *
     * @return the slope
     */
    public double m() {
        return ((start().getY() - end().getY()) / (start().getX() - end().getX()));
    }

    /**
     * B method.
     *
     * @return the b value of the equation y = ax + b
     */
    public double b() {
        return this.start.getY() - this.m() * this.start.getX();
    }


    /**
     * IsInRange method.
     * checks if point is in range of line
     *
     * @param p the point is checked if in range
     * @return true if point in line's range, false otherwise
     */
    public boolean isInRange(Point p) {
        return ((min(this.start.getX(), this.end.getX()) <= p.getX()
                && (p.getX() <= max(this.start.getX(), this.end.getX()))
                && (min(this.start.getY(), this.end.getY()) <= p.getY()))
                && (p.getY() <= max(this.start.getY(), this.end.getY())));
    }

    /**
     * Max method.
     * receives to double values and checks which is bigger
     *
     * @param a the value is checked if bigger than b
     * @param b the value is checked if bigger than a
     * @return a if a is bigger than b, returns b if b is bigger or equals a
     */
    public double max(double a, double b) {
        if (a > b) {
            return a;
        }
        return b;
    }

    /**
     * Min method.
     * receives to double values and checks which is smaller
     *
     * @param a the value is checked if smaller than b
     * @param b the value is checked if smaller than a
     * @return a if a is smaller than b, returns b if b is smaller or equals a
     */
    public double min(double a, double b) {
        if (a < b) {
            return a;
        }
        return b;
    }

    /**
     * IntersectionWith method.
     * checks what is the intersection point
     *
     * @param other the line it's intersection point with this line is checked
     * @return the intersection point if the lines intersect, null otherwise
     */
    public Point intersectionWith(Line other) {
        if (!isIntersecting(other)) {
            return null;
        }

        // 2 points
        if (this.start.equals(this.end) && other.start.equals(other.end)) {
            return other.start; // we know for sure that points intersect

        }

        // point and line
        if (this.start.equals(this.end)) { // this is the point
            return this.start; // we checked intersection, so point must be intersection point
        }

        if (other.start.equals(other.end)) { // other is the point
            return other.start; // we checked intersection, so point must be intersection point

        }

        // 2 lines
        // verticals
        if (this.start.getX() == this.end.getX() && other.start.getX() == other.end.getX()) {
            if ((this.isInRange(other.start) && this.isInRange(other.end))
                    || (other.isInRange(this.start) && other.isInRange(this.end))) {
                // one line contains the other - infinity points
                return null;
            }
            // if we got here not both start and end points on segment - one of them

            if (this.start.equals(other.start) || this.end.equals(other.start)) {
                return other.start;
            }
            if (this.start.equals(other.end) || this.end.equals(other.end)) {
                return other.end;
            }
            return null; // lines overlap each other in more then one point
        }

        // same slope
        if (this.m() == other.m()) {
            if ((this.isInRange(other.start) && this.isInRange(other.end))
                    || (other.isInRange(this.start) && other.isInRange(this.end))) {
                // one line contains the other - infinity points
                return null;
            }
            // if we got here not both start and end points on segment - one of them

            if (this.start.equals(other.start) || this.end.equals(other.start)) {
                return other.start;
            }
            if (this.start.equals(other.end) || this.end.equals(other.end)) {
                return other.end;
            }
            return null; // lines overlap each other in more then one point
        }

// if we got here we know lines intersect in one point
        // intersect

        if ((this.start.getX() - this.end.getX()) == 0) { // line this is vertical
           double x = this.start.getX();
           double y = other.m() * x + other.b();
           return new Point(x, y);
        }
        if ((other.start.getX() - other.end.getX()) == 0) { // line other is vertical
            double x = other.start.getX();
            double y = this.m() * x + this.b();
            return new Point(x, y);
        }
        double x = (other.b() - this.b()) / (this.m() - other.m());
        double y = this.m() * x + this.b();
        return new Point(x, y);
    }

    /**
     * closestIntersectionToStartOfLine method.
     * If this line does not intersect with the rectangle, return null.
     * Otherwise, return the closest intersection point to the start of the line.
     *
     * @param rect the rectangle that is checked with this line
     * @return null if there is no intersection,
     * otherwise return the closest intersection point to the start of the line
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        java.util.List<Point> list = rect.intersectionPoints(this);

        if (list == null) {
            return null;
        }

        Point closestPoint = list.get(0); // get first point in list

        for (Point point : list) { // go over list to check which point is the closest to start of line
            if (this.start.distance(closestPoint) > this.start.distance(point)) {
                closestPoint = point;
            }
        }
        return closestPoint;
    }

    /**
     * Equals method.
     * checks if 2 lines are the same
     *
     * @param other the other line checked if the same as this line
     * @return true if lines are equal, false otherwise
     */
    public boolean equals(Line other) {
        return (this.start.equals(other.start) && this.end.equals(other.end))
                || (this.start.equals(other.end) && this.end.equals(other.start));
    }

}
