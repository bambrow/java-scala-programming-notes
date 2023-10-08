package design_pattern.other.producer_consumer;

import java.util.ArrayList;
import java.util.List;

public class ProducerConsumerDemo2 {
    private static final int MAX_QUEUE_CAPACITY = 5;

    public static void main(String[] args) {
        DataQueue dataQueue = new DataQueue(MAX_QUEUE_CAPACITY);
        int producerCount = 3;
        int consumerCount = 3;
        List<Thread> threads = new ArrayList<>();

        Producer producer = new Producer(dataQueue);
        for(int i = 0; i < producerCount; i++) {
            Thread producerThread = new Thread(producer);
            producerThread.start();
            threads.add(producerThread);
        }
        Consumer consumer = new Consumer(dataQueue);
        for(int i = 0; i < consumerCount; i++) {
            Thread consumerThread = new Thread(consumer);
            consumerThread.start();
            threads.add(consumerThread);
        }

        ThreadUtil.sleep(2000);

        producer.stop();
        consumer.stop();

        ThreadUtil.waitForAllThreadsToComplete(threads);
    }
}
