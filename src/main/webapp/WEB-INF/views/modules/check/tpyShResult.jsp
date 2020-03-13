<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用户管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/UserRegister/registerResult">审核结果查看</a></li>
		<!--<c:if test="${user.checkFlag=='1'}">-->
		<li><a href="${ctx}/UserRegister/registerUpdate">特派员（自然人）信息</a></li>
		<!--</c:if>-->
		</ul><br/>
	<form:form id="inputForm" modelAttribute="user"  method="post" class="form-horizontal">
		<sys:message content="${message}"/>
		<table id="contentTable" class="table  table-bordered">
		<tbody>
			<tr>
				<td>姓名:</td>
				<td>
			        ${user.name}
				</td>
				<td>所属区域:</td>
				<td>
					${user.office.name}
				</td>
				<td>籍贯:</td>
				<td>
					${user.tpyLocation}
				</td>
			</tr>
			<tr>
				<td>工作单位名称:</td>
				<td>
			        ${user.tpyCompany}
				</td>
				<td>部门:</td>
				<td>
					${user.tpyDept}
				</td>
				<td>职位:</td>
				<td>
					${user.tpyPosition}
				</td>
			</tr>
			
			<tr>
				<td>学历:</td>
				<td>
			        ${fns:getDictLabels(user.tpyQulification,'tpy_qulification',user.tpyQulification)}
				</td>
				<td>身份证号:</td>
				<td>
					${user.tpyIdcard}
				</td>
				<td>Email:</td>
				<td>
					${user.email}
				</td>
			</tr>
			<tr>
				<td>座机:</td>
				<td>
			        ${user.phone}
				</td>
				<td>手机:</td>
				<td>
					${user.mobile}
				</td>
				<td>参加工作时间:</td>
				<td>
					${user.tpyBeginWorkDate}
				</td>
			</tr>
			<tr>
				<td>专业:</td>
				<td>
			        ${user.tpyMajor}
				</td>
				
				<td>职称:</td>
				<td>
					${fns:getDictLabels(user.tpyTitle,'tpy_title',user.tpyTitle)}
				</td>
				<td></td>
				<td>
				</td>
			</tr>
			<tr>
				<td>特长:</td>
				<td colspan="5">
					${user.tpySpecial}
				</td>
			</tr>
			<tr>
				<td>通信地址:</td>
				<td colspan="5">
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
					${user.bankName}
				</td>
				<td>邮编:</td>
				<td>
					${user.tpyPostCode}
				</td>
			</tr>
			<tr>
				<td>开户行:</td>
				<td colspan="5">
					${user.bankOpen}
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
                <img alt="暂无图片" src="/tjImage${imagename}" style="width:500px;height:600px"/> 
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