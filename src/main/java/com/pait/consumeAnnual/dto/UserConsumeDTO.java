package com.pait.consumeAnnual.dto;

public class UserConsumeDTO {
    private Long id_user_consume;
    private Long user_id;
    private Double consume_amount;
    private String consume_address;
    private String pay_method;
    private String product_desc;
    private String product_type;
    private java.sql.Timestamp created_time;
    private String created_by;
    private String memo;
    private Long priority;
    private String pay_platform;
    private java.sql.Timestamp consume_time;

    public Long getId_user_consume() {
        return id_user_consume;
    }

    public void setId_user_consume(Long id_user_consume) {
        this.id_user_consume = id_user_consume;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Double getConsume_amount() {
        return consume_amount;
    }

    public void setConsume_amount(Double consume_amount) {
        this.consume_amount = consume_amount;
    }

    public String getConsume_address() {
        return consume_address;
    }

    public void setConsume_address(String consume_address) {
        this.consume_address = consume_address;
    }

    public String getPay_method() {
        return pay_method;
    }

    public void setPay_method(String pay_method) {
        this.pay_method = pay_method;
    }

    public String getProduct_desc() {
        return product_desc;
    }

    public void setProduct_desc(String product_desc) {
        this.product_desc = product_desc;
    }

    public String getProduct_type() {
        return product_type;
    }

    public void setProduct_type(String product_type) {
        this.product_type = product_type;
    }

    public java.sql.Timestamp getCreated_time() {
        return created_time;
    }

    public void setCreated_time(java.sql.Timestamp created_time) {
        this.created_time = created_time;
    }

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
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

    public String getPay_platform() {
        return pay_platform;
    }

    public void setPay_platform(String pay_platform) {
        this.pay_platform = pay_platform;
    }

    public java.sql.Timestamp getConsume_time() {
        return consume_time;
    }

    public void setConsume_time(java.sql.Timestamp consume_time) {
        this.consume_time = consume_time;
    }
}
