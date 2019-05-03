package com.workbench.protocolSimulation.dto;

import com.workbench.common.BaseDto;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class ProtocolSimulationDto extends BaseDto {
    // 时间
    private String operTime;

    //条形码
    private String snCode;

    //充气压力KPa
    private String kPre;

    //差压值Pa
    private String dkPre;

    // 泄漏率ml/min
    private String leakage;

    //判断结果(OK或者NG)
    private String result;

    //
    private String protocolType;

    private String memo;
}
