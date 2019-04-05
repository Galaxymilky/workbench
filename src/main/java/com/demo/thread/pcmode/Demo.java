package com.demo.thread.pcmode;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;


/**
 * Created by dynam on 2017/4/4.
 */
public class Demo {

    static final int MAX_BUFFER_SIZE = 10;
    static final int NUM_OF_CONSUMER = 5;
    static final int NUM_OF_PRODUCER = 2;

    public static void main(String[] args) {

        BlockingQueue<Production> buffer = new LinkedBlockingQueue<Production>();

        ExecutorService executorService = Executors.newFixedThreadPool(NUM_OF_PRODUCER + NUM_OF_CONSUMER);

        for (int i = 0; i < NUM_OF_CONSUMER; i++) {
            executorService.execute(new Consumer(buffer));
        }

        for (int i = 0; i < NUM_OF_PRODUCER; i++) {
            executorService.execute(new Producer(buffer));
        }
    }
}
