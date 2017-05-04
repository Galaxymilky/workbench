<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>

<%@ include file="/jsp/common/headlocal.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <style type="text/css">
        .c_label {
            text-align: center;
        }

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
            background: -webkit-gradient(linear, left top, left bottom, color-stop(0%, #FAFAFA), color-stop(100%, #E9E9E9));
            background: -webkit-linear-gradient(top, #FAFAFA 0%, #E9E9E9 100%);
            background: -o-linear-gradient(top, #FAFAFA 0%, #E9E9E9 100%);
            background: -ms-linear-gradient(top, #FAFAFA 0%, #E9E9E9 100%);
            background: linear-gradient(top, #FAFAFA 0%, #E9E9E9 100%);
            filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#FAFAFA', endColorstr='#E9E9E9');
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
            background: -moz-linear-gradient(top, #4A515B 0%, #3C4049 100%);
            background: -webkit-gradient(linear, left top, left bottom, color-stop(0%, #4A515B), color-stop(100%, #3C4049));
            background: -webkit-linear-gradient(top, #4A515B 0%, #3C4049 100%);
            background: -o-linear-gradient(top, #4A515B 0%, #3C4049 100%);
            background: -ms-linear-gradient(top, #4A515B 0%, #3C4049 100%);
            background: linear-gradient(top, #4A515B 0%, #3C4049 100%);
            filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#4A515B', endColorstr='#3C4049');
            -ms-filter: "progid:DXImageTransform.Microsoft.gradient(startColorstr='#4A515B', endColorstr='#3C4049')";
            border-color: #2B2E33;
        }

        #main-nav.nav-tabs.nav-stacked > li.active > a, #main-nav.nav-tabs.nav-stacked > li > a:hover > span {
            color: #FFF;
        }

        #main-nav.nav-tabs.nav-stacked > li {
            margin-bottom: 4px;
        }

        /*定义二级菜单样式*/
        .secondmenu a {
            font-size: 13px;
            color: #4A515B;
        }

        .navbar-static-top {
            background-color: #212121;
            margin-bottom: 5px;
        }

        .navbar-brand {
            background: url('') no-repeat 10px 8px;
            display: inline-block;
            vertical-align: middle;
            padding-left: 50px;
            color: #fff;
        }
    </style>

    <title></title>
    <!--[if IE]>
		<script src="http://libs.useso.com/js/html5shiv/3.7/html5shiv.min.js"></script>
	<![endif]-->
</head>
<body>
<div class="panel panel-default" style="overflow-y:scroll;height:630px">
    <div class="panel-body">
        <table id="example" class="table table-striped table-bordered" cellspacing="0" width="100%">
            <tbody style="border: 1px">
            <tr>
                <td class="c_label">
                    <div>会议名称</div>
                </td>
                <td colspan="5">${meetingInfo.mName}</td>
            </tr>
            <tr>
                <td class="c_label">
                    <div>会议地点</div>
                </td>
                <td>${meetingInfo.mAddress}</td>
                <td class="c_label">
                    <div>会议时间</div>
                </td>
                <td colspan="3">${strMeetingTime}</td>
            </tr>
            <tr>
                <td class="c_label">
                    <div>会议内容</div>
                </td>
                <td colspan="5">${meetingInfo.content}</td>
            </tr>
            <tr>
                <td class="c_label">
                    <div>联系人</div>
                </td>
                <td>${meetingInfo.userName}</td>
                <td class="c_label">
                    <div>联系电话</div>
                </td>
                <td>${meetingInfo.userPhone}</td>
                <td class="c_label">
                    <div>反馈时间</div>
                </td>
                <td>${strFeedBackTime}</td>
            </tr>
            <tr>
                <td class="c_label">
                    <div>备注</div>
                </td>
                <td colspan="5">${meetingInfo.remark}</td>
            </tr>
            <tr>
                <td class="c_label">
                    <div>附件列表</div>
                </td>
                <td colspan="5">
                    <c:forEach items="${meetingInfo.attachList}" var="attachment">
                        <p><a style="cursor: pointer;"
                              onclick="downfile('${attachment.id}')">${attachment.fileOrigName}</a></p>
                    </c:forEach>
                </td>

            </tr>
            </tbody>
        </table>
    </div>
</div>


<script type="text/javascript">
    //下载附件
    function downfile(fileid) {
        window.location.href = contextPath + "/fileUpload.do?method=downloadAttachment&fileId=" + fileid;
    }
</script>

</body>
</html>