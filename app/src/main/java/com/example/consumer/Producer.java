package com.example.consumer;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.util.Log;

import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {
    private final BlockingQueue<String> queue;
    private final QueueHandler.QueueListener listener;
    private volatile boolean isStopped = false;
    private Thread thread;

    public Producer(BlockingQueue<String> queue, QueueHandler.QueueListener listener) {
        this.queue = queue;
        this.listener = listener;
    }

    public void produce(String product) {
        try {
            queue.put(product);
            listener.addToOutputProducer(product);
            listener.addToQueueContents(getQueueContents());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    // ...

    private String getQueueContents() {
        StringBuilder sb = new StringBuilder();
        for (String item : queue) {
            sb.append(item).append("\n");
        }
        return sb.toString();
    }
   public void stop() {
        boolean isStopped = true;
        thread.interrupt();
    }


    @Override
    public void run() {

    }
}


