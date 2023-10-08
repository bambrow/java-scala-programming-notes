package concurrent.thread_local;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ThreadLocal demo 3. This uses two different ThreadLocal objects.
 * Two ThreadLocal objects individually saves its own variable.
 */
public class ThreadLocalDemo3 {
    static ThreadLocal<User> threadLocalUser1 = new ThreadLocal<>();
    static ThreadLocal<User> threadLocalUser2 = new ThreadLocal<>();

    public static void main(String[] args) {
        threadLocalUser1.set(new User());
        threadLocalUser2.set(new User());
        System.out.println("User1 id: " + threadLocalUser1.get().getId() + ", User2 id: " + threadLocalUser2.get().getId() + " in thread " + Thread.currentThread().getName());
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
                threadLocalUser1.set(new User());
                threadLocalUser2.set(new User());
                System.out.println("User1 id: " + threadLocalUser1.get().getId() + ", User2 id: " + threadLocalUser2.get().getId() + " in thread " + Thread.currentThread().getName());
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                threadLocalUser1.remove(); // this is required
                threadLocalUser2.remove(); // this is required
            }
        }
    }
}
