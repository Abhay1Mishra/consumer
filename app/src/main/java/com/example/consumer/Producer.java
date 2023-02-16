package com.example.consumer;

import android.util.Log;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class Producer  implements Runnable {
    private final BlockingQueue<String> queue;
    private final MainActivity mainActivity;

    public Producer(BlockingQueue<String> queue, MainActivity mainActivity) {
        this.queue = queue;
        this.mainActivity = mainActivity;
    }

    public void produce(String product) {
        try {
            queue.put(product);
            mainActivity.addToOutputProducer("Produced: " + product);
            mainActivity.addToQueueContents("Added to Queue: " + product);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void run() {

    }
}