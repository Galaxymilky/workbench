package com.demo.thread.demo;

import java.util.Vector;

/**
 * Created by dynamicniu on 2018/3/31.
 */
public class ProductQueue {
    Vector<Production> queueL;

    Vector<Production> queueR;

    volatile boolean flagL;

    volatile boolean flagR;

    public ProductQueue(Vector<Production> queueL, Vector<Production> queueR, boolean flagL, boolean flagR) {
        this.queueL = queueL;
        this.queueR = queueR;
        this.flagL = flagL;
        this.flagR = flagR;
    }

    public static class Production {
        final static int LEFT = 0;

        final static int RIGHT = 1;

        int type;

        public Production(int type) {
            this.type = type;
        }
    }


}
