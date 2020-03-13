<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>待办发文管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/dbfw/jjbgWddb/">待办发文列表</a></li>
		<shiro:hasPermission name="dbfw:jjbgWddb:edit"></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="jjbgGwdj" action="${ctx}/dbfw/jjbgWddb/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>题目：</label>
				<form:input path="title" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>题目</th>
				<shiro:hasPermission name="dbfw:jjbgWddb:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="jjbgGwdj">
			<tr>
				<td><a href="${ctx}/dbfw/jjbgWddb/form?id=${jjbgGwdj.id}">
					${jjbgGwdj.title}
				</a></td>
				<shiro:hasPermission name="dbfw:jjbgWddb:edit"><td>
    				<a href="${ctx}/dbfw/jjbgWddb/form?id=${jjbgGwdj.id}">修改</a>
					<a href="${ctx}/dbfw/jjbgWddb/delete?id=${jjbgGwdj.id}" onclick="return confirmx('确认要删除该待办发文吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>