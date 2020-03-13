<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用户管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
		$("#zpr").focus();			
			$("#inputForm").validate({
			
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox"
			});
		});
		$('selectForm').submit(function() {
  			alert($(this).serialize());
 			 return false;
		});
	</script>
</head>
<body>	
	<ul class="nav nav-tabs">
		<li class="active">服务对象申请特派员信息(自然人)</li>
	</ul>
	<form:form id="inputForm" modelAttribute="sqtpy" action="${ctx}/sqtpy/tpy/saveshtpy" method="post" class="form-horizontal">
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
					<form:input path="xqdwname" htmlEscape="false" readonly="true" maxlength="50" class="required"/>
				</td>
				<td class="tit"><lable>归属区域:</lable></td>
				<td>
					<form:input path="office.name" readonly="true" htmlEscape="false" maxlength="50" class="required"/>
				</td>
				
			</tr>
			<tr>
				<td class="tit"><lable>联系人:</lable></td>
				<td>
					<form:input path="corpcorName" htmlEscape="false" readonly="true" maxlength="50" class="required"/>
				</td>
				<td class="tit"><lable>电话:</lable></td>
				<td>
					<form:input path="xqdwphone" readonly="true" htmlEscape="false" maxlength="50" class="required"/>
				</td>
				
			</tr>
			<tr>
				<td class="tit"><lable>需求信息:</lable></td>
				<td colspan="3">
					<form:textarea path="xqdwsqReason" htmlEscape="false" rows="4" readonly="true" maxlength="1000" style="width:1060px"   class="required"/>
				</td>		
			</tr>
			<tr>
				<td colspan="4"><h4>特派员信息</h4></td>
			</tr>
			<tr>
				<td class="tit"><lable>姓名:</lable></td>
				<td>
					<form:input path="tpyname" readonly="true" htmlEscape="false" class="required" id="tpyxm" maxlength="100" />
				</td>
				<td class="tit"><lable></lable></td>
				<td>				
				</td>
			</tr>
			<tr>
				<td class="tit"><lable>工作单位:</lable></td>
				<td>
					<form:input path="company" readonly="true" htmlEscape="false" class="required" id="gzdw" maxlength="100"/>
				</td>
				<td class="tit"><lable>手机:</lable></td>
				<td>
					<form:input path="mobile" readonly="true" htmlEscape="false" class="required" id="shouji" maxlength="100"/>
				</td>
				
			</tr>
			<tr>
				<td class="tit"><lable>专业:</lable></td>
				<td>
					<form:input path="zy" readonly="true" id="zy" htmlEscape="false" class="required" maxlength="100"/>
				</td>
				<td class="tit"><lable>职称:</lable></td>
				<td>
					<form:input path="zc" readonly="true" id="zc" htmlEscape="false" class="required" maxlength="100"/>
				</td>
				
				
			</tr>
			
			<tr>
				<td class="tit"><lable>特长:</lable></td>
				<td colspan="5">
					<form:textarea path="techspecial" htmlEscape="false" rows="4" readonly="true" maxlength="1000" style="width:1060px" id="zytc"  class="required"/>
				</td>
			</tr>
			<tr>
				
				<td class="tit"><lable>服务开始时间:</lable></td>
				<td>
					<form:input path="starTime" readonly="true" htmlEscape="false" class="required" maxlength="100"/>
				</td>
				<td class="tit"><lable>服务结束时间:</lable></td>
				<td>
					<form:input path="endTime" readonly="true" htmlEscape="false" class="required" maxlength="100"/>
				</td>						
			</tr>
			<tr>
				<td colspan="4"><h4>审核信息</h4></td>
			</tr>			
			<tr>
				<td class="tit"><lable>审核人:</lable></td>
				<td>
					<form:input path="zpr" readonly="true" htmlEscape="false" class="required" maxlength="100"/>
				</td>
				<td class="tit"><lable>审核时间:</lable></td>
				<td>
					<form:input path="zpTime" readonly="true" htmlEscape="false" class="required" maxlength="100"/>
				</td>
				
			</tr>
			<tr>
				<td class="tit"><lable>审核状态:</lable></td>
				<td>
					<form:input path="state" readonly="true" htmlEscape="false" class="required" maxlength="1000"/>
				</td>
				<td class="tit"><lable></lable></td>
				<td>		
				</td>
			</tr>
			<tr>
				<td class="tit"><lable>审核意见:</lable></td>
				<td colspan="3">
					<form:textarea path="zpryj" readonly="true" htmlEscape="false" rows="4" style="width:1060px" maxlength="1000" class="required"/>
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