package com.workbench.ssmdemo.service;

import com.workbench.common.DataPager;
import com.workbench.ssmdemo.entity.AppUser;

import java.util.List;

/**
 * Created by dynam on 2017/5/1.
 */
public interface AppUserService {

    int multiInsert(AppUser appUser);
    

    List<AppUser> listAppUser(int offset, int limit);


    List<AppUser> listAppUserAjax(DataPager page);


    int getTotalCount();

    /**
     * 根据用户登录名获取用户
     *
     * @param loginName 登录名
     * @return
     */
    AppUser getAppUserByLoginName(String loginName);
}
