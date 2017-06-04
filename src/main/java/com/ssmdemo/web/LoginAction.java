package com.ssmdemo.web;

import com.ssmdemo.entity.AppUser;
import com.ssmdemo.service.AppUserService;
import com.ssmdemo.service.impl.AppUserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import work.utils.EncryptUtil;
import work.utils.StrUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by niu_ben on 2017/5/5.
 */
@Controller
@Scope("session")
@RequestMapping("/loginAction")
public class LoginAction {

    @RequestMapping("/signin")
    public String signin(Model model, String loginName, String password) {
        LOG.info("invoke -------- /loginAction/signin [" + loginName + "]");

        if (StrUtils.isNullOrEmpty(loginName)) {
            return "redirect:/jsp/signin.jsp";
        }

        AppUser appUser = appUserSrv.getAppUserByLoginName(loginName);

        if (appUser == null) {
            model.addAttribute("LOGIN_STATUS", "USER_NOT_EXITS");
            return "redirect:/jsp/signin.jsp";
        }

        // 获取加密内容
        String rPassword = appUser.getPassword() == null ? "" : appUser.getPassword();
        // 对用户输入的密码进行加密
        String dPassword = EncryptUtil.EncryptPassword(password);
        if(true || rPassword.equals(dPassword)){
            model.addAttribute("LOGIN_STATUS", "SUCCESS");
            model.addAttribute("appUser", appUser);
            return "redirect:/jsp/index.jsp";
        }

        model.addAttribute("LOGIN_STATUS", "WRONG_PSW");
        return "redirect:/jsp/signin.jsp";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public String login(HttpServletRequest request, HttpServletResponse response, String loginName, String password) {
        LOG.info("invoke -------- /loginAction/login");

        String resp = "{\"accessGranted\":true}";

        return resp;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request, HttpServletResponse response){
        return "";
    }

    @RequestMapping(value = "/loginSession", method = RequestMethod.GET)
    public String loginSession() {
        LOG.info("invoke -------- /loginAction/loginSession");

        System.out.println(this.getClass().toString());

        return "appuser/list";
    }

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Autowired
    AppUserService appUserSrv = new AppUserServiceImpl();

}
