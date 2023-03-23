package com.example.consumer;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.BreakIterator;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity implements QueueListener {
    private TextView outputTextViewProducer;
    private TextView outputTextViewConsumer;
    private TextView queueContentsTextView;

    private QueueHandler queueHandler;

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

        queueHandler = new QueueHandler(this);

        produceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                queueHandler.produce("Product " + (queueHandler.getQueueSize() + 1));
            }
        });

        consumeButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                String product = queueHandler.consume();
                if (product == null) {

                    outputTextViewConsumer.setText("Queue is empty");

                } else {
                    outputTextViewConsumer.setText("Consumed: " + product);
                    queueContentsTextView.setText("Queue Contents: " + queueHandler.queue.toString() + "\n");
                }
            }
        });

        queueHandler.start();
    }

    public void addToOutputProducer(final String message) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                outputTextViewProducer.append(message + "\n");
                Log.d("Producer", "Producing message: " + message);


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


                Log.d("CONSUMER", "Consumed: " + message);

            }
        });
    }

}