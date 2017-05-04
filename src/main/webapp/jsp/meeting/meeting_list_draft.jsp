<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>

<%@ include file="/jsp/common/headlocal.jsp" %>
<html>
<head>
    <title></title>
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
                    {"data": "mName", "targets": 0},
                    {"data": "meetingTime", "targets": 1},
                    {"data": "mAddress", "targets": 2},
                    {"data": "createtime", "targets": 3},
                    {
                        targets: 4,
                        render: function (a, b, c, d) {
                            var btnhtml = "<button class=\"btn btn-primary btn-xs\" onclick=\"modifyData('" + c.id + "')\" >修改</button>";
                            btnhtml += "&nbsp;<button class=\"btn btn-danger btn-xs\" onclick=\"deleteData('" + c.id + "')\" >删除</button>";
                            return btnhtml;
                        }
                    }
                ],
                "scrollY": "425px",
                "paging": true,//是否分页
                "lengthMenu": [10, 20, 30],//分页长度列表
                "pageLength": 10,//每页多少条
                "lengthChange": true,//允许改变分页长度
                "pagingType": "full_numbers",
                "processing": true,
                "serverSide": true,//服务端处理
                "ajax": {
                    "url": contextPath + "/meetingInfo.do?method=querydatabystatus&status=0",
                    "type": "POST",
                    "dataType": "json"
                },
                "stateSave": false,	//保存状态
                "order": [[ 3, "asc" ]], //默认第i+1列排序
                rowId: 'id'//数据标识,
                //"dom": '<"toolbar">'
            });
            $("div.toolbar").html('<b style="color:red">自定义文字、图片等等</b>');
            table = $('#example').DataTable();
            /*
             var table = $('#example').DataTable();
             var lastIdx = null;

             $('#example tbody').on( 'click', 'tr', function () {
             if ( $(this).hasClass('selected') ) {
             $(this).removeClass('selected');
             }
             else {
             table.$('tr.selected').removeClass('selected');
             $(this).addClass('selected');
             }
             } );

             $('#example tbody').on( 'mouseover', 'td', function () {
             var colIdx = table.cell(this).index().column;
             if ( colIdx !== lastIdx ) {
             $( table.cells().nodes() ).removeClass('highlight');
             $( table.column( colIdx ).nodes() ).addClass('highlight');
             }
             }).on( 'mouseleave', function () {
             $( table.cells().nodes() ).removeClass('highlight');
             });
             */
        }
        function deleteData(id) {
        	if (confirm("确认要删除?")) {
        		
            $.ajax({
                type: "POST",
                url: contextPath + '/meetingInfo.do?method=deletemeetinginfo',
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
        }
        function modifyData(id) {
            window.location.href = contextPath + "/jsp/meeting/addInfo.jsp?id=" + id;
        }
    </script>
    <!--[if IE]>
		<script src="http://libs.useso.com/js/html5shiv/3.7/html5shiv.min.js"></script>
	<![endif]-->
</head>
<body>
<div class="panel panel-default">
    <div class="panel-body">
        <table id="example" class="table table-striped table-bordered" cellspacing="0" width="100%">
            <thead style="background: #c6e0fb">
            <tr>
                <th style="width:200px">会议名称</th>
                <th style="width:60px">会议时间</th>
                <th style="width:60px">会议地点</th>
                <th style="width:60px">创建时间</th>
                <th style="width:60px">操作</th>
            </tr>
            </thead>
        </table>
    </div>
</div>
</body>
</html>