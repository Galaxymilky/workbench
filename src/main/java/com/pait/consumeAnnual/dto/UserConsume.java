package com.pait.consumeAnnual.dto;

import java.sql.Timestamp;

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

    public Long getIdUserConsume() {
        return idUserConsume;
    }

    public void setIdUserConsume(Long idUserConsume) {
        this.idUserConsume = idUserConsume;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Double getConsumeAmount() {
        return consumeAmount;
    }

    public void setConsumeAmount(Double consumeAmount) {
        this.consumeAmount = consumeAmount;
    }

    public String getConsumeAddress() {
        return consumeAddress;
    }

    public void setConsumeAddress(String consumeAddress) {
        this.consumeAddress = consumeAddress;
    }

    public String getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Long getPriority() {
        return priority;
    }

    public void setPriority(Long priority) {
        this.priority = priority;
    }

    public String getPayPlatform() {
        return payPlatform;
    }

    public void setPayPlatform(String payPlatform) {
        this.payPlatform = payPlatform;
    }

    public Timestamp getConsumeTime() {
        return consumeTime;
    }

    public void setConsumeTime(Timestamp consumeTime) {
        this.consumeTime = consumeTime;
    }

    public String getConsumeTimeStr() {
        return consumeTimeStr;
    }

    public void setConsumeTimeStr(String consumeTimeStr) {
        this.consumeTimeStr = consumeTimeStr;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
