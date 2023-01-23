/**
 * @author Tal Ishon.
 * The interface HitListener.
 */
public interface HitListener {
    /**
     * HitEvent method.
     * This method is called whenever the beingHit object is hit.
     *
     * @param beingHit the Block which is being hit.
     * @param hitter   the hitter is the Ball that's doing the hitting.
     */
    void hitEvent(Block beingHit, Ball hitter);
}
