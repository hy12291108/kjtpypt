<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>贫困村服务协议管理</title>
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
			$("#searchForm").attr("action","${ctx}/queryinfo/info/Allyshlist");
			$("#searchForm").submit();
	    	return false;
	    }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/queryinfo/info/Allyshlist">帮扶信息列表</a></li>	
	</ul>
	<form:form id="searchForm" modelAttribute="sqtpy" action="${ctx}/queryinfo/info/Allyshlist" method="post" class="breadcrumb form-search ">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
		<ul class="ul-form">
			<li><label>需求单位:</label><form:input path="xqdwname" htmlEscape="false" maxlength="50" class="input-medium"/></li>
			<li><label>特派员：</label><form:input path="tpyname" htmlEscape="false" maxlength="100" class="input-medium"/></li>
			<li><label>归属区域：</label><sys:treeselect id="office" name="office.id" value="${user.office.id}" labelName="office.name" labelValue="${user.office.name}"
								title="部门" url="/UserRegister/treeData?type=2" cssClass="required" notAllowSelectParent="true"/></li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" onclick="return page();"/>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><th>需求单位名称</th><th>需求单位联系人</th><th>管辖单位</th><th>联系人电话</th><th>特派员姓名</th><th>特派员电话</th><th>帮扶日期</th><th>申请方式</th><th>状态</th><th>操作</th></thead>
		<tbody>
		<c:forEach items="${page.list}" var="sqtpy">
			<tr>
				<td>${sqtpy.xqdwname}</td>
				<td>${sqtpy.corpcorName}</td>											
				<td>${sqtpy.office.name}</td>
				<td>${sqtpy.xqdwphone}</td>
				<td>${sqtpy.tpyname}</td>
				<td>${sqtpy.mobile}</td>
				<td>${sqtpy.starTime} 至 ${sqtpy.endTime}</td>
				<td><c:choose>
						<c:when test="${sqtpy.ismajor == '0'}">
							<font color=blue >按个人</font> 
						</c:when>
						<c:when test="${sqtpy.ismajor == '1'}">
							<font color=red >按专业</font> 
						</c:when>
				</c:choose></td>
				<td><c:choose>
						<c:when test="${sqtpy.state == '1'}">
							<font color=blue >审核中</font> 
						</c:when>
						<c:when test="${sqtpy.state == '2'}">
							<font color=red >已通过</font> 
						</c:when>
						<c:when test="${sqtpy.state == '3'}">
							<font color=red >未通过</font> 
						</c:when>
						<c:when test="${sqtpy.state == '4'}">
							<font color=red >已完成</font> 
						</c:when>
						<c:otherwise>
							<font color=green >未提交</font> 
						</c:otherwise>
				</c:choose></td>							
				<td><c:choose>				
						<c:when test="${sqtpy.state == '1'}">	
							<a href="${ctx}/queryinfo/info/bfinfo?id=${sqtpy.id}"><font color=blue >查看</font> </a>				
						</c:when>
						<c:when test="${sqtpy.state == '2'}">
							<a href="${ctx}/queryinfo/info/bfinfo?id=${sqtpy.id}"><font color=blue >查看</font> </a>
						</c:when>
						<c:when test="${sqtpy.state == '3'}">
							<a href="${ctx}/queryinfo/info/bfinfo?id=${sqtpy.id}"><font color=blue >查看</font> </a>
						</c:when>
						<c:when test="${sqtpy.state == '4'}">
							<a href="${ctx}/queryinfo/info/bfinfo?id=${sqtpy.id}"><font color=blue >查看</font> </a>
						</c:when>
				</c:choose></td>
		</c:forEach>
		</tbody>		
	</table>
	<div class="pagination">${page}</div>
</body>
</html>