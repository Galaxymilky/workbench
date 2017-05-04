package com.ssmdemo.entity;

import java.util.Date;

/**
 * Created by dynam on 2017/5/1.
 */
public class AppUser {
    private long userId;

    private String userName;

    private String loginName;

    private Date createTime;

    private long userPhone;

    private int priority;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public long getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(long userPhone) {
        this.userPhone = userPhone;
    }

    @Override
    public String toString() {
        return "AppUser = [userId=" + userId + ", userName=" + userName + ", loginName=" + loginName + ", createTime=" + createTime + ", priority=" + priority + "]";
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
