package com.pait.taskPool.service;

import com.pait.consumeAnnual.dto.UserConsume;
import com.pait.consumeAnnual.dto.UserConsumeDTO;

import java.util.*;

/**
 * Created by dynamicniu on 2018/4/1.
 */
public interface TaskPoolService {

    List<UserConsume> intoQueueFullAmount();

}
