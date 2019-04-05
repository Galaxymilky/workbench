package com.workbench.ssmdemo.service;

import com.workbench.ssmdemo.entity.AppChannel;
import com.workbench.ssmdemo.entity.AppTheme;

import java.util.*;

/**
 * Created by dynamicniu on 2017/5/23.
 */
public interface AppBlogService {
    List<AppChannel> listAppChannel();

    List<AppTheme> listAppTheme();

    Map<String, List<AppTheme>> listAppThemeByChannel();
}
