<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>基本信息</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	$(document).ready(function() {
	
	}); 
	function addMember(teamId){
		 $.ajax({  
	        data:"teamId="+teamId,  
	        type:"post",  
	        dataType:'json',  
	        url:"${ctx}/VillageManage/ajax",  
	        success:function(data){
	        	var items="" ;
	        	 for(var i=0;i<data.user.length;i++){
	        		 if(data.user[i].name==undefined){
	        			 data.user[i].name = ""; 
	        		 }
	        		 if(data.user[i].tpyCompany==undefined){
	        			 data.user[i].tpyCompany = "";
	        		 }
	        		 if(data.user[i].tpyTitle==undefined){
	        			 data.user[i].tpyTitle = "";
	        		 }
	        		 if(data.user[i].tpyMajor==undefined){
	        			 data.user[i].tpyMajor = "";
	        		 }
	        		 if(data.user[i].mobile==undefined){
	        			 data.user[i].mobile = "";
	        		 }
	        		 if(data.user[i].email==undefined){
	        			 data.user[i].email = "";
	        		 }
	        		 
		            	var td1 = "<tr><td>"  + data.user[i].name;
		            	var td2 = "</td><td>" + data.user[i].tpyCompany;
		            	var td3 = "</td><td>" + data.user[i].tpyTitle;
		            	var td4 = "</td><td>" + data.user[i].tpyMajor;
		            	var td5 = "</td><td>" + data.user[i].mobile;
		            	var td6 = "</td><td>" + data.user[i].email;
		            	var td7 = "</td><td><input type='checkbox'  name='userId' value='"+data.user[i].id+"'/></td></tr>";
		            	items = items+td1+td2+td3+td4+td5+td6+td7;
		            }
	            $("#userTable tbody").html("");
	        	$("#userTable tbody").append(items);
	        	$("#addModel").modal();
	        		},  
	        error:function(data){  
	            alert("出错了！！");  
	        }  
	       	 });  
	}
	function  userInfoSearch(teamId){
		var name = $("#name").val();
		var tpyMajor = $("#tpyMajor").val();
		$.ajax({  
	        data: {"name":name,"tpyMajor":tpyMajor,"teamId":teamId},
	        type:"post",  
	        dataType:'json',  
	        url:"${ctx}/VillageManage/findUserOrderList",  
	        success:function(data){
	        	//alert(data.user.length);
	    		//alert(data.user[0].name);
	        	var items="" ;
	            for(var i=0;i<data.user.length;i++){
	            	 if(data.user[i].name==undefined){
	        			 data.user[i].name = ""; 
	        		 }
	        		 if(data.user[i].tpyCompany==undefined){
	        			 data.user[i].tpyCompany = "";
	        		 }
	        		 if(data.user[i].tpyTitle==undefined){
	        			 data.user[i].tpyTitle = "";
	        		 }
	        		 if(data.user[i].tpyMajor==undefined){
	        			 data.user[i].tpyMajor = "";
	        		 }
	        		 if(data.user[i].mobile==undefined){
	        			 data.user[i].mobile = "";
	        		 }
	        		 if(data.user[i].email==undefined){
	        			 data.user[i].email = "";
	        		 }
	            	var td1 = "<tr><td>"  + data.user[i].name;
	            	var td2 = "</td><td>" + data.user[i].tpyCompany;
	            	var td3 = "</td><td>" + data.user[i].tpyTitle;
	            	var td4 = "</td><td>" + data.user[i].tpyMajor;
	            	var td5 = "</td><td>" + data.user[i].mobile;
	            	var td6 = "</td><td>" + data.user[i].email;
	            	var td7 = "</td><td><input type='checkbox'  name='userId' value='"+data.user[i].id+"'/></td></tr>";
	            	items = items+td1+td2+td3+td4+td5+td6+td7;
	            }
	            $("#userTable tbody").html("");
	        	$("#userTable tbody").append(items);
	        	$("#addModel").modal();
	        		},  
	        error:function(data){  
	            alert("出错了！！");  
	        }  
	       	 });  
	}
	function teamMemberAdd(){
		$("#teamMemberAdd").attr("action","${ctx}/VillageManage/teamMemberAdd");
		$("#teamMemberAdd").submit();
	}
	</script>

</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="">团队成员信息维护</a></li>
	</ul>
	<form action=""  method="post" class="form-horizontal">
	<div class="emi-box">
	<c:if test="${!empty message}">
	<div id ="div" class="alert alert-success alert-dismissible" role="alert">
	  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	 ${message}
	</div>
	</c:if> 
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><th class="sort-column name">成员</th><th>单位</th><th>职称</th><th>专业</th><th>手机号码</th><th>email</th><th>成员类型</th><th>操作</th></tr></thead>
		<tbody>
		<c:forEach items="${teamMember}" var="teamMember">
		<tr>
		<td>${teamMember.name}</td>
		<td>${teamMember.tpyCompany}</td>
		<td>${teamMember.tpyTitle}</td>
		<td>${teamMember.tpyMajor}</td>
		<td>${teamMember.mobile}</td>
		<td>${teamMember.email}</td>
		<td>${teamMember.memberType}</td>
		<td>
		<a href="${ctx}/VillageManage/teamMemberDel?id=${teamMember.id}&teamId=${teamId}" onclick="return confirmx('确认要删除吗？删除后无法恢复！', this.href)">删除</a>
		</td>
		</tr>
		</c:forEach>
		</tbody>
	</table>
	</div>
	<div class="form-actions table-bordered-bt">
	<%-- <button type="button" class="btn btn-success btn-xs" onclick="addMember('${teamMember[0].teamId}')">添加</button> --%>
	<input id="btnSubmit" class="btn btn-primary" type="button" value="添加" onclick="addMember('${teamMember[0].teamId}')" />
	<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
	</div>
	</form>
	<form id="teamMemberAdd" action="" method="post" class="form-horizontal">
		<div class="modal fade bs-example-modal-lg" id="addModel"
			tabindex="-1" role="dialog">
			<div class="modal-dialog modal-lg" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title">待选队员列表</h4>
					</div>
					<div id="searchForm"  class="form-search">
						<ul class="ul-form">
							<li><label>姓名:</label><input type="text" class="form-control" id="name" name="name"  placeholder="姓名"></li>
							<li><label>专业:</label><input type="text" class="form-control" id="tpyMajor" name="tpyMajor" placeholder="专业"></li>
							<li class="btns">
							<button type="button" class="btn btn-success btn-xs" onclick="userInfoSearch('${teamId}')">查询</button>
							</li>
							<li class="clearfix"></li>
						</ul>
					</div>
					<div class="modal-body">
						<table id="userTable"
							class="table table-bordered ">
							<thead>
								<tr>
									<th>姓名</th>
									<th>单位</th>
									<th>职称</th>
									<th>专业</th>
									<th>手机</th>
									<th>email</th>
									<shiro:hasPermission name="sys:user:edit">
										<th>操作</th>
									</shiro:hasPermission>
								</tr>
							</thead>
							<tbody>
							</tbody>
						</table>
					</div>
					<input type="hidden" name="teamId" value="${teamId}"/>
					<div class="modal-footer">
						<button type="button" class="btn btn-primary" onclick="teamMemberAdd()">保存</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					</div>
				</div>
			</div>
		</div>
		</form>
</body>
</html>