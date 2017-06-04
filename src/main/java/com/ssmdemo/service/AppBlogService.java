package com.ssmdemo.service;

import com.ssmdemo.entity.AppChannel;
import com.ssmdemo.entity.AppTheme;

import java.util.*;

/**
 * Created by dynamicniu on 2017/5/23.
 */
public interface AppBlogService {
    List<AppChannel> getAppChannelList();

    List<AppTheme> getAppThemeList();

    Map<String, List<AppTheme>> getAppThemeByChannel();
}
