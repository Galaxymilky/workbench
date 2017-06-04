<%--
  Created by IntelliJ IDEA.
  User: dynam
  Date: 2017/4/30
  Time: 17:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%

//    if (null == null || "".equals(null)) {
//        out.println("<script>alert('没有登录！');window.close();</script>");
//        return;
//    }

%>
<%@ include file="/jsp/common/headlocal.jsp" %>
<html>
<head>

    <link rel="stylesheet" href="../css/index.css" type="text/css"/>

    <%--<style type="text/css"></style>--%>

    <title>Application</title>
</head>
<body>
<div id="header" class="navbar navbar-duomi navbar-static-top" role="navigation">

</div>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-2" style="padding:7px">
            <ul id="main-nav" class="nav nav-tabs nav-stacked">
                <li>
                    <a id="main" href="#uimapp" class="nav-header collapsed" data-toggle="collapse">
                        <i class="fa fa-home">&nbsp;</i>
                        UIM App <span class="pull-right glyphicon glyphicon-chevron-down"></span>
                    </a>

                    <ul id="uimapp" class="nav nav-list collapse secondmenu">

                        <li class="li-active">
                            <a href="#" url="<%=contextPath %>/jsp/appuser/newlist.jsp">
                                <i class="fa fa-pencil-square-o"></i>
                                用户管理</a>
                        </li>

                        <li class="">
                            <a href="#" url="<%=contextPath %>/appuser/list">
                                <i class="fa fa-bars"></i>
                                用户管理</a>
                        </li>

                    </ul>
                </li>


                <li>
                    <a id="main2" href="#mtgRoom" class="nav-header collapsed" data-toggle="collapse">
                        <i class="fa fa-home">&nbsp;</i>
                        其他应用<span class="pull-right glyphicon glyphicon-chevron-down"></span>
                    </a>

                    <ul id="mtgRoom" class="nav nav-list collapse secondmenu">

                        <li class="">
                            <a href="#" url="jsp/websocket/demo.jsp">
                                <i class="fa fa-gear"></i>
                                聊天室</a>
                        </li>

                    </ul>
                </li>

                <li>
                    <a id="main3" href="#setting" class="nav-header collapsed" data-toggle="collapse">
                        <i class="fa fa-home">&nbsp;</i>
                        系统设置 <span class="pull-right glyphicon glyphicon-chevron-down"></span>
                    </a>

                    <ul id="setting" class="nav nav-list collapse secondmenu">

                        <li class="">
                            <a href="#" url="jsp/setting/meeting_list_syslog.jsp">
                                <i class="fa fa-gear"></i>
                                系统设置</a>
                        </li>

                    </ul>
                </li>


            </ul>
        </div>
        <div id="iframe-main" class="col-md-10">
            <iframe id="content" scrolling="no" frameborder="0" allowTransparency="true" src="" marginheight="0"
                    marginwidth="0"></iframe>
        </div>
    </div>
</div>
</body>

<script type="text/javascript">

    function resetExpand(index) {
        var expandVal_1 = $("#main").attr("aria-expanded");
        var expandVal_2 = $("#main2").attr("aria-expanded");
        var expandVal_3 = $("#main3").attr("aria-expanded");

        if (index == 1) {
            if (expandVal_2 == "true") {
                $("#main2").trigger("click");
            }
            if (expandVal_3 == "true") {
                $("#main3").trigger("click");
            }
        } else if (index == 2) {
            if (expandVal_1 == "true") {
                $("#main").trigger("click");
            }
            if (expandVal_3 == "true") {
                $("#main3").trigger("click");
            }
        } else {
            if (expandVal_1 == "true") {
                $("#main").trigger("click");
            }
            if (expandVal_2 == "true") {
                $("#main2").trigger("click");
            }
        }
    }


    $(document).ready(
        function () {
            //$("#main2").trigger("click");
            $("#mtgRoom li > a").click(
                function () {
                    resetExpand(2);
                    $("#mtgRoom li").attr("class", "");
                    $("#uimapp li").attr("class", "");
                    $("#setting li").attr("class", "");

                    $(this).parent().attr("class", "li-active");
                    $("#content").attr("src", $(this).attr("url"));
                    $("#content").height($(document).height() - $("#header").height() - 10);
                });
            $("#mtgRoom li a:eq(0)").trigger("click");
        });

    $(document).ready(
        function () {
            //$("#main3").trigger("click");
            $("#setting li > a").click(
                function () {
                    resetExpand(3);

                    $("#setting li").attr("class", "");
                    $("#mtgRoom li").attr("class", "");
                    $("#uimapp li").attr("class", "");

                    $(this).parent().attr("class", "li-active");
                    $("#content").attr("src", $(this).attr("url"));
                    $("#content").height($(document).height() - $("#header").height() - 10);
                });
            $("#setting li a:eq(0)").trigger("click");
        });

    $(document).ready(
        function () {
            //$("#main").trigger("click");
            $("#uimapp li > a").click(
                function () {
                    resetExpand(1);
                    $("#uimapp li").attr("class", "");
                    $("#mtgRoom li").attr("class", "");
                    $("#setting li").attr("class", "");

                    $(this).parent().attr("class", "li-active");
                    $("#content").attr("src", $(this).attr("url"));
                    $("#content").height($(document).height() - $("#header").height() - 10);
                });
            $("#uimapp li a:eq(1)").trigger("click");
        });


    function changeMenu(destStr) {
        alert('1');
        alert(destStr);
        switch (destStr) {
            case "new":
                index = 0;
                break;
            case "newlist":
                index = 1;
                break;
            case "todo":
                index = 2;
                break;
            case "accepted":
                index = 3;
                break;
            case "draft":
                index = 4;
                break;
            case "room":
                index = 5;
                break;
            default:
                1;
        }

        if (index == 5) {
            $("#mtgRoom li a:eq(" + 1 + ")").trigger("click");
        } else {
            $("#uimapp li a:eq(" + index + ")").trigger("click");
        }

    }
</script>

</html>