package concurrent.counter_demo;

/**
 * Demo for a concurrent counter without lock. Result is incorrect.
 */
public class NoLockDemo {
    public static void main(String[] args) throws Exception {
        AddThread1 t1 = new AddThread1();
        SubThread1 t2 = new SubThread1();
        Thread t3 = new Thread(t1);
        Thread t4 = new Thread(t2);
        t3.start();
        t4.start();
        t3.join();
        t4.join();
        System.out.println(Counter1.count);
    }
}

class Counter1 {
    public static int count = 0;
}

class AddThread1 implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            Counter1.count += 1;
        }
        System.out.println(Thread.currentThread().getName() + " finished");
    }
}

class SubThread1 implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            Counter1.count -= 1;
        }
        System.out.println(Thread.currentThread().getName() + " finished");
    }
}
