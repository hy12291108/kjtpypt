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
		<li class="active"><a>团队服务协议查看(贫困村)</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="teamMember" action="${ctx}/dailywork/village/vilProtocol/saveshvilprotocolByteam" method="post" class="form-horizontal" enctype="multipart/form-data">
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
					<form:input  readonly="true"  path="villageName" htmlEscape="false" maxlength="64" class="input-xlarge "/>
				</td>
				<td class="tit"><lable>特派团:</lable></td>
				<td>
					<form:input  readonly="true"  path="teamName" htmlEscape="false" maxlength="64" class="input-xlarge "/>
				</td>
			</tr>
			<tr>
				<td class="tit"><lable>村书记:</lable></td>
				<td>
					<form:input  readonly="true"  path="secretaryName" htmlEscape="false" maxlength="64" class="input-xlarge "/>
				</td>
				<td class="tit"><lable>村书记电话:</lable></td>
				<td>
					<form:input  readonly="true"  path="secretaryPhone" htmlEscape="false" maxlength="64" class="input-xlarge "/>
				</td>
			</tr>
			<tr>
				<td class="tit"><lable>村联系人:</lable></td>
				<td>
					<form:input  readonly="true"  path="deputy" htmlEscape="false" maxlength="64" class="input-xlarge "/>
				</td>
				<td class="tit"><lable>联系人电话:</lable></td>
				<td>
					<form:input  readonly="true"  path="deputyPhone" htmlEscape="false" maxlength="64" class="input-xlarge "/>
				</td>
			</tr>
			<tr>
				<td class="tit"><lable>特派员:</lable></td>
				<td>
					<form:input  readonly="true"  path="name" htmlEscape="false" maxlength="64" class="input-xlarge "/>
				</td>
				<td class="tit"><lable>联系电话:</lable></td>
				<td>
					<form:input  readonly="true"  path="mobile" htmlEscape="false" maxlength="64" class="input-xlarge "/>
				</td>
			</tr>
			<tr>
				<td class="tit"><lable>团长/团员:</lable></td>
				<td>
					<form:input  readonly="true"  path="memberType" htmlEscape="false" maxlength="64" class="input-xlarge "/>
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
				<td class="tit"><lable>服务协议:</lable></td>
				<td colspan="5">
					<br>
					<c:forEach items="${teamMember.sysAttachmentList}" var="a1">
					<a href="${ctx}/dailywork/village/vilProtocol/showImage?filename=${a1.attName}&fileType=${a1.attFolder}"  target="_blank" id="delAction">${a1.attOriginname}</a></br></br>
					</c:forEach>
					<br>
				</td>
			</tr>
			<tr>
				<td colspan="5"><h4>审核信息</h4></td>
			</tr>
			<tr>			
				<td class="tit"><lable>审核人:</lable></td>
				<td>
					<form:input  readonly="true" path="checkPerson" htmlEscape="false" maxlength="64" class="input-xlarge"/>
				</td>
				<td class="tit"><lable>审核时间:</lable></td>
				<td>
					<form:input  readonly="true" path="checkTime" htmlEscape="false" maxlength="64" class="input-xlarge"/>
				</td>
			</tr>
			<tr>	
				<td class="tit"><lable>审核状态:</lable></td>
				<td>
					<form:input  readonly="true" path="teamprotocolFlag" htmlEscape="false" maxlength="64" class="input-xlarge"/>
				</td>			
				<td class="tit"><lable></lable></td>
				<td>
				</td>
			</tr>
			<tr>
				<td class="tit"><lable>审核意见:</lable></td>
				<td colspan="5">
					<form:textarea path="checkOpinion" readonly="true" htmlEscape="false" rows="4" maxlength="1000" style="width:1120px" class="required"/>
				</td>
			</tr>
		<tbody>
		</table>	
		<div class="form-actions">
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>