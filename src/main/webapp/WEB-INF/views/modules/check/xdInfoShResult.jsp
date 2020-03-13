<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用户管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/UserRegister/registerResult">审核结果查看</a></li>

		<li><a href="${ctx}/UserRegister/registerUpdate">服务对象信息</a></li>

	</ul><br/>
	<form:form id="inputForm" modelAttribute="user"  method="post" class="form-horizontal">
		<table id="contentTable" class="table  table-bordered  ">
		<tbody>
			<tr>
				<td><lable>单位名称:</lable></td>
				<td>
					${user.name}
				</td>
				<td><lable>单位类型:</lable></td>
				<td>
					 ${fns:getDictLabels(user.corpType,'corp_type',user.corpType)}
				</td>
				<td><lable>所属区域:</lable></td>
				<td>
					${user.office.name}
				</td>
			</tr>
			<tr>
				<td><lable>法定代表人:</lable></td>
				<td>
					${user.corpLegRepName}
				</td>
				<td><lable>电话:</lable></td>
				<td>
					${user.mobile}
				</td>
				<td><lable>统一社会信用代码:</lable></td>
				<td>
					${user.corpOrgCode}
				</td>
			</tr>
			<tr>
				<td><lable>联系人:</lable></td>
				<td>
			        ${user.corpCorName}
			        
				</td>
				<td><lable>电话:</lable></td>
				<td>
					${user.corpCorPhone}
				</td>
				<td><lable>成立日期:</lable></td>
				<td>
					${user.corpEstDate}
				</td>
			</tr>
			<tr>
				<td><lable>注册资本:</lable></td>
				<td>
			        ${user.corpZczb}
				</td>
				<td><lable>Email:</lable></td>
				<td>
					${user.email}
				</td>
				<td><lable>去年收入:</lable></td>
				<td>
			        ${user.corpExIncome}
				</td>
			</tr>
			<tr>
				<td><lable>用工人数:</lable></td>
				<td>
					${user.corpNumWorker}
				</td>
				<td><lable>现有规模:</lable></td>
				<td>
					${user.corpScale}
				</td>
				<td></td>
				<td></td>
			</tr>
			<tr>
				<td><lable>技术需求:</lable></td>
				<td colspan="5">
					${user.corpNeeds}
				</td>
			</tr>
			<tr>
				<td><lable>备注:</lable></td>
				<td colspan="5">
					${user.remarks}
				</td>
			</tr>
			<tr>
				<td>
				<lable>审核人:</lable></td>
				<td>
				<c:if test="${user.checkFlag!='0'}">
				${user.checkPerson}
				</c:if>
				</td>
				<td><lable>审核时间:</lable></td>
				<td>
				<c:if test="${user.checkFlag!='0'}">
				${user.checkTime}
				</c:if>
				</td>
				<td><lable>审核结果:</lable></td>
				<td>
				<c:if test="${user.checkFlag=='0'}">
				审核中
				</c:if>
				<c:if test="${user.checkFlag!='0'}">
				${fns:getDictLabels(user.checkFlag,'check_flag',user.checkFlag)}
				</c:if>
				</td>
		</tr>
		<tr>
				<td>
				<lable>审核意见:</lable></td>
				<td  colspan="5">
				<c:if test="${user.checkFlag!='0'}">
					${user.checkAdvice}
				</c:if>
				</td>
		</tr>
		</tbody>
		</table>
	</form:form>
</body>
</html>