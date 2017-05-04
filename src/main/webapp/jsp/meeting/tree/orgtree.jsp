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
    <link rel="stylesheet" href="js/style.css" type="text/css"/>
    <title>组织机构树</title>
    <script type="text/javascript" src="js/treeview/ua.js"></script>
    <script type="text/javascript" src="js/treeview/ftiens4.js"></script>
    <script type="text/javascript" src="js/prototype.js"></script>
    <Script type="text/javascript">
        function openFolderInTree(linkID) {
            var folderObj = getElById('itemTextLink' + linkID);
            if (folderObj != null)
                folderObj.click();
        }
        function selectNode(id) {
            var pars = 'id=' + id;
            prototype_ajax(pars);
        }
        function prototype_ajax(pars) {
            var url = 'ChooseUserAction.jsp';
            var myAjax = new Ajax.Request(
                    url,
                    {
                        method: 'get',
                        parameters: pars,
                        onSuccess: reportSuccess,
                        evalScripts: true
                    });
        }
        var len = 0;
        function reportSuccess(request) {
            //清空select中的option
            var qh = parent.basefrm.document.all["SrcSelect"];
            for (var x = qh.length - 1; x >= 0; x--) {
                qh.options[x] = null;
            }
            //取值写新的option
            var tt = request.responseText;
            if (tt != "") {
                var list = tt.split(";");
                for (var i = 0; i < list.length; i++) {
                    var list2 = list[i].split(",");
                    if (list2 != "" && list2.length == 2) {
                        var n1 = parent.basefrm.document.createElement("OPTION");
                        n1.value = list2[0].replace(/[\r\n]/g, "");
                        n1.text = list2[1].replace(/[\r\n]/g, "");
                        //alert("n1.value:"+n1.value+n1.value.length+"       n1.text:"+n1.text+n1.text.length);
                        qh.add(n1);
                    }
                }
            }
            //len = parent.basefrm.document.all["SrcSelect"].length;
        }
    </SCRIPT>
    <script type="text/javascript">
        USETEXTLINKS = 1;
        STARTALLOPEN = 0;
        HIGHLIGHT = 1;
        PRESERVESTATE = 0;
        USEICONS = 1;
        BUILDALL = 0;
        ICONPATH = "js/treeview/";
        foldersTree = gFld("单位列表", "javascript:undefined");
        foldersTree.treeID = "orgTree";
        <%if(depts!=null && depts.length>0){
            for(int i=0; i<depts.length; i++){
                Department dept = (Department)depts[i] ;
                if(dept != null){
                    int tmp = dept.getParentId();
                    int tmpid = dept.getDepartId();
                    if(tmp<1 || tmpid==rootOrgId){%>
        aux<%=dept.getDepartId()%> = insFld(foldersTree, gFld('<%=dept.getDepartName()%>', 'javascript:parent.frames.left.selectNode(<%=dept.getDepartId()%>)'));
        <%}
        else{%>
        aux<%=dept.getDepartId()%> = insFld(aux<%=dept.getParentId()%>, gFld('<%=dept.getDepartName()%>', 'javascript:parent.frames.left.selectNode(<%=dept.getDepartId()%>)'));
        <%}
                    }
                }
            }%>

    </script>
</head>
<body>
<form name="form1">
    <div class="openWin openWin_maxMain">

        <div class="top">
            <i></i><font>选择人员</font>
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
        <div class="bottom"></div>
    </div>
</form>
</body>
</html>
