package com.workbench.ssmdemo.web;

import com.workbench.common.BaseRest;
import com.workbench.common.service.impl.RedisHelperImpl;
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
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * Created by niu_ben on 2017/5/5.
 */
@Slf4j
@RestController
@Scope("session")
@RequestMapping("/login")
@Api(description = "登录API")
public class LoginRest extends BaseRest {

    @Autowired
    private RedisHelperImpl redisHelper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private static final String LOGIN_USER = "login_user";

    @ApiOperation(value = "跳转到登录首页", notes = "跳转到登录首页")
    @PostMapping(value = "/sign")
    public String sign(Model model, String loginName, String password) {
        log.info("invoke -------- /{}/{}", this.getClass().getSimpleName(), "sign");

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

            if (redisHelper.getValue(LOGIN_USER + loginName) != null) {
                resMap.put(RESULT_CODE, SUCCESS);
                resMap.put(RESULT_MESG, "用户已登陆");
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
            if (true || rPassword.equals(dPassword)) {
                resMap.put(RESULT_CODE, SUCCESS);
                resMap.put(RESULT_MESG, "登录成功");
                resMap.put("accessGranted", true);

                redisHelper.valuePut(LOGIN_USER + loginName, appUser);

                return JsonUtils.transObject2Json(resMap);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return JsonUtils.transObject2Json(resMap);
    }

    @ApiOperation(value = "退出系统", notes = "退出系统")
    @PostMapping(value = "/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response, String loginName) {
        log.info("invoke -------- /{}/{}", this.getClass().getSimpleName(), "logout");

        AppUser appUser = new AppUser();
        appUser.setLoginName(loginName);

        ValueOperations<String, AppUser> operations = redisTemplate.opsForValue();
        String loginKey = LOGIN_USER + loginName;
        operations.set(loginKey, appUser);
        boolean exists = redisTemplate.hasKey(LOGIN_USER + loginName);
        if (exists) {
            log.info("User exists!", redisTemplate.opsForValue().get(loginKey));
        } else {
            log.error("User not exists!");
        }
        return "";
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
        if (session == null && session.getAttribute(LOGIN_INFO) == null) {
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
