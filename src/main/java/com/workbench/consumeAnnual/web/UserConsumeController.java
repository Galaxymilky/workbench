package com.workbench.consumeAnnual.web;


import com.workbench.consumeAnnual.dao.UserConsumeDAO;
import com.workbench.taskPool.thread.*;
import com.workbench.common.DataPager;
import com.workbench.consumeAnnual.dto.UserConsume;
import com.workbench.consumeAnnual.service.UserConsumeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.*;
import java.util.concurrent.*;

/**
 * Created by dynam on 2017/5/1.
 */
@RestController
@RequestMapping("/userConsume")
@Api(description = "用户消费记录 API")
@Slf4j
public class UserConsumeController {

    @ApiOperation(value = "查询", notes = "根据条件查询用户消费记录")
    @GetMapping(value = "/listUserConsumeByCondition")
    public String blog(Model model, Integer offset, Integer limit) {
        log.info("invoke -------- /appuser/blog");
        Map<String, Object> params = new HashMap<>();
        List<UserConsume> channelList = consumeService.listUserConsumeByCondition(offset, limit, params);
        model.addAttribute("userConsumeList", channelList);

        model.addAttribute("map", params);

        return "jeecms/loginon";
    }


    @GetMapping(value = "/listUserConsumeHtml")
    public String list(Model model, Integer offset, Integer limit) {
        log.info("invoke -------- /appuser/list");
        offset = offset == null ? 0 : offset; // 从第几行开始
        limit = limit == null ? 15 : limit; // 检索多少行

        Map<String, Object> params = new HashMap<String, Object>();
        List<UserConsume> list = consumeService.listUserConsumeByCondition(offset, limit, params);
        model.addAttribute("userConsumeList", list);
        return "userConsume/list";
    }

    @PostMapping(params = "method=listUserConsume")
    @ResponseBody
    public DataPager listUserConsume(HttpServletRequest request) {
        log.info("invoke -------- /userConsume.do?method=listUserConsume");

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

    @PostMapping(value = "/getTaskPool")
    public Map<String, Object> getTaskPool(Model model, HttpServletRequest request, HttpServletResponse response) {
        log.info("invoke -------- /userConsume/getTaskPool");

        Map<String, Object> result = new HashMap<String, Object>();

        System.out.println("开始单个获取任务!");

        String payPlatform = request.getParameter("payPlatform") == null ? "PP1" : "PP4";

        int t = new Random().nextInt(10);
        if (t >= 5) {
            payPlatform = "PP1";
        } else {
            payPlatform = "PP4";
        }

        Consumer consumer = null;
        try {
            ExecutorService executorService = SingleExecutorService.getInstance();
            if (porter == null) {
                System.out.println("Create Porter");
                porter = new Porter(buffer4, buffer3, buffer1, userConsumeDAO);
                executorService.execute(porter);
            }

            if (Porter.PAY_PLATFORM_1.equals(payPlatform)) {
                consumer = new Consumer(buffer1, Porter.PAY_PLATFORM_1);
            } else if (Porter.PAY_PLATFORM_3.equals(payPlatform)) {
                consumer = new Consumer(buffer3, Porter.PAY_PLATFORM_4);
            } else if (Porter.PAY_PLATFORM_4.equals(payPlatform)) {
                consumer = new Consumer(buffer4, Porter.PAY_PLATFORM_4);
            }

            Future future = executorService.submit(consumer);
            if (future.get() == null) {
                System.out.println("暂时没有任务，清稍后再点击获取");
            } else {
                System.out.println(future.get());
                System.out.println(future.isDone());
            }
            result.put("result", "3");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
//        return "userConsume/list";
    }

    @PostMapping(value = "/batchTaskPool")
    public Map<String, Object> batchTaskPool(Model model, HttpServletRequest request, HttpServletResponse response) {
        log.info("invoke -------- /userConsume/batchTaskPool");

        Map<String, Object> result = new HashMap<String, Object>();

        Integer taskNum = 1;

        String taskRate = "";
        String taskDone = "1,2,3";
        String registerStart = "1";
        String registerEnd = "1";
        String secondOrg = "";


        String payPlatform = "PP3";
        String createdBy = request.getParameter("createdBy");
        String consumeAddress = request.getParameter("consumeAddress");

        System.out.println("开始批量获取任务! 参数：payPlatform=" + payPlatform + ",createdBy=" + createdBy + ".consumeAddress=" + consumeAddress);

        int hashCode = getTargetHash(payPlatform, createdBy, consumeAddress);
        System.out.println("目标HashCode：" + hashCode);

        ConsumerPrimer consumer = null;
        try {
            ExecutorService executorService = SingleExecutorService.getInstance();
            if (porter == null) {
                System.out.println("Create Porter");
                porter = new Porter(buffer4, buffer3, buffer1, userConsumeDAO);
                executorService.execute(porter);
            }

            if (Porter.PAY_PLATFORM_3.equals(payPlatform)) {
                consumer = new ConsumerPrimer(buffer3, hashCode);
            } else {
                return null;
            }

            Future future = executorService.submit(consumer);
            if (future.get() == null) {
                System.out.println("暂时没有任务，清稍后再点击获取");
            } else {
                System.out.println(future.get());
                System.out.println(future.isDone());
            }
            result.put("result", "3");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    private static int getTargetHash(String v1, String v2, String v3) {
        String s = v1 + v2 + v3;
        return s.hashCode();
    }


    private BlockingQueue<UserConsume> buffer1 = new LinkedBlockingQueue<>();

    private BlockingQueue<UserConsume> buffer3 = new LinkedBlockingQueue<>();

    private BlockingQueue<UserConsume> buffer4 = new LinkedBlockingQueue<>();

    private BlockingQueue bufferPriority = new PriorityBlockingQueue();

    @Autowired
    private UserConsumeService consumeService;

    @Autowired
    private UserConsumeDAO userConsumeDAO;

    private static Porter porter;

}
