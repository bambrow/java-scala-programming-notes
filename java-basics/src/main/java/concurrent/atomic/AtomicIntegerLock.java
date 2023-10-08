package concurrent.atomic;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;

/**
 * A lock implementation using AtomicInteger.
 */
public class AtomicIntegerLock implements Lock {
    private final AtomicInteger counter = new AtomicInteger(0);
    private final ConcurrentLinkedQueue<Thread> queue = new ConcurrentLinkedQueue<>();

    @Override
    public void lock() {
        // expected 0, if matched, directly acquire lock
        if (counter.compareAndSet(0, 1)) {
            return;
        }
        // if not, add current thread to queue
        queue.add(Thread.currentThread());
        // Spinlock, busy waiting
        while (true) {
            if (0 == counter.get()) { // get expected value
                // if matched, remove current thread from queue and acquire lock
                if (counter.compareAndSet(0, 1)) {
                    queue.remove(Thread.currentThread());
                    return;
                }
            } else { // not the expected value, park current thread
                LockSupport.park();
            }
        }
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        // not implemented
    }

    @Override
    public boolean tryLock() {
        return false; // not implemented
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false; // not implemented
    }

    @Override
    public void unlock() {
        // set counter to 0
        counter.set(0);
        // get the first thread in queue
        Thread head = queue.peek();
        if (null != head) {
            // wake up
            LockSupport.unpark(head);
        }
    }

    @Override
    public Condition newCondition() {
        return null; // not implemented
    }
}
