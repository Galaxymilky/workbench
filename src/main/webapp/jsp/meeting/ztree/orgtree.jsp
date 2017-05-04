<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@page import="com.haidian.common.DBConnection" %>
<%@page import="org.nutz.dao.impl.SimpleDataSource" %>
<%@page import="org.nutz.dao.impl.NutDao" %>
<%@page import="com.haidian.dept.pojo.*" %>
<%@page import="org.nutz.dao.Dao" %>
<%@page import="org.nutz.dao.Cnd" %>
<%
    //页面不缓存
    response.setHeader("Cache-Control", "no-cache");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", 0);

    String basepath = request.getContextPath();
    String orgParentId = request.getParameter("orgParentId");
    Dao dao = DBConnection.getTmpDao();
    List<Department> deptList = new ArrayList<Department>();
    deptList = dao.query(Department.class, null);

    Department[] depts = new Department[deptList.size()];
    if (deptList.size() > 0) {
        for (int i = 0; i < deptList.size(); i++) {
            depts[i] = deptList.get(i);
        }
    }

    int rootOrgId = -2;

%>
<html>
<head>
    <title>组织机构树</title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="js/style.css" type="text/css"/>
    <link rel="stylesheet" href="css/demo.css" type="text/css">
    <link rel="stylesheet" href="css/zTreeStyle/zTreeStyle.css" type="text/css">
    <script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
    <script type="text/javascript" src="js/jquery.ztree.core.js"></script>
    <script type="text/javascript" src="js/jquery.ztree.excheck.js"></script>
    <script type="text/javascript" src="js/jquery.ztree.exedit.js"></script>

    <SCRIPT type="text/javascript">
        <!--
        var setting = {
            async: {
                enable: true,
                url: getUrl
            },
            check: {
                enable: true
            },
            data: {
                simpleData: {
                    enable: true
                }
            },
            view: {
                expandSpeed: ""
            },
            callback: {
                beforeExpand: beforeExpand,
                onAsyncSuccess: onAsyncSuccess,
                onAsyncError: onAsyncError
            }
        };

        var zNodes =[
            {name:"500个节点", id:"1", count:500, times:1, isParent:true},
            {name:"1000个节点", id:"2", count:1000, times:1, isParent:true},
            {name:"2000个节点", id:"3", count:2000, times:1, isParent:true}
        ];

        var log, className = "dark",
            startTime = 0, endTime = 0, perCount = 100, perTime = 100;
        function getUrl(treeId, treeNode) {
            var curCount = (treeNode.children) ? treeNode.children.length : 0;
            var getCount = (curCount + perCount) > treeNode.count ? (treeNode.count - curCount) : perCount;
            var param = "id="+treeNode.id+"_"+(treeNode.times++) +"&count="+getCount;
            return "../asyncData/getNodesForBigData.php?" + param;
        }
        function beforeExpand(treeId, treeNode) {
            if (!treeNode.isAjaxing) {
                startTime = new Date();
                treeNode.times = 1;
                ajaxGetNodes(treeNode, "refresh");
                return true;
            } else {
                alert("zTree 正在下载数据中，请稍后展开节点。。。");
                return false;
            }
        }
        function onAsyncSuccess(event, treeId, treeNode, msg) {
            if (!msg || msg.length == 0) {
                return;
            }
            var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
                totalCount = treeNode.count;
            if (treeNode.children.length < totalCount) {
                setTimeout(function() {ajaxGetNodes(treeNode);}, perTime);
            } else {
                treeNode.icon = "";
                zTree.updateNode(treeNode);
                zTree.selectNode(treeNode.children[0]);
                endTime = new Date();
                var usedTime = (endTime.getTime() - startTime.getTime())/1000;
                className = (className === "dark" ? "":"dark");
                showLog("[ "+getTime()+" ]&nbsp;&nbsp;treeNode:" + treeNode.name );
                showLog("加载完毕，共进行 "+ (treeNode.times-1) +" 次异步加载, 耗时："+ usedTime + " 秒");
            }
        }
        function onAsyncError(event, treeId, treeNode, XMLHttpRequest, textStatus, errorThrown) {
            var zTree = $.fn.zTree.getZTreeObj("treeDemo");
            alert("异步获取数据出现异常。");
            treeNode.icon = "";
            zTree.updateNode(treeNode);
        }
        function ajaxGetNodes(treeNode, reloadType) {
            var zTree = $.fn.zTree.getZTreeObj("treeDemo");
            if (reloadType == "refresh") {
                treeNode.icon = "css/zTreeStyle/img/loading.gif";
                zTree.updateNode(treeNode);
            }
            zTree.reAsyncChildNodes(treeNode, reloadType, true);
        }
        function showLog(str) {
            if (!log) log = $("#log");
            log.append("<li class='"+className+"'>"+str+"</li>");
            if(log.children("li").length > 4) {
                log.get(0).removeChild(log.children("li")[0]);
            }
        }
        function getTime() {
            var now= new Date(),
                h=now.getHours(),
                m=now.getMinutes(),
                s=now.getSeconds(),
                ms=now.getMilliseconds();
            return (h+":"+m+":"+s+ " " +ms);
        }

        $(document).ready(function(){
            $.fn.zTree.init($("#treeDemo"), setting, zNodes);

        });
        //-->
    </SCRIPT>

</head>
<body>
<form name="form1">
    <div class="openWin openWin_maxMain">

        <div class="top">
            <i></i><font>选择人员</font>
        </div>


        <div class="zTreeDemoBackground left">
            <ul id="treeDemo" class="ztree"></ul>
        </div>

        <div class="maxMain">
            <div class="main">
                <div class="maxMainScroll">
                    <SCRIPT>
                        initializeDocument();
                        openFolderInTree(1);
                    </SCRIPT>
                </div>
            </div>
        </div>

    </div>
</form>
</body>
</html>