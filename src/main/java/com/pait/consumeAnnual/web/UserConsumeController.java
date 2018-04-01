package com.pait.consumeAnnual.web;


import com.pait.consumeAnnual.dao.UserConsumeDAO;
import com.pait.taskPool.thread.Consumer;
import com.pait.taskPool.thread.Porter;

import com.pait.taskPool.thread.SingleExecutorService;
import com.pait.taskPool.thread.SinglePorter;
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

import java.util.*;
import java.util.concurrent.*;


/**
 * Created by dynam on 2017/5/1.
 */
@Controller
@RequestMapping("/userConsume")
public class UserConsumeController {

    @RequestMapping(value = "/listUserConsumeByCondition", method = RequestMethod.GET)
    public String blog(Model model, Integer offset, Integer limit) {
        LOG.info("invoke -------- /appuser/blog");
        Map<String, Object> params = new HashMap<>();
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

        System.out.println("测试开始!");

        String payPlatformStr = request.getParameter("payPlatform") == null ? "PP1" : "PP4";

        int t = new Random().nextInt(10);
        if (t >= 5) {
            payPlatformStr = "PP1";
        } else {
            payPlatformStr = "PP4";
        }

        Consumer consumer = null;
        try {
            ExecutorService executorService = SingleExecutorService.getInstance();
            if (porter == null) {
                System.out.println("Create Porter");
                porter = new Porter(buffer4, buffer1, userConsumeDAO);
                executorService.execute(porter);
            }

            if (Porter.PAY_PLATFORM_1.equals(payPlatformStr)) {
                consumer = new Consumer(buffer1, Porter.PAY_PLATFORM_1);
            } else if (Porter.PAY_PLATFORM_4.equals(payPlatformStr)) {
                consumer = new Consumer(buffer4, Porter.PAY_PLATFORM_4);
            }

            Future future = executorService.submit(consumer);
            if (future.get() == null) {
                System.out.println("暂时没有任务，清稍后再点击获取");
            } else {
                System.out.println(future.get());
                System.out.println(future.isDone());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pager;
    }

    @RequestMapping(value = "/getTaskPool", method = RequestMethod.POST)
    public Model getTaskPool(Model model, HttpServletRequest request, HttpServletResponse response) {
        LOG.info("invoke -------- /userConsume/getTaskPool");

        Map<String, Object> params = new HashMap<String, Object>();

        Consumer consumer = null;
        try {
            String payPlatformStr = request.getParameter("payPlatform") == null ? "1" : "0";
//            int payPlatform = Integer.parseInt(payPlatformStr);
//            if (taskType == 0) {
//                taskType = new Random(System.currentTimeMillis()).nextInt();
//            }


            model.addAttribute("result", "3");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return model;
//        return "userConsume/list";
    }



    private BlockingQueue<UserConsume> buffer1 = new LinkedBlockingQueue<>();

    private BlockingQueue<UserConsume> buffer4 = new LinkedBlockingQueue<>();

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserConsumeService consumeService;

    @Autowired
    UserConsumeDAO userConsumeDAO;

    private static Porter porter;

}
