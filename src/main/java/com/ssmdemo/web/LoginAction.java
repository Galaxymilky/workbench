package com.ssmdemo.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by niu_ben on 2017/5/5.
 */
@Controller
@Scope("session")
@RequestMapping("/loginAction")
public class LoginAction {

    @RequestMapping("/login")
    public String login() {
        LOG.info("invoke -------- /loginAction/login");
        return "appuser/list";
    }

    @RequestMapping(value = "/loginSession", method = RequestMethod.GET)
    public String loginSession() {
        LOG.info("invoke -------- /loginAction/loginSession");
        System.out.println(this.getClass().toString());
        return "appuser/list";
    }

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

}
