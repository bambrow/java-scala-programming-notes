package concurrent.atomic;

import java.util.concurrent.atomic.LongAdder;

/**
 * Demo for LongAdder.
 * LongAdder will have better performance with high concurrency.
 * AtomicLong uses lock-free CAS strategy, kind of like a spinlock.
 * The throughput may not be ideal with CPU overhead.
 * LongAdder uses multiple Cell which individually provides CAS operation support.
 */
public class LongAdderDemo {
    public static void main(String[] args) throws InterruptedException {
        LongAdder counter = new LongAdder();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                counter.increment();
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                counter.decrement();
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("Counter: " + counter.longValue());
    }
}
