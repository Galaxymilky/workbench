<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>

<%@ include file="/jsp/common/headlocal.jsp" %>

<html>
<head>
    <style type="text/css">
        .c_label {
            text-align: center;
        }

        #main-nav {
            margin-left: 1px;
        }

        #main-nav.nav-tabs.nav-stacked > li > a {
            padding: 10px 8px;
            font-size: 14px;
            font-weight: 600;
            color: #4A515B;
            background: #E9E9E9;
            background: -moz-linear-gradient(top, #FAFAFA 0%, #E9E9E9 100%);
            background: -webkit-gradient(linear, left top, left bottom, color-stop(0%, #FAFAFA), color-stop(100%, #E9E9E9));
            background: -webkit-linear-gradient(top, #FAFAFA 0%, #E9E9E9 100%);
            background: -o-linear-gradient(top, #FAFAFA 0%, #E9E9E9 100%);
            background: -ms-linear-gradient(top, #FAFAFA 0%, #E9E9E9 100%);
            background: linear-gradient(top, #FAFAFA 0%, #E9E9E9 100%);
            filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#FAFAFA', endColorstr='#E9E9E9');
            -ms-filter: "progid:DXImageTransform.Microsoft.gradient(startColorstr='#FAFAFA', endColorstr='#E9E9E9')";
            border: 1px solid #D5D5D5;
            border-radius: 4px;
        }

        #main-nav.nav-tabs.nav-stacked > li > a > span {
            color: #4A515B;
        }

        #main-nav.nav-tabs.nav-stacked > li.active > a, #main-nav.nav-tabs.nav-stacked > li > a:hover {
            color: #FFF;
            background: #3C4049;
            background: -moz-linear-gradient(top, #4A515B 0%, #3C4049 100%);
            background: -webkit-gradient(linear, left top, left bottom, color-stop(0%, #4A515B), color-stop(100%, #3C4049));
            background: -webkit-linear-gradient(top, #4A515B 0%, #3C4049 100%);
            background: -o-linear-gradient(top, #4A515B 0%, #3C4049 100%);
            background: -ms-linear-gradient(top, #4A515B 0%, #3C4049 100%);
            background: linear-gradient(top, #4A515B 0%, #3C4049 100%);
            filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#4A515B', endColorstr='#3C4049');
            -ms-filter: "progid:DXImageTransform.Microsoft.gradient(startColorstr='#4A515B', endColorstr='#3C4049')";
            border-color: #2B2E33;
        }

        #main-nav.nav-tabs.nav-stacked > li.active > a, #main-nav.nav-tabs.nav-stacked > li > a:hover > span {
            color: #FFF;
        }

        #main-nav.nav-tabs.nav-stacked > li {
            margin-bottom: 4px;
        }

        /*定义二级菜单样式*/
        .secondmenu a {
            font-size: 13px;
            color: #4A515B;
        }

        .navbar-static-top {
            background-color: #212121;
            margin-bottom: 5px;
        }

        .navbar-brand {
            background: url('') no-repeat 10px 8px;
            display: inline-block;
            vertical-align: middle;
            padding-left: 50px;
            color: #fff;
        }
		
		.label-info {
		    margin-right: 5px;
		}
		
    </style>

    <title></title>

    <script type="text/javascript">
        $(document).ready(function () {
            var radio = $("input[type='radio']");
            radio.click(function () {
                //alert($(this).attr("value"));
                $("#sty").attr("value", $(this).attr("value"));
                //alert($("#sty").attr("value"));
            });
        });
        //保存回执
        function saveData() {
            //alert(JSON.stringify( $("#ppForm").serialize()));
            $.post(contextPath + "/meetingInfo.do?method=savareceipte", $("#ppForm").serialize(), function (data) {
                alert(JSON.stringify(data));
                if (data.success) {
                    alert("回执成功");
                    parent.changeMenu(1);
                } else
                    alert("回执失败");
            }, "json");
        }
        //下载附件
        function downfile(fileid) {
            window.location.href = contextPath + "/fileUpload.do?method=downloadAttachment&fileId=" + fileid;
        }
        //返回列表
        function returnList() {
            parent.changeMenu("todo");
        }
        
        var index = 0;
        var rowNum = 0;
        
        function addPP() {
        	// 添加页面人员名称
            var ppName = document.getElementById("ppName").value;
            var ppDuty = document.getElementById("ppDuty").value;
            var ppPhone = document.getElementById("ppPhone").value;
            if (ppName == '') {
                alert("请输入数据");
            } else {
                /** 改用标签
            	var nowVal = $("#ppList").text();
                if (nowVal == '') {
                    $("#ppList").append(ppName);
                } else {
                    $("#ppList").append("," + ppName);
                }
            	*/
            	
            	if(index % 10 == 0){
            		
            		rowNum++;
            		var rowHtmlStr = "<h4 id='rowCur_"+rowNum+"'>"
	            		  + "<span class='label label-info' id='tagCur_"+index+"'>" + ppName + "&nbsp;&nbsp;"
			          	  + "<a style='cursor: pointer;' onclick='removeTag("+index+")'><i class='fa fa-times'></i></a>"
			          	  + "</span>"
			          	  + "</h4>";
			        
			        $("#xher").append(rowHtmlStr);
            	}else{
		           	var x = "<span class='label label-info' id='tagCur_"+index+"'>" + ppName + "&nbsp;&nbsp;"
			          	  + "<a style='cursor: pointer;' onclick='removeTag("+index+")'><i class='fa fa-times'></i></a>"
			          	  + "</span>";
		           	$("#rowCur_"+rowNum).append(x);
            	}
            	
            	index++;
            }
            
            // 添加隐藏域值
            var novPp = ppName + '#' + ppDuty + '#' + ppPhone;
            var ppContent = document.getElementById("ppContent").value;
            if (ppContent == '') {
            	$("#ppContent").val(novPp);
                //$("#ppContent").append(novPp);
            } else {
            	$("#ppContent").val(ppContent + "~" + novPp);
                //$("#ppContent").append("~" + novPp);
            }
            
        }
		
        function removeTag(val){
        	$("#tagCur_"+val).remove();
        }
        
    </script>
    <!--[if IE]>
    <script src="http://libs.useso.com/js/html5shiv/3.7/html5shiv.min.js"></script>
    <![endif]-->
</head>
<body>

<br>

<P><a href="#" onclick="returnList()" style="cursor: pointer;">返回列表</a></P>

<div class="panel panel-default" style="overflow-y:scroll;height:530px">
    <div class="panel-body">
        <p>填写回执</p>

        <div class="container" style="margin:0">
            <form id="ppForm" class="form-horizontal">
                <div class="form-group">
                    <input type="hidden" value="准时参会" name="sty" id="sty"/>

                    <div class="col-md-2">
                        <label class="control-label">是否准时参会</label>
                    </div>

                    <div class="col-md-10">
                        <label class="radio">
                            <input class="control-label" type="radio" id="ched" name="check" value="准时参会"
                                   checked="checked"/>准时参会
                        </label>
                    </div>

                    <div class="col-sm-offset-2 col-sm-10">
                        <label class="radio">
                            <input class="control-label" type="radio" name="check" value="不参会"/>不参会
                        </label>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-md-2">
                        <label class="control-label">联系人信息</label>
                    </div>

                    <div class="col-md-3">
                        <label for="depName" class="control-label">联系人</label>
                        <input type="text" class="form-control" id="depName" name="depName"
                               value="${meetingInfo.userName}">
                    </div>

                    <div class="col-md-3">
                        <label for="userPhone" class="control-label">联系人电话</label>
                        <input type="text" class="form-control" id="userPhone" name="userPhone"
                               value="${meetingInfo.userPhone}">
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-md-2">
                        <label class="control-label">选择参会人</label>
                    </div>
                    <div class="col-md-3">
                        <label for="ppName" class="control-label">参会人</label>
                        <input class="form-control pp" type="text" id="ppName" name="ppName" value="">
                    </div>
                    <div class="col-md-3">
                        <label for="ppDuty" class="control-label">职务</label>
                        <input class="form-control pp" type="text" id="ppDuty" name="ppDuty" value="">
                    </div>
                    <div class="col-md-3">
                        <label for="ppPhone" class="control-label">联系电话</label>
                        <input class="form-control pp" type="text" id="ppPhone" name="ppPhone" value="">
                    </div>
                    <div class="col-sm-offset-2 col-sm-10">
                        <a href="javascript:void(0)" id="addbtn" class="btn btn-default" onclick="addPP()">增加参会人</a>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-md-2">
                        <label class="control-label">参会人列表</label>
                    </div>

                    <div class="col-md-9">
                        <textarea class="form-control" rows="3" id="ppList" name="ppList"></textarea>
                        <input type="hidden" id="ppContent" name="ppContent" value="" />
                    </div>
                </div>
				
				<div class="form-group">
                    <div class="col-md-2">
                        <label class="control-label">参会人列表</label>
                    </div>
					
                    <div class="col-md-9" id="xher">
                       	<!-- 
                        <h4>
                        	<span class="label label-info" >张山&nbsp;&nbsp;
                        		<a style="cursor: pointer" onclick="removeTag()"><i class="fa fa-times"></i></a>
                        	</span>
                        	&nbsp;&nbsp;
                        	
                        </h4>
                       	 -->
                        
                    </div>
                </div>
				
				
				
                <input type="hidden" name="receive_id" value="${receive_id}"/>
            </form>
        </div>

        <div style="text-align: center">
            <a href="javascript:void(0)" id="savbtn" class="btn btn-danger" onclick="saveData('${receive_id}')">保存回执</a>
        </div>
        <div style="height: 30px;">
        </div>

        <table id="example2" class="table table-striped table-bordered" cellspacing="0" width="100%">
            <tbody style="border: 1px">
            <tr>
                <td class="c_label" style="width: 160px;">
                    <div>会议名称</div>
                </td>
                <td colspan="5">${meetingInfo.mName}</td>
            </tr>
            <tr>
                <td class="c_label">
                    <div>会议地点</div>
                </td>
                <td>${meetingInfo.mAddress}</td>
                <td class="c_label">
                    <div>会议时间</div>
                </td>
                <td colspan="3">${strMeetingTime}</td>
            </tr>
            <tr>
                <td class="c_label">
                    <div>会议内容</div>
                </td>
                <td colspan="5">${meetingInfo.content}</td>
            </tr>
            <tr>
                <td class="c_label">
                    <div>联系人</div>
                </td>
                <td>${meetingInfo.userName}</td>
                <td class="c_label">
                    <div>联系电话</div>
                </td>
                <td>${meetingInfo.userPhone}</td>
                <td class="c_label">
                    <div>反馈时间</div>
                </td>
                <td>${strFeedBackTime}</td>
            </tr>
            <tr>
                <td class="c_label">
                    <div>备注</div>
                </td>
                <td colspan="5">${meetingInfo.remark}</td>
            </tr>
            <tr>
                <td class="c_label">
                    <div>附件列表</div>
                </td>
                <td colspan="5">
                    <c:forEach items="${meetingInfo.attachList}" var="attachment">
                        <p><a style="cursor: pointer;"
                              onclick="downfile('${attachment.id}')">${attachment.fileOrigName}</a></p>
                    </c:forEach>
                </td>

            </tr>
            </tbody>
        </table>
    </div>
</div>


</body>
</html>