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
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@RestController
@RequestMapping("/protocolSimulation")
@Api(description = "协议模拟 API")
@Slf4j
public class ProtocolSimulationRest extends BaseRest {

    private static final String FUNCTION = "FUNCTION";

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
        result.put(RESULT_CODE, SUCCESS);

        List<ProtocolSimulationJson> list = protocolSimulationService.listProtocolSimulation(protocolType, operDate);
        result.put("protocolDataList", list);

        Integer httpCount = protocolSimulationService.countProtocol("1");
        result.put("httpCount", httpCount);
        Integer tcpCount = protocolSimulationService.countProtocol("2");
        result.put("tcpCount", tcpCount);

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

        HttpSession session = request.getSession();

        String resultMesg = processProtocol(session, command);

        result.put(RESULT_CODE, SUCCESS);
        result.put(RESULT_MESG, resultMesg);

        return result;
    }

    private String buildReturnProtocol(String cmd) {
        StringBuffer buffer = new StringBuffer("");

        buffer.append("00").append(" ")
                .append("00").append(" ")
                .append("00").append(" ");

        // K8 起始字符组，与查询报文对应
        buffer.append(cmd.substring(6, 8)).append(" ");

        // K9 起始字符串，协议报文类型，0000代表MODBUS/TCP类型
        buffer.append("00").append(" ");

        // K10 起始字符串，包长，代表还有多少字节的指令，返回协议固定为0009
        buffer.append("09").append(" ");

        // K11 设备地址。
        buffer.append(cmd.substring(12, 14)).append(" ");

        // K12 功能码
        buffer.append(cmd.substring(14, 16)).append(" ");

        // K13 数据字节长度，固定0006，表示还有6个字节的数据
        buffer.append("06").append(" ");

        // K14 内容随机。
        // 00 1E 00 32 00 28
        // 十六进制转十进制，分别为：充气压力30，差压值50，泄露率40
        Double d1 = new Double(Math.random() * 180);

        Double d2 = new Double(Math.random() * 50);

        Double d3 = new Double(Math.random() * 10);

        log.info("data1 = {}, data2 = {}, data3 = {}", d1, d2, d3);

        String data1 = intToHex(d1.intValue());
        String data2 = intToHex(d2.intValue());
        String data3 = intToHex(d3.intValue());

        buffer.append("00").append(" ")
                .append(data1).append(" ");

        buffer.append("00").append(" ")
                .append(data2).append(" ");

        buffer.append("00").append(" ")
                .append(data3).append(" ");

        log.info("d1 = {}, d2 = {}, d3 = {}", data1, data2, data3);

        ProtocolSimulationForm form = new ProtocolSimulationForm();
        form.setKPre(String.valueOf(d1.intValue()));
        form.setDkPre(String.valueOf(d2.intValue()));
        form.setLeakage(String.valueOf(d3.intValue()));
        form.setProtocolType("2");
        form.setSnCode("999999999999");
        form.setResult("OK");
        protocolSimulationService.addProtocolSimulation(form);

        log.info("添加成功");

        return buffer.toString();
    }

    private String processProtocol(HttpSession session, String cmd) {
        StringBuffer buffer = new StringBuffer("");
        cmd = cmd.replace(" ", "");
        if (cmd.length() == 2 * 10) {
            if ("0000000000".equals(cmd.substring(0, 9))) {
                buffer.append("");
            }
            if ("01".equals(cmd.substring(18, 20))) {
                buffer.append("机器启动");
                session.setAttribute(FUNCTION, "1");
            } else if ("00".equals(cmd.substring(18, 20))) {
                buffer.append("机器停止");
                session.setAttribute(FUNCTION, "0");
            } else {
                buffer.append("指令错误");
            }
        } else if (cmd.length() == 2 * 12) {
            if (!"1".equals(session.getAttribute(FUNCTION))) {
                buffer.append("机器未启动，请先启动机器");
                return buffer.toString();
            }

            // 如果K1、K2、K3不同，报指令格式错误
            if (!"00".equals(cmd.substring(6, 8))) {
                // 区分查询与响应报文是否一组
                buffer.append("指令格式错误");
                return buffer.toString();
            }
            if (!"00".equals(cmd.substring(8, 10))) {
                // 协议类型，0000代表MODBUS/TCP类型协议
                buffer.append("指令格式错误");
                return buffer.toString();
            }
            if (!"06".equals(cmd.substring(10, 12))) {
                // 代表后边有6个字节内容
                buffer.append("指令格式错误");
                return buffer.toString();
            }

            if (!"01".equals(cmd.substring(12, 14))) {
                // 设备地址不存在
                buffer.append("设备地址不存在");
                return buffer.toString();
            }
            if (!"03".equals(cmd.substring(14, 16))) {
                // 功能码缺失
                buffer.append("功能码缺失");
                return buffer.toString();
            }
            if (!"0000".equals(cmd.substring(16, 20))) {
                // 寄存器地址或数量不正确
                buffer.append("寄存器地址或数量不正确");
                return buffer.toString();
            }

            if (!"0003".equals(cmd.substring(20, 24))) {
                // 寄存器地址或数量不正确
                buffer.append("寄存器地址或数量不正确");
                return buffer.toString();
            }

            // 组装返回的协议Hex数据
            buffer.append(buildReturnProtocol(cmd));

        } else {
            buffer.append("指令错误");
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

    private String intToHex(int n) {
        StringBuilder sb = new StringBuilder(8);
        char[] b = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        while (n != 0) {
            sb = sb.append(b[n % 16]);
            n = n / 16;
        }
        if (sb.toString().length() == 1) {
            sb.append("0");
        } else if (sb.toString().length() <= 0) {
            sb.append("00");
        }
        return sb.reverse().toString();
    }
}
