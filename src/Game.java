import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.Color;

/**
 * @author Tal Ishon.
 * Game class.
 * This class creates a game.
 */
public class Game {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private biuoop.KeyboardSensor keyboard;
    private int screenWidth = 800;
    private int screenHeight = 600;
    private GUI gui;
    private Sleeper sleeper;
    private BlockRemover remover;
    private Counter ballsLeft;
    private Paddle paddle;
    private Counter score;
    private ScoreTrackingListener scoreTrackingListener;
    private ScoreIndicator scoreIndicator;

    /**
     * Game method.
     * This method constructs a game object.
     */
    public Game() {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.sleeper = new biuoop.Sleeper();
        this.gui = new biuoop.GUI("Arkanoid", this.screenWidth, this.screenHeight);
        this.keyboard = gui.getKeyboardSensor();
        remover = new BlockRemover(this, new Counter());
        ballsLeft = new Counter();
        score = new Counter();
        scoreTrackingListener = new ScoreTrackingListener(score);
        scoreIndicator = new ScoreIndicator(score, screenWidth, screenHeight);
    }

    /**
     * GetEnvironment.
     *
     * @return the game environment
     */
    public GameEnvironment getEnvironment() {
        return environment;
    }

    /**
     * AddCollidable Method.
     * This method adds a given collidable object to game.
     *
     * @param c a Collidable.
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * AddSprite method.
     * this method adds a given sprite object to game.
     *
     * @param s a sprite.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * RemoveCollidable method.
     *
     * @param c the collidable we want to remove
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * RemoveSprite method.
     *
     * @param s the sprite we want to remove
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * CreatePaddle method.
     * this method creates the paddle of the game.
     */
    public void setPaddle() {
        Rectangle r = new Rectangle(new Point(300, 560), 100, 20);
        this.paddle = new Paddle(r, Color.orange, screenWidth, screenHeight, keyboard);
        paddle.addToGame(this);
        this.addSprite(paddle);
    }

    /**
     * SetBalls method.
     * this method creates the balls of the game.
     */
    public void setBalls() {
        Ball ball = new Ball(new Point(150, 70), 5, Color.white);
        Ball ball2 = new Ball(new Point(240, 70), 5, Color.white);
        Ball ball3 = new Ball(new Point(250, 100), 5, Color.white);
        ball.setVelocity(4, 4);
        ball2.setVelocity(3, 3);
        ball3.setVelocity(5, 4);
        ball.setGameEnvironment(this.environment);
        ball2.setGameEnvironment(this.environment);
        ball3.setGameEnvironment(this.environment);
        ball.addToGame(this);
        ball2.addToGame(this);
        ball3.addToGame(this);
        ball.setPaddle(paddle);
        ball2.setPaddle(paddle);
        ball3.setPaddle(paddle);
        ballsLeft.increase(3);
    }

    /**
     * SetBlocks method.
     * initialize game's blocks.
     */
    public void setBlocks() {
        // creating new beginning point for every row
        Point[] points = new Point[6];
        double width = 55;
        double height = 20;
        for (int i = 0; i < 6; i++) {
            points[i] = new Point(780 - width, 120 + i * height);
        }
        // creating all blocks except of frame's blocks
        int blocks = 12;
        int lines = 6;
        for (int j = 0, i = blocks; i > lines; i--, j++) {
            for (int n = i; n > 0; n--) {
                Block newBlock = new Block(
                                new Rectangle(
                                new Point(points[j].getX(), points[j].getY()), width, height),
                                new Color(300 - 10 * i, 15 * i, 20));
                points[j] = new Point(points[j].getX() - width, points[j].getY());
                newBlock.addToGame(this);
                newBlock.addHitListener(remover);
                newBlock.addHitListener(scoreTrackingListener);
                // initialize blocks number before removing them in game
                remover.getRemainingBlocks().increase(1);
            }
        }
    }

    /**
     * SetFrame method.
     * initialize game's frame.
     */
    public void setFrame() {
        Block[] frameBlocks = new Block[4];
        frameBlocks[0] = new Block(new Rectangle(new Point(0, 20), 800, 20), Color.lightGray);
        frameBlocks[1] = new Block(new Rectangle(new Point(0, 20), 20, 600), Color.lightGray);
        frameBlocks[2] = new Block(new Rectangle(new Point(780, 20), 20, 600), Color.lightGray);
        frameBlocks[3] = new Block(new Rectangle(new Point(0, 600), 800, 0), Color.lightGray);
        for (Block block : frameBlocks) {
            block.addToGame(this);
        }
        BallRemover ballRemover = new BallRemover(this, ballsLeft);
        // creating balls' death-region
        frameBlocks[3].addHitListener(ballRemover);
        scoreIndicator.addToGame(this);
    }

    /**
     * Initialize method.
     * Initialize a new game: create the Blocks and Ball (and Paddle),
     * and add them to the game.
     */
    public void initialize() {
        setFrame();
        setBlocks();
        setPaddle();
        setBalls();
    }

    /**
     * Run method.
     * Run the game -- start the animation loop.
     */
    public void run() {
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        // loop stops when there are no balls left or there are no blocks in game
        while (remover.getRemainingBlocks().getValue() != 0 && ballsLeft.getValue() != 0) {

            long startTime = System.currentTimeMillis(); // timing

            DrawSurface d = gui.getDrawSurface();
            // background.
            d.setColor(Color.gray);
            d.fillRectangle(0, 0, screenWidth, screenHeight);
            this.sprites.drawAllOn(d);
            gui.show(d);
            this.sprites.notifyAllTimePassed();

            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(millisecondsPerFrame);
            }
        }

        if (remover.getRemainingBlocks().getValue() == 0) { // if we removed all blocks in game
            score.increase(100);
        }
        gui.close();
    }
}
