package com.workbench.quartz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by dynamicniu on 2018/4/1.
 */
@Component
public class TaskPoolAssistantQuartz {

    @Scheduled(cron = "30 * * * * ?")
    public void taskPoolAssignToQueue() {
        LOG.info("@Scheduled--------taskPoolAssignToQueue()");
//        List<UserConsume> list = taskPoolService.getNewData();

//        if (list == null || list.size() <= 0) {
//            return;
//        }


    }

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

}
