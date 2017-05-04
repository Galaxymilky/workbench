<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@ include file="/jsp/common/headlocal.jsp" %>
<html>
<head>
	<title></title>
	<script type="text/javascript">
		var table	= null;
		var selected = [];
		$(document).ready(function(){
			loadData();
		});
		
		function loadData(){
			$('#example').dataTable({
				"language": {
					"url": contextPath + "/js/common/i18n/Chinese.json"
                },
                "select" : {
                    style:     "os",
                    className: "row-selected"
                },
                "columnDefs": [
					{ "data": "title", "targets": 0},
					{ "data": "regtime", "targets": 1},
					{
	                    targets: 2,
	                    render: function (a, b, c, d) {
	                    	var btnhtml = "<button class=\"btn btn-primary btn-xs\" onclick=\"modifyData('"+c.id+"')\" >修改</button>";
	                    	btnhtml += "&nbsp;<button class=\"btn btn-danger btn-xs\" onclick=\"deleteData('"+c.id+"')\" >删除</button>";
	                        return btnhtml;
	                    }
	                }
				],                
                "paging": true,//是否分页
                "lengthMenu": [ 10, 25, 50, 75, 100 ],//分页长度列表
                "pageLength": 10,//每页多少条
                "lengthChange": true,//允许改变分页长度
		        "pagingType": "full_numbers",
		        "processing": true,
		        "serverSide": true,//服务端处理
		        "ajax": {
		            "url" : contextPath+"/demo.do?method=querydata",
		            "type" : "POST",
		            "dataType" : "json"
		        },
		        "stateSave" : false	//保存状态
		        //"order": [[ 1, "desc" ]]
                ,rowId: 'id'//数据标识,
				//"dom": '<"toolbar">'
			});
			$("div.toolbar").html('<b style="color:red">自定义文字、图片等等</b>');
			table = $('#example').DataTable();
			/*
			var table = $('#example').DataTable();
			var lastIdx = null;
			
		    $('#example tbody').on( 'click', 'tr', function () {
		        if ( $(this).hasClass('selected') ) {
		            $(this).removeClass('selected');
		        }
		        else {
		            table.$('tr.selected').removeClass('selected');
		            $(this).addClass('selected');
		        }
		    } );
			
			$('#example tbody').on( 'mouseover', 'td', function () {
	            var colIdx = table.cell(this).index().column;
	            if ( colIdx !== lastIdx ) {
	                $( table.cells().nodes() ).removeClass('highlight');
	                $( table.column( colIdx ).nodes() ).addClass('highlight');
	            }
	        }).on( 'mouseleave', function () {
	            $( table.cells().nodes() ).removeClass('highlight');
	        });
			*/
		}
		function deleteData(id){
			$.ajax({
				type : "POST",
				url : contextPath+'/demo.do?method=deletedemoobj',
				data : {ids:id},
				dataType : 'json',
				async : false,
				error:function(request){
				},
				success : function(result){
					if(result.success){
						alert("删除成功!");
						table.ajax.reload();
					}
				}
			});
		}
		function modifyData(id){
			window.location.href = contextPath + "/jsp/demo/form.jsp?id="+id;
		}
	</script>
</head>
<body>
	<div class="panel panel-default">
		<div class="panel-body">
			<table id="example" class="table table-striped table-bordered" cellspacing="0" width="100%">
				<thead>
					<tr>
						<th>标题</th>
						<th>时间</th>
						<th style="width:60px"></th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
</body>
</html>