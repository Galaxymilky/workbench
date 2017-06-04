package com.ssmdemo.dao;

import com.ssmdemo.entity.AppChannel;

import java.util.List;

/**
 * Created by dynamicniu on 2017/5/23.
 */
public interface AppChannelDao {

    /**
     * 根据登陆名查询用户对象
     *
     * @return
     */
    List<AppChannel> queryAll();

}
