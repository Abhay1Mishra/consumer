package com.example.consumer;

import static android.content.ContentValues.TAG;

import android.util.Log;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class QueueHandler {
    private final Producer producer ;
    private final Consumer consumer ;
    public ArrayBlockingQueue<String> queue;

    public QueueHandler(QueueListener listener) {
        queue = new ArrayBlockingQueue<>(10);
        this.producer = new Producer(queue, listener);
        this.consumer = new Consumer(queue, listener);
    }

    public void produce(String product) {
        producer.produce(product);
    }

    public  String consume() {
        return consumer.consume();
    }

    public void start() {
        new Thread(producer).start();
        new Thread(consumer).start();
    }



    public int getQueueSize() {
        return queue.size();
    }

    public void onDataConsumed(Object data) {
        Log.d(TAG, "Data consumed: " + data.toString());
    }

}
