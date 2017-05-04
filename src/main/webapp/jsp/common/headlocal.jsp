<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%

    response.setHeader("Cache-Control", "no-cache");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", 0);

    String contextPath = request.getContextPath();

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=9"/>
    <meta name="viewport" content="initial-scale=1.0, maximum-scale=2.0"/>

    <link rel="stylesheet" type="text/css" href="<%=contextPath %>/plugins/font-awesome/css/font-awesome.min.css"/>
    <link rel="stylesheet" type="text/css" href="<%=contextPath %>/js/ui/bootstrap/css/bootstrap.css"/>
    <link rel="stylesheet" type="text/css" href="<%=contextPath %>/js/plugins/DataTables/css/dataTables.bootstrap.css"/>
    <link rel="stylesheet" type="text/css" href="<%=contextPath %>/js/ui/bootstrap/datetimepicker/css/bootstrap-datetimepicker.min.css"/>
    <link rel="stylesheet" type="text/css" href="<%=contextPath %>/js/ui/bootstrap/datepicker/css/bootstrap-datepicker.min.css"/>

    <script type="text/javascript">
        var contextPath = "<%= contextPath %>";
        var queryString = "<%= request.getQueryString()==null ? "" : request.getQueryString() %>";
    </script>

    <script type="text/javascript" src="<%=contextPath %>/js/respond.js"></script>
    <script type="text/javascript" src="<%=contextPath %>/js/html5shiv.min.js"></script>
    <script type="text/javascript" src="<%=contextPath %>/js/jquery-1.11.1.min.js"></script>
    <!--
    <script type="text/javascript" src="/js/jquery-2.1.4.min.js"></script>
    -->
    <script type="text/javascript" src="<%=contextPath %>/js/ui/bootstrap/js/bootstrap.min.js"></script>
    <!--
    <script type="text/javascript" src="<%=contextPath %>/ckeditor/ckeditor.js"></script>
    -->
    <script type="text/javascript" src="<%=contextPath %>/js/plugins/DataTables/js/jquery.dataTables.min.js"></script>
    <script type="text/javascript" src="<%=contextPath %>/js/plugins/DataTables/js/dataTables.bootstrap.min.js"></script>

    <script type="text/javascript" src="<%=contextPath %>/js/ui/jquery.validate/jquery.validate.min.js"></script>
    <script type="text/javascript" src="<%=contextPath %>/js/ui/bootstrap/datepicker/js/bootstrap-datepicker.min.js"></script>
    <script type="text/javascript" src="<%=contextPath %>/js/ui/bootstrap/datepicker/locales/bootstrap-datepicker.zh-CN.min.js"></script>
    <script type="text/javascript" src="<%=contextPath %>/js/ui/bootstrap/datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
    <script type="text/javascript" src="<%=contextPath %>/js/ui/bootstrap/datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
    