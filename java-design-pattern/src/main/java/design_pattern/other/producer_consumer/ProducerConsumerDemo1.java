package design_pattern.other.producer_consumer;

import java.util.ArrayList;
import java.util.List;

public class ProducerConsumerDemo1 {
    private static final int MAX_QUEUE_CAPACITY = 5;

    public static void main(String[] args) {
        DataQueue dataQueue = new DataQueue(MAX_QUEUE_CAPACITY);
        Producer producer = new Producer(dataQueue);
        Thread producerThread = new Thread(producer);

        Consumer consumer = new Consumer(dataQueue);
        Thread consumerThread = new Thread(consumer);

        producerThread.start();
        consumerThread.start();

        List<Thread> threads = new ArrayList<>();
        threads.add(producerThread);
        threads.add(consumerThread);

        ThreadUtil.sleep(2000);

        producer.stop();
        consumer.stop();

        ThreadUtil.waitForAllThreadsToComplete(threads);
    }
}
