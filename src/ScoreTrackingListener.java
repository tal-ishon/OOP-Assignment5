/**
 * @author Tal Ishon.
 * ScoreTrackingListener class.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * ScoreTrackingListener constructor.
     * Instantiates a new Score tracking listener.
     *
     * @param scoreCounter the score counter
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

   @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        currentScore.increase(5);
    }
}
