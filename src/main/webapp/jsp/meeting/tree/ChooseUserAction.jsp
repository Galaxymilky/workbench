<%@page import="java.sql.PreparedStatement" %>
<%@page import="java.sql.ResultSet" %>
<%@page import="java.sql.Connection" %>
<%@page import="com.haidian.common.DBConnection" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ page import="java.util.*" %>
<%
    //页面不缓存
    response.setHeader("Cache-Control", "no-cache");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", 0);

    String basepath = request.getContextPath();

    String id = request.getParameter("id");
    int idInt = 0;
    if (id != null && !id.equals("")) {
        idInt = Integer.parseInt(id);
    }
    String returnstr = "";


    PreparedStatement ps = null;
    Connection conn = null;
    ResultSet rs = null;
    try {
        conn = DBConnection.getConnection();
        String sql = "select a.user_id,  b.realname as username FROM jc_user a, jc_user_ext b where a.user_id = b.user_id and depart_id = ?";
        ps = conn.prepareStatement(sql);
        ps.setInt(1, idInt);
        rs = ps.executeQuery();
        while (rs != null && rs.next()) {
            returnstr += ";" + rs.getString(1) + "," + rs.getString(2);
        }
        if (returnstr != null && !returnstr.trim().equals("")) {
            returnstr = returnstr.substring(1);
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        if (rs != null)
            rs.close();
        if (ps != null)
            ps.close();
        if (conn != null)
            conn.close();
    }

    StringBuffer buffer = new StringBuffer(returnstr);
    out.clear();
    out.println(buffer.toString());

%>

