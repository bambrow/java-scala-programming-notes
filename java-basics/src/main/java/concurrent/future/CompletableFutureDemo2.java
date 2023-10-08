package concurrent.future;

import java.util.concurrent.CompletableFuture;

/**
 * Serial CompletableFuture demo.
 */
public class CompletableFutureDemo2 {
    public static void main(String[] args) throws InterruptedException {
        // create async task
        CompletableFuture<String> cf1 =
                CompletableFuture.supplyAsync(CompletableFutureDemo2::waitAndReturn);
        // cf2 will run after cf1 returns, parameter is a Function
        CompletableFuture<String> cf2 =
                cf1.thenApplyAsync(CompletableFutureDemo2::waitAndReturn2);
        cf2.thenAccept(System.out::println);
        Thread.sleep(3000);
    }

    private static String waitAndReturn() {
        try {
            Thread.sleep(1000);
            return "hello world";
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static String waitAndReturn2(String message) {
        try {
            Thread.sleep(1000);
            return "hello again after " + message;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
