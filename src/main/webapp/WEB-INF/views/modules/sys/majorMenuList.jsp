<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>专业菜单管理</title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/treetable.jsp" %>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
    	
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/sys/majorMenu/list">一级专业列表</a></li>
		<li><a href="${ctx}/sys/majorMenu/add1">一级专业添加</a></li>
	</ul>
	<sys:message content="${message}"/>
	<%-- <form:form id="searchForm" modelAttribute="user" action="${ctx}/sys/user/findListByAdmin" method="post" class="breadcrumb form-search ">
		
		<ul class="ul-form">
			<li><label>归属公司：</label><sys:treeselect id="company" name="company.id" value="${user.company.id}" labelName="company.name" labelValue="${user.company.name}" 
				title="公司" url="/sys/office/treeData?type=1" cssClass="input-small" allowClear="true"/></li>
			<li><label>登录名：</label><form:input path="loginName" htmlEscape="false" maxlength="50" class="input-medium"/></li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" onclick="return page();"/>
				<input id="btnExport" class="btn btn-primary" type="button" value="导出"/>
				<input id="btnImport" class="btn btn-primary" type="button" value="导入"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form> --%>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><th>名称</th><th>一级专业/二级专业</th><th>操作</th></tr></thead>
		<tbody>
		<c:forEach items="${majorList}" var="major">
				<tr>
					<td>${major.name}</td>
					<td>
					<c:if test="${major.menuFlag=='0'}">
					一级专业
					</c:if>
					</td>
					<shiro:hasPermission name="sys:user:edit"><td>
					<a href="${ctx}/sys/majorMenu/selectSecond?id=${major.id}">查看</a>
					<a href="${ctx}/sys/majorMenu/delete?id=${major.id}" onclick="return confirmx('确认要删除该条信息吗？删除后无法回复！',this.href)">删除</a>
					<c:if test="${major.menuFlag=='0'}">
					<a href="${ctx}/sys/majorMenu/add2?id=${major.id}">添加下级菜单</a>
					</c:if>
					</td></shiro:hasPermission>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	
</body>
</html>