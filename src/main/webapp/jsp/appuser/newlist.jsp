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

    <%@ include file="/jsp/common/headbk.jsp" %>

    <script type="text/javascript">
        var table = null;
        var selected = [];
        $(document).ready(function () {
            loadData();
        });

        function loadData() {
            $('#example').dataTable({
                "language": {
                    "url": contextPath + "/js/common/i18n/Chinese.json"
                },
                "select": {
                    style: "os",
                    className: "row-selected"
                },
                "columnDefs": [
                    {"data": "userId", "targets": 0},
                    {"data": "userName", "targets": 1},
                    {"data": "loginName", "targets": 2},
                    {"data": "priority", "targets": 3},
                    {"data": "userPhone", "targets": 4}
                ],
                "paging": true,//是否分页
                "lengthMenu": [10, 25, 50, 75, 100],//分页长度列表
                "pageLength": 10,//每页多少条
                "lengthChange": true,//允许改变分页长度
                "pagingType": "full_numbers",
                "processing": true,
                "serverSide": true,//服务端处理
                "ajax": {
                    "url": contextPath + "/appuser?method=getListAjax",
                    "type": "POST",
                    "dataType": "json"
                },
                "stateSave": false,	//保存状态
                "order": [[0, "desc"]],
                rowId: 'userId'//数据标识
                //"dom": '<"toolbar">'
            });
            $("div.toolbar").html('<b style="color:red">自定义文字、图片等等</b>');
            table = $('#example').DataTable();

        }

        function deleteData(id) {
            $.ajax({
                type: "POST",
                url: contextPath + '/demo.do?method=deletedemoobj',
                data: {ids: id},
                dataType: 'json',
                async: false,
                error: function (request) {
                },
                success: function (result) {
                    if (result.success) {
                        alert("删除成功!");
                        table.ajax.reload();
                    }
                }
            });
        }
        function modifyData(id) {
            window.location.href = contextPath + "/jsp/demo/form.jsp?id=" + id;
        }
    </script>

</head>
<body>
<div class="panel panel-default">
    <div class="panel-body">
        <table id="example" class="table table-striped table-bordered" cellspacing="0" width="100%">
            <thead>
            <tr>
                <th>用户ID</th>
                <th>用户名</th>
                <th>登录名</th>
                <th>优先级</th>
                <th>手机号</th>
            </tr>
            </thead>
        </table>
    </div>
</div>
</body>
</html>
