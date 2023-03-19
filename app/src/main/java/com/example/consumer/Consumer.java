package com.example.consumer;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.util.Log;

import java.text.BreakIterator;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;


public class Consumer implements Runnable {
    private final ArrayBlockingQueue<String> queue;
    private final QueueHandler.QueueListener listener;
    private volatile boolean isStopped = false;
    private Thread thread;

    public Consumer(ArrayBlockingQueue<String> queue, QueueHandler.QueueListener listener) {
        this.queue = queue;
        this.listener = listener;
    }

    public String consume() {
        String product = null;
        try {
            product = queue.take();
            listener.addToOutputConsumer(product);
            listener.addToQueueContents(getQueueContents());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return product;
    }



    private String getQueueContents() {
        StringBuilder sb = new StringBuilder();
        for (String item : queue) {
            sb.append(item).append("\n");
        }
        return sb.toString();
    }

    @Override
    public void run() {

    }

    public void stop() {
    }
}