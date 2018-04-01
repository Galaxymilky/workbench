package demo.thread.pcmode_1;

import com.pait.consumeAnnual.dto.UserConsumeDTO;

import java.util.concurrent.BlockingQueue;

/**
 * Created by dynam on 2017/4/4.
 */
public class Consumer implements Runnable {

    private BlockingQueue<Production> buffer;

    private String taskType;

    public Consumer(BlockingQueue<Production> buffer) {
        this.buffer = buffer;
    }

    public Consumer(BlockingQueue<Production> buffer, String taskType) {
        this.buffer = buffer;
        this.taskType = taskType;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Production production = buffer.take();
                System.out.println("Consumer[" + Thread.currentThread().getName() + "] take " + production);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
