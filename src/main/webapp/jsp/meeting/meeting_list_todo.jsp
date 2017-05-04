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
                    {"data": "mName",
                    	targets: 0,
                    	render: function(a, b, c, d){
                    		var ahtml = "<a style=\"cursor: pointer;\" onclick=\"queryData('" + c.meetingId + "','"+c.id+"')\">"+c.mName+"</a>";
                    		return ahtml;
                    	}
                    	
                    },
                    {"data": "meetingTime", "targets": 1},
                    {"data": "mAddress", "targets": 2},
                    {"data": "userName", "targets": 3},
                    {"data": "createTime", "targets": 4},
                    {"data": "isRead",
                        targets: 5,
                        render: function (a, b, c, d) {
                        	var ahtml = "<p>未读</p>";
                        	if(c.isRead == 1){
                        		ahtml = "<p>已读</p>";
                        	}
                            return ahtml;
                        }
                    },
                    {"data": "isReceipt",
                        targets: 6,
                        render: function (a, b, c, d) {
                        	var ahtml = "<p>未回</p>";
                        	if(c.isReceipt == 1){
                        		ahtml = "<p>已回</p>";
                        	}
                            return ahtml;
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
                "searchable": true,
                "serverSide": true,//服务端处理
                "ajax": {
                    "url": contextPath + "/meetingInfo.do?method=querytodolist&userName=一处用户",
                    "type": "POST",
                    "dataType": "json"
                },
                "stateSave": false,	//保存状态
                "order": [[ 4, "desc" ]], //默认第i+1列排序
                rowId: 'id'//数据标识,
                //"dom": '<"toolbar">'
            });
            $("div.toolbar").html('<b style="color:red">自定义文字、图片等等</b>');
            table = $('#example').DataTable();
        }
        
        function queryData(meetingId, id) {
            window.location.href = contextPath + "/meetingInfo.do?method=gettodolistdetail&meetingId=" + meetingId+"&id="+id;
        }
        
    </script>
    <!--[if IE]>
		<script src="http://libs.useso.com/js/html5shiv/3.7/html5shiv.min.js"></script>
	<![endif]-->
</head>
<body>
<div class="panel panel-default" >
    <div class="panel-body" ><!-- style="overflow-y:scroll;min-height:630px" -->
        <table id="example" class="table table-striped table-bordered" ><!-- cellspacing="0" width="100%" -->
            <thead style="background: #c6e0fb">
            <tr>
                <th style="width:200px">会议名称</th>
                <th style="width:80px">会议时间</th>
                <th style="width:60px">会议地点</th>
                <th style="width:60px">发送人</th>
                <th style="width:80px">接收时间</th>
                <th style="width:30px">阅读</th>
                <th style="width:30px">回执</th>
            </tr>
            </thead>
            
            
        </table>
    </div>
</div>
</body>
</html>