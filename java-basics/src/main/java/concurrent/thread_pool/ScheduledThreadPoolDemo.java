package concurrent.thread_pool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Scheduled thread pool demo.
 */
public class ScheduledThreadPoolDemo {
    public static void main(String[] args) throws InterruptedException {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(3);
        for (int i = 1; i <= 3; i++) {
            executor.scheduleAtFixedRate(
                    new MyThread3(i),
                    i, // initial delay
                    i, // period
                    TimeUnit.SECONDS
            );
        }
        TimeUnit.SECONDS.sleep(20);
        executor.shutdown();
    }

    static class MyThread3 implements Runnable {
        private final int id;

        public MyThread3(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            System.out.println("Thread-" + id + " started");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Thread-" + id + " finished");
        }
    }
}
