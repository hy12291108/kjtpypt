<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
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
		<li><a href="${ctx}/dailywork/village/vilProtocol/">待上传服务对象协议列表</a></li>
		<li><a href="${ctx}/dailywork/village/vilProtocol/list">已上传服务对象协议列表</a></li>
		<li class="active"><a>服务对象协议添加</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="vilProtocol" action="${ctx}/dailywork/village/vilProtocol/save" method="post" class="form-horizontal" enctype="multipart/form-data">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>	
		<table id="contentTable" class="table-form">
		<tbody>
			<tr>
				<td colspan="4"><h4>服务对象信息</h4></td>
			</tr>
			<tr>
				<td class="tit"><lable>服务对象:</lable></td>
				<td>
					<form:input  readonly="true"  path="vilName" htmlEscape="false" maxlength="64" class="input-xlarge "/>
				</td>
				<td class="tit"><lable>类型:</lable></td>
				<td>
					需求单位
				</td>
			</tr>
			<tr>
				<td class="tit"><lable>联系人:</lable></td>
				<td>
					<form:input  readonly="true"  path="vilContact" htmlEscape="false" maxlength="64" class="input-xlarge "/>
				</td>
				<td class="tit"><lable>联系电话:</lable></td>
				<td>
					<form:input  readonly="true"  path="vilContactphone" htmlEscape="false" maxlength="64" class="input-xlarge "/>
				</td>
			</tr>
			<tr>
				<td colspan="4"><h4>特派员信息</h4></td>
			</tr>
			<tr>
				<td class="tit"><lable>特派员:</lable></td>
				<td>
					<form:input  readonly="true"  path="vilTpyname" htmlEscape="false" maxlength="64" class="input-xlarge "/>
				</td>
				<td class="tit"><lable>联系电话:</lable></td>
				<td>
					<form:input  readonly="true"  path="vilTpyphone" htmlEscape="false" maxlength="64" class="input-xlarge "/>
				</td>
			</tr>
			<tr>
				<td class="tit"><lable>需求信息:</lable></td>
				<td colspan="5">
					<form:textarea path="vilNeeds" htmlEscape="false" readonly="true" rows="4" maxlength="1000" style="width:1130px"/>
				</td>
				
			</tr>
			<tr>
				<td class="tit"><lable>模板下载:</lable></td>
				<td colspan="5">
					<br>
					<a href="${ctx}/dailywork/tpy/downloadFile?name=Frkjtpyfwxymb.doc" style="color: red">法人科技特派员服务协议模板</a><br>
					<br>
				    <a href="${ctx}/dailywork/tpy/downloadFile?name=Zrrkjtpyfwxymb.doc" style="color: red">自然人科技特派员服务协议模板</a>
				    <br> <br>
				</td>
				
			</tr>
			<tr>
				<td class="tit"><lable>上传文件:</lable></td>
				<td colspan="5">
				<br>
					<input   type="file" name="file"  multiple="multiple" class="required"> <br/>
		  
					  <%-- <a href="${ctx}/dailywork/village/vilProtocol/showImage?filename=myImage.jpg"  target="_blank">预览图片</a> --%>
					<c:forEach items="${vilProtocol.sysAttachmentList}" var="a1">
					勾选删除：
					<input type="checkbox" name="checkbox" value="${a1.attName}">
					<a href="${ctx}/dailywork/village/vilProtocol/showImage?filename=${a1.attOriginname}&fileType=${a1.attFolder}"  target="_blank" id="delAction">${a1.attOriginname}</a></br>
					<%-- <a href="${ctx}/dailywork/village/vilProtocol/deleteImage?filename=${a1.attName}"  target="_blank">删除</a> --%>
					<!-- <a  href="javascript:delFunction('${a1.attName}')">删除</a><br/> -->
					</c:forEach>
					<br>
				</td>
			</tr>
			<c:set var="vilProtocol" scope="session" value="${vilProtocol}"/>
		<c:if test="${vilProtocol.fwxystateflag=='2' }">
		<tr>
				<td><lable>审核建议:</lable></td>
				<td colspan="5">
					<form:textarea path="fwxyopinion" htmlEscape="false" readonly="true" rows="4" maxlength="1000" style="width:1130px"/>
				</td>
				
		</tr>
		</c:if>
		<c:if test="${vilProtocol.fwxystateflag=='1'||vilProtocol.fwxystateflag=='0' }">
		<div hidden class="control-group">
			<label class="control-label">审核建议：</label>
			<div class="controls">
				<form:textarea readonly="true" path="fwxyopinion" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		</c:if>
		<tbody>
		</table>
		<div hidden class="control-group">
			<label class="control-label">创建者角色：</label>
			<div class="controls">
				<form:input readonly="true" path="vilCreateRole" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div> 	
		<div hidden class="control-group">
			<label class="control-label">特派员id：</label>
			<div class="controls">
				<form:input path="vilTpyid" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div hidden class="control-group">
			<label class="control-label">服务协议审核状态</label>
			<div class="controls">
				<form:input path="fwxystateflag" htmlEscape="false" maxlength="64" class="input-xlarge "/>
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
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>