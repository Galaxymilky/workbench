<%@page import="com.haidian.meeting.pojo.UserInfo"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
	String id = request.getParameter("id");
	UserInfo userInfo = (UserInfo)request.getSession().getAttribute("userInfo");
	if(userInfo == null || userInfo.equals("")){
		out.println("<script>alert('没有登录！');window.close();</script>");
		return;
	}
	int userId = userInfo.getUser_id();
	String userName = userInfo.getRealname();
	String userPhone = userInfo.getPhone();
	
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/jsp/common/headlocal.jsp" %>
<html>
<head>
    <title></title>
    <link rel="stylesheet" type="text/css" href="<%=



     %>/js/plugins/webuploader-0.1.5/webuploader.css">
    <script type="text/javascript" src="<%=contextPath %>/js/plugins/webuploader-0.1.5/webuploader.js"></script>
    <script type="text/javascript" src="<%=contextPath %>/js/ui/bootstrapvalidator/dist/js/bootstrapValidator.min.js"></script>
</head>
<body>
<div class="panel panel-default" style="overflow-y:scroll;height:630px">
    <div class="panel-body">
        <div class="container" style="margin:0">
            <form id="info-form" class="form-horizontal" role="form" style="padding:15px 10px;" enctype="multipart/form-data">
                    <input type="hidden" name="id" value="">
                        <div class="form-group">
                            <label for="mName" class="col-md-2 control-label">会议名称</label>
                            <div class="col-md-10">
                                <input type="text" class="form-control" id="mName" name="mName" value="" placeholder="请输入会议名称" >
                            </div>
                        </div>
                    <div class="form-group">
                        <label for="mAddress" class="col-md-2 control-label">会议地点</label>
                        <div class="col-md-4">
                            <input type="text" class="form-control" id="mAddress" name="mAddress" value="" placeholder="请输入会议地点">
                        </div>
                        
                        <label for="meetingTime" class="col-md-2 control-label">会议时间</label>
                        <div class="col-md-4">
                            <input type="text" class="form-control" id="meetingTime" name="meetingTime" value="" placeholder="请输入会议时间" readonly>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="contentX" class="col-md-2 control-label"></label>
                        <div class="col-md-10">
                            <textarea id="contentX" class="form-control" rows="30" cols="40" name="content">请输入会议内容</textarea>
                            <script type="text/javascript">CKEDITOR.replace('content');</script>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="userName" class="col-md-2 control-label">联系人</label>
                        <div class="col-md-4">
                            <input type="text" class="form-control" id="userName" name="userName" value="<%=userName %>" placeholder="请输入联系人" >
                        </div>
                        
                        <label for="userPhone" class="col-md-2 control-label">联系电话</label>
                        <div class="col-md-4">
                            <input type="text" class="form-control" id="userPhone" name="userPhone" value="<%=userPhone %>" placeholder="请输入联系电话" >
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="feedBackTime" class="col-md-2 control-label">反馈时间</label>
                        <div class="col-md-4">
                            <input type="text" class="form-control" id="feedBackTime" name="feedBackTime" value="" placeholder="请输入反馈时间" readonly>
                        </div>
                        
                        <label for="remark" class="col-md-2 control-label">备注</label>
                        <div class="col-md-4">
                            <input type="text" class="form-control" id="remark" name="remark" value="" placeholder="请输入备注">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="deptcontactName" class="col-md-2 control-label">收文单位</label>
                        <div class="col-md-4">
                            <input type="text" class="form-control" id="deptcontactName" name="deptcontactName" value="" placeholder="收文单位">
                            <input type="hidden" class="form-control" id="deptcontactId" name="deptcontactId" value="" placeholder="收文单位">
                        </div>
                        <label><a href="javascript:void(0)" id="shouwen" class="btn btn-default" onclick="showDept()">收文单位</a></label>
                    </div>
                    
                    <div class="form-group">
		            	<label for="thelist" class="col-md-2 control-label" style="text-align:right">附件列表</label>
						<div class="col-md-4" >
							<div id="uploader" class="wu-example">
							    <div id="thelist" class="uploader-list"></div>
							    <div class="btns">
							        <div id="picker" >选择文件</div>
							        <!-- 选完文件自动上传
							        <a id="ctlBtn" class="btn btn-default">开始上传</a>
							        -->
							    </div>
							</div>
						</div>
						<input type="hidden" class="form-control" id="attachmentStr" name="attachmentStr" value="">
		            </div>
                    
                    <div class="form-group">
                        <label for="attachListX" class="col-md-2 control-label">已上传附件</label>

                        <div id="attachListX" class="col-md-4">
                        	
                        </div>
                        <input id="isSubmit" type="hidden" value="" />
                    </div>
                    
                    <div class="form-group">
                        <div class="col-md-offset-2 col-md-10">
                        <!-- 
                            <a href="javascript:void(0)" id="savbtn1" class="btn btn-danger" onclick="saveData(1)">发送</a>
                            <a href="javascript:void(0)" id="savbtn2" class="btn btn-default" onclick="saveData(0)">暂存</a>
                         -->
                            <button id="savbtn1" class="btn btn-danger" type="submit" onclick="saveData(1)">发送</button>
                            <button id="savbtn2" class="btn btn-default" type="submit" onclick="saveData(0)">暂存</button>
                            <input id="hiddenVal" name="hiddenVal" type="hidden" value="" />
                            <a href="javascript:void(0)" id="celbtn" class="btn btn-default" onclick="doReset()">重置</a>
                        </div>
                    </div>
            </form>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
$(document).ready(function() {
    $('#info-form').bootstrapValidator({
        live: 'enabled',
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            mName: {
                group: '.col-md-10',
                validators: {
                    notEmpty: {
                        message: '会议名称不能为空'
                    }
                }
            }
        	,mAddress: {
                group: '.col-md-4',
                validators: {
                    notEmpty: {
                        message: '会议地点不能为空'
                    }
                }
            }
            
        }
    })
    .on('success.form.bv', function(e) {
        // Prevent form submission
        e.preventDefault();
		// Get the form instance
        var $form = $(e.target);
        // Get the BootstrapValidator instance
        var bv = $form.data('bootstrapValidator');
        // Use Ajax to submit form data
        
        var status = document.getElementById("hiddenVal").value;
        var recUser = document.getElementById("deptcontactName").value;
    	if(status == 1 && recUser == ""){
    		alert("请选择收文单位！");
    	}else{
	        var attachJsonStrs = '';
	    	$("#thelist :input").each(function (i, e) {
	    		attachJsonStrs += "~" + $(e).val();
	        });
	    	$('#attachmentStr').val(attachJsonStrs);
	    	
	        var str = CKEDITOR.instances.contentX.getData();
	        document.getElementById("contentX").value = str;
	        
	        $.ajax({
	            type: "POST",
	            url: contextPath + '/meetingInfo.do?method=savemeetinginfo&status=' + status,
	            data: $("form").serialize(),
	            dataType: 'json',
	            async: false,
	            error: function (request) {
	            },
	            success: function (result) {
	                if (result.success) {
	                    alert("保存成功!");
	                    $('#isSubmit').val('1');
	                    
	                    parent.changeMenu(1);
	                }
	            }
	        });
    	}
    });
});
</script>

<script type="text/javascript">
    jQuery(function() {
        var $ = jQuery,
                $list = $('#thelist'),
                $btn = $('#ctlBtn'),
                state = 'pending',
                uploader;

        var uploader = WebUploader.create({
        	// 选完文件后，是否自动上传。
        	auto: true,
            // 不压缩image
            resize: false,
            // swf文件路径
            swf: '<%=contextPath %>/js/plugins/webuploader-0.1.5/Uploader.swf',
            // 文件接收服务端。
            server: contextPath+'/fileUpload.do?method=uploadAttachment',
            // 选择文件的按钮。可选。
            // 内部根据当前运行是创建，可能是input元素，也可能是flash.
            pick: '#picker',
            // 准备下一个文件
            prepareNextFile: true
        });

        // 当有文件添加进来的时候
        uploader.on( 'fileQueued', function( file ) {
            $list.append( '<div id="' + file.id + '" class="item">' +
            '<h4 class="info">' + file.name + '</h4><a style="cursor: pointer;" class="itemCancel">取消</a>' +
            '<p class="state">等待上传...</p>' +
            '<input class="attachJsonStr" type="hidden" value="" />' +
            '</div>' );
        });

        // 文件上传过程中创建进度条实时显示。
        uploader.on( 'uploadProgress', function( file, percentage ) {
            var $li = $( '#'+file.id ),
                    $percent = $li.find('.progress .progress-bar');

            // 避免重复创建
            if ( !$percent.length ) {
                $percent = $('<div class="progress progress-striped active">' +
                '<div class="progress-bar" role="progressbar" style="width: 0%">' +
                '</div>' +
                '</div>').appendTo( $li ).find('.progress-bar');
            }

            $li.find('p.state').text('上传中');

            $percent.css( 'width', percentage * 100 + '%' );
            
        });

        uploader.on( 'uploadSuccess', function( file, response ) {
        	
            var parseJson = jQuery.parseJSON(response._raw);
            
            $( '#'+file.id ).find('p.state').text('已上传');
            // 上传成功后，更改a标签class属性、文字、服务器返回的文件信息
            $( '#'+file.id ).find('a.itemCancel').text('删除');
            $( '#'+file.id ).find('a.itemCancel').attr("class", "itemDel");
            $( '#'+file.id ).find('a.itemDel').attr("onclick", "delUnFile('"+parseJson.fileName+"')");
            $( '#'+file.id ).find('input.attachJsonStr').val(response._raw);
            
            //var attachmentStrVal = $('#attachmentStr').val()+"~";
            //$('#attachmentStr').val(attachmentStrVal + response._raw);
        });

        uploader.on( 'uploadError', function( file ) {
            $( '#'+file.id ).find('p.state').text('上传出错');
        });

        uploader.on( 'uploadComplete', function( file ) {
            $( '#'+file.id ).find('.progress').fadeOut();
        });

        uploader.on( 'all', function( type ) {
            if ( type === 'startUpload' ) {
                state = 'uploading';
            } else if ( type === 'stopUpload' ) {
                state = 'paused';
            } else if ( type === 'uploadFinished' ) {
                state = 'done';
            }

            if ( state === 'uploading' ) {
                $btn.text('暂停上传');
            } else {
                $btn.text('开始上传');
            }
        });

        $btn.on( 'click', function() {
            if ( state === 'uploading' ) {
                uploader.stop();
            } else {
                uploader.upload();
            }
        });
        
        $("#thelist").on("click", ".itemCancel", function(){
			uploader.removeFile($(this).parent().attr("id"));//从上传文件列表中删除
			$(this).parent().remove();//从上传列表dom中删除
		});
        
        $("#thelist").on("click", ".itemDel", function(){
        	uploader.removeFile($(this).parent().attr("id"));
			$(this).parent().remove();
        	
		});
    });

    $(document).ready(function () {
        $('#meetingTime').datetimepicker();
        $('#feedBackTime').datetimepicker();
        if (<%=null != id && !"".equals(id) %>) {
            $.ajax({
                type: "POST",
                url: contextPath + '/meetingInfo.do?method=getmeetinginfo',
                data: {id: '<%=id %>'},
                dataType: 'json',
                async: false,
                error: function (request) {
                },
                success: function (result) {
                    if (result) {
                        $("#info-form :input").each(function (i, e) {
                            $(e).val(result[$(e).attr("name")]);
                        });
                        
                        var list = result.attachList;
                        if(list != null){
                        	var ahtml = "";
                            for(var i=0; i<list.length; i++){
                            	var idx = "p_a_"+i;
                            	ahtml += "<p id='"+idx+"' style=\"cursor: pointer;\"><a onclick=\"downfile('"+list[i].id+"')\">"+list[i].fileOrigName+"</a>"
                            		+"&nbsp;&nbsp;<a class='deldiv' onclick=\"deletefile('"+list[i].fileName+"')\">删除</a></p>";
                            }
                            document.getElementById("attachListX").innerHTML = ahtml;
                        }
                        
                        
                    }
                }
            });
        }
    });
	
    //提交前，设置隐藏域，以便提交时获取提交类型为发送还是暂存
    function saveData(status) {
    	document.getElementById("hiddenVal").value=status;
    }
	
    //显示接收人员
    function showDept() {
        var url = "tree/orgmain.jsp";
        window.open(url, "发送单位", "resizable=yes,scrollbars=yes,toolbar=no,menubar=no,status=yes,location=no,width=700,height=550,left=330,top=70");
    }
	
    //重置表单内容
    function doReset() {
        $("#info-form")[0].reset();
    }
    
  	//下载附件
    function downfile(fileid){
	   	window.location.href = contextPath+"/fileUpload.do?method=downloadAttachment&fileId="+fileid;
	}
  	
 	//删除已上传但未存数据库的文件
  	function delUnFile(fileName){
		$.ajax({
            type: "POST",
            url: contextPath + '/fileUpload.do?method=deleteAttachment&fileName=' + fileName +'&fileStatus=1',
            //data: $("form").serialize(),
            //dataType: 'json',
            async: false,
            error: function (request) {
            },
            success: function (result) {
                if (result.success) {
                	uploader.removeFile($(this).parent().attr("id"));
        			$(this).parent().remove();
                }
            }
        });
  	}
  	
  	//删除附件
  	function deletefile(fileName){
  		$.ajax({
            type: "POST",
            url: contextPath + '/fileUpload.do?method=deleteAttachment&fileName=' + fileName,
            //data: $("form").serialize(),
            dataType: 'json',
            async: false,
            error: function (request) {
            },
            success: function (result) {
                if (result.success) {
                	
                	$("#attachListX").on("click", ".deldiv", function(){
            			//uploader.removeFile($(this).parent().attr("id"));//从上传文件列表中删除
            			$(this).parent().remove();//从上传列表dom中删除
            		});
                }
            }
        });
  		
  	}
  	
  	/*
  	
  	$('#info-form').bootstrapValidator('validate').on('success.form.bv',function(e){
    	    e.preventDefault();
    	    submitForm(status);
    	});
  	
  	window.onbeforeunload = function (event) {
  		var x = $('#isSubmit').val();
  	    if (x != 1) {
  	        return "你有数据正在提交中,假如现在离开,你的操作可能无效.";
  	    }
  	}
  	
  	//原有表单提交方法
    function submitForm(status){
    	var attachJsonStrs = '';
    	$("#thelist :input").each(function (i, e) {
    		attachJsonStrs += "~" + $(e).val();
        });
    	$('#attachmentStr').val(attachJsonStrs);
    	
        var str = CKEDITOR.instances.contentX.getData();
        document.getElementById("contentX").value = str;
        
        $.ajax({
            type: "POST",
            url: contextPath + '/meetingInfo.do?method=savemeetinginfo&status=' + status,
            data: $("form").serialize(),
            dataType: 'json',
            async: false,
            error: function (request) {
            },
            success: function (result) {
                if (result.success) {
                    alert("保存成功!");
                    $('#isSubmit').val('1');
                    
                    parent.changeMenu(1);
                }
            }
        });
    }
  	
  	*/
  	
</script>
</html>