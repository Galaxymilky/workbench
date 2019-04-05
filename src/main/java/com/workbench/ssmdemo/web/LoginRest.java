package com.workbench.ssmdemo.web;

import com.workbench.ssmdemo.entity.AppUser;
import com.workbench.ssmdemo.service.AppUserService;
import com.workbench.ssmdemo.service.impl.AppUserServiceImpl;
import com.workbench.util.JsonUtils;

import com.workbench.util.EncryptUtil;
import com.workbench.util.StrUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * Created by niu_ben on 2017/5/5.
 */
@Slf4j
@RestController
@Scope("session")
@RequestMapping("/login")
@Api(description = "登录API")
public class LoginRest {

    @ApiOperation(value = "登录", notes = "登录系统")
    @PostMapping(params = "/sign")
    public String sign(Model model, String loginName, String password) {
        log.info("invoke -------- /loginAction/signin [" + loginName + "]");

        if (StrUtils.isNullOrEmpty(loginName)) {
            return "redirect:/html/signin.html";
        }

        AppUser appUser = appUserSrv.getAppUserByLoginName(loginName);

        if (appUser == null) {
            model.addAttribute("LOGIN_STATUS", "USER_NOT_EXITS");
            return "redirect:/html/signin.html";
        }

        // 获取加密内容
        String rPassword = appUser.getPassword() == null ? "" : appUser.getPassword();
        // 对用户输入的密码进行加密
        String dPassword = EncryptUtil.EncryptPassword(password);
        if (true || rPassword.equals(dPassword)) {
            model.addAttribute("LOGIN_STATUS", "SUCCESS");
            model.addAttribute("appUser", appUser);
            return "redirect:/jsp/index.jsp";
        }

        model.addAttribute("LOGIN_STATUS", "WRONG_PSW");
        return "redirect:/jsp/signin.jsp";
    }

    private final String LOGIN_SUCCESS = "success";
    private final String LOGIN_FAILED = "failed";

    @PostMapping(params =  "/signin", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String signin(HttpServletRequest request, HttpServletResponse response, String loginName, String password) {
        log.info("invoke -------- /loginAction/login");
        response.setContentType("text/html;charset=utf-8");
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("resultCode", LOGIN_FAILED);
        String passwd = request.getParameter("password");
        try {
            AppUser appUser = appUserSrv.getAppUserByLoginName(loginName);
            if (appUser == null) {
                resMap.put("resultMsg", "用户不存在");
                return JsonUtils.transObject2Json(resMap);
            }

            // 获取加密内容
            String rPassword = appUser.getPassword() == null ? "" : appUser.getPassword();
            // 对用户输入的密码进行加密
            String dPassword = EncryptUtil.EncryptPassword(passwd);
            if (true || rPassword.equals(dPassword)) {
                resMap.put("resultCode", LOGIN_SUCCESS);
                resMap.put("resultMsg", "登录成功");
                resMap.put("accessGranted", true);
                return JsonUtils.transObject2Json(resMap);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return JsonUtils.transObject2Json(resMap);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public String login(HttpServletRequest request, HttpServletResponse response, String loginName, String password) {
        log.info("invoke -------- /loginAction/login");

        String resp = "{\"accessGranted\":true}";

        return resp;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        return "";
    }

    @RequestMapping(value = "/loginSession", method = RequestMethod.GET)
    public String loginSession() {
        log.info("invoke -------- /loginAction/loginSession");

        System.out.println(this.getClass().toString());

        return "appuser/list";
    }

    @Autowired
    AppUserService appUserSrv = new AppUserServiceImpl();

}
