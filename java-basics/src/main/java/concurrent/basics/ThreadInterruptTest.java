package concurrent.basics;

/**
 * Interrupt test. Will continuously check if current thread is interrupted.
 * See `volatile` for another implementation.
 */
public class ThreadInterruptTest {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {
            int n = 0;
            boolean interrupted = false;
            while (!interrupted) {
                try {
                    Thread.sleep(10);
                    n++;
                    System.out.println(Thread.currentThread().getName() + ": " + n);
                } catch (InterruptedException e) {
                    System.out.println(Thread.currentThread().getName() + ": interrupted!");
                    interrupted = true;
                }
            }
        });

        t.start();
        Thread.sleep(100);
        t.interrupt();
        t.join(); // wait for completion
        System.out.println("Finished!");
    }
}
