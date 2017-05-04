<%@ page contentType="text/html; charset=GBK" %>
<%
    //页面不缓存
    response.setHeader("Cache-Control", "no-cache");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", 0);

    String basepath = request.getContextPath();
    String orgParentId = request.getParameter("orgParentId");
%>
<html>
<head>
    <style>
        .yes {
            padding: 4px 7px;
            padding-bottom: 2px;
            line-height: normal;
            height: auto;
            cursor: pointer;
            border: 1px solid #A5CBE7;
            font-size: 12px;
            line-height: 16px;
            height: 22px;
            padding: 2px 2px 0 2px;
            margin: 0 3px -1px 0;
            border-bottom: #000 solid 1px;
            border-right: #000 solid 1px;
            background: #003063;
            color: #FFFFFF;
        }

        arrowLeft, arrowRight, arrowAllLeft, arrowAllRight, arrowTop, arrowBottom, arrowMaxTop, arrowMaxBottom {
            background-image: url(js/arrow.gif);
            background-repeat: no-repeat;
            display: -moz-inline-box !important;
            display: inline-block;
            -moz-box-align: center !important;
            -moz-box-pack: center !important;
            margin: 2px 0;
        }

        arrowLeft {
            background-position: 0 0px;
            width: 25px;
            height: 25px;
        }

        arrowLeft:hover {
            background-position: -25px 0px;
        }

        arrowAllLeft {
            background-position: 0 -175px;
            width: 25px;
            height: 25px;
        }

        arrowAllLeft:hover {
            background-position: -25px -175px;
        }

        arrowRight {
            background-position: 0 -25px;
            width: 25px;
            height: 25px;
        }

        arrowRight:hover {
            background-position: -25px -25px;
        }

        arrowAllRight {
            background-position: 0 -150px;
            width: 25px;
            height: 25px;
        }

        arrowAllRight:hover {
            background-position: -25px -150px;
        }

        arrowMaxBottom {
            background-position: 0 -50px;
            width: 25px;
            height: 25px;
        }

        arrowMaxBottom:hover {
            background-position: -25px -50px;
        }

        arrowBottom {
            background-position: 0 -75px;
            width: 25px;
            height: 25px;
        }

        arrowBottom:hover {
            background-position: -25px -75px;
        }

        arrowMaxBottom {
            background-position: 0 -50px;
            width: 25px;
            height: 25px;
        }

        arrowMaxBottom:hover {
            background-position: -25px -50px;
        }

        arrowTop {
            background-position: 0 -100px;
            width: 25px;
            height: 25px;
        }

        arrowTop:hover {
            background-position: -25px -100px;
        }

        arrowMaxTop {
            background-position: 0 -125px;
            width: 25px;
            height: 25px;
        }

        arrowMaxTop:hover {
            background-position: -25px -125px;
        }
    </style>
    <title>组织机构树</title>
    <Script type="text/javascript">
        //关闭
        function close_onclick() {
            window.close();
        }
        //删除右边的元素
        function RtoL_onclick() {
            var docs = document.form1.ObjSelect;
            for (var x = docs.length - 1; x >= 0; x--) {
                var opt = docs.options[x];
                if (opt.selected) {
                    docs.options[x] = null;
                }
            }
        }
        //全部删除
        function RtoLALL_onclick() {
            var docs = document.form1.ObjSelect;
            for (var x = docs.length - 1; x >= 0; x--) {
                var opt = docs.options[x];
                docs.options[x] = null;
            }
        }
        //单个元素单击删除
        function R_onclick(fromObj) {
            var fromObjOptions = fromObj.options;
            for (var i = 0; i < fromObjOptions.length; i++) {
                if (fromObjOptions[i].selected) {
                    fromObj.options[i] = null;
                }
            }
        }
        function moveLeftOrRight(fromObj, toObj) {
            var fromObjOptions = fromObj.options;
            for (var i = 0; i < fromObjOptions.length; i++) {
                if (fromObjOptions[i].selected) {
                    var tmp = 0;
                    for (var j = 0; j < toObj.options.length; j++) {
                        if (toObj.options[j].value == fromObjOptions[i].value) {
                            tmp = 1;
                            break;
                        }
                    }
                    if (tmp == 0)
                        toObj.options[toObj.options.length] = new Option(
                                fromObjOptions[i].text, fromObjOptions[i].value, 0,
                                0);
                }
            }
        }

        function moveLeftOrRightAll(fromObj, toObj) {
            var fromObjOptions = fromObj.options;
            for (var i = 0; i < fromObjOptions.length; i++) {
                var tmp = 0;
                for (var j = 0; j < toObj.options.length; j++) {
                    if (toObj.options[j].value == fromObjOptions[i].value) {
                        tmp = 1;
                        break;
                    }
                }
                if (tmp == 0)
                    toObj.options[toObj.options.length] = new Option(
                            fromObjOptions[i].text, fromObjOptions[i].value, 0, 0);
            }
        }
        
        /**
        var indexNum = window.dialogArguments.form1.hidnum.value;
        //动态增加行
        function addrow(name, type, id) {
            var exsit = "";
            var flag = 0;
            //判断是不是存在该参数
            var ftr = dialogArguments.document.getElementById("fileTR");
            var trs = ftr.getElementsByTagName("tr");
            if (trs.length >= 1) {
                for (var i = 0; i < trs.length; i++) {
                    var cellsType = trs[i].cells(0).aa;
                    var cellsID = trs[i].cells(0).bb;
                    if (cellsID == id && cellsType == type) {
                        flag = 1;
                        exsit += name + "\n";
                    }
                }
            }
            
            if (flag == 0) {
                //往打开的页面写
                indexNum = indexNum * 1 + 1;
                var tab = window.dialogArguments.document.getElementById("fileTR");
                var newTR = window.dialogArguments.document.createElement("tr");
                var newTd1 = window.dialogArguments.document.createElement("td");
                var newTd2 = window.dialogArguments.document.createElement("td");
                var newTd3 = window.dialogArguments.document.createElement("td");
                //新增行ID;
                var trID = "tr_" + indexNum;
                newTR.id = trID;
                newTd1.aa = type;
                newTd1.bb = id;
                newTd1.cc = name;
                newTd1.innerHTML = name;
                newTd2.innerHTML = "用户";
                newTd3.innerHTML = "<a class='btC1'href='#' onclick=delRow('"
                + trID + "')>删除</a>";
                newTR.appendChild(newTd1);
                newTR.appendChild(newTd2);
                newTR.appendChild(newTd3);
                tab.appendChild(newTR);
            }
            return exsit;
        }
        
        */
        
        function add_onclick() {
            var obj = document.getElementById("seleids");
            var options = obj.options;
            var len = options.length;
            var opttexts = "";
            var optvalues = "";
            for (var i = 0; i < len; i++) {
                var opt = options[i];
                opttexts += "," + opt.text;
                optvalues += "," + opt.value;
                //alert(opt.value + '----' + opt.text);//右面的所有值
            }
            // alert(optvalues.substring(1)+"---"+opttexts.substring(1));
            window.parent.opener.document.getElementById("deptcontactId").value = optvalues.substring(1);
            // window.opener.document.getElementById("deptcontactId").value = optvalues.substring(1);//optvalues  传到页面的部门ID字符串
            window.parent.opener.document.getElementById("deptcontactName").value = opttexts.substring(1); //opttexts  传到页面的部门名称字符串
            /* 	var exsit = "";
             var docs = document.form1.ObjSelect;
             for (var x = 0; x <= docs.length - 1; x++) {
             var opt = docs.options[x];
             exsit += addrow(opt.text, "0", opt.value);
             }
             if (exsit != "") {
             alert("如下人员已经存在：\n" + exsit);
             }
             //window.dialogArguments.form1.hidnum.value = indexNum;*/
            window.parent.close();
        }
    </SCRIPT>
</head>
<body>
<form name="form1">
    <div class="openWin">
        <div class="top"></div>
        <div class="main form_1">
            <table class="form">
                <tr>
                    <td><select name="SrcSelect"
                                style="width: 170px; height: 480px;" size="27" multiple
                                ondblclick="moveLeftOrRight(document.form1.SrcSelect,document.form1.ObjSelect)">
                    </select></td>
                    <td width="25" style="vertical-align: middle"><a href="#"
                                                                     class="arrowRight" title="向右"
                                                                     onClick="moveLeftOrRight(document.form1.SrcSelect,document.form1.ObjSelect)"></a>
                        <a href="#" class="arrowAllRight" title="全部向右"
                           onClick="moveLeftOrRightAll(document.form1.SrcSelect,document.form1.ObjSelect)"></a>
                        <a href="#" class="arrowAllLeft" title="全部向左"
                           onClick="RtoLALL_onclick()"></a> <a href="#" class="arrowLeft"
                                                               title="向左"
                                                               onClick="R_onclick(document.form1.ObjSelect)"></a></td>
                    <td><select name="ObjSelect" id="seleids"
                                style="width: 170px; height: 480px;" size="27" multiple
                                ondblclick="R_onclick(document.form1.ObjSelect)">
                        <%
                            if (orgParentId == null || "".equals(orgParentId)
                                    || "null".equals(orgParentId)) {
                        %>

                        <%
                            }
                        %>
                    </select></td>
                </tr>
            </table>
        </div>
        <div class="bottom" style="text-align: left; padding-left: 37px">
            <a href="#" class="yes" onClick="add_onclick()">确定</a> <a
                class="yes" href="#" onClick="close_onclick()">取消</a>
        </div>


    </div>
</form>
</body>
</html>
