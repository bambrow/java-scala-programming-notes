package concurrent.basics;

/**
 * DaemonThread test.
 * Conventional threads will block the exit of JVM.
 * DaemonThread will not.
 */
public class DaemonThreadTest {
    static class MyThread3 extends Thread {
        @Override
        public void run() {
            try {
                Thread.sleep(1);
                System.out.println("Thread " + Thread.currentThread().getName() + " is running.");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            MyThread3 thread = new MyThread3();
            thread.setName(String.valueOf(i));
            thread.setDaemon(true);
            thread.start();
        }
    }
}
