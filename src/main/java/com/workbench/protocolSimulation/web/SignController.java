package com.workbench.protocolSimulation.web;

import com.workbench.common.BaseRest;
import com.workbench.common.DataJson;
import com.workbench.ssmdemo.entity.AppUser;
import com.workbench.ssmdemo.service.AppUserService;
import com.workbench.ssmdemo.service.impl.AppUserServiceImpl;
import com.workbench.util.EncryptUtil;
import com.workbench.util.JsonUtils;
import com.workbench.util.StrUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/sign")
@Api(description = "登录API")
public class SignController extends BaseRest {

    @ApiOperation(value = "登录系统", notes = "登录系统")
    @PostMapping(value = "/signIn", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String signIn(HttpServletRequest request, HttpServletResponse response, String loginName, String password) {
        log.info("invoke -------- /{}/{}", this.getClass().getSimpleName(), "signIn");

        response.setContentType("text/html;charset=utf-8");
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put(RESULT_CODE, FAILED);
        String passwd = request.getParameter("password");
        try {

            HttpSession httpSession = request.getSession();

            AppUser user = (AppUser) httpSession.getAttribute(LOGIN_INFO);

            if (user != null && loginName.equals(user.getLoginName())) {
                resMap.put(RESULT_CODE, SUCCESS);
                resMap.put(RESULT_MESG, "用户已登陆");
                resMap.put("accessGranted", true);
                return JsonUtils.transObject2Json(resMap);
            }

            if ("admin".equals(loginName)) {
                if ("123".equals(passwd)) {
                    resMap.put(RESULT_CODE, SUCCESS);
                    resMap.put(RESULT_MESG, "登录成功");
                    resMap.put("accessGranted", true);
                    AppUser appUser = new AppUser();
                    appUser.setLoginName("admin");
                    appUser.setPassword("123");
                    httpSession.setAttribute(LOGIN_INFO, appUser);
                } else {
                    resMap.put(RESULT_MESG, "用户不存在");
                }
                return JsonUtils.transObject2Json(resMap);
            }

            AppUser appUser = appUserSrv.getAppUserByLoginName(loginName);
            if (appUser == null) {
                resMap.put(RESULT_MESG, "用户不存在");
                return JsonUtils.transObject2Json(resMap);
            }

            // 获取加密内容
            String rPassword = appUser.getPassword() == null ? "" : appUser.getPassword();
            // 对用户输入的密码进行加密
            String dPassword = EncryptUtil.EncryptPassword(passwd);
            if (rPassword.equals(dPassword)) {
                resMap.put(RESULT_CODE, SUCCESS);
                resMap.put(RESULT_MESG, "登录成功");
                resMap.put("accessGranted", true);

                httpSession.setAttribute(LOGIN_INFO, appUser);

                return JsonUtils.transObject2Json(resMap);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return JsonUtils.transObject2Json(resMap);
    }

    @ApiOperation(value = "退出系统", notes = "退出系统")
    @PostMapping(value = "/logout")
    public DataJson logout(HttpServletRequest request, HttpServletResponse response, String loginName) {
        log.info("invoke -------- /{}/{}", this.getClass().getSimpleName(), "logout");

        AppUser appUser = new AppUser();
        appUser.setLoginName(loginName);

        HttpSession httpSession = request.getSession();

        Object loginUser = httpSession.getAttribute(LOGIN_INFO);
        if (loginUser == null) {
            log.error("User {} already logout!", loginName);
        }

        httpSession.removeAttribute(LOGIN_INFO);

        return new DataJson(SUCCESS, SUCCESS);
    }

    @ApiOperation(value = "获取用户登录信息", notes = "获取用户登录信息")
    @PostMapping(value = "/getLoginInfo", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getLoginInfo(HttpServletRequest request, HttpServletResponse response) {
        log.info("invoke -------- /{}/{}", this.getClass().getSimpleName(), "getLoginInfo");

        response.setContentType("text/html;charset=utf-8");
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put(RESULT_CODE, FAILED);

        HttpSession session = request.getSession();
        if (session.getAttribute(LOGIN_INFO) == null) {
            resMap.put(RESULT_MESG, "用户未登录");
            return JsonUtils.transObject2Json(resMap);
        }

        AppUser appUser = (AppUser) session.getAttribute(LOGIN_INFO);

        resMap.put(RESULT_CODE, "success");
        resMap.put(LOGIN_INFO, appUser);

        return JsonUtils.transObject2Json(resMap);
    }

    @Autowired
    AppUserService appUserSrv = new AppUserServiceImpl();
}
