package com.example.consumer;

public interface QueueListener {
    void addToOutputProducer(String product);

    void addToOutputConsumer(String product);

    void addToQueueContents(String contents);
}