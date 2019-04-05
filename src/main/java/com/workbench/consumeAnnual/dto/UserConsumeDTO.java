package com.workbench.consumeAnnual.dto;

import lombok.Data;

@Data
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

}
