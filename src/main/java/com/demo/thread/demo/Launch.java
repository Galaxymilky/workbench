package com.demo.thread.demo;

import java.util.Vector;


/**
 * Created by dynam on 2017/4/4.
 */
public class Launch {
    public static void main(String[] args) throws InterruptedException {

        final Vector<ProductQueue.Production> queueL = new Vector<>();
        final Vector<ProductQueue.Production> queueR = new Vector<>();

        boolean flagL = true;

        boolean flagR = false;

        ProductQueue queue = new ProductQueue(queueL, queueR, flagL, flagR);

        for (int i = 1; i < 98; i++) {
            ThreadWorker worker = new ThreadWorker(queue);
            worker.start();
        }
        Thread.sleep(5000);

        ThreadMaster master = new ThreadMaster(queue);
        master.start();

        ThreadPacker packer = new ThreadPacker(queue);
        packer.start();

    }
}
