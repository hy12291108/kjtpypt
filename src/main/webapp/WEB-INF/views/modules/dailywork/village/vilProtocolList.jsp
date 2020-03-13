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
			$("#searchForm").attr("action","${ctx}/dailywork/village/vilProtocol/list");
			$("#searchForm").submit();
	    	return false;
	    }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/dailywork/village/vilProtocol/">待上传服务对象协议列表</a></li>
		<li class="active"><a href="${ctx}/dailywork/village/vilProtocol/list">已上传服务对象协议列表</a></li>
		<%-- <li><a href="${ctx}/dailywork/village/vilProtocol/form">贫困村服务协议添加</a></li> --%>
	</ul>
	<form:form id="searchForm" modelAttribute="sqtpy" action="${ctx}/dailywork/village/vilProtocol/list" method="post" class="breadcrumb form-search ">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
		</form:form>
	<h4>服务对象：</h4>
		<sys:message content="${message}"/>
    	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>特派员</th>		
				<th>特派员电话</th>
				<th>需求单位</th>		
				<th>联系人</th>
				<th>联系人电话</th>	
				<th>服务时间</th>
				<th class="sort-column create_date">填写时间</th>
				<th>协议是否审核</th>
				<th>操作</th>
				<!-- <th>备注信息</th>
				<th>村id</th>
				<th>村名称</th>
				<th>所属区县</th>
				<th>联系人</th>
				
				<th>角色</th>
				<th>更新时间</th>
				<shiro:hasPermission name="dailywork:village:vilProtocol:edit"><th>操作</th></shiro:hasPermission> -->
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="sqtpy">
			<tr>
				<td>
					${sqtpy.tpyname}		
				</td>
				<td>
					${sqtpy.mobile}
				</td>
				<td>
					${sqtpy.xqdwname}		
				</td>
				<td>
					${sqtpy.corpcorName}
				</td>
				<td>
					${sqtpy.xqdwphone}
				</td>
				<td>
					${sqtpy.starTime}至${sqtpy.endTime}
				</td>
				<td><fmt:formatDate value="${sqtpy.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td><c:choose>
						<c:when test="${sqtpy.fwxystateflag == '0'}">
							<font color=green >审核中</font> 
						</c:when>
						<c:when test="${sqtpy.fwxystateflag == '1'}">
							<font color=blue >已通过</font> 
						</c:when>
						<c:when test="${sqtpy.fwxystateflag == '2'}">
							<font color=red >未通过</font> 
						</c:when>
						<c:when test="${sqtpy.fwxystateflag == '3'}">
							<font color=green >重新审核中</font> 
						</c:when>
				</c:choose>
				</td>
				<td>
				<c:choose>
						<c:when test="${sqtpy.fwxystateflag == '0'}">
							<a href="${ctx}/dailywork/village/vilProtocol/form?fwxy=${sqtpy.fwxy}&id=${sqtpy.id}">查看</a>
						</c:when>
						<c:when test="${sqtpy.fwxystateflag == '1'}">
							<a href="${ctx}/dailywork/village/vilProtocol/form?fwxy=${sqtpy.fwxy}&id=${sqtpy.id}">查看</a> 
						</c:when>
						<c:when test="${sqtpy.fwxystateflag == '2'}">
							<a href="${ctx}/dailywork/village/vilProtocol/add?recid=${sqtpy.id}&type=${tpye}&vilid=${sqtpy.xqdwid}&tpyid=${sqtpy.tpyid}">添加</a> 
						</c:when>
						<c:when test="${sqtpy.fwxystateflag == '3'}">
							<a href="${ctx}/dailywork/village/vilProtocol/form?fwxy=${sqtpy.fwxy}&id=${sqtpy.id}">查看</a> 
						</c:when>
				</c:choose>
				
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>