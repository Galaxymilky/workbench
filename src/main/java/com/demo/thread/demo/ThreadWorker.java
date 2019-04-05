package com.demo.thread.demo;

/**
 * Created by dynamicniu on 2018/3/31.
 */
public class ThreadWorker extends Thread {
    ProductQueue queue;

    ThreadWorker(ProductQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (queue.queueL.size() < 101 || queue.queueR.size() < 101) {
            if (queue.flagL) {
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (queue.queueL) {
                    queue.queueL.add(new ProductQueue.Production(ProductQueue.Production.LEFT));
                    System.out.println("工作线程，生产1：" + queue.queueL.size() + "，生产2：" + queue.queueR.size());
                }
            } else {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (queue.queueR) {
                    queue.queueR.add(new ProductQueue.Production(ProductQueue.Production.RIGHT));
                    System.out.println("工作线程，生产1：" + queue.queueL.size() + "，生产2：" + queue.queueR.size());
                }
            }
        }
    }
}
