package concurrent.volatile_demo;

/**
 * Volatile indicator demo. Use `running` as indicator.
 * `volatile` solves visibility: other threads will immediately see the new value of this variable, once updated.
 * Always use `volatile` for variables shared between threads.
 */
public class VolatileDemo {
    public static void main(String[] args) throws InterruptedException {
        MyThread1 t = new MyThread1();
        new Thread(t).start();
        Thread.sleep(1000);
        t.running = false;
    }
}

class MyThread1 implements Runnable {
    public volatile boolean running = true;

    @Override
    public void run() {
        int i = 0;
        while (running) {
            try {
                System.out.println(Thread.currentThread().getName() + ": " + ++i);
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            Thread.sleep(500);
            System.out.println(Thread.currentThread().getName() + ": Finished!");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
