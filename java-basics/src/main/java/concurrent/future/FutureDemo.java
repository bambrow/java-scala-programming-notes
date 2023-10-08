package concurrent.future;

import java.util.concurrent.*;

/**
 * A simple demo with Callable and Future.
 */
public class FutureDemo {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newFixedThreadPool(1);
        Callable<String> t1 = new MyThread();
        Future<String> f1 = executor.submit(t1);
        while (!f1.isDone()) {
            Thread.sleep(100);
            System.out.println("Callable not done. Waiting...");
        }
        System.out.println("Callable done. Get: " + f1.get());
        executor.shutdown();
    }

    static class MyThread implements Callable<String> {
        @Override
        public String call() throws Exception {
            Thread.sleep(1000);
            return "hello world";
        }
    }
}
