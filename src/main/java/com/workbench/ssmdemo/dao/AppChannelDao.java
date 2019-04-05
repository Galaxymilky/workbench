package com.workbench.ssmdemo.dao;

import com.workbench.ssmdemo.entity.AppChannel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by dynamicniu on 2017/5/23.
 */
@Mapper
public interface AppChannelDao {

    /**
     * 根据登陆名查询用户对象
     *
     * @return
     */
    List<AppChannel> queryAll();

}
