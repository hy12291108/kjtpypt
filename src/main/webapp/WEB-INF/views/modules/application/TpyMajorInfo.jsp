<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用户管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {			
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox"
			});
		});
	</script>
</head>
<body>	
<ul class="nav nav-tabs">
		<li><a href="${ctx}/sqtpy/tpy/list">申请自然人特派员</a></li>
		<li><a href="${ctx}/sqtpy/tpy/frlist">申请法人特派员</a></li>
		<li class="active"><a>按专业申请特派员</a></li>
	</ul>
	<form:form id="inputForm" modelAttribute="user" action="${ctx}/sqtpy/tpy/SaveSqtpybymajor" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="personFlag"/>
		<sys:message content="${message}"/>	
				<table id="contentTable" class="table-form">
		<tbody>
			<tr>
				<td colspan="5"><h4>请选择相应信息</h4></td>
			</tr>
			<tr>
				<td class="tit"><lable>专业类型:</lable></td>
				<td>
					<form:select path="tpyMajor" maxlength="50" class="input-xlarge" style="width:220px">
					<form:options items="${tpymajorlist}" />				
				</form:select>
				</td>
				<td class="tit"><lable>类型:</lable></td>
				<td>
					自然人特派员
				</td>		
			</tr>
			<tr>
				<td class="tit"><lable>服务开始时间:</lable></td>
				<td>
					<input id="starTime" name="starTime"  class="required" type="text" readonly="readonly" maxlength="100" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
				<span class="help-inline"><font color="red">*</font> </span>
				</td>
				<td class="tit"><lable>服务结束时间:</lable></td>
				<td>
					<input id="endTime" name="endTime" type="text"  class="required" readonly="readonly" maxlength="100"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
				<span class="help-inline"><font color="red">*</font> </span>
				</td>		
			</tr>
			<tr>
				<td class="tit"><lable>需求信息:</lable></td>
				<td colspan="5">
				
					<form:textarea path="tpyReason" htmlEscape="false" rows="4" style="width:1060px" maxlength="999" class="required"/>
					<span class="help-inline"><font color="red">*</font> </span>
				</td>
				
				
			</tr>
		<tbody>
		</table>			
		<!--  <div class="control-group">
			<label class="control-label">专业:</label>
			<div class="controls">
				<form:select path="tpyMajor" maxlength="50" class="input-xlarge">
					<form:options items="${tpymajorlist}" />				
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">服务开始时间:</label>
			<div class="controls">
				<input id="starTime" name="starTime" type="text" class="input-xlarge" readonly="readonly" maxlength="100" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>	
		<div class="control-group">
			<label class="control-label">服务结束时间:</label>
			<div class="controls">
				<input id="endTime" name="endTime" type="text" class="input-xlarge"  readonly="readonly" maxlength="100"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>	
		<div class="control-group">
			<label class="control-label">需求单位的申请理由:</label>
			<div class="controls">
				<form:input path="tpyReason"  htmlEscape="false"  maxlength="300" class="input-xlarge" />
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>	-->
		<div class="form-actions table-bordered-bt">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>