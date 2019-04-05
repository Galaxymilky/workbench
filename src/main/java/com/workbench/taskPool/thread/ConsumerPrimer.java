package com.workbench.taskPool.thread;

import com.workbench.consumeAnnual.dto.UserConsume;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;

/**
 * Created by dynam on 2017/4/4.
 */
public class ConsumerPrimer implements Callable {

    private BlockingQueue<UserConsume> buffer;

    private int hashCode;

    public ConsumerPrimer(BlockingQueue<UserConsume> buffer) {
        this.buffer = buffer;
    }

    public ConsumerPrimer(BlockingQueue<UserConsume> buffer, int hashCode) {
        this.buffer = buffer;
        this.hashCode = hashCode;
    }

    @Override
    public Object call() throws Exception {
        String threadName = Thread.currentThread().getName();
        if (buffer.size() <= 0) {
            return null;
        }

        for (int i = 0; i < buffer.size(); i++) {
            UserConsume production = buffer.poll();

            if (production == null) {
                return null;
            }

            Long id = production.getIdUserConsume();
            if (hashCode == production.getHashCode()) {
                System.out.println("获取成功");
                System.out.println("ConsumerPrimer[" + threadName + "] pool " + id + " from " + hashCode + " && result = " + buffer.size());
                return production;
            } else {
                System.out.println("继续检索");
                System.out.println("ConsumerPrimer[" + threadName + "] put back" + id);
                buffer.add(production);
            }

        }

        return null;
    }
}
