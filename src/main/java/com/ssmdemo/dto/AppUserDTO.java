package com.ssmdemo.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ssmdemo.util.DateJsonSerializer;

import java.util.Date;

/**
 * Created by dynamicniu on 2017/8/20.
 */
public class AppUserDTO {

    @JSONField(format="yyyy-MM-dd HH:mm:ss", name = "create_date")
    private Date createDate = new Date();

    @JSONField(name = "user_id")
    private String userId;

    @JSONField(name = "user_name")
    private String userName;

    private String memo;

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    @Override
    public String toString() {
        return "AppUserDTO{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", memo='" + memo + '\'' +
                '}';
    }
}
