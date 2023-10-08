package concurrent.counter_demo;

/**
 * Demo for a concurrent counter with lock. Use `synchronized`.
 * `synchronized` will correctly release lock regardless of exception.
 */
public class SimpleLockDemo {
    public static void main(String[] args) throws Exception {
        AddThread2 t1 = new AddThread2();
        SubThread2 t2 = new SubThread2();
        Thread t3 = new Thread(t1);
        Thread t4 = new Thread(t2);
        t3.start();
        t4.start();
        t3.join();
        t4.join();
        System.out.println(Counter2.count);
    }
}

class Counter2 {
    public static final Object LOCK = new Object();
    public static int count = 0;
}

class AddThread2 implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            synchronized (Counter2.LOCK) {
                Counter2.count += 1;
            }
        }
        System.out.println(Thread.currentThread().getName() + " finished");
    }
}

class SubThread2 implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            synchronized (Counter2.LOCK) {
                Counter2.count -= 1;
            }
        }
        System.out.println(Thread.currentThread().getName() + " finished");
    }
}
