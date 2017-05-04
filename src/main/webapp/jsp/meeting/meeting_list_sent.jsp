<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>


<%@ include file="/jsp/common/headlocal.jsp" %>
<html>
<head>
    <title>已发送会议通知</title>
    <style type="text/css">
        .closenew {
            background-color: #8b8b8b;
            border: 2px solid #fff;
            border-radius: 25px;
            box-shadow: 0 0 3px 1px #999;
            color: #fff;
            font-family: Arial, sans-serif;
            font-size: 20px;
            font-weight: bold;
            height: 25px;
            line-height: 20px;
            outline: medium none;
            padding: 0;
            position: absolute;
            right: -12px;
            text-align: center;
            text-decoration: none;
            text-shadow: 0 1px 0 #ffffff;
            top: -12px;
            width: 25px;
        }

        table.gridtable {
            font-family: verdana, arial, sans-serif;
            font-size: 14px;
            color: #333333;
            border-width: 1px;
            border-color: #666666;
            border-collapse: collapse;
        }

        table.gridtable th {
            border-width: 1px;
            padding: 8px;
            border-style: solid;
            border-color: #666666;
            background-color: #dedede;
        }

        table.gridtable td {
            border-width: 1px;
            padding: 8px;
            border-style: solid;
            border-color: #666666;
            background-color: #ffffff;
        }
    </style>

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
                    		var ahtml = "<a style=\"cursor: pointer;\" onclick=\"queryData('" + c.id + "')\">"+c.mName+"</a>";
                    		return ahtml;
                    	}	
                    },
                    {"data": "meetingTime", "targets": 1},
                    {"data": "mAddress", "targets": 2},
                    {
                        targets: 3,
                        render: function (a, b, c, d) {
                            var ahtml = "总（<a style=\"cursor: pointer;\" onclick=\"showbk('" + c.id + "')\">" + c.totalNum + "</a>）";
                            ahtml += "&nbsp;未回执（<a style=\"cursor: pointer;\" onclick=\"showbk2('" + c.id + "')\">" + (c.totalNum - c.receiptNum) + "</a>）";
                            return ahtml;
                        }
                    },
                    {
                        targets: 4,
                        render: function (a, b, c, d) {
                            var btnhtml = "<button class=\"btn btn-primary btn-xs\" onclick=\"queryData('" + c.id + "')\" >查看</button>";
                            btnhtml += "&nbsp;<button class=\"btn btn-primary btn-xs\" id=\"showbk\" onclick=\"showbk('" + c.id + "')\" >回执</button>";
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
                    "url": contextPath + "/meetingInfo.do?method=querydatabystatus&status=1",
                    "type": "POST",
                    "dataType": "json"
                },
                "stateSave": false,	//保存状态
                "order": [[ 1, "desc" ]], //默认第i+1列排序
                rowId: 'id'//数据标识,
                //"dom": '<"toolbar">'
            });
            $("div.toolbar").html('<b style="color:red">自定义文字、图片等等</b>');
            table = $('#example').DataTable();
        }

        function queryData(meetingId) {
        	window.location.href = contextPath + "/meetingInfo.do?method=getSentDetail&meetingId=" + meetingId;
        }

        function showbk(id) {
            $.get(contextPath + "/meetingInfo.do?method=queryattend&id=" + id, function (data) {
                if (data.rel == "1") {
                    var str = "";
                    var squ = 1;
                    $.each(data.list, function (s) {
                        //遍历list
                        var tr = "<tr>";
                        tr += "<td>" + (s + 1) + "</td>";
                        tr += "<td>";
                        if (this.isReceipt == "1") {
                            tr += "已回执" + "</td>";
                        } else if (this.isReceipt == "0") {
                            tr += "未回执" + "</td>";
                        } else {
                            tr += "回执错误" + "</td>";
                        }
                        tr += "<td>" + this.deptName + "</td>";
                        tr += "<td>" + this.deptcontactName + "</td>";
                        if (this.deptcontact_phone == "null" || this.deptcontact_phone == "" || this.deptcontact_phone == null) {
                            tr += "<td>" + "未留联系电话" + "</td>";
                        } else {
                            tr += "<td>" + this.deptcontact_phone + "</td>";
                        }
                        tr += "</tr>"
                        str += tr;
                    });
                    $("#group_tbody").html(str);
                } else {
                    $("#load_Modal .modal-body").html("加载失败...");
                    $("#load_Modal").modal("show");
                }
                $("#group_Modal").modal("show");
            }, "json");
        }
		
        function showbk2(id) {
            $.get(contextPath + "/meetingInfo.do?method=queryUnattend&id=" + id, function (data) {
                if (data.rel == "1") {
                    var str = "";
                    var squ = 1;
                    $.each(data.list, function (s) {
                        //遍历list
                        var tr = "<tr>";
                        tr += "<td>" + (s + 1) + "</td>";
                        tr += "<td>";
                        if (this.isReceipt == "1") {
                            tr += "已回执" + "</td>";
                        } else if (this.isReceipt == "0") {
                            tr += "未回执" + "</td>";
                        } else {
                            tr += "回执错误" + "</td>";
                        }
                        tr += "<td>" + this.deptName + "</td>";
                        tr += "<td>" + this.deptcontactName + "</td>";
                        if (this.deptcontact_phone == "null" || this.deptcontact_phone == "" || this.deptcontact_phone == null) {
                            tr += "<td>" + "未留联系电话" + "</td>";
                        } else {
                            tr += "<td>" + this.deptcontact_phone + "</td>";
                        }
                        tr += "</tr>"
                        str += tr;
                    });
                    $("#group_tbody").html(str);
                } else {
                    $("#load_Modal .modal-body").html("加载失败...");
                    $("#load_Modal").modal("show");
                }
                $("#group_Modal").modal("show");
            }, "json");
        }
        
    </script>
    <!--[if IE]>
		<script src="http://libs.useso.com/js/html5shiv/3.7/html5shiv.min.js"></script>
	<![endif]-->
</head>
<body>
<div class="panel panel-default">
    <div class="panel-body">
        <table id="example" class="table table-striped table-bordered" >
            <thead style="background: #c6e0fb">
            <tr>
                <th style="width:200px">会议名称</th>
                <th style="width:80px">会议时间</th>
                <th style="width:60px">会议地点</th>
                <th style="width:80px">回执状态</th>
                <th style="width:60px">操作</th>
            </tr>
            </thead>
        </table>
    </div>

    <div id="load_Modal" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-body">
                </div>
            </div>
        </div>
    </div>
    <div id="group_Modal" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="closenew" data-dismiss="modal" aria-label="Close"
                            style="padding-top:-30px; "><span aria-hidden="true">&times;</span></button>
                    <h5 class="modal-title" style="text-align: center;">回执信息</h5>
                </div>
                <div class="modal-body">
                    <form>
                        <table class="gridtable" style="width: 100%;font-size: 14px;">
                            <tr>
                                <th>序号</th>
                                <th>回执状态</th>
                                <th>接收部门</th>
                                <th>联系人</th>
                                <th>联系电话</th>
                            </tr>
                            <tbody id="group_tbody">
                            </tbody>
                        </table>
                    </form>
                </div>
            </div>
        </div>
    </div>

</div>
</body>
</html>