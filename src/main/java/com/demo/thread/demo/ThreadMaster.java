package com.demo.thread.demo;

/**
 * Created by dynamicniu on 2018/3/31.
 */
public class ThreadMaster extends Thread {
    ProductQueue lrQueue;

    ThreadMaster(ProductQueue lrQueue) {
        this.lrQueue = lrQueue;
    }

    @Override
    public void run() {
        while (true) {
            if (lrQueue.queueL.size() < lrQueue.queueR.size()) {
                lrQueue.flagL = true;
                lrQueue.flagR = false;
            } else {
                lrQueue.flagL = false;
                lrQueue.flagR = true;
            }
        }
    }
}
