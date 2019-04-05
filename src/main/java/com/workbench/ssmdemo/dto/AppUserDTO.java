package com.workbench.ssmdemo.dto;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Created by dynamicniu on 2017/8/20.
 */
@Getter
@Setter
public class AppUserDTO {

    @JSONField(format="yyyy-MM-dd HH:mm:ss", name = "create_date")
    private Date createDate = new Date();

    @JSONField(name = "user_id")
    private String userId;

    @JSONField(name = "user_name")
    private String userName;

    private String memo;

    @Override
    public String toString() {
        return "AppUserDTO{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", memo='" + memo + '\'' +
                '}';
    }
}
