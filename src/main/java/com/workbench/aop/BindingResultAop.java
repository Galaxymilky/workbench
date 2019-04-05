package com.workbench.aop;

import com.workbench.ssmdemo.dto.BaseResult;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

/**
 * Created by dynam on 2017/5/1.
 * <p>
 * 采用AOP的方式处理参数问题。
 */
@Component
@Aspect
public class BindingResultAop {
    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(* com.workbench.ssmdemo.web.*.*(..))")
    public void aopMehod() {
    }

    @Around("aopMehod()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        LOG.info("before method invoking!");
        BindingResult bindingResult = null;
        for (Object arg : joinPoint.getArgs()) {
            if (arg instanceof BindingResult) {
                bindingResult = (BindingResult) arg;
            }
        }
        if (bindingResult != null) {
            if (bindingResult.hasErrors()) {
                String errorInfo = "";
                return new BaseResult<Object>(false, errorInfo);
            }
        }

        return joinPoint.proceed();
    }
}
