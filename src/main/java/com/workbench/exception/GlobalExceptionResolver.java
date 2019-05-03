package com.workbench.exception;

import com.workbench.util.JsonUtils;
import com.workbench.ssmdemo.dto.BaseResult;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * Created by dynam on 2017/4/30.
 */
@Slf4j
public class GlobalExceptionResolver {

    @ResponseBody
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        log.error("Access to " + request.getRequestURI() + " wrong , Error Msg : " + ex.getMessage());

        // Jump to error msg page
//        ModelAndView error = new ModelAndView("error");
//        error.addObject("errMsg", ex.getMessage());
//        error.addObject("errType", ex.getClass().getSimpleName().replace("\"", "'"));
//        return error;

        // return error msg by Json
        try {
            PrintWriter writer = response.getWriter();
            BaseResult<String> result = new BaseResult(false, ex.getMessage());
            writer.write(JsonUtils.transObject2Json(result));
            writer.flush();
        } catch (Exception e) {
            log.error("Exception:" + e);
        }
        return null;
    }
}
