package com.workbench.protocolSimulation.web;

import com.workbench.common.BaseRest;
import com.workbench.protocolSimulation.dto.ProtocolSimulationForm;
import com.workbench.protocolSimulation.dto.ProtocolSimulationJson;
import com.workbench.protocolSimulation.service.ProtocolSimulationService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/protocolSimulation")
@Api(description = "协议模拟 API")
@Slf4j
public class ProtocolSimulationRest extends BaseRest {

    @Autowired
    private ProtocolSimulationService protocolSimulationService;

    @PostMapping(value = "/add")
    public Map<String, Object> add(HttpServletRequest request, ProtocolSimulationForm form) {
        log.info("invoke -------- /{}/{}", this.getClass().getSimpleName(), "add");

        Map<String, Object> result = new HashMap<>();

        protocolSimulationService.addProtocolSimulation(form);

        result.put(RESULT_CODE, SUCCESS);
        result.put(RESULT_MESG, "成功");

        return result;
    }


    @PostMapping(value = "/list")
    public Map<String, Object> list(HttpServletRequest request, String protocolType, String operDate) {
        log.info("invoke -------- /{}/{}", this.getClass().getSimpleName(), "list");
        Map<String, Object> result = new HashMap<>();

        List<ProtocolSimulationJson> list = protocolSimulationService.listProtocolSimulation(protocolType, operDate);

        result.put(RESULT_CODE, SUCCESS);
        result.put("protocolDataList", list);

        return result;
    }

    @PostMapping(value = "/tcpProtocolProcess")
    public Map<String, Object> tcpProtocolProcess(HttpServletRequest request, String command) {
        log.info("invoke -------- /{}/{}", this.getClass().getSimpleName(), "tcpProtocolProcess");
        Map<String, Object> result = new HashMap<>();

        if (!isHex(command)) {
            result.put(RESULT_CODE, FAILED);
            result.put(RESULT_CODE, "检测到命令不符合规范，请重试");
        }

        String resultMesg = processProtocol(command);

        result.put(RESULT_CODE, SUCCESS);
        result.put(RESULT_MESG, resultMesg);

        return result;
    }

    private String processProtocol(String cmd) {
        StringBuffer buffer = new StringBuffer("");
        cmd = cmd.replace(" ", "");
        if (cmd.length() == 2 * 10) {
            if ("0000000000".equals(cmd.substring(0, 9))) {
                buffer.append("");
            }
            if ("01".equals(cmd.substring(18, 20))) {
                buffer.append("机器启动");
            } else if ("00".equals(cmd.substring(18, 20))) {
                buffer.append("机器停止");
            }
        } else if (cmd.length() == 2 * 12) {

        }

        return buffer.toString();
    }

    private boolean isHex(String cmd) {
        String reg = "(^0x[a-f0-9]{1,2}$)|(^0X[A-F0-9]{1,2}$)|(^[A-F0-9]{1,2}$)|(^[a-f0-9]{1,2}$)";
        if (cmd == null || "".equals(cmd)) {
            return false;
        }

        cmd = cmd.replace(" ", "");

        if (cmd.length() % 2 != 0 || cmd.length() <= 0) {
            return false;
        }

        String[] array = cmd.split(" ");
        for (int i = 0; i < cmd.length(); i++) {
            char c = cmd.charAt(i);
            if (((c >= 'a') && (c <= 'f')) || ((c >= 'A') && (c <= 'F')) || ((c >= '0') && (c <= '9'))) {
                continue;
            } else {
                return false;
            }
        }
        return true;
    }

    private String validateHexCmd(String cmd) {
        return "";
    }
}
