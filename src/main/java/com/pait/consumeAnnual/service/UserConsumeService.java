package com.pait.consumeAnnual.service;

import com.ssmdemo.common.DataPager;
import com.pait.consumeAnnual.dto.UserConsume;

import java.util.List;
import java.util.Map;

/**
 * Created by dynam on 2017/5/1.
 */
public interface UserConsumeService {

    List<UserConsume> listUserConsumeByCondition(int offset, int limit, Map<String, Object> params);

    int getTotalCount();

    List<UserConsume> queryAll();

    List<UserConsume> listUserConsumePager(DataPager pager);

    int countUserConsume();
}
