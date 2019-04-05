package com.workbench.consumeAnnual.dao;

import com.workbench.consumeAnnual.dto.UserConsume;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

/**
 * Created by dynamicniu on 2018/1/14.
 */
@Mapper
public interface UserConsumeDAO {
    /**
     * 增加新对象
     *
     * @param userName
     * @param loginName
     * @param userPhone
     * @param priority
     * @return
     */
    int insertUserConsume(@Param("userName") String userName, @Param("loginName") String loginName,
                          @Param("userPhone") long userPhone, @Param("priority") int priority);

    /**
     * 根据登陆名查询用户对象
     *
     * @param loginName
     * @return
     */
    UserConsume queryByLoginName(String loginName);

    /**
     * 根据偏移量查询用户列表
     *
     * @param offset
     * @param limit
     * @return
     */
    List<UserConsume> queryAll(@Param("offset") int offset, @Param("limit") int limit) throws Exception;

    /**
     * 获取用户总数
     */
    int queryTotalCount();

    /**
     * 更新优先级
     *
     * @param newPriority
     */
    void updatePriority(@Param("newPriority") int newPriority);

    /**
     * DataTable 列表页面检索，普通条件检索
     *
     * @param offset
     * @param limit
     * @return
     */
    List<UserConsume> listConsumeByCondition(@Param("offset") int offset, @Param("limit") int limit, Map<String, Object> params);

    /**
     * DataTable 列表页面检索，普通条件检索
     *
     * @param priority
     * @param productType
     * @return
     */
    List<UserConsume> listConsumeByCondition(@Param("offset") int offset, @Param("limit") int limit,
                                                @Param("priority") String priority, @Param("productType") String productType,
                                                @Param("payMethod") String payMethod, @Param("payPlatform") String payPlatform);

    /**
     * DataTable 列表页面检索，模糊查询
     *
     * @param offset
     * @param limit
     * @param priority
     * @return
     */
    List<UserConsume> listConsumeByFuzzy(@Param("offset") int offset, @Param("limit") int limit,
                                            @Param("priority") String priority, @Param("userName") String userName,
                                            @Param("userPhone") String userPhone, @Param("loginName") String loginName);

    int countUserConsume();

    List<UserConsume> listAllData();
}
