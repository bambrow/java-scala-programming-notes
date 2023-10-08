package concurrent.fork_join;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * A demo for array sum using fork/join.
 */
public class ArraySumDemo {
    private static final Random random = new Random();

    public static void main(String[] args) {
        // create a random long array
        long[] arr = new long[100000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(100000);
        }
        long start = System.currentTimeMillis();
        // computes expected sum
        long sum = 0L;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        long end = System.currentTimeMillis();
        System.out.printf("Expected sum=%d, time taken=%d\n", sum, end - start);

        // fork/join
        ForkJoinTask<Long> task = new ArraySum(arr, 0, arr.length);
        start = System.currentTimeMillis();
        sum = ForkJoinPool.commonPool().invoke(task);
        end = System.currentTimeMillis();
        System.out.printf("Fork join sum=%d, time taken=%d\n", sum, end - start);
    }

    static class ArraySum extends RecursiveTask<Long> {
        private static final int THRESHOLD = 5000;
        private long[] arr;
        private int start;
        private int end;

        public ArraySum(long[] arr, int start, int end) {
            this.arr = arr;
            this.start = start;
            this.end = end;
        }

        @Override
        protected Long compute() {
            // if the task size is less than threshold, directly compute
            if (end - start < THRESHOLD) {
                long sum = 0;
                for (int i = start; i < end; i++) {
                    sum += this.arr[i];
                }
                System.out.printf("Finished %d-%d, result=%d\n", start, end, sum);
                return sum;
            }
            // otherwise, split the task
            int mid = (end + start) / 2;
            System.out.printf("Split %d-%d into %d-%d and %d-%d\n", start, end, start, mid, mid, end);
            ArraySum s1 = new ArraySum(this.arr, start, mid);
            ArraySum s2 = new ArraySum(this.arr, mid, end);
            // invoke all subtasks
            invokeAll(s1, s2);
            long r1 = s1.join();
            long r2 = s2.join();
            long res = r1 + r2;
            System.out.printf("Finished %d-%d, result=%d+%d=%d\n", start, end, r1, r2, res);
            return res;
        }
    }
}
