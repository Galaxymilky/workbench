package com.ssmdemo.dao;

import com.ssmdemo.entity.AppUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by dynam on 2017/4/30.
 */
public interface AppUserDao {
    /**
     * 根据登陆名查询用户对象
     *
     * @param loginName
     * @return
     */
    AppUser queryByLoginName(String loginName);

    /**
     * 根据偏移量查询用户列表
     *
     * @param offset
     * @param limit
     * @return
     */
    List<AppUser> queryAll(@Param("offset") int offset, @Param("limit") int limit);

    /**
     * 更新优先级
     */
    void updatePriority(@Param("newPriority") int newPriority);
}
