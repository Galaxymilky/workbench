/**
 * Created by dynamicniu on 2018/1/14.
 */
var table = null;
var selected = [];
var contextPath = null;

function getRootPath() {
    //获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
    var curPath = window.document.location.href;
    //获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
    var pathName = window.document.location.pathname;
    var pos = curPath.indexOf(pathName);
    //获取主机地址，如： http://localhost:8083
    var localhostPath = curPath.substring(0, pos);
    //获取带"/"的项目名，如：/uimcardprj
    var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
    return (localhostPath + projectName);
}

function getContextPath() {
    var curPath = window.document.location.href;
    var pathName = window.document.location.pathname;

    var pos = curPath.indexOf(pathName);
    contextPath = curPath.substring(0, pos);

    return contextPath;
}

/**
 * 初始化数据
 * 商品说明</th> 消费金额</th> 付款方式</th> 商品类型</th> 消费时间</th> 消费地点</th> 操作</th>
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
            {"data": "createdTime", "targets": 4},
            {"data": "consumeAddress", "targets": 5},
            {
                targets: 6,
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
        "order": [[1, "desc"]], //默认第i+1列排序
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

$(document).ready(function () {
    getContextPath();
    loadData();
});