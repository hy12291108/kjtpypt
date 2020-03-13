<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>特派员工作日志管理</title>
<meta name="decorator" content="default" />
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/dailywork/tpy/tpyProtocolView?id=${tpyProtocol.id}">查看我的特派员服务协议</a></li>
	</ul>
		<form:form modelAttribute="tpyProtocol" class="form-horizontal" >
		<div class="control-group">
			<label class="control-label">姓名：</label>
			<div class="controls">
			<form:input readonly="true" path="user.name" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">电话：</label>
			<div class="controls">
			<form:input readonly="true"  path="user.phone" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">专业：</label>
			<div class="controls">
			<form:input readonly="true"  path="user.tpyMajor" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">工作单位：</label>
			<div class="controls">
			<form:input readonly="true"  path="user.tpyCompany" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:textarea readonly="true" path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>	
		附件：</br>
		<c:forEach items="${tpyProtocol.sysAttachmentList}" var="a1">
					<a href="${ctx}/dailywork/village/vilProtocol/showImage?filename=${a1.attName}&fileType=${a1.attFolder}" target="_blank">${a1.attOriginname}</a></br>
		</c:forEach>
		<div class="form-actions">
				<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)" />
		</div>
		</form:form>
</body>
</html>