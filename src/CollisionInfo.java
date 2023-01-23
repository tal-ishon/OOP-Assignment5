/**
 * @author Tal Ishon.
 * CollisionInfo Class.
 * This class holds information about collisions.
 */
public class CollisionInfo {

    private Point collisionPoint;
    private Collidable collisionObject;
    /**
     * construct a CollisionInfo with point and collidable.
     * @param collisionPoint a point.
     * @param collisionObject .
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }
    /**
     * collisionPoint method.
     * @return the point at which the collision occurs.
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }
    /**
     * collisionObject method.
     * @return collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }
}
