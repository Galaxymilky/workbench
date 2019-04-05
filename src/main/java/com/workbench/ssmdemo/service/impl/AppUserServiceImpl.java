package com.workbench.ssmdemo.service.impl;

import com.workbench.common.DataPager;
import com.workbench.ssmdemo.dao.AppUserDao;
import com.workbench.ssmdemo.entity.AppUser;
import com.workbench.ssmdemo.service.AppUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.workbench.util.StrUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dynam on 2017/5/1.
 */
@Slf4j
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

    @Autowired
    private AppUserDao appUserDao;
}
