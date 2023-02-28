package com.example.consumer;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.BreakIterator;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity implements QueueHandler.QueueListener {
    private TextView outputTextViewProducer;
    private TextView outputTextViewConsumer;
    private TextView queueContentsTextView;

    private final BlockingQueue<String> queue = new ArrayBlockingQueue<>(10);
    private final Producer producer = new Producer(queue, this);
    private final Consumer consumer = new Consumer(queue, this);
    private final ExecutorService threadPool = Executors.newFixedThreadPool(2);

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        outputTextViewProducer = findViewById(R.id.outputTextViewProducer);
        outputTextViewConsumer = findViewById(R.id.outputTextViewConsumer);
        queueContentsTextView = findViewById(R.id.queueContentsTextView);
        Button produceButton = findViewById(R.id.produceButton);
        Button consumeButton = findViewById(R.id.consumeButton);

        produceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                producer.produce("Product " + (queue.size() + 1));
            }
        });

        consumeButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                if (queue.isEmpty()) {
                    outputTextViewConsumer.setText("Queue is empty");
                } else {
                    String product = consumer.consume();
                    outputTextViewConsumer.setText("Consumed: " + product);
                    queueContentsTextView.setText("Queue Contents: " + queue.toString());
                }
            }
        });


        new Thread(producer).start();
        new Thread(consumer).start();
    }

    public void addToOutputProducer(final String message) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                outputTextViewProducer.append(message + "\n");
            }
        });
    }

    public void addToQueueContents(final String message) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                queueContentsTextView.setText(message);
            }
        });
    }

    public void addToOutputConsumer(final String message) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                outputTextViewConsumer.append(message + "\n");
            }
        });
    }
}