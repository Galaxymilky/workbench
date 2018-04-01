/**
 * Created by dynamicniu on 2018/1/14.
 */
var table = null;
var selected = [];
var contextPath = null;

/**
 * 初始化数据
 * <th style="width:12%">商品说明</th>
 <th style="width:8%">消费金额</th>
 <th style="width:8%">付款方式</th>
 <th style="width:8%">商品类型</th>
 <th style="width:8%">参与人</th>
 <th style="width:8%">消费时间</th>
 <th style="width:8%">消费地点</th>
 <th style="width:8%">备注</th>
 <th style="width:8%">操作</th>
 * */
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
            {
                "data": "productDesc",
                targets: 0,
                render: function (a, b, c, d) {
                    var ahtml = "<a style=\"cursor: pointer;\" onclick=\"queryData('" + c.idUserConsume + "')\">" + c.productDesc + "</a>";
                    return ahtml;
                }
            },
            {"data": "consumeAmount", "targets": 1},
            {"data": "payMethod", "targets": 2},
            {"data": "productType", "targets": 3},
            {"data": "createdBy", "targets": 4},
            {"data": "consumeTimeStr", "targets": 5},
            {"data": "consumeAddress", "targets": 6},
            {"data": "memo", "targets": 7},
            {
                targets: 8,
                render: function (a, b, c, d) {
                    var btnhtml = "<button class=\"btn btn-primary btn-xs\" onclick=\"queryData('" + c.id + "')\" >查看详情</button>";
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
            "url": contextPath + "/userConsume.do?method=listUserConsume&status=1",
            "type": "POST",
            "dataType": "json"
        },
        "stateSave": false,	//保存状态
        // "order": [[1, "desc"]], //默认第i+1列排序
        rowId: 'id'//数据标识,
        //"dom": '<"toolbar">'
    });
    $("div.toolbar").html('<b style="color:red">自定义文字、图片等等</b>');
    table = $('#example').DataTable();
}

function queryData(meetingId) {
    window.location.href = contextPath + "/meetingInfo.do?method=getSentDetail&meetingId=" + meetingId;
}

function getTaskPool() {
    var payPlatform = '1'
    var params = {
        "payPlatform": payPlatform
    }

    $.post(contextPath + "/userConsume/getTaskPool", params, function (data) {
        alert(data.result);
    })
}

function batchTaskPool() {
    var payPlatform = '1'
    var consumeAddress = '南阳'
    var createdBy = 'NIUBEN'
    var params = {
        "payPlatform": payPlatform,
        "consumeAddress": consumeAddress,
        "createdBy": createdBy
    }

    $.post(contextPath + "/userConsume/batchTaskPool", params, function (data) {
        alert(data.result);
    })
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

$(document).ready(function () {
    getContextPath();
    loadData();
});