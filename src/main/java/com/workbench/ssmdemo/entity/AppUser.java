package com.workbench.ssmdemo.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * Created by dynam on 2017/5/1.
 */
@Getter
@Setter
@ToString
public class AppUser {
    private long userId;

    private String userName;

    private String loginName;

    private Date createTime;

    private long userPhone;

    private int priority;

    private String password;

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
