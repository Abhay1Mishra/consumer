package com.example.consumer;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.util.Log;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class Producer implements Runnable {
    private final ArrayBlockingQueue<String> queue;
    private final QueueHandler.QueueListener listener;
    private Thread thread;

    public Producer(ArrayBlockingQueue<String>queue, QueueHandler.QueueListener listener) {
        this.queue = queue;
        this.listener = listener;
    }

    public void produce(String product) {
        boolean isInserted = false;
        while (!isInserted) {
            try {
                isInserted = queue.offer(product, 500, TimeUnit.MILLISECONDS);
                if (isInserted) {
                    listener.addToOutputProducer("Produced: " + product);
                    listener.addToQueueContents("Added to Queue: " + product);
                }
            } catch (InterruptedException e) {
                Log.d("Producer", "Interrupted exception while producing: " + e.getMessage());
                Thread.currentThread().interrupt();
            }
        }
    }


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


