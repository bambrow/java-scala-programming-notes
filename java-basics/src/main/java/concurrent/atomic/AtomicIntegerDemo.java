package concurrent.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Demo for AtomicInteger.
 * Atomic uses lock-free (CAS, compare and swap) strategy, kind of like an optimistic lock.
 * CAS strategy:
 * CAS(O,V,N), V is the actual value in memory; O is expected (old) value; N is the value to update.
 *  - When V == O, then the value is updated;
 *  - When V != O, then update failed.
 * CAS can bring ABA problem:
 *  - Thread A read the current value as A;
 *  - Thread Y changed value from A to B;
 *  - Thread Z changed value from B to A;
 *  - Then A might think the value has not been changed.
 * Java provides AtomicStampedReference to solve this problem.
 */
public class AtomicIntegerDemo {
    public static void main(String[] args) throws InterruptedException {
        AtomicInteger counter = new AtomicInteger(0);
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                counter.getAndIncrement();
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                counter.getAndDecrement();
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("Counter: " + counter.get());
    }
}
