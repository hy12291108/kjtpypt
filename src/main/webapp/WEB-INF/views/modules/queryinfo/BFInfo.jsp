<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>审批管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#name").focus();
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
</head>
<body>
	<form:form class="form-horizontal">
		<sys:message content="${message}"/>
		<table id="contentTable" class="mytable">
		<tbody>
			<tr>
				<td colspan="4"><h4>服务对象信息</h4></td>
			</tr>
			<tr>
				<td><lable>服务对象:</lable></td>
				<td>
					${xqdwuser.name}
				</td>
				<td><lable>统一社会信用代码:</lable></td>
				<td>
					${xqdwuser.corpOrgCode}
				</td>
				
			</tr>
			<tr>
				<td><lable>成立日期:</lable></td>
				<td>
					${xqdwuser.corpEstDate}
				</td>
				<td><lable>注册资本:</lable></td>
				<td>
					${xqdwuser.corpZczb}
				</td>
				
			</tr>
			<tr>
				<td><lable>法定代表人:</lable></td>
				<td>
					${xqdwuser.corpLegRepName}
					
				</td>
				<td><lable>电话:</lable></td>
				<td>
					${xqdwuser.mobile}
				</td>
				
			</tr>
			<tr>
				<td><lable>联系人:</lable></td>
				<td>
					${xqdwuser.corpCorName}
					
				</td>
				<td><lable>电话:</lable></td>
				<td>
					${xqdwuser.corpCorPhone}
				</td>
				
			</tr>
			<tr>
				<td><lable>公司人数:</lable></td>
				<td>
					${xqdwuser.corpNumWorker}
					
				</td>
				<td><lable>主营业务:</lable></td>
				<td>
					${xqdwuser.corpMajor}
				</td>
				
			</tr>

			<tr>
				<td colspan="4"><h4>特派员信息</h4></td>
			</tr>

			<tr>
				<td><lable>姓名:</lable></td>
				<td>
					${tpyuser.name}
				</td>
				<td><lable>性别:</lable></td>
				<td>
					${fns:getDictLabels(tpyuser.sex,'sex',tpyuser.sex)}
				</td>
				
			</tr>
			<tr>
				<td><lable>学历:</lable></td>
				<td>
					${tpyuser.tpyQulification}
				</td>
				<td><lable>出生年月:</lable></td>
				<td>
					${tpyuser.tpyBirthDate}
				</td>
				
			</tr>
			<tr>
				<td><lable>工作单位:</lable></td>
				<td>
					${tpyuser.tpyCompany}
				</td>
				<td><lable>职称:</lable></td>
				<td>
					${tpyuser.tpyTitle}
				</td>
				
			</tr>
			<tr>
				<td><lable>专业:</lable></td>
				<td>
					${tpyuser.tpyMajor}
				</td>
				<td><lable>联系方式:</lable></td>
				<td>
					${tpyuser.mobile}
				</td>
				
			</tr>
			<tr>
				<td><lable>专业特长:</lable></td>
				<td colspan="3">
					${tpyuser.tpySpecial}
				</td>
				
				
			</tr>
			<tr>
				<td><lable>通信地址:</lable></td>
				<td colspan="3">
					${tpyuser.tpyAddress}
				</td>
				
				
			</tr>
			
			<tr>
				<td colspan="4"><h4>帮扶信息</h4></td>
			</tr>
			<tr>
				<td><lable>帮扶开始时间:</lable></td>
				<td>
					${sqtpy.starTime}
					
				</td>
				<td><lable>帮扶结束时间:</lable></td>
				<td>
					${sqtpy.endTime}
				</td>
				
			</tr>
			<tr>
				<td><lable>需求信息:</lable></td>
				<td colspan="3">
					${sqtpy.xqdwsqReason}
				</td>
			</tr>
			
			<tr>
				<td><lable>审核人姓名:</lable></td>
				<td>
					${sqtpy.zpr}
					
				</td>
				<td><lable>审核时间:</lable></td>
				<td>
					${sqtpy.zpTime}
				</td>
				
			</tr>
			<tr>
				<td><lable>审核意见:</lable></td>
				<td colspan="3">
					${sqtpy.zpryj}
				</td>
			</tr>
		  <tbody>
		</table>
		<!--  <fieldset>
			<legend>特派员信息</legend>
			<table class="table-form">
				<tr>
					<td class="tit">姓名</td><td colspan="2">${tpyuser.name}</td>
					<td class="tit">性别</td><td colspan="2">${tpyuser.sex}</td>
				</tr>
				<tr>
					<td class="tit">出生年月</td><td colspan="2">${tpyuser.tpyBirthDate}</td>
					<td class="tit">学历</td><td colspan="2">${tpyuser.tpyQulification}</td>
				</tr>
				<tr>				
					<td class="tit">职称</td><td colspan="2">${tpyuser.tpyTitle}</td>
					<td class="tit">工作单位</td><td colspan="2">${tpyuser.tpyCompany}</td>
				</tr>
				<tr>		
					<td class="tit">专业</td><td colspan="2">${tpyuser.tpyMajor}</td>
					<td class="tit">专业特长</td><td colspan="2">${tpyuser.tpySpecial}</td>
				</tr>
				<tr>
					<td class="tit">个人住址</td><td colspan="2">${tpyuser.tpyAddress}</td>
					<td class="tit">联系方式</td><td colspan="2">${tpyuser.mobile}</td>
				</tr>			
			</table>
		</fieldset>
		<fieldset>
			<legend>需求单位信息</legend>
			<table class="table-form">
				<tr>
					<td class="tit">需求单位名称</td><td colspan="2">${xqdwuser.name}</td>
					<td class="tit">组织机构代码</td><td colspan="2">${xqdwuser.corpOrgCode}</td>				
				</tr>
				<tr>
					<td class="tit">单位成立日期</td><td colspan="2">${xqdwuser.corpEstDate}</td>
					<td class="tit">注册资本</td><td colspan="2">${xqdwuser.corpZczb}</td>
				</tr>
				<tr>
					<td class="tit">法人姓名</td><td colspan="2">${xqdwuser.corpLegRepName}</td>
					<td class="tit">法人联系电话</td><td colspan="2">${xqdwuser.mobile}</td>
				</tr>
				<tr>
					<td class="tit">联系人姓名</td><td colspan="2">${xqdwuser.corpCorName}</td>
					<td class="tit">联系人联系电话</td><td colspan="2">${xqdwuser.corpCorPhone}</td>
				</tr>
				<tr>
					<td class="tit">公司人数</td><td colspan="2">${xqdwuser.corpNumWorker}</td>
					<td class="tit">主营业务</td><td colspan="2">${xqdwuser.corpMajor}</td>
				</tr>
			</table>
		</fieldset>
		<fieldset>
			<legend>帮扶信息</legend>
			<table class="table-form">
				<tr>
					<td class="tit">帮扶开始时间</td><td colspan="2">${sqtpy.starTime}</td>
					<td class="tit">帮扶结束时间</td><td colspan="2">${sqtpy.endTime}</td>				
				</tr>
				<tr>
					<td class="tit">服务需求</td><td colspan="5">${sqtpy.xqdwsqReason}</td>				
				</tr>
				<tr>
					<td class="tit">审核人姓名</td><td colspan="2">${sqtpy.zpr}</td>
					<td class="tit">审核时间</td><td colspan="2">${sqtpy.zpTime}</td>
				</tr>
				<tr>
					<td class="tit">审核人意见</td><td colspan="5">${sqtpy.zpryj}</td>		
				</tr>
			</table>
		</fieldset>-->
		<div class="form-actions">
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)" />
		</div>
	</form:form>
</body>
</html>
