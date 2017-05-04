package com.ssmdemo.service;

import com.ssmdemo.entity.AppUser;

import java.util.List;

/**
 * Created by dynam on 2017/5/1.
 */
public interface AppUserService {
    List<AppUser> getAppUserList(int offset, int limit);
}
