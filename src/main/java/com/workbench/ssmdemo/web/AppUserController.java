package com.workbench.ssmdemo.web;

import com.workbench.ssmdemo.entity.AppChannel;
import com.workbench.ssmdemo.entity.AppTheme;
import com.workbench.ssmdemo.service.AppBlogService;
import com.workbench.common.DataPager;
import com.workbench.ssmdemo.entity.AppUser;
import com.workbench.ssmdemo.service.AppUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * Created by dynam on 2017/5/1.
 */
@Slf4j
@RestController
@RequestMapping("/appuser")
@Api(description = "用户信息 API")
public class AppUserController {

    @GetMapping(value = "/blog")
    @ApiOperation(value = "示例", notes = "示例")
    public String blog(Model model, Integer offset, Integer limit) {
        log.info("invoke -------- /appuser/blog");
        List<AppChannel> channelList = appBlogService.listAppChannel();
        List<AppTheme> themesList = appBlogService.listAppTheme();
        model.addAttribute("channelList", channelList);
        model.addAttribute("themesList", themesList);

        Map<String, List<AppTheme>> map = appBlogService.listAppThemeByChannel();
        model.addAttribute("map", map);

        return "jeecms/loginon";
    }

    @GetMapping("/multiInsert")
    public String multiInsert() {
        log.info("invoke -------- /appuser/multiInsert");

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

    @GetMapping(value = "/list")
    public String list(Model model, Integer offset, Integer limit) {
        log.info("invoke -------- /appuser/list");
        offset = offset == null ? 0 : offset; // 从第几行开始
        limit = limit == null ? 15 : limit; // 检索多少行
        List<AppUser> list = appUserService.listAppUser(offset, limit);
        model.addAttribute("userList", list);
        return "appuser/list";
    }

    @GetMapping(value = "/newlist")
    public String newlist(Model model, Integer offset, Integer limit) {
        log.info("invoke -------- /appuser/newlist");
        offset = offset == null ? 0 : offset;
        limit = limit == null ? 15 : limit;
        List<AppUser> list = appUserService.listAppUser(offset, limit);
        model.addAttribute("userList", list);
        return "appuser/newlist";
    }

    @PostMapping(params = "method=getListAjax")
    @ResponseBody
    public DataPager getListAjax(HttpServletRequest request, HttpServletResponse response) {
        log.info("invoke -------- /appuser?method=getListAjax");

        DataPager pager = new DataPager();
        try {
            pager.init(request);

            List<AppUser> list = appUserService.listAppUserAjax(pager);
            pager.setData(list);

            int count = appUserService.getTotalCount();
            pager.setRecordsTotal(count);
            pager.setRecordsFiltered(pager.getRecordsTotal());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return pager;
    }

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private AppBlogService appBlogService;

}
