package concurrent.thread_local;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * InheritableThreadLocal demo 3. This proves the value in child thread would not change even if parent thread changed it.
 */
public class InheritableThreadLocalDemo3 {
    static InheritableThreadLocal<User> threadLocalUser = new InheritableThreadLocal<>();

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 3; i++) {
            threadLocalUser.set(new User());
            System.out.println("User id: " + threadLocalUser.get().getId() + " in thread " + Thread.currentThread().getName());
            executor.submit(new MyThread());
        }
        threadLocalUser.remove();
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
                TimeUnit.SECONDS.sleep(3);
                System.out.println("User id: " + threadLocalUser.get().getId() + " in thread " + Thread.currentThread().getName()); // same result
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                threadLocalUser.remove();
            }
        }
    }
}
