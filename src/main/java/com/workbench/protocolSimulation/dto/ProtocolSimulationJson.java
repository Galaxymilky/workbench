package com.workbench.protocolSimulation.dto;

import com.workbench.common.BaseJson;
import lombok.Data;

@Data
public class ProtocolSimulationJson extends BaseJson {

    //
    private Integer idProtocolSimulation;

    // 时间
    private String operTime;

    // 日期
    private String operDate;

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
}
