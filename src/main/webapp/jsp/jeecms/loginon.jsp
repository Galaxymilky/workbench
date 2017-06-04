<%--
  Created by IntelliJ IDEA.
  User: dynam
  Date: 2017/5/1
  Time: 11:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/jsp/common/tag.jsp" %>
<html>
<head>
    <title>用户列表</title>

    <%@ include file="/jsp/common/head.jsp" %>

</head>
<body>
<div class="panel panel-default">

    <c:forEach items="${channelList}" var="channel" step="1" varStatus="status">
        <div class="panel panel-default">
            <div class="panel-heading" role="tab" id="heading${channel.channelId}">
                <h4 class="panel-title">
                    <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion1" href="#collapse${channel.channelId}" aria-expanded="false"
                       aria-controls="collapse${channel.channelId}">
                            ${channel.channelName}
                    </a>
                </h4>
            </div>
            <div id="collapse${channel.channelId}" class="panel-collapse collapse" role="tabpanel" aria-labelledby="heading${channel.channelId}">
                <div class="panel-body">
                    <ul class="tup ace-thumbnails_b" id="${channel.channelId}">
                        <span style="color:#FF0000;"></span>
                        <c:forEach items="${map}" var="themeList" step="1" varStatus="status">
                        <c:if test="${themeList.key == channel.channelId}" >
                            <c:forEach items="${themeList.value}" var="theme">
                                <li>
                                    <span class="name">
                                        <a title="${theme.themeName} ${theme.themeId}" class="cboxElement" data-rel="colorbox_b">${theme.themeName}</a>
                                    </span>
                                </li>
                            </c:forEach>
                        </c:if>
                        </c:forEach>
                    </ul>
                </div>
            </div>
        </div>
    </c:forEach>

</div>
</body>
</html>
