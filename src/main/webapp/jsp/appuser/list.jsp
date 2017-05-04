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
    <div class="panel-body">
        <table id="example" class="table table-striped table-bordered" cellspacing="0" width="100%">
            <thead>
            <tr>
                <th>用户名</th>
                <th>登录名</th>
                <th>优先级</th>
                <th>手机号</th>
                <th>创建时间</th>
                <th style="width:60px"></th>
            </tr>
            </thead>

            <tbody>
            <c:forEach var="appuser" items="${userList}">
                <tr>
                    <td>${appuser.userName}</td>
                    <td>${appuser.loginName}</td>
                    <td>${appuser.priority}</td>
                    <td>${appuser.userPhone}</td>
                    <td><fmt:formatDate value="${appuser.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                </tr>
            </c:forEach>
            </tbody>

        </table>
    </div>
</div>
</body>
</html>
