<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>三区人才申请</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
		
		});
		function page(n,s){
			if(n) $("#pageNo").val(n);
			if(s) $("#pageSize").val(s);
			$("#searchForm").attr("action","${ctx}/threeSq/threeAreaCheck");
			$("#searchForm").submit();
	    	return false;
	    }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/threeSq/threeAreaCheck">三区人才审核列表</a></li>
		<li ><a href="${ctx}/threeSq/threeAreaTalent">审核通过三区人才</a></li>
		<li ><a href="${ctx}/threeSq/getFailTalent">审核未通过三区人才</a></li>
	</ul>
	<c:if test="${!empty message}">
		<div id ="div" class="alert alert-success alert-dismissible" role="alert">
			 <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			 ${message}
		</div>
	</c:if>
	<form:form id="searchForm" modelAttribute="threeArea" action="${ctx}/threeSq/threeAreaCheck" method="post" class="breadcrumb form-search ">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
		<ul class="ul-form">
			<li><label>姓&nbsp;&nbsp;名：</label><form:input path="name" htmlEscape="false" maxlength="50" class="input-medium"/></li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" onclick="return page();"/>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	 <%-- <input type="hidden" name="id" value="${threeArea.id}"> --%>
	 <div>
		<table class="table table-bordered">
						<thead>
							<tr>
								<th>年度</th>
								<th>管辖部门</th>
								<th>姓名</th>
								<th>性别</th>
								<th>Email</th>
								<th>手机号码</th>
								<th>学历</th>
								<th>专业</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody class="tbody-job-list">
						<c:forEach items="${page.list}" var="threeArea">
							<tr class="tr-length">
								<td>${threeArea.year}</td>
								<td>${threeArea.zoneName}</td>
								<td>${threeArea.name}</td>
								<td>${threeArea.sex}</td>
								<td>${threeArea.email}</td>
								<td>${threeArea.mobile}</td>
								<td>${threeArea.tpyQulification}</td>
								<td>${threeArea.tpyMajor}</td>
								<td>
								<c:if test="${threeArea.status=='1'}">
								<%-- <a href="${ctx}/threeSq/updatestatus?status=2&id=${threeArea.id}">通过</a>
								<a href="${ctx}/threeSq/updatestatus?status=3&id=${threeArea.id}">退回</a> --%>
								<a href="${ctx}/threeSq/threeAreaInfoCheck?id=${threeArea.id}">审核</a>
								</c:if>
								<c:if test="${threeArea.status=='2'}">
								<%-- <a href="${ctx}/threeSq/updatestatus?status=4&id=${threeArea.id}">通过</a>
								<a href="${ctx}/threeSq/updatestatus?status=5&id=${threeArea.id}">退回</a> --%>
								<a href="${ctx}/threeSq/threeAreaInfoCheck?id=${threeArea.id}">审核</a>
								</c:if>
								</td>
							</tr>
						</c:forEach>
						</tbody>
					</table>
					<div class="pagination">${page}</div>
	</div>
	<%--<div class="form-actions">
	<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
			 <input id="btnSubmit" class="btn btn-primary" type="submit" value="提交县科委"/>
			<button type="button" class="btn btn-default btn-xs" ><a href="${ctx}/UserSh/baseInfo">修改信息</a></button>
	</div> --%>

</body>
</html>