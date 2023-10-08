package concurrent.thread_local;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * InheritableThreadLocal demo 2.
 * The first 3 threads works as expected, while the last thread throws java.lang.NullPointerException.
 * This is because when used with ExecutorService, there will be thread reuse.
 * The thread local variable can only be passed with thread creation.
 * The first 3 threads had called the remove() method, so the thread local variable has been cleared.
 */
public class InheritableThreadLocalDemo2 {
    static InheritableThreadLocal<User> threadLocalUser = new InheritableThreadLocal<>();

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 4; i++) {
            threadLocalUser.set(new User());
            System.out.println("User id: " + threadLocalUser.get().getId() + " in thread " + Thread.currentThread().getName());
            executor.submit(new MyThread());
            threadLocalUser.remove();
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
                System.out.println("User id: " + threadLocalUser.get().getId() + " in thread " + Thread.currentThread().getName());
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                threadLocalUser.remove(); // comment this will work, but may cause memory leak
            }
        }
    }
}
