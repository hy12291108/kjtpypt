<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用户管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出用户数据吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","${ctx}/sys/user/export");
						$("#searchForm").submit();
					}
				},{buttonsFocus:1});
				top.$('.jbox-body .jbox-icon').css('top','55px');
			});
			$("#btnImport").click(function(){
				$.jBox($("#importBox").html(), {title:"导入数据", buttons:{"关闭":true}, 
					bottomText:"导入文件不能超过5M，仅允许导入“xls”或“xlsx”格式文件！"});
			});
		});
		function page(n,s){
			if(n) $("#pageNo").val(n);
			if(s) $("#pageSize").val(s);
			$("#searchForm").attr("action","${ctx}/sqtpy/tpy/frlist");
			$("#searchForm").submit();
	    	return false;
	    }   
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/sqtpy/tpy/list">申请自然人特派员</a></li>
		<li class="active"><a href="${ctx}/sqtpy/tpy/frlist">申请法人特派员</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="user" action="${ctx}/sqtpy/tpy/frlist" method="post" class="breadcrumb form-search ">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
		<ul class="ul-form">
			
			<li><label>特派员姓名：</label><form:input path="name" htmlEscape="false" maxlength="50" class="input-medium"/></li>
			<li><label>法人代表：</label><form:input path="corpLegRepName" htmlEscape="false" maxlength="50" class="input-medium"/></li>
			<li><label>联系人姓名：</label><form:input path="corpCorName" htmlEscape="false" maxlength="50" class="input-medium"/></li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" onclick="return page();"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><th class="sort-column tpyname">特派员姓名</th>
				   <th>所属区域</th>
				   <th class="sort-column tpyname">法人代表</th>
				   <th>法人电话</th>
				   <th class="sort-column tpyname">联系人姓名</th>
				   <th>联系电话</th><th>操作</th></tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="user">
			<tr>
				<td>${user.name}</td>
				<td>${user.office.area.name}</td>
				<td>${user.corpLegRepName}</td>
				<td>${user.mobile}</td>
				<td>${user.corpCorName}</td>
				<td>${user.corpCorPhone}</td>
				<td>
    				<a href="${ctx}/sqtpy/tpy/frform?id=${user.id}"><font color = blue>选择</font></a>					
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>