package concurrent.lock_reentrant;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Use ReentrantLock to realize wait/notify.
 * newCondition() will return a Condition where:
 *   - await() be like synchronized wait()
 *   - signal() be like synchronized notify()
 *   - signalAll() be like synchronized notifyAll()
 * Check WaitNotifyDemo for original wait/notify usage.
 */
public class WaitNotifyDemo {
    public static void main(String[] args) throws InterruptedException {
        TaskQueue tq = new TaskQueue();
        List<Thread> tl = new ArrayList<>();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                tq.add(i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        t1.start();

        for (int i = 0; i < 10; i++) {
            Thread t2 = new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " get: " + tq.remove());
            });
            tl.add(t2);
            t2.start();
        }

        t1.join();
    }

    static class TaskQueue {
        private final Lock lock = new ReentrantLock();
        private final Condition condition = lock.newCondition(); // get a new Condition from lock
        Queue<Integer> queue = new LinkedList<>();

        public void add(int i) {
            lock.lock();
            try {
                queue.add(i);
                condition.signalAll(); // similar to synchronized notifyAll()
            } finally {
                lock.unlock();
            }
        }

        public int remove() {
            lock.lock();
            try {
                while (queue.isEmpty()) {
                    condition.await(); // similar to synchronized wait()
                    // need to reacquire lock here
                }
                return queue.remove();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
        }
    }
}
