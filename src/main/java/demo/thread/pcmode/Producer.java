package demo.thread.pcmode;

import java.util.concurrent.BlockingQueue;

/**
 * Created by dynam on 2017/4/4.
 */
public class Producer implements Runnable {

    private BlockingQueue<Production> buffer;

    public Producer(BlockingQueue<Production> buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while (true) {
            try {
                while (buffer.size() < Demo.MAX_BUFFER_SIZE) {
                    Thread.currentThread().sleep(5000);
                    Production production = new Production();
                    buffer.put(production);
                    System.out.println("Producer[" + Thread.currentThread().getName() + "] put " + production);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
