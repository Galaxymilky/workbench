<%--
  Created by IntelliJ IDEA.
  User: dynam
  Date: 2017/5/1
  Time: 11:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" pageEncoding="UTF-8" %>
<%
    response.setHeader("Cache-Control", "no-cache");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", 0);

    String contextPath = request.getContextPath();
%>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=2.0">

<link rel="stylesheet" type="text/css" href="<%=contextPath %>/font-awesome/css/font-awesome.min.css"/>
<link rel="stylesheet" type="text/css" href="<%=contextPath %>/js/ui/bootstrap/css/bootstrap.css"/>
<link rel="stylesheet" type="text/css" href="<%=contextPath %>/js/plugins/DataTables/css/dataTables.bootstrap.css"/>

<script type="text/javascript" src="<%=contextPath %>/js/respond.js"></script>
<script type="text/javascript" src="<%=contextPath %>/js/html5shiv.min.js"></script>
<script type="text/javascript" src="<%=contextPath %>/js/jquery-2.1.4.min.js"></script>
<script type="text/javascript" src="<%=contextPath %>/js/plugins/DataTables/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="<%=contextPath %>/js/plugins/DataTables/js/dataTables.bootstrap.min.js"></script>

<script type="text/javascript">
    var contextPath = "<%= contextPath %>";
    var queryString = "<%= request.getQueryString()==null ? "" : request.getQueryString() %>";
</script>