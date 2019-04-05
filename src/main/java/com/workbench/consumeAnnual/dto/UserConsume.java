package com.workbench.consumeAnnual.dto;

import lombok.Data;

@Data
public class UserConsume {
    private Long idUserConsume;
    private Long userId;
    private Double consumeAmount;
    private String consumeAddress;
    private String payMethod;
    private String productDesc;
    private String productType;
    private java.sql.Timestamp createdTime;
    private String createdBy;
    private String memo;
    private Long priority;
    private String payPlatform;
    private java.sql.Timestamp consumeTime;
    private String consumeTimeStr;
    private String orderId;
    private int hashCode;

}
