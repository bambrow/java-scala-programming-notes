package concurrent.callable;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

/**
 * Callable demo with invoke.
 */
public class InvokeDemo {
    public static void main(String[] args) throws InterruptedException, ExecutionException {

        ExecutorService executor = Executors.newWorkStealingPool();
        List<Callable<String>> callableList1 = Arrays.asList(
                () -> "task1",
                () -> "task2",
                () -> "task3");

        // invokeAll: wait for all to complete
        executor.invokeAll(callableList1)
                .stream()
                .map(future -> {
                    try {
                        return future.get();
                    }
                    catch (Exception e) {
                        throw new IllegalStateException(e);
                    }
                })
                .forEach(System.out::println);
        // all tasks will be printed

        System.out.println("---");

        List<Callable<String>> callableList2 = Arrays.asList(
                createCallable("task1", 2),
                createCallable("task2", 1),
                createCallable("task3", 3));

        // invokeAny: wait for any to complete
        String result = executor.invokeAny(callableList2);
        System.out.println(result);
        // only task2 wil be printed

        // executor shutdown convention
        try {
            System.out.println("shutting down executor...");
            executor.shutdown();
            executor.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            System.err.println("task interrupted...");
        } finally {
            if (!executor.isTerminated()) {
                System.err.println("terminate non-finished tasks...");
            }
            executor.shutdownNow();
            System.out.println("executor has been shutdown...");
        }

    }

    private static Callable<String> createCallable(String result, long sleep) {
        return () -> {
            TimeUnit.SECONDS.sleep(sleep);
            return result;
        };
    }
}
