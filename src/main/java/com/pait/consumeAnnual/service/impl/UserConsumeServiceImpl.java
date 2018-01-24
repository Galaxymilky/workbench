package com.pait.consumeAnnual.service.impl;

import com.ssmdemo.common.DataPager;
import com.pait.consumeAnnual.dao.UserConsumeDAO;
import com.pait.consumeAnnual.dto.UserConsume;
import com.pait.consumeAnnual.service.UserConsumeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import work.utils.StrUtils;

/**
 * Created by dynamicniu on 2018/1/14.
 */
@Service
public class UserConsumeServiceImpl implements UserConsumeService {
    @Override
    public List<UserConsume> listUserConsumeByCondition(int offset, int limit, Map<String, Object> params) {
        List<UserConsume> list = new ArrayList<UserConsume>();

        String priority = (String) params.get("priority");
        String productType = (String) params.get("productType");
        String payMethod = (String) params.get("payMethod");
        String payPlatform = (String) params.get("payPlatform");

        list = userConsumeDAO.listConsumeByCondition(offset, limit, priority, productType, payMethod, payPlatform);

//        list = userConsumeDAO.listConsumeByCondition(offset, limit, params);

        return list;
    }


    @Override
    public int getTotalCount() {
        return 0;
    }

    @Override
    public List<UserConsume> queryAll() {
        return null;
    }

    @Override
    public List<UserConsume> listUserConsumePager(DataPager pager) {
        List<UserConsume> result = new ArrayList<UserConsume>();
        int offset = (pager.getPageIndex() - 1) * 10;
        int limit = pager.getPageSize();
        String srchVal = pager.getSearchVal();
        if (StrUtils.isNullOrEmpty(srchVal)) {
            try {
                result = userConsumeDAO.queryAll(offset, limit);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            //srchVal = "%"+ srchVal +"%";
            // result = userConsumeDAO.queryFuzzy(offset, limit, srchVal, srchVal, srchVal, srchVal);
        }
        return result;
    }

    @Override
    public int countUserConsume() {
        return userConsumeDAO.countUserConsume();
    }

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private UserConsumeDAO userConsumeDAO;
}
