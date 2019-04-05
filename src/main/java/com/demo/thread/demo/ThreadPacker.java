package com.demo.thread.demo;

/**
 * Created by dynamicniu on 2018/3/31.
 */
public class ThreadPacker extends Thread {
    ProductQueue queue;

    ThreadPacker(ProductQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            while (!queue.queueL.isEmpty() && !queue.queueR.isEmpty()) {
                ProductQueue.Production pl = queue.queueL.remove(0);
                ProductQueue.Production pr = queue.queueR.remove(0);
                System.out.println("打包线程，库存1：" + queue.queueL.size() + ",库存2：" + queue.queueR.size());
                pack(pl, pr);
            }
        }
    }

    void pack(ProductQueue.Production pL, ProductQueue.Production pR) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
