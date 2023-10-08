package concurrent.lock_read_write;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Counter implemented with read/write lock.
 * This lock resolves the following problem:
 *           Read   |   Write
 * Read  |  allow   |  disallow
 * Write | disallow |  disallow
 * ReentrantReadWriteLock will:
 *   - allow concurrent reads
 *   - block all threads while writing
 * This lock is a Pessimistic Lock.
 */
public class Counter {
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private final Lock readLock = readWriteLock.readLock(); // read lock
    private final Lock writeLock = readWriteLock.writeLock(); // write lock
    private int count = 0;

    public void increment() {
        writeLock.lock();
        try {
            count++;
        } finally {
            writeLock.unlock();
        }
    }

    public int get() {
        readLock.lock();
        try {
            return count;
        } finally {
            readLock.unlock();
        }
    }
}
