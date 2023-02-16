package com.example.consumer;

import android.util.Log;

import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable{
    private final BlockingQueue<String> queue;
    private final MainActivity mainActivity;

    public Consumer(BlockingQueue<String> queue, MainActivity mainActivity) {
        this.queue = queue;
        this.mainActivity = mainActivity;
    }

    public String consume() {
        String product = null;
        try {
            product = queue.take();
            mainActivity.addToOutputConsumer("Consumed: " + product);
            mainActivity.addToQueueContents("Removed from Queue: " + product);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return product;
    }

    @Override
    public void run() {
        // run indefinitely
        while (true) {}
    }
}
