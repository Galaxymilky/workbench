package com.ssmdemo.service.impl;

import com.ssmdemo.dao.AppUserDao;
import com.ssmdemo.entity.AppUser;
import com.ssmdemo.service.AppUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dynam on 2017/5/1.
 */
@Service
public class AppUserServiceImpl implements AppUserService {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AppUserDao appUserDao;

    @Override
    public List<AppUser> getAppUserList(int offset, int limit) {
        List<AppUser> result = new ArrayList<>();

        result = appUserDao.queryAll(offset, limit);
        if (result == null) {
            LOG.info("");
        } else {
            LOG.info("");
        }

        return result;
    }
}
