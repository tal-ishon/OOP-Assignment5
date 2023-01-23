import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Tal Ishon.
 * class Rectangle.
 * creates new Rectangles.
 */
public class Rectangle {

    private Point upperLeft;
    private Point upperRight;
    private Point bottomLeft;
    private Point bottomRight;
    private double width;
    private double height;
    private Line upperLine;
    private Line bottomLine;
    private Line leftLine;
    private Line rightLine;
    private Color color;

    /**
     * constructor of a new Rectangle.
     * Create a new rectangle with location and width/height.
     *
     * @param upperLeft the point which indicates the upper left vertex of rectangle
     * @param width     the width of the rectangle
     * @param height    the height of the rectangle
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
        this.upperRight = new Point(upperLeft.getX() + getWidth(), upperLeft.getY());
        this.bottomLeft = new Point(upperLeft.getX(), upperLeft.getY() + getHeight());
        this.bottomRight = new Point(upperRight.getX(), upperRight.getY() + getHeight());
        this.upperLine = new Line(this.upperLeft, this.upperRight);
        this.bottomLine = new Line(this.bottomLeft, this.bottomRight);
        this.leftLine = new Line(this.upperLeft, this.bottomLeft);
        this.rightLine = new Line(this.upperRight, this.bottomRight);
    }

    /**
     * IsRectIntersectingLine method.
     *
     * @param line the line which is checked if intersect this rectangle
     * @return true if intersect, false otherwise.
     */
    public boolean isRectIntersectingLine(Line line) {
        return !this.intersectionPoints(line).isEmpty();
    }

    /**
     * Is point in rect range boolean.
     *
     * @param p the p
     * @return the boolean
     */
    public boolean isPointInRectRange(Point p) {
        return this.getUpperLine().isInRange(p) && this.getRightLine().isInRange(p)
                && this.getLeftLine().isInRange(p) && this.getBottomLine().isInRange(p);
    }

    /**
     * intersectionPoints method.
     *
     * @param line the line we check intersection with rectangle
     * @return a (possibly empty) List of intersection point with the specified line.
     */
    public List<Point> intersectionPoints(Line line) {
        List<Point> myList = new ArrayList<Point>();
        Point p1 = getUpperLine().intersectionWith(line);
        Point p2 = getBottomLine().intersectionWith(line);
        Point p3 = getLeftLine().intersectionWith(line);
        Point p4 = getRightLine().intersectionWith(line);

        if (p1 != null) {
            myList.add(p1);
        }
        if (p2 != null) {
            myList.add(p2);
        }
        if (p3 != null) {
            myList.add(p3);
        }
        if (p4 != null) {
            myList.add(p4);
        }
        return myList;
    }

    /**
     * getWidth method.
     *
     * @return the width of the rectangle
     */
    public double getWidth() {
        return this.width;
    }
    /**
     * getHeight method.
     *
     * @return the height of the rectangle
     */
    public double getHeight() {
        return this.height;
    }
    /**
     * getUpperLeft method.
     *
     * @return the upper-left point of the rectangle
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }
    /**
     * getUpperRight method.
     *
     * @return the upper right of the rectangle.
     */
    public Point getUpperRight() {
        return this.upperRight;
    }
    /**
     * getBottomLeft method.
     *
     * @return the bottom left of the rectangle.
     */
    public Point getBottomLeft() {
        return this.bottomLeft;
    }
    /**
     * getBottomRight method.
     *
     * @return the bottom right of the rectangle.
     */
    public Point getBottomRight() {
        return this.bottomRight;
    }
    /**
     * getUpperLine method.
     *
     * @return the upper line of the rectangle.
     */
    public Line getUpperLine() {
        return this.upperLine;
    }
    /**
     * getBottomLine method.
     *
     * @return the bottom line of the rectangle.
     */
    public Line getBottomLine() {
        return this.bottomLine;
    }
    /**
     * getLeftLine method.
     *
     * @return the left line of the rectangle.
     */
    public Line getLeftLine() {
        return this.leftLine;
    }
    /**
     * getRightLine method.
     *
     * @return the right line of the rectangle.
     */
    public Line getRightLine() {
        return this.rightLine;
    }
    /**
     * Sets color.
     *
     * @param c the c
     */
    public void setColor(Color c) {
        this.color = c;
    }

}
