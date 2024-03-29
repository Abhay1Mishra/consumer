package com.example.consumer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.text.BreakIterator;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;


public class Consumer {
    private final ArrayBlockingQueue<String> queue;
    private final QueueListener listener;


    private QueueHandler queueHandler;

    public Consumer(ArrayBlockingQueue<String> queue, QueueListener listener) {
        this.queue = queue;
        this.listener = listener;
    }

    public String consume() {
        String product = null;
        try {
            product = queue.take();
            listener.addToOutputConsumer(product);
            listener.addToQueueContents(getQueueContents());
        } catch (InterruptedException ignored) {

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


}