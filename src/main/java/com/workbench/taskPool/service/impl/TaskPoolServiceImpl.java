package com.workbench.taskPool.service.impl;

import com.workbench.consumeAnnual.dao.UserConsumeDAO;
import com.workbench.consumeAnnual.dto.UserConsume;
import com.workbench.taskPool.service.TaskPoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by dynamicniu on 2018/4/1.
 */
@Service
public class TaskPoolServiceImpl implements TaskPoolService {
    @Override
    public List<UserConsume> intoQueueFullAmount() {
        List<UserConsume> list = userConsumeDAO.listAllData();
        return list;
    }


    @Autowired
    private UserConsumeDAO userConsumeDAO;
}
