package concurrent.collection;

import java.util.*;
import java.util.concurrent.*;

/**
 * Concurrent collection demo.
 */
public class CollectionDemo {
    public static void main(String[] args) {
        // ArrayList - CopyOnWriteArrayList
        List<Integer> arrayList0 = new ArrayList<>();
        List<Integer> arrayList = new CopyOnWriteArrayList<>();

        // HashMap - ConcurrentHashMap
        Map<Integer, Integer> hashMap0 = new HashMap<>();
        Map<Integer, Integer> hashMap = new ConcurrentHashMap<>();

        // HashSet - CopyOnWriteArraySet
        Set<Integer> hashSet0 = new HashSet<>();
        Set<Integer> hashSet = new CopyOnWriteArraySet<>();

        // ArrayDeque - ArrayBlockingQueue
        Queue<Integer> arrayDeque0 = new ArrayDeque<>();
        Queue<Integer> arrayDeque = new ArrayBlockingQueue<>(1);

        // LinkedList - LinkedBlockingQueue
        Queue<Integer> linkedList0 = new LinkedList<>();
        Queue<Integer> linkedList = new LinkedBlockingQueue<>();

        // LinkedList - LinkedBlockingDeque
        Deque<Integer> linkedDeque0 = new LinkedList<>();
        Deque<Integer> linkedDeque = new LinkedBlockingDeque<>();

        // Not recommended
        Map<Integer, Integer> unsafeMap = new HashMap<>();
        Map<Integer, Integer> safeMap = Collections.synchronizedMap(unsafeMap);
    }
}
