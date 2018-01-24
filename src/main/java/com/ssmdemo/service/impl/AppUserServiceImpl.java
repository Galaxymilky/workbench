package com.ssmdemo.service.impl;

import com.ssmdemo.common.DataPager;
import com.ssmdemo.dao.AppUserDao;
import com.ssmdemo.entity.AppUser;
import com.ssmdemo.service.AppUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import work.utils.StrUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dynam on 2017/5/1.
 */
@Service
public class AppUserServiceImpl implements AppUserService {

    @Override
    public int getTotalCount() {
        return appUserDao.queryTotalCount();
    }

    @Override
    public List<AppUser> listAppUser(int offset, int limit) {
        List<AppUser> result = new ArrayList<>();

        result = appUserDao.queryAll(offset, limit);

        return result;
    }

    @Override
    public List<AppUser> listAppUserAjax(DataPager pager) {
        List<AppUser> result = new ArrayList<>();
        int offset = (pager.getPageIndex() - 1) * 10;
        int limit = pager.getPageSize();
        String srchVal = pager.getSearchVal();
        if (StrUtils.isNullOrEmpty(srchVal)) {
            result = appUserDao.queryAll(offset, limit);
        } else {
            //srchVal = "%"+ srchVal +"%";
            result = appUserDao.queryFuzzy(offset, limit, srchVal, srchVal, srchVal, srchVal);
        }
        return result;
    }

    @Override
    public int multiInsert(AppUser appUser) {
        String userName = appUser.getUserName();
        String loginName = appUser.getLoginName();
        long userPhone = appUser.getUserPhone();
        int priority = appUser.getPriority();
        int res = appUserDao.insertUser(userName, loginName, userPhone, priority);
        return res;
    }

    @Override
    public AppUser getAppUserByLoginName(String loginName) {
        return appUserDao.queryByLoginName(loginName);
    }

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AppUserDao appUserDao;
}
