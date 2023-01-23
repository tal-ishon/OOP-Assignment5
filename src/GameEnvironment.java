import java.util.ArrayList;
import java.util.List;

/**
 * @author Tal Ishon.
 * GameEnvironment class.
 * The type Game environment.
 */
public class GameEnvironment {
    private  List<Collidable> collidableList;

    /**
     * GameEnvironment method.
     * Constructor of environment.
     * Generates the environment from a new list of Collidables.
     */
    public GameEnvironment() {
        this.collidableList = new ArrayList<Collidable>();
    }
    /**
     * GameEnvironment method.
     * Constructor of environment.
     * Generates the environment from a new list of Collidables.
     * @param list the list with collidables.
     */
    public GameEnvironment(List<Collidable> list) {
        this.collidableList = list;
    }
    /**
     * AddCollidable method.
     * add the given collidable to the environment.
     *
     * @param c the collidable which is added to environment
     */
    public void addCollidable(Collidable c) {
        this.collidableList.add(c);
    }
    /**
     * RemoveCollidable method.
     * Remove the given collidable to the environment.
     *
     * @param c the collidable which is removed to environment
     */
    public void removeCollidable(Collidable c) {
        this.collidableList.remove(c);
    }
    /**
     * getClosestCollision method.
     * Assume an object moving from line.start() to line.end().
     * method calculates which collide in list is the closest to start of line
     *
     * @param trajectory the line the object "moves" on from it's start to it's end
     * @return null if this object will not collide with any of the collidables
     * in this collection or if CollidableList is empty, otherwise, return the information
     * about the closest collision that is going to occur.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        if (this.collidableList.isEmpty()) { // there are no collidables in list
            return null;
        }
        // make a copy of all the collidables in game
        List<Collidable> allCollidables = new ArrayList<>(this.collidableList);
        // creating new empty list of collidables
        List<Collidable> collides = new ArrayList<>();

        for (Collidable collidable : allCollidables) {
            // make a list with all collidabels which collides with line trajectory
            if (collidable.getCollisionRectangle().isRectIntersectingLine(trajectory)) {
                collides.add(collidable);
            }
        }
        if (collides.isEmpty()) { // no collidable collides with line trajectory
            return null;
        }
        // if we got here we have a list of collidables which collides with line trajectory
        // now we want to check which of the collidables is closer to start of line trajectory
        Collidable closestCollidable = collides.get(0);
        Point closestPoint = trajectory.closestIntersectionToStartOfLine(collides.get(0).getCollisionRectangle());
        for (int i = 0; i < collides.size(); i++) {

            Point collClosestPoint = trajectory.closestIntersectionToStartOfLine(
                    collides.get(i).getCollisionRectangle());
            // calculating distance between current collidable's closest intersection point to start of line trajectory
            double distance = collClosestPoint.distance(trajectory.start());

            // check which point is closer to start of line trajectory -
            // current closestPoint or the new point in list
            if (closestPoint.distance(trajectory.start()) > distance) {
                closestCollidable = collides.get(i);
                closestPoint = collClosestPoint;
            }
        }
        return new CollisionInfo(closestPoint, closestCollidable);
    }
}
