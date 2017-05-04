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

    <%--<link rel="stylesheet" href="css/index.css" type="text/css"/>--%>

    <style type="text/css">
        #main-nav {
            margin-left: 1px;
        }

        #main-nav.nav-tabs.nav-stacked > li > a {
            padding: 10px 8px;
            font-size: 14px;
            font-weight: 600;
            color: #4A515B;
            background: #E9E9E9;
            background: -moz-linear-gradient(top, #FAFAFA 0%, #E9E9E9 100%);
            background: -webkit-gradient(linear, left top, left bottom, color-stop(0%, #FAFAFA),
            color-stop(100%, #E9E9E9));
            background: -webkit-linear-gradient(top, #FAFAFA 0%, #E9E9E9 100%);
            background: -o-linear-gradient(top, #FAFAFA 0%, #E9E9E9 100%);
            background: -ms-linear-gradient(top, #FAFAFA 0%, #E9E9E9 100%);
            background: linear-gradient(top, #FAFAFA 0%, #E9E9E9 100%);
            filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#FAFAFA',
            endColorstr='#E9E9E9');
            -ms-filter: "progid:DXImageTransform.Microsoft.gradient(startColorstr='#FAFAFA', endColorstr='#E9E9E9')";
            border: 1px solid #D5D5D5;
            border-radius: 4px;
        }

        #main-nav.nav-tabs.nav-stacked > li > a > span {
            color: #4A515B;
        }

        #main-nav.nav-tabs.nav-stacked > li.active > a, #main-nav.nav-tabs.nav-stacked > li > a:hover {
            color: #FFF;
            background: #3C4049;
            background: -moz-linear-gradient(top, #87CEFF 20%, #CAE1FF 80%);
            background: -webkit-gradient(linear, left top, left bottom, color-stop(0%, #4A515B),
            color-stop(100%, #3C4049));
            background: -webkit-linear-gradient(top, #4A515B 0%, #3C4049 100%);
            background: -o-linear-gradient(top, #4A515B 0%, #3C4049 100%);
            background: -ms-linear-gradient(top, #4A515B 0%, #3C4049 100%);
            background: linear-gradient(top, #4A515B 0%, #3C4049 100%);
            filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#87CEFF',
            endColorstr='#CAE1FF');
            -ms-filter: "progid:DXImageTransform.Microsoft.gradient(startColorstr='#87CEFF', endColorstr='#CAE1FF')";
            border-color: #2B2E33;
        }

        #main-nav.nav-tabs.nav-stacked > li.active > a, #main-nav.nav-tabs.nav-stacked > li > a:hover > span {
            color: #FFF;
        }

        #main-nav.nav-tabs.nav-stacked > li {
            margin-bottom: 4px;
        }

        /*å®šä¹‰lié€‰ä¸­åŽçš„æ ·å¼*/
        .li-active {
            background-color: #c6e0fb;
        }

        /*å®šä¹‰äºŒçº§èœå•æ ·å¼*/
        .secondmenu a {
            font-size: 16px;
            color: #4A515B;
        }

        .navbar-static-top {
            background-color: rgb(219, 234, 249);
            margin-bottom: 5px;
        }

        .navbar-brand {
            background: url('') no-repeat 10px 8px;
            display: inline-block;
            vertical-align: middle;
            padding-left: 50px;
            color: #fff;
        }

        #main-nav {
            background: #F3F3F3;
            border-radius: 6px
        }

        .row .col-md-2, .row .col-md-10, .row {
            background: #fff
        }

        .li-active {
            border-radius: 6px;
            background: #2786C6;
            color: #fff
        }

        .nav > li > a:hover, .nav > li > a:focus {
            background: #2786C6;
            color: #fff
        }

        .li-active a:focus {
            border-radius: 6px;
            background: #2786C6;
            color: #fff
        }

        .li-active a {
            color: #fff
        }

        #iframe-main {
            font-size: 0;
            height: 100%;
            padding: 4px;
            padding-top: 7px;
            padding-bottom: 0;
            padding-left: 0;
        }

        #content {
            width: 100%;
            height: 100%;
        }

    </style>

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
                            <a href="#" url="jsp/appuser/newlist.jsp">
                                <i class="fa fa-pencil-square-o"></i>
                                用户管理</a>
                        </li>

                        <li class="">
                            <a href="#" url="jsp/appuser/list.jsp">
                                <i class="fa fa-bars"></i>
                                用户管理</a>
                        </li>

                    </ul>
                </li>


                <li>
                    <a id="main2" href="#mtgRoom" class="nav-header collapsed" data-toggle="collapse">
                        <i class="fa fa-home">&nbsp;</i>
                        会议管理<span class="pull-right glyphicon glyphicon-chevron-down"></span>
                    </a>

                    <ul id="mtgRoom" class="nav nav-list collapse secondmenu">

                        <li class="">
                            <a href="#" url="jsp/mtgRoom/meeting_add_room.jsp">
                                <i class="fa fa-gear"></i>
                                会议管理</a>
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