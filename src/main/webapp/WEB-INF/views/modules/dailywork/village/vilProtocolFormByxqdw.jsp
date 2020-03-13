<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>贫困村服务协议管理</title>
	<meta name="decorator" content="default"/>
	
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
		
		
	</script>
	<style type="text/css">
	.delStyle{ text-decoration: line-through; font-weight:100;}
	</style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active">
		<a href="${ctx}/dailywork/village/vilProtocol/xqdwform?id=${vilProtocol.id}">添加贫困村服务协议</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="vilProtocol" action="${ctx}/dailywork/village/vilProtocol/savexqdw" method="post" class="form-horizontal" enctype="multipart/form-data">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>	
		<div hidden class="control-group">
			<label class="control-label">创建者角色：</label>
			<div class="controls">
				<form:input readonly="true" path="vilCreateRole" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div> 	
		<div class="control-group">
			<label class="control-label">村名称：</label>
			<div class="controls">
				<form:input  readonly="true"  path="vilName" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">联系人：</label>
			<div class="controls">
				<form:input  readonly="true"  path="vilContact" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
	
		<div class="control-group">
			<label class="control-label">特派员名称：</label>
			<div class="controls">
				<form:input  readonly="true"  path="vilTpyname" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">村需求：</label>
			<div class="controls">
				<form:textarea readonly="true" path="vilNeeds" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<c:set var="vilProtocol" scope="session" value="${vilProtocol}"/>
		<c:if test="${vilProtocol.fwxystateflag1=='2' }">
		<div class="control-group">
			<label class="control-label">审核建议：</label>
			<div class="controls">
				<form:textarea readonly="true" path="fwxyopinion1" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		</c:if>
		<c:if test="${vilProtocol.fwxystateflag1=='1'||vilProtocol.fwxystateflag1=='0' }">
		<div hidden class="control-group">
			<label class="control-label">审核建议：</label>
			<div class="controls">
				<form:textarea readonly="true" path="fwxyopinion1" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		</c:if>
		<div hidden class="control-group">
			<label class="control-label">特派员id：</label>
			<div class="controls">
				<form:input path="vilTpyid" htmlEscape="false" maxlength="64" class="input-xlarge "/>
		</div>
		</div>
		<div hidden class="control-group">
			<label class="control-label">服务协议审核状态</label>
			<div class="controls">
				<form:input path="fwxystateflag1" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div hidden class="control-group">
			<label class="control-label">村id：</label>
			<div class="controls">
				<form:input path="vilId" htmlEscape="false" maxlength="64" class="input-xlarge "/>
		</div>
		</div>
		
	 	<div hidden class="control-group">
			<label class="control-label">帮扶关系id：</label>
			<div class="controls">
				<form:input path="helpRelationid" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div> 
		<div hidden class="control-group">
			<label class="control-label">订单id：</label>
			<div class="controls">
				<form:input path="ddId" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div> 
		点击下载贫困村服务协议模板：
		  <a href="${ctx}/dailywork/tpy/downloadFile?name=vilTemplate.docx" style="color: red"> 贫困村服务协议模板</a><br>
		上传附件：
		   <input type="file" name="file"  multiple="multiple"> <br/>
		<c:forEach items="${vilProtocol.sysAttachmentList}" var="a1">
		勾选删除：
		<input type="checkbox" name="checkbox" value="${a1.attName}">
		<a href="${ctx}/dailywork/village/vilProtocol/showImage?filename=${a1.attOriginname}&fileType=${a1.attFolder}"  target="_blank" id="delAction">${a1.attOriginname}</a></br>
		</c:forEach>
		
		<div class="form-actions">
			
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>