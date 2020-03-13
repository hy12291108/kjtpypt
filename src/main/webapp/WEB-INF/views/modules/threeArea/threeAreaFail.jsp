<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>三区人才</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
		 /* if($("#tpyInfoId").val()==null||$("#tpyInfoId").val()==""){
					//跳转首页
					if(self.frameElement && self.frameElement.tagName == "IFRAME" || $('#left').length > 0 || $('.jbox').length > 0){
						top.location = "${ctx}";
						}
			}  */
		});
		function page(n,s){
			if(n) $("#pageNo").val(n);
			if(s) $("#pageSize").val(s);
			$("#searchForm").attr("action","${ctx}/threeSq/getFailTalent");
			$("#searchForm").submit();
	    	return false;
	    }
	</script>
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li ><a href="${ctx}/threeSq/threeAreaCheck">三区人才审核列表</a></li>
		<li ><a href="${ctx}/threeSq/threeAreaTalent">审核通过三区人才</a></li>
		<li class="active"><a href="${ctx}/threeSq/getFailTalent">审核未通过三区人才</a></li>
	</ul>
	<c:if test="${!empty message}">
		<div id ="div" class="alert alert-success alert-dismissible" role="alert">
			 <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			 ${message}
		</div>
	</c:if>
	<form:form id="searchForm" modelAttribute="threeArea" action="${ctx}/threeSq/getFailTalent" method="post" class="breadcrumb form-search ">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
		<ul class="ul-form">
			<li><label>姓&nbsp;&nbsp;名：</label><form:input path="name" htmlEscape="false" maxlength="50" class="input-medium"/></li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" onclick="return page();"/>
			<li class="clearfix"></li>
		</ul>
	</form:form>
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
								<th>状态</th>
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
								 <c:if test="${threeArea.status=='3'}">
								 <font color="red">市未通过 </font>
								 </c:if>
								 <c:if test="${threeArea.status=='5'}">
								 <font color="red">省未通过</font>
								 </c:if>
								</td> 
							</tr>
						</c:forEach>
						</tbody>
					</table>
					<div class="pagination">${page}</div>
	</div>
</body>
</html>