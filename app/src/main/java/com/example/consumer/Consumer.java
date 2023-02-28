package com.example.consumer;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.util.Log;

import java.text.BreakIterator;
import java.util.concurrent.BlockingQueue;


public class Consumer implements Runnable {
    private final BlockingQueue<String> queue;
    private final MainActivity mainActivity;
    private Thread thread;

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
            Log.d("Consumer", "Interrupted exception while Consuming: " + e.getMessage());

        }
        return product;
    }
    public void stop() {
        boolean isStopped = true;
        thread.interrupt();
    }

    @Override
    public void run() {

    }
}
