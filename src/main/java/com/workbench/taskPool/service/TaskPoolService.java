package com.workbench.taskPool.service;

import com.workbench.consumeAnnual.dto.UserConsume;

import java.util.*;

/**
 * Created by dynamicniu on 2018/4/1.
 */
public interface TaskPoolService {

    List<UserConsume> intoQueueFullAmount();

}
