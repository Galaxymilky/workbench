package com.ssmdemo.service;

import com.ssmdemo.common.DataPager;
import com.ssmdemo.entity.AppUser;

import java.util.List;

/**
 * Created by dynam on 2017/5/1.
 */
public interface AppUserService {
    int multiInsert(AppUser appUser);

    List<AppUser> getAppUserList(int offset, int limit);

    List<AppUser> getAppUserListAjax(DataPager page);

    int getTotalCount();
}
