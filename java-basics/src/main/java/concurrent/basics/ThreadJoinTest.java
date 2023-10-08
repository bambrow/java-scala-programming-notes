package concurrent.basics;

import java.util.concurrent.TimeUnit;

/**
 * Join test. Will wait until completion.
 */
public class ThreadJoinTest {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName());
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread t2 = new Thread(() -> {
           System.out.println(Thread.currentThread().getName());
        });

        t1.start();
        t1.join(); // wait for completion
        t2.start();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Finished!");
    }
}
