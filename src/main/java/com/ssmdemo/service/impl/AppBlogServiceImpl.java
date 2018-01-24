package com.ssmdemo.service.impl;

import com.ssmdemo.dao.AppChannelDao;
import com.ssmdemo.dao.AppThemeDao;
import com.ssmdemo.entity.AppChannel;
import com.ssmdemo.entity.AppTheme;
import com.ssmdemo.service.AppBlogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dynamicniu on 2017/5/23.
 */
@Service
public class AppBlogServiceImpl implements AppBlogService {

    @Override
    public List<AppChannel> listAppChannel() {
        return appChannelDao.queryAll();
    }

    @Override
    public List<AppTheme> listAppTheme() {
        return appThemeDao.queryAll();
    }

    @Override
    public Map<String, List<AppTheme>> listAppThemeByChannel() {
        Map<String, List<AppTheme>> map = new HashMap<String, List<AppTheme>>();

        // appThemeList must be ordered
        List<AppTheme> appThemeList = appThemeDao.queryAll();

        if (appThemeList == null) {
            return null;
        }

        long channelId = appThemeList.get(0).getChannelId();
        List<AppTheme> list_0 = new ArrayList<AppTheme>();
        map.put(String.valueOf(channelId), list_0);

        for (int i = 0; i < appThemeList.size(); i++) {
            AppTheme appTheme = appThemeList.get(i);
            if (appTheme == null) {
                continue;
            }

            long cur = appTheme.getChannelId();

            if (channelId == cur) {
                map.get(String.valueOf(cur)).add(appTheme);
            } else {
                List<AppTheme> list = new ArrayList<AppTheme>();
                list.add(appTheme);
                map.put(String.valueOf(cur), list);
                channelId = cur;
            }
        }

        return map;
    }

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AppThemeDao appThemeDao;

    @Autowired
    private AppChannelDao appChannelDao;
}
