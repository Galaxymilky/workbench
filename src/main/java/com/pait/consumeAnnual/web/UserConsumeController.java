package com.pait.consumeAnnual.web;


import com.ssmdemo.common.DataPager;
import com.pait.consumeAnnual.dto.UserConsume;
import com.pait.consumeAnnual.service.UserConsumeService;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;


/**
 * Created by dynam on 2017/5/1.
 */
@Controller
@RequestMapping("/userConsume")
public class UserConsumeController {

    @RequestMapping(value = "/listUserConsumeByCondition", method = RequestMethod.GET)
    public String blog(Model model, Integer offset, Integer limit) {
        LOG.info("invoke -------- /appuser/blog");
        Map<String, Object> params= new HashMap<>();
        List<UserConsume> channelList = consumeService.listUserConsumeByCondition(offset, limit, params);
        model.addAttribute("userConsumeList", channelList);

        model.addAttribute("map", params);

        return "jeecms/loginon";
    }


    @RequestMapping(value = "/listUserConsumeHtml", method = RequestMethod.GET)
    public String list(Model model, Integer offset, Integer limit) {
        LOG.info("invoke -------- /appuser/list");
        offset = offset == null ? 0 : offset; // 从第几行开始
        limit = limit == null ? 15 : limit; // 检索多少行

        Map<String, Object> params = new HashMap<String, Object>();
        List<UserConsume> list = consumeService.listUserConsumeByCondition(offset, limit, params);
        model.addAttribute("userConsumeList", list);
        return "userConsume/list";
    }

    @RequestMapping(params = "method=listUserConsume")
    @ResponseBody
    public DataPager listUserConsume(HttpServletRequest request, HttpServletResponse response) {
        LOG.info("invoke -------- /userConsume.do?method=listUserConsume");

        DataPager pager = new DataPager();

        List<UserConsume> list = new ArrayList<UserConsume>();

        try {
            pager.init(request);

            list = consumeService.listUserConsumePager(pager);
            pager.setData(list);

            int count = consumeService.countUserConsume();

            pager.setRecordsTotal(count);
            pager.setRecordsFiltered(pager.getRecordsTotal());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return pager;
    }

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserConsumeService consumeService;

}
