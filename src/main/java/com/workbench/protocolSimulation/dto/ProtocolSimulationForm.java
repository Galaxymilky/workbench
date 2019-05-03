package com.workbench.protocolSimulation.dto;

import com.workbench.common.BaseForm;
import lombok.Data;

@Data
public class ProtocolSimulationForm extends BaseForm {
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

}
