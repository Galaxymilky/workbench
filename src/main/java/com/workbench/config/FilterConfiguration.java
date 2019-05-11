package com.workbench.config;

import com.workbench.config.filter.CasFilter;
import com.workbench.config.filter.TestFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.core.annotation.Order;

@Configuration
public class FilterConfiguration {

    @Bean
    @Order(0)
    public FilterRegistrationBean companyUrlFilterRegister() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        //注入过滤器
        registration.setFilter(new CasFilter());
        //拦截规则
        registration.addUrlPatterns("/*");
        //过滤器名称
        registration.setName("CasFilter");
        //过滤器顺序
        //registration.setOrder(FilterRegistrationBean.LOWEST_PRECEDENCE);
        return registration;
    }

    @Bean
    @Order(1)
    public FilterRegistrationBean outLinkSecurityFilterRegister() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        //注入过滤器
        registration.setFilter(new TestFilter());
        //拦截规则
        registration.addUrlPatterns("/*");
        //过滤器名称
        registration.setName("TestFilter");
        //过滤器顺序
        //registration.setOrder(FilterRegistrationBean.LOWEST_PRECEDENCE - 1);
        return registration;
    }

}
