package demo.thread.pcmode;

import java.util.concurrent.BlockingQueue;

/**
 * Created by dynam on 2017/4/4.
 */
public class Consumer implements Runnable {

    private BlockingQueue<Production> buffer;

    public Consumer(BlockingQueue<Production> buffer) {
        this.buffer = buffer;
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
