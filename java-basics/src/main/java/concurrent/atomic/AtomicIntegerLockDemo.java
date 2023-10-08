package concurrent.atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * Demo or AtomicIntegerLock.
 */
public class AtomicIntegerLockDemo {
    static long count = 0;

    public static void main(String[] args) throws InterruptedException {
        Lock lock = new AtomicIntegerLock();
        ExecutorService threadPool = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 10; i++) {
            threadPool.submit(new AddThread(lock));
        }

        TimeUnit.SECONDS.sleep(2);

        System.out.println("Count value = " + count);
        threadPool.shutdown();
    }

    public static class AddThread implements Runnable {
        Lock lock;

        public AddThread() {}

        public AddThread(Lock lock) {
            this.lock = lock;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                lock.lock();
                try {
                    count++;
                } catch (Exception ignored) {

                } finally {
                    lock.unlock();
                }
            }
        }
    }
}
