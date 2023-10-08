package concurrent.future;

import java.util.concurrent.CompletableFuture;

/**
 * Parallel CompletableFuture demo.
 */
public class CompletableFutureDemo3 {
    public static void main(String[] args) throws InterruptedException {
        // create async tasks
        CompletableFuture<String> cf1 =
                CompletableFuture.supplyAsync(CompletableFutureDemo3::waitAndReturn);
        CompletableFuture<String> cf2 =
                CompletableFuture.supplyAsync(CompletableFutureDemo3::waitAndReturn2);
        // cf3 is a combined CompletableFuture using anyOf
        CompletableFuture<Object> cf3 =
                CompletableFuture.anyOf(cf1, cf2);
        // cf4 will run after any of cf1, cf2 returns
        CompletableFuture<String> cf4 =
                cf3.thenApplyAsync(x -> waitAndReturn3(x.toString()));
        cf4.thenAccept(System.out::println);
        Thread.sleep(4000);
    }

    private static String waitAndReturn() {
        try {
            Thread.sleep(1000);
            return "hello world";
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static String waitAndReturn2() {
        try {
            Thread.sleep(1000);
            return "hello world 2";
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static String waitAndReturn3(String message) {
        try {
            Thread.sleep(1000);
            return "hello again after " + message;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
