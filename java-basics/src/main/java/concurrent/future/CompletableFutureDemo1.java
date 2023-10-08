package concurrent.future;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * A simple CompletableFuture demo with a callback.
 */
public class CompletableFutureDemo1 {
    // see detailed explanation in mailDetail
    public static void main(String[] args) throws InterruptedException {
        // create async task
        CompletableFuture<String> cf1 =
                CompletableFuture.supplyAsync(CompletableFutureDemo1::waitAndReturn);
        // callback when completed
        cf1.thenAccept(r -> System.out.println("Get result: " + r));
        // callback when exception
        cf1.exceptionally(e -> {
            e.printStackTrace();
            return null;
        });
        Thread.sleep(3000);
    }

    // this method is for detailed explanation of main method
    public static void mainDetail() throws InterruptedException {
        // supplyAsync: parameter is a Supplier
        CompletableFuture<String> cf1 =
                CompletableFuture.supplyAsync(new Supplier<String>() {
                    @Override
                    public String get() {
                        return CompletableFutureDemo1.waitAndReturn();
                    }
                });
        // thenAccept: parameter is a Consumer
        cf1.thenAccept(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println("Get result: " + s);
            }
        });
        // exceptionally: parameter is a Function
        cf1.exceptionally(new Function<Throwable, String>() {
            @Override
            public String apply(Throwable throwable) {
                throwable.printStackTrace();
                return null;
            }
        });
        Thread.sleep(3000);
    }

    private static String waitAndReturn() {
        try {
            Thread.sleep(2000);
            return "hello world";
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
