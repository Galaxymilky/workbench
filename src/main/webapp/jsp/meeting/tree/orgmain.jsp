<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
    response.setHeader("Cache-Control", "no-cache");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", 0);
    String orgParentId = request.getParameter("orgParentId");
%>
<html>
<head>
    <title>
        选择参会单位
    </title>
</head>

<frameset cols="255,*" framespacing="0" frameborder="no" border="0" bordercolor="#FFFFFF">
    <frame src="orgtree.jsp?orgParentId=<%=orgParentId %>" name="left" scrolling="no"/>
    <frame src="user.jsp?orgParentId=<%=orgParentId %>" name="basefrm" scrolling="no"/>
</frameset>

</html>
