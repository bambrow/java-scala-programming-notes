package design_pattern.other.producer_consumer;

public class Producer implements Runnable {
    private final DataQueue dataQueue;
    private volatile boolean runFlag;

    private static int idSeq = 0;

    public Producer(DataQueue dataQueue) {
        this.dataQueue = dataQueue;
        runFlag = true;
    }

    @Override
    public void run() {
        produce();
    }

    public void produce() {
        while (runFlag) {
            Message message = generateMessage();
            while (dataQueue.isFull()) {
                try {
                    dataQueue.waitOnFull();
                } catch (InterruptedException e) {
                    break;
                }
            }
            if (!runFlag) {
                break;
            }
            dataQueue.add(message);
            dataQueue.notifyAllForEmpty();
        }
    }

    private Message generateMessage() {
        Message message = new Message(++idSeq, Math.random());
        System.out.printf("[%s] Generated Message. Id: %d, Data: %f\n", Thread.currentThread().getName(), message.getId(), message.getData());
        ThreadUtil.sleep((long) (message.getData() * 100));
        return message;
    }

    public void stop() {
        runFlag = false;
        dataQueue.notifyAllForFull();
    }
}
