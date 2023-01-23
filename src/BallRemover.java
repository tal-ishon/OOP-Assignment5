/**
 * BallRemover class.
 * in charge of removing balls from screen when it's needed.
 */
public class BallRemover implements HitListener {
    private Game game;
    private Counter remainingBalls;

    /**
     * Constructor of a new Block remover.
     *
     * @param game          the game
     * @param removedBall the removed ball
     */
    public BallRemover(Game game, Counter removedBall) {
        this.game = game;
        this.remainingBalls = removedBall;
    }

    /**
     * GetRemainingBlocks.
     *
     * @return the remaining blocks
     */
    public Counter getRemainingBalls() {
        return remainingBalls;
    }

    /**
     * GetGame method.
     *
     * @return the game
     */
    public Game getGame() {
        return game;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        if (getRemainingBalls().getValue() != 0) {
            getRemainingBalls().decrease(1); // only 1 block was hit
            hitter.removeFromGame(getGame());
        }
    }
}
