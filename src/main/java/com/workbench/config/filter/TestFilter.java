package com.workbench.config.filter;

import com.workbench.ssmdemo.entity.AppUser;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@Slf4j
public class TestFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("TestFilter");

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        res.addHeader("Access-Control-Allow-Origin", "*");
        String uri = req.getRequestURI();

        if (uri.indexOf("login") != -1 || uri.indexOf("Login") != -1
                || uri.endsWith("sign/signIn")
                || uri.contains(".css") || uri.contains(".js")
                || uri.contains(".jpg") || uri.contains(".png")
                || uri.contains(".jpng")) {
            chain.doFilter(req, res);
        } else {
            HttpSession session = req.getSession();
            AppUser user = (AppUser) session.getAttribute("loginInfo");

            if (user == null) {
                session.invalidate();
                res.setContentType("text/html;charset=utf-8");
                PrintWriter out = res.getWriter();
                out.println("<script language='javascript' type='text/javascript'>");
                out.println("window.top.location.href='" + req.getContextPath() + "/domain/dashboard-data-login.html'");
                out.println("</script>");
            } else {
                chain.doFilter(req, res);
            }
        }

        if (uri.indexOf("redirect") != -1) {
            if (null != request.getParameter("userimg")) {
                chain.doFilter(req, res);
            }
        }
    }

    @Override
    public void destroy() {

    }
}
