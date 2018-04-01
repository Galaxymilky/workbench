package com.pait.taskPool.thread;

import com.pait.consumeAnnual.dao.UserConsumeDAO;
import com.pait.consumeAnnual.dto.UserConsume;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by dynamicniu on 2018/4/1.
 */
public class SinglePorter implements Runnable {
    @Override
    public void run() {
        System.out.println("Porter start work");
        List<UserConsume> list = dao.listAllData();
        if (list == null || list.size() <= 0) {
            return;
        }

        for (int i = 0; i < list.size(); i++) {
            UserConsume dto = list.get(i);

            if (PAY_PLATFORM_4.equals(dto.getPayPlatform())) {
                buffer4.add(dto);
                System.out.println("BUFFER_4 ADD {" + dto.getIdUserConsume() + "}");
            } else if (PAY_PLATFORM_1.equals(dto.getPayPlatform())) {
                buffer1.add(dto);
                System.out.println("BUFFER_1 ADD {" + dto.getIdUserConsume() + "}");
            }
        }
        System.out.println("Porter end work");
    }

    public SinglePorter(BlockingQueue<UserConsume> buffer4, BlockingQueue<UserConsume> buffer1, UserConsumeDAO dao) {
        this.buffer4 = buffer4;
        this.buffer1 = buffer1;
        this.dao = dao;
    }

    public static final String PAY_PLATFORM_1 = "PP1";

    public static final String PAY_PLATFORM_4 = "PP4";

    BlockingQueue<UserConsume> buffer4;

    BlockingQueue<UserConsume> buffer1;

    UserConsumeDAO dao;



}
