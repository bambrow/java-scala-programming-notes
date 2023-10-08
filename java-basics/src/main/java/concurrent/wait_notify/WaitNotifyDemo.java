package concurrent.wait_notify;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * A queue implementation, demonstrating wait/notify.
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
                try {
                    System.out.println(Thread.currentThread().getName() + " get: " + tq.remove());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
            tl.add(t2);
            t2.start();
        }

        t1.join();
    }

    static class TaskQueue {
        Queue<Integer> queue = new LinkedList<>();

        // synchronized will acquire `this` lock
        // notify() and notifyAll() must be called inside synchronized block
        public synchronized void add(int i) {
            this.queue.add(i);
            this.notifyAll(); // wake up the threads waiting for `this` lock
        }

        // synchronized will acquire `this` lock
        // wait() must be called inside synchronized block
        public synchronized int remove() throws InterruptedException {
            while (queue.isEmpty()) {
                this.wait(); // this will release the synchronized lock while waiting
                // need to reacquire lock here
            }
            return queue.remove();
        }
    }
}
