package com.example.consumer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class QueueHandler {
    private final Producer producer;
    private final Consumer consumer;

    public QueueHandler(MainActivity mainActivity) {
        BlockingQueue<String> queue = new ArrayBlockingQueue<>(10);
        this.producer = new Producer(queue, mainActivity);
        this.consumer = new Consumer(queue, mainActivity);
    }

    public void produce(String product) {
        producer.produce(product);
    }

    public String consume() {
        return consumer.consume();
    }

    public void start() {
        new Thread(producer).start();
        new Thread(consumer).start();
    }

    public void stop() {
        producer.stop();
        consumer.stop();
    }



    public interface QueueListener {
        void addToOutputProducer(String product);
        void addToOutputConsumer(String product);
        void  addToQueueContents(String contents);
    }
}
