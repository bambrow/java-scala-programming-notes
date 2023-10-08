package concurrent.thread_pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Fixed thread pool demo. The thread pool size is fixed.
 */
public class FixedThreadPoolDemo {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 6; i++) {
            executor.submit(new MyThread1(i));
        }
        executor.shutdown();
    }

    static class MyThread1 implements Runnable {
        private final int id;

        public MyThread1(int id) {
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
