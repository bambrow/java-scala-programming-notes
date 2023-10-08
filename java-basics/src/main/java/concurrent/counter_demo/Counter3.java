package concurrent.counter_demo;

/**
 * A typical thread-safe counter.
 */
public class Counter3 {
    private int count = 0;

    public void add(int k) {
        synchronized (this) {
            count += k;
        }
    }

    public void sub(int k) {
        synchronized (this) {
            count -= k;
        }
    }

    // same as synchronized (this)
    public synchronized void inc() {
        count += 1;
    }

    public synchronized void dec() {
        count -= 1;
    }

    public int get() {
        return count;
    }
}
