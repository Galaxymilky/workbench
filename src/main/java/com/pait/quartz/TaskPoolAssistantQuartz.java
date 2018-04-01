package com.pait.quartz;

import com.pait.consumeAnnual.dto.UserConsume;
import com.pait.consumeAnnual.dto.UserConsumeDTO;
import com.pait.taskPool.service.TaskPoolService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.List;

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
