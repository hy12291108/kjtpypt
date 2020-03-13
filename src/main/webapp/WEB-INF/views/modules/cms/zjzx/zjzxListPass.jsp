<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>文章管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	$(document).ready(function() {
		var message = $("#message").val(); 
		if(message!=null&&message!=''){
			document.getElementById("messageBox").style.display="";
		};
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
		<li class="active"><a href="${ctx}/cms/article/zjzxListPass">专家咨询列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="article" action="${ctx}/cms/article/zjzxListPass" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
	<sys:message content="${message}"/>
	<input type="hidden" value="${message}" id="message">
	<table id="contentTable" class="table table-bordered ">
		<thead><tr><th>栏目</th><th>标题</th><th>发布者</th><th>发布时间</th><th>操作</th></tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="article">
			<tr>
				<td>${article.category.name}</td>
				<td>${fns:abbr(article.title,40)}</td>
				<td>${article.user.name}</td>
				<td><fmt:formatDate value="${article.createDate}" type="both"/></td>
				<td>
					<a href="${ctx}/cms/article/zjzxReply?id=${article.id}">回复</a>
					<a href="/kjtpypt/f/commentList?id=${article.id}">查看</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	</form:form>
	<div class="pagination">${page}</div>
</body>
</html>