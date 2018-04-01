package com.pait.taskPool.thread;

import com.pait.consumeAnnual.dto.UserConsume;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;

/**
 * Created by dynam on 2017/4/4.
 */
public class Consumer implements Callable {

    private BlockingQueue<UserConsume> buffer;

    private String taskType;

    public Consumer(BlockingQueue<UserConsume> buffer) {
        this.buffer = buffer;
    }

    public Consumer(BlockingQueue<UserConsume> buffer, String taskType) {
        this.buffer = buffer;
        this.taskType = taskType;
    }

    @Override
    public Object call() throws Exception {
        UserConsume production = null;
        production = buffer.poll();

        if (production == null) {
            return null;
        }

        String threadName = Thread.currentThread().getName();
        Long id = production.getIdUserConsume();
        System.out.println("Consumer[" + threadName + "] take " + id + " from " + taskType + " && result = " + buffer.size());

        return production;
    }
}
