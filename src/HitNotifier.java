/**
 * @author Tal Ishon.
 * The interface HitNotifier.
 */
public interface HitNotifier {
    /**
     * AddHitListener method.
     *
     * @param hl a listener to hit events.
     */
    void addHitListener(HitListener hl);

    /**
     * RemoveHitListener method.
     *
     * @param hl the hl we want to remove from the list of gamemanagement.listeners to hit events.
     */
    void removeHitListener(HitListener hl);
}
