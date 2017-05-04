package com.ssmdemo.web;


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
import java.util.List;

/**
 * Created by dynam on 2017/5/1.
 */
@Controller
@RequestMapping("/appuser")
public class AppUserController {
    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AppUserService appUserService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model, Integer offset, Integer limit) {
        LOG.info("invoke -------- /appuser/list");
        offset = offset == null ? 0 : offset;
        limit = limit == null ? 0 : limit;
        List<AppUser> list = appUserService.getAppUserList(offset, limit);
        model.addAttribute("userList", list);
        return "appuser/list";
    }

    @RequestMapping(value = "/newlist", method = RequestMethod.GET)
    public String newlist(Model model, Integer offset, Integer limit) {
        LOG.info("invoke -------- /appuser/newlist");
        offset = offset == null ? 0 : offset;
        limit = limit == null ? 0 : limit;
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

            List<AppUser> list = appUserService.getAppUserList(0, pager.getPageSize());

            pager.setData(list);
            pager.setRecordsTotal(list.size());
            pager.setRecordsFiltered(pager.getRecordsTotal());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return pager;
    }

}
