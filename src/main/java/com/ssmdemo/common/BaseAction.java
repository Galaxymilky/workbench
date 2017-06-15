package com.ssmdemo.common;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * 系统公用基本Action. <br/>
 */
public class BaseAction {

    private static final int CONTENTTYPE_NORMAL = 0;

    private static final int CONTENTTYPE_JSON = 0;

    public static final Map<String, Boolean> SUCCESS = new HashMap<String, Boolean>() {

        private static final long serialVersionUID = 3626936784968834092L;

        {
            put("success", true);
        }
    };

    public static final Map<String, Boolean> FAILURE = new HashMap<String, Boolean>() {

        private static final long serialVersionUID = 3626936784968834092L;

        {
            put("success", false);
        }
    };

    public Map<String, Object> getOK() {
        return new HashMap<String, Object>() {
            private static final long serialVersionUID = 3626936784968834092L;

            {
                put("success", true);
            }
        };
    }

    public Map<String, Object> getNOT() {
        return new HashMap<String, Object>() {
            private static final long serialVersionUID = 3626936784968834092L;

            {
                put("success", false);
            }
        };
    }

    /**
     * 写回信息
     *
     * @param response
     * @param strMsg
     */
    public void writeMsg(HttpServletResponse response, String strMsg, int contentType) {

        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        if (contentType == CONTENTTYPE_JSON) {
            response.setContentType("application/json; charset=utf-8");
        } else {
            response.setContentType("text/html; charset=utf-8");
        }

        PrintWriter out = null;
        try {
            out = response.getWriter();
            if (out != null) {
                out.write(strMsg);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (out != null) {
                out.flush();
                out.close();
            }
        }
    }
}
