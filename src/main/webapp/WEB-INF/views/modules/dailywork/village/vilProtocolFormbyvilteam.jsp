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
		<li><a href="${ctx}/dailywork/village/vilProtocol/vilteamlist">待上传团队协议列表</a></li>
		<li><a href="${ctx}/dailywork/village/vilProtocol/yscvilteamlist">已上传团队协议列表</a></li>
		<li class="active"><a>贫困村团队协议添加</a></li>
	</ul><br/>
	
	<!--2019-04-18修改贫困村团协议,原来的提交方法（savevilprotocol） -->
	<form:form id="inputForm" modelAttribute="teamMember" action="${ctx}/dailywork/village/vilProtocol/savevilTprotocol" method="post" class="form-horizontal" enctype="multipart/form-data">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>	
		<table id="contentTable" class="table-form">
		<tbody>
			<tr>
					<td colspan="5"><h4>团队协议信息</h4></td>
			</tr>
			<tr>
				<td class="tit"><lable>贫困村:</lable></td>
				<td>
					<form:input  readonly="true"  path="villageName" htmlEscape="false" maxlength="64" class="input-xlarge"/>
				</td>
				<td class="tit"><lable>特派团:</lable></td>
				<td>
					<form:input  readonly="true"  path="teamName" htmlEscape="false" maxlength="64" class="input-xlarge"/>
				</td>
			</tr>
			<tr>
				<td class="tit"><lable>村书记:</lable></td>
				<td>
					<form:input  readonly="true"  path="secretaryName" htmlEscape="false" maxlength="64" class="input-xlarge"/>
				</td>
				<td class="tit"><lable>村书记电话:</lable></td>
				<td>
					<form:input  readonly="true"  path="secretaryPhone" htmlEscape="false" maxlength="64" class="input-xlarge"/>
				</td>
			</tr>
			<tr>
				<td class="tit"><lable>村联系人:</lable></td>
				<td>
					<form:input  readonly="true"  path="deputy" htmlEscape="false" maxlength="64" class="input-xlarge"/>
				</td>
				<td class="tit"><lable>联系人电话:</lable></td>
				<td>
					<form:input  readonly="true"  path="deputyPhone" htmlEscape="false" maxlength="64" class="input-xlarge"/>
				</td>
			</tr>
			<tr>
				<td class="tit"><lable>特派员:</lable></td>
				<td>
					<form:input  readonly="true"  path="name" htmlEscape="false" maxlength="64" class="input-xlarge"/>
				</td>
				<td class="tit"><lable>联系电话:</lable></td>
				<td>
					<form:input  readonly="true"  path="mobile" htmlEscape="false" maxlength="64" class="input-xlarge"/>
				</td>
			</tr>
			<tr>
				<td class="tit"><lable>团长/团员:</lable></td>
				<td>
					<form:input  readonly="true"  path="memberType" htmlEscape="false" maxlength="64" class="input-xlarge"/>
				</td>
				<td class="tit"><lable></lable></td>
				<td>
					
				</td>
			</tr>
			<tr>
				<td class="tit"><lable>贫困村需求:</lable></td>
				<td colspan="5">
					<form:textarea path="scienceNeed" htmlEscape="false" readonly="true" rows="4" maxlength="1000" style="width:1120px"/>
				</td>
				
			</tr>
			<tr>
				<td class="tit"><lable>模板下载:</lable></td>
				<td colspan="5">
					<br>
					<a href="${ctx}/dailywork/tpy/downloadFile?name=Kjtptfwxy.docx" style="color: red">团队服务协议模板</a><br>
					<br>
				</td>		
			</tr>
			<tr>
				<td class="tit"><lable>团队协议上传:</lable></td>
				<td colspan="5">
					<br>
				    <input   type="file" name="file"  multiple="multiple" class="required" >
					 <br/>
		  
					  <%-- <a href="${ctx}/dailywork/village/vilProtocol/showImage?filename=myImage.jpg"  target="_blank">预览图片</a> --%>
					<c:forEach items="${teamMember.sysAttachmentList}" var="a1">
					勾选删除：
					<input type="checkbox" name="checkbox" value="${a1.attName}">
					<a href="${ctx}/dailywork/village/vilProtocol/showImage?filename=${a1.attOriginname}&fileType=${a1.attFolder}"  target="_blank" id="delAction">${a1.attOriginname}</a></br>
					<%-- <a href="${ctx}/dailywork/village/vilProtocol/deleteImage?filename=${a1.attName}"  target="_blank">删除</a> --%>
					<!-- <a  href="javascript:delFunction('${a1.attName}')">删除</a><br/> -->
					</c:forEach>
					<br>
				</td>
			</tr>
	
		<tbody>
		</table>
		<div hidden class="control-group">
			<label class="control-label">创建者角色：</label>
			<div class="controls">
				<form:input readonly="true" path="villageName" htmlEscape="false" maxlength="64" class="input-xlarge"/>
			</div>
		</div> 	
		<div hidden class="control-group">
			<label class="control-label">特派员id：</label>
			<div class="controls">
				<form:input path="villageName" htmlEscape="false" maxlength="64" class="input-xlarge"/>
			</div>
		</div>
		<div hidden class="control-group">
			<label class="control-label">服务协议审核状态</label>
			<div class="controls">
				<form:input path="villageName" htmlEscape="false" maxlength="64" class="input-xlarge"/>
			</div>
		</div>
		<div hidden class="control-group">
			<label class="control-label">村id：</label>
			<div class="controls">
				<form:input path="villageName" htmlEscape="false" maxlength="64" class="input-xlarge"/>
		</div>
		</div>
	 	<div hidden class="control-group">
			<label class="control-label">帮扶关系id：</label>
			<div class="controls">
				<form:input path="villageName" htmlEscape="false" maxlength="64" class="input-xlarge"/>
			</div>
		</div> 
		<div hidden class="control-group">
			<label class="control-label">订单id：</label>
			<div class="controls">
				<form:input path="villageName" htmlEscape="false" maxlength="64" class="input-xlarge"/>
			</div>
		</div> 
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>