<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>文章管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		function viewComment(href){
			top.$.jBox.open('iframe:'+href,'查看评论',$(top.document).width()-220,$(top.document).height()-120,{
				buttons:{"关闭":true},
				loaded:function(h){
					$(".jbox-content", top.document).css("overflow-y","hidden");
					$(".nav,.form-actions,[class=btn]", h.find("iframe").contents()).hide();
					$("body", h.find("iframe").contents()).css("margin","10px");
				}
			});
			return false;
		}
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
		<li class="active"><a href="${ctx}/cms/article/zjzxList">专家咨询列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="article" action="${ctx}/cms/article/zjzxList" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<%-- <label>标题：</label><form:input path="title" htmlEscape="false" maxlength="50" class="input-small"/>&nbsp;
		<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>&nbsp;&nbsp; --%>
	<sys:message content="${message}"/>
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
					<shiro:hasPermission name="cms:article:edit">
					<a href="${ctx}/cms/article/zjzxUpdate?id=${article.id}">审核</a>
					</shiro:hasPermission>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	</form:form>
	<div class="pagination">${page}</div>
	
</body>
</html>