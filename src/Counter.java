/**
 * @author Tal Ishon.
 * Counter class.
 */
public class Counter {
    private int count;
    /**
     * Constructor of a new Counter.
     * @param count the count which initializes the count.
     */
    public Counter(int count) {
        this.count = count;
    }
    /**
     * Constructor of a new Counter.
     * When we don't initialize the Counter with a number,
     * we just start it from 0.
     */
    public Counter() {
        this.count = 0;
    }
    /**
     * Increase method.
     * add number to current count.
     * @param number the number which is added to count
     */
    public void increase(int number) {
        this.count = getValue() + number;
    }
    /**
     * Decrease method.
     * subtract number from current count.
     * @param number the number which is decreased from count
     */
    public void decrease(int number) {
        this.count = getValue() - number;
    }
    /**
     * GetValue method.
     * @return the current count value.
     */
    public int getValue() {
        return this.count;
    }
}
