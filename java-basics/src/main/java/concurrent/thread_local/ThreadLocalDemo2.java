package concurrent.thread_local;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ThreadLocal demo 2. Demo 1 is recommended.
 */
public class ThreadLocalDemo2 {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 10; i++) {
            executor.submit(new MyThread());
        }
        executor.shutdown();
    }

    static class MyThread implements Runnable {
        ThreadLocal<User> threadLocalUser = new ThreadLocal<>();

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
