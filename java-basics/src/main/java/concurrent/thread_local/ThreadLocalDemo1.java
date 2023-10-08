package concurrent.thread_local;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ThreadLocal demo 1. This is the recommended usage.
 */
public class ThreadLocalDemo1 {
    static ThreadLocal<User> threadLocalUser = new ThreadLocal<>();

    public static void main(String[] args) {
        threadLocalUser.set(new User());
        System.out.println("User id: " + threadLocalUser.get().getId() + " in thread " + Thread.currentThread().getName());
        ExecutorService executor = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 10; i++) {
            executor.submit(new MyThread());
        }
        executor.shutdown();
    }

    static class MyThread implements Runnable {
        @Override
        public void run() {
            process();
        }

        private void process() {
            try {
                threadLocalUser.set(new User());
                System.out.println("User id: " + threadLocalUser.get().getId() + " in thread " + Thread.currentThread().getName());
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                threadLocalUser.remove(); // this is required
            }
        }
    }
}
