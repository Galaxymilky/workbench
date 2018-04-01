package com.pait.taskPool.thread;

import com.pait.consumeAnnual.dao.UserConsumeDAO;
import com.pait.consumeAnnual.dto.UserConsume;
import com.pait.consumeAnnual.dto.UserConsumeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.BlockingQueue;

import java.util.List;

/**
 * Created by dynamicniu on 2018/4/1.
 */
public class Porter implements Runnable {
    @Override
    public void run() {
        while (true) {
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

            try {
                Thread.sleep(1000 * 60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public Porter(BlockingQueue<UserConsume> buffer4, BlockingQueue<UserConsume> buffer1, UserConsumeDAO dao) {
        this.buffer4 = buffer4;
        this.buffer1 = buffer1;
        this.dao = dao;
    }

    BlockingQueue<UserConsume> buffer4;

    BlockingQueue<UserConsume> buffer1;

    UserConsumeDAO dao;

    public static final String PAY_PLATFORM_1 = "PP1";

    public static final String PAY_PLATFORM_4 = "PP4";

}
