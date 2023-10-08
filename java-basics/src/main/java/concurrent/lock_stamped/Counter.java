package concurrent.lock_stamped;

import java.util.concurrent.locks.StampedLock;

/**
 * Counter implemented with stamped lock.
 * A stamped lock is a kind of Optimistic Lock compared with read/write lock.
 * However, stamped lock is not a reentrant lock.
 */
public class Counter {
    private final StampedLock stampedLock = new StampedLock();
    private int countA = 0;
    private int countB = 0;

    public void increment() {
        long stamp = stampedLock.writeLock(); // write lock
        try {
            countA++;
            countB++;
        } finally {
            stampedLock.unlock(stamp);
        }
    }

    public int get() {
        long stamp = stampedLock.tryOptimisticRead(); // optimistic read lock
        int a = countA;
        int b = countB; // this two reads are not atomic
        if (!stampedLock.validate(stamp)) { // validate the optimistic read lock
            stamp = stampedLock.readLock(); // if not validated (there are writes while reading), obtain a pessimistic read lock
            try {
                a = countA;
                b = countB;
            } finally {
                stampedLock.unlockRead(stamp); // release the pessimistic read lock
            }
        }
        return a + b;
    }
}
