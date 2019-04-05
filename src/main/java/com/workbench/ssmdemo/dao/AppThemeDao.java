package com.workbench.ssmdemo.dao;

import com.workbench.ssmdemo.entity.AppTheme;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by dynamicniu on 2017/5/23.
 */
@Mapper
public interface AppThemeDao {
    List<AppTheme> queryAll();

}
