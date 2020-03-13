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
		<c:if test="${user.checkFlag=='1'}">
		<li><a href="${ctx}/UserRegister/registerUpdate">特派员（法人）信息</a></li>
		</c:if>
		</ul><br/>
	<form:form id="inputForm" modelAttribute="user" action="/kjtpypt/a/UserSh/tpyShResult" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<input id="oldLoginName" name="oldLoginName" type="hidden" value="${user.loginName}" disabled="disabled">
						<form:input path="loginName" htmlEscape="false" maxlength="50" class="required userName" type="hidden"/>
		<table id="contentTable" class="table  table-bordered  ">
			<tbody>
				<tr>
					<td>单位名称:</td>
					<td>
						${user.name}
					</td>
					<td>单位类型:</td>
					<td>
						${fns:getDictLabels(user.corpType,'corp_type',user.corpType)}
					</td>
					<td>所属区域:</td>
					<td>
			            ${user.office.name}
					</td>
				</tr>
				<tr>
					<td>法定代表人:</td>
					<td>
			           ${user.corpLegRepName}
					</td>
					<td>电话:</td>
					<td>
						${user.mobile}
					</td>
					<td>Email:</td>
					<td>
						${user.email}
					</td>
				</tr>
				<tr>
					<td>联系人姓名:</td>
					<td>
			           ${user.corpCorName}
					</td>
					<td>电话:</td>
					<td>
						${user.corpCorPhone}
					</td>
					<td>邮政编码:</td>
					<td>
						${user.tpyPostCode}
					</td>
				</tr>
				<tr>
					<td>统一社会信用代码:</td>
					<td>
			           ${user.corpOrgCode}
					</td>
					<td>单位成立日期:</td>
					<td>
						${user.corpEstDate}
					</td>
					<td>通信地址:</td>
					<td>
						${user.tpyAddress}
					</td>
				</tr>
				<tr>
					<td>银行账号:</td>
					<td>
			           ${user.bankAccount}
					</td>
					<td>开户名:</td>
					<td>
						<!--<form:input path="bankName" htmlEscape="false" maxlength="100" class="required"/>-->
						${user.bankName}
					</td>
					<td>开户行:</td>
					<td>
						${user.bankOpen}
					</td>
				</tr>
				<tr>
					<td>备注</td>
					<td colspan="5">
						${user.remarks}
					</td>
				</tr>
				<tr>
					<td>推荐表图片:</td>
					<td colspan="5">
					<c:if test="${empty imagePathList}">
					<img alt="暂无图片" src="" /> <br/>  
					</c:if>
					<c:if test="${imagePathList==null}">
					<img alt="暂无图片" src="" /> <br/>  
					</c:if>
					<c:if test="${!empty imagePathList}">
					<c:forEach items="${imagePathList}" var="imagename">  
	                <img alt="暂无图片" src="/tjImage${imagename}" style="width:400px;height:200px"/> <br/>  
	        		</c:forEach>  
					</c:if>
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