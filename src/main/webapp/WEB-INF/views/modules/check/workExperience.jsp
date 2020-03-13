<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>基本信息完善</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
		});
		function addJob(){
			if($('tr.tr-length').length>=3){
				return confirmx("最多添加三条工作经历","${ctx}/UserSh/findWork?tpyId=${experience[0].tpyInfoId}");
			}
			$("#myModal").modal();
		}
		function updateJob(id){
			$.ajax({  
		        data: {"id":id},
		        type: "post",  
		        dataType:'json',  
		        url:"${ctx}/UserSh/findOneWork",  
		        success:function(data){
		        	$("#workId").val(data.experience.id);
		        	$("#tpyCorpName1").val(data.experience.tpyCorpName);
		        	$("#d4315").val(data.experience.tpyWorkDate);
		        	$("#d4316").val(data.experience.tpyLeaveDate);
		        	$("#tpyWork1").val(data.experience.tpyWork);
		        	$("#myModal1").modal();
		        		},  
		        error:function(data){  
		            alert("出错了！！");  
		        }  
		       	 });  
		}
		function workExperienceAdd(){
			if($("#tpyCorpName").val()===""||$("#tpyCorpName").val()==null){
				$("#tpyCorpName").focus();
				return;
			}
			if($("#d4313").val()===""||$("#d4313").val()==null){
				$("#d4313").focus();
				return;
			}
			if($("#tpyWork").val()===""||$("#tpyWork").val()==null){
				$("#tpyWork").focus();
				return;
			}
			if($("#d4314").val()===""||$("#d4314").val()==null){
				$("#d4314").focus();
				return;
			}
			$("#addExperience").attr("action","${ctx}/UserSh/addExperience");
			$("#addExperience").submit();
		}
		
		function workExperienceUpdate(){
			if($("#tpyCorpName1").val()===""||$("#tpyCorpName1").val()==null){
				$("#tpyCorpName1").focus();
				return;
			}
			if($("#d4315").val()===""||$("#d4315").val()==null){
				$("#d4315").focus();
				return;
			}
			if($("#tpyWork1").val()===""||$("#tpyWork1").val()==null){
				$("#tpyWork1").focus();
				return;
			}
			if($("#d4316").val()===""||$("#d4316").val()==null){
				$("#d4316").focus();
				return;
			}
			$("#updateExperience").attr("action","${ctx}/UserSh/updateExperience");
			$("#updateExperience").submit();
		}
		 function deleteExperience(delHref){
			 if($('tr.tr-length').length<=1){
				return ("最后一条不能删除","${ctx}/UserSh/findWork?tpyId=${experience[0].tpyInfoId}");
				}
			 return confirmx('确认要删除吗？删除后无法恢复！',delHref)
		 }
		</script>
</head>
<body>
	<ul id="ulWork" class="nav nav-tabs">
			<li><a href="${ctx}/UserSh/baseInfo">基本信息完善</a></li>
 			<li class="active"><a href="${ctx}/UserSh/findWork?tpyId=${experience[0].tpyInfoId}">特派员经历</a></li>
	</ul> 
	<c:if test="${!empty message}">
		<div id ="div" class="alert alert-success alert-dismissible" role="alert">
			 <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			 ${message}
		</div>
	</c:if>
	<div>
		<table class="table table-bordered">
						<thead>
							<tr>
								<th>单位名称</th>
								<th>入职时间</th>
								<th>离职时间</th>
								<th>工作內容</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody class="tbody-job-list">
						<c:forEach items="${experience}" var="experience">
							<tr class="tr-length">
								<td>${experience.tpyCorpName}</td>
								<td>${experience.tpyWorkDate}</td>
								<td>${experience.tpyLeaveDate}</td>
								<td>${experience.tpyWork}</td>
								<td>
									<button type="button" class="btn btn-default btn-xs" onclick="updateJob('${experience.id}')">修改</button>
								 	<!--  <a id="delHref" onclick="deleteExperience('${ctx}/UserSh/deleteExperience?id=${experience.id}')">删除</a>-->
								 </td>
							</tr>
							</c:forEach>
						</tbody>
					</table>
		</div>
		<div class="btgroup form-actions">
				<input id="btnSubmit"  type="submit" class="btn btn-primary" value="添加" onclick="addJob()" >
				<input id="btnCancel"  type="button" class="btn btn-default" value="返回" onclick="history.go(-1)"/>
		</div>
		
					<!-- 添加工作经历 -->
					<form method="post" action="" id="addExperience" >
					<div class="modal fade bs-example-modal-sm" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
					  <div class="modal-dialog" role="document">
					    <div class="modal-content">
					      <div class="modal-header">
					        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					        <h4 class="modal-title" id="myModalLabel">工作经历</h4>
					      </div>
					      <div class="modal-body">
					        <input type="text" name="tpyCorpName"  id="tpyCorpName"
									placeholder="单位名称" required /><span class="help-inline"><font
										color="red">*</font></span>
								<input type="text" name="tpyWorkDate" 
									placeholder="入职时间" id="d4313" class="Wdate" 
									required
									onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd',isShowClear:false,maxDate:'#F{$dp.$D(\'d4314\')||\'%y-%M-%d\'}'})" /><span
									class="help-inline"><font color="red">*</font></span>
								<input type="text" name="tpyWork" placeholder="工作內容" id="tpyWork"
									required /><span class="help-inline"><font color="red">*</font></span>
								<input type="text" name="tpyLeaveDate" 
									placeholder="离职时间" id="d4314" class="Wdate" type="text"
									required
									onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd',isShowClear:false,minDate:'#F{$dp.$D(\'d4313\')}',maxDate:'%y-%M-%d'})" /><span
									class="help-inline"><font color="red">*</font></span>
								<input type="hidden" name="tpyInfoId" value="${experience[0].tpyInfoId}">
					      </div>
					      <div class="modal-footer">
					      <button type="button" class="btn btn-primary" onclick="workExperienceAdd()">保存</button>
					      <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					      </div>
					    </div>
					  </div>
					</div>
					</form>
					<!-- 修改工作经历 -->
					<form method="post" action="" id="updateExperience" >
					<div class="modal fade bs-example-modal-sm" id="myModal1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
					  <div class="modal-dialog" role="document">
					    <div class="modal-content">
					      <div class="modal-header">
					        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					        <h4 class="modal-title" id="myModalLabel">工作经历</h4>
					      </div>
					      <div class="modal-body" id="updateModel">
					       <input type="hidden" name="id" id="workId">
					       <input type="text" name="tpyCorpName"  id="tpyCorpName1" 
									placeholder="单位名称" required /><span class="help-inline"><font
										color="red">*</font></span>
								<input type="text" name="tpyWorkDate"  
									placeholder="入职时间" id="d4315" class="Wdate" type="text"
									required
									onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd',isShowClear:false,maxDate:'#F{$dp.$D(\'d4316\')||\'%y-%M-%d\'}'})" /><span
									class="help-inline"><font color="red">*</font></span>
								<input type="text" name="tpyWork" placeholder="工作內容" id="tpyWork1" 
									required /><span class="help-inline"><font color="red">*</font></span>
								<input type="text" name="tpyLeaveDate" 
									placeholder="离职时间" id="d4316" class="Wdate" type="text" 
									required
									onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd',isShowClear:false,minDate:'#F{$dp.$D(\'d4315\')}',maxDate:'%y-%M-%d'})" /><span
									class="help-inline"><font color="red">*</font></span>
					      </div>
					      <div class="modal-footer">
					      <button type="button" class="btn btn-primary" onclick="workExperienceUpdate()">保存</button>
					      <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					      </div>
					    </div>
					  </div>
					</div>
					</form>
		
</body>
</html>