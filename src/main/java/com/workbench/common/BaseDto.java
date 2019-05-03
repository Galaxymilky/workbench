package com.workbench.common;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class BaseDto {
    private Timestamp createdTime;

    private String createdBy;

    private Timestamp updatedTime;

    private String updatedBy;
}
