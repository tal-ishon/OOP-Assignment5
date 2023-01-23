/**
 * BlockRemover class.
 *
 * this class is in charge of removing blocks from the game,
 * as well as keeping count of the number of blocks that remain.
 */
public class BlockRemover implements HitListener {
    private Game game;
    private Counter remainingBlocks;

    /**
     * Constructor of a new Block remover.
     *
     * @param game          the game
     * @param removedBlocks the removed blocks
     */
    public BlockRemover(Game game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = removedBlocks;
    }

    /**
     * GetRemainingBlocks.
     *
     * @return the remaining blocks
     */
    public Counter getRemainingBlocks() {
        return remainingBlocks;
    }

    /**
     * GetGame method.
     *
     * @return the game
     */
    public Game getGame() {
        return game;
    }

    /**
     * HitEvent method.
     *
     * @param beingHit          the block that was hit
     * @param hitter the ball which hit the block
     */
    // Blocks that are hit should be removed
    // from the game. Remember to remove this listener from the block
    // that is being removed from the game.
    public void hitEvent(Block beingHit, Ball hitter) {
        if (getRemainingBlocks().getValue() != 0) {
            getRemainingBlocks().decrease(1); // only 1 block was hit
            beingHit.removeFromGame(getGame());
            beingHit.removeHitListener(this);
        }
    }
}