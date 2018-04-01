package com.pait.taskPool.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by dynamicniu on 2018/3/31.
 */
public class SingleExecutorService {

    private ExecutorService executorService;

    private SingleExecutorService() {

    }

    private static class HolderClass {
        private final static ExecutorService instance = Executors.newFixedThreadPool(6 + 1);
    }

    public static ExecutorService getInstance() {
        return HolderClass.instance;
    }

    public static void main(String[] args) {
        ExecutorService s1, s2;
        s1 = SingleExecutorService.getInstance();
        s2 = SingleExecutorService.getInstance();

        ExecutorService s3 = Executors.newCachedThreadPool();

        System.out.println(s1 == s2);
    }
}
