package concurrent.thread_pool;

import java.util.concurrent.*;

/**
 * Cached thread pool demo. This thread pool size will fluctuate.
 */
public class CachedThreadPoolDemo {
    public static void main(String[] args) {
        // ExecutorService executor = Executors.newCachedThreadPool();
        ExecutorService executor = new ThreadPoolExecutor(
                1, // core pool size
                3, // maximum pool size
                0L, // keep alive time
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(3) // work queue
        );
        for (int i = 0; i < 6; i++) {
            executor.submit(new MyThread2(i));
        }
        executor.shutdown();
    }

    static class MyThread2 implements Runnable {
        private final int id;

        public MyThread2(int id) {
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
