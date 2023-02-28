package com.example.consumer;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.util.Log;

import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {
    private final BlockingQueue<String> queue;
    private final MainActivity mainActivity;
    private Thread thread;
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
            Log.d("Producer", "Interrupted exception while producing: " + e.getMessage());
        }
    }
    public void stop() {
        boolean isStopped = true;
        thread.interrupt();
    }


    @Override
    public void run() {

    }
}



