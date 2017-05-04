<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String id = request.getParameter("id");
%>
<%@ include file="/jsp/common/headlocal.jsp" %>
<html>
<head>
	<title></title>
</head>
<body>
	<div class="panel panel-default">
		<div class="panel-body">
			<div class="container" style="margin:0">
				<form id="info-form" class="form-horizontal" role="form" style="padding:15px 10px;">
					<input type="hidden" name="id" value="">
				   <div class="form-group">
				      <label for="firstname" class="col-md-2 control-label">标题</label>
				      <div class="col-md-4">
				         <input type="text" class="form-control" id="title" name="title" value="" placeholder="请输入标题">
				      </div>
				      <label for="firstname" class="col-md-2 control-label">日期</label>
				      <div class="col-md-4">
				         <input type="text" class="form-control" id="regtime" name="regtime" value="" placeholder="请输入日期" readonly>
				      </div>
				   </div>
				   <div class="form-group">
				      <label for="lastname" class="col-md-2 control-label">描述</label>
				      <div class="col-md-10">
				         <input type="text" class="form-control" id="note" name="note" value="" placeholder="请输入描述">
				      </div>
				   </div>
				   <div class="form-group">
				      <div class="col-md-offset-2 col-md-10">
				         <a href="javascript:void(0)" id="savbtn" class="btn btn-danger" onclick="saveData()" >保存</a>
				         <a href="javascript:void(0)" id="celbtn" class="btn btn-default" onclick="doReset()" >重置</a>
				      </div>
				   </div>
				</form>
			</div>
		</div>
	</div>
</body>
	<script type="text/javascript">
		$(document).ready(function(){
			$('#regtime').datepicker();
			if(<%=null != id && !"".equals(id) %>){
				$.ajax({
					type : "POST",
					url : contextPath+'/demo.do?method=getdemoobj',
					data : {id:'<%=id %>'},
					dataType : 'json',
					async : false,
					error:function(request){
					},
					success : function(result){
						if(result){
							$("#info-form :input").each(function(i,e){
								$(e).val(result[$(e).attr("name")]);
							});
						}
					}
				});
			}
		});
		
		function saveData(){
			$.ajax({
				type : "POST",
				url : contextPath+'/demo.do?method=savedemoobj',
				data : $("form").serialize(),
				dataType : 'json',
				async : false,
				error:function(request){
				},
				success : function(result){
					if(result.success){
						alert("保存成功!");
						parent.changeMenu(1);
					}
				}
			});
		}
		
		function doReset(){
			$("#info-form")[0].reset();
		}
	</script>
</html>