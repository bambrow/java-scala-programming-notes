package concurrent.lock_reentrant;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock demo for counter.
 * ReentrantLock is more flexible than synchronized.
 * ReentrantLock also provides a tryLock() method.
 */
public class ReentrantLockDemo {
    public static void main(String[] args) throws InterruptedException {
        Counter1 c = new Counter1();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                c.increment();
            }
            System.out.println(Thread.currentThread().getName() + " finished");
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                c.decrement();
            }
            System.out.println(Thread.currentThread().getName() + " finished");
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(c.get());
    }
}

class Counter1 {
    private final Lock lock = new ReentrantLock();
    private int count = 0;

    public void increment() {
        lock.lock();
        try {
            count++;
        } finally {
            lock.unlock();
        }
    }

    public void decrement() {
        lock.lock();
        try {
            count--;
        } finally {
            lock.unlock();
        }
    }

    public int get() {
        return count;
    }
}
