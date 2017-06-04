package com.ssmdemo.web;


import com.ssmdemo.entity.AppChannel;
import com.ssmdemo.entity.AppTheme;
import com.ssmdemo.service.AppBlogService;
import com.ssmdemo.common.DataPager;
import com.ssmdemo.entity.AppUser;
import com.ssmdemo.service.AppUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * Created by dynam on 2017/5/1.
 */
@Controller
@RequestMapping("/appuser")
public class AppUserController {

    @RequestMapping(value = "/blog", method = RequestMethod.GET)
    public String blog(Model model, Integer offset, Integer limit) {
        LOG.info("invoke -------- /appuser/blog");
        List<AppChannel> channelList = appBlogService.getAppChannelList();
        List<AppTheme> themesList = appBlogService.getAppThemeList();
        model.addAttribute("channelList", channelList);
        model.addAttribute("themesList", themesList);

        Map<String, List<AppTheme>> map = appBlogService.getAppThemeByChannel();
        model.addAttribute("map", map);

        return "jeecms/loginon";
    }

    @RequestMapping("/multiInsert")
    public String multiInsert() {
        LOG.info("invoke -------- /appuser/multiInsert");

        for (int i = 1; i < 1000; i++) {
            System.out.println();
            AppUser appUser = new AppUser();
            appUser.setLoginName("test" + i);
            appUser.setPriority(i);
            appUser.setUserName("测试" + i);
            int res = appUserService.multiInsert(appUser);
            System.out.println("Curr is " + i + " & res = " + res);
        }

        return "";

    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model, Integer offset, Integer limit) {
        LOG.info("invoke -------- /appuser/list");
        offset = offset == null ? 0 : offset; // 从第几行开始
        limit = limit == null ? 15 : limit; // 检索多少行
        List<AppUser> list = appUserService.getAppUserList(offset, limit);
        model.addAttribute("userList", list);
        return "appuser/list";
    }

    @RequestMapping(value = "/newlist", method = RequestMethod.GET)
    public String newlist(Model model, Integer offset, Integer limit) {
        LOG.info("invoke -------- /appuser/newlist");
        offset = offset == null ? 0 : offset;
        limit = limit == null ? 15 : limit;
        List<AppUser> list = appUserService.getAppUserList(offset, limit);
        model.addAttribute("userList", list);
        return "appuser/newlist";
    }

    @RequestMapping(params = "method=getListAjax")
    @ResponseBody
    public DataPager getListAjax(HttpServletRequest request, HttpServletResponse response) {
        LOG.info("invoke -------- /appuser?method=getListAjax");

        DataPager pager = new DataPager();
        try {
            pager.init(request);

            List<AppUser> list = appUserService.getAppUserListAjax(pager);
            pager.setData(list);

            int count = appUserService.getTotalCount();
            pager.setRecordsTotal(count);
            pager.setRecordsFiltered(pager.getRecordsTotal());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return pager;
    }

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private AppBlogService appBlogService;

}
