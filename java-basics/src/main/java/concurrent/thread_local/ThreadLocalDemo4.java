package concurrent.thread_local;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ThreadLocal demo 4. Use a fixed thread pool for thread reuse.
 * Skip the ThreadLocal.remove(), and the User variable in ThreadLocal would be accessible in other threads.
 * Always call ThreadLocal.remove() before the thread finishes.
 */
public class ThreadLocalDemo4 {
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
                if (threadLocalUser.get() == null) { // check if there is already a value; thread reuse
                    threadLocalUser.set(new User());
                }
                System.out.println("User id: " + threadLocalUser.get().getId() + " in thread " + Thread.currentThread().getName());
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                // threadLocalUser.remove(); // do nothing here
            }
        }
    }
}
