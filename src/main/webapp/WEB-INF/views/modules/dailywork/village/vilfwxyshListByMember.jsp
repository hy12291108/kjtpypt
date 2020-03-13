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
			$("#searchForm").attr("action","${ctx}/dailywork/village/vilProtocol/vilFwxyByMember");
			$("#searchForm").submit();
	    	return false;
	    }
	</script>
</head>
<body>
		<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/dailywork/village/vilProtocol/vilFwxyByMember">待审核个人协议列表</a></li>
		<li><a href="${ctx}/dailywork/village/vilProtocol/vilYshFwxyByMember">已审核个人协议列表</a></li>
		</ul>
		<form:form id="searchForm" modelAttribute="teamMember" action="${ctx}/dailywork/village/vilProtocol/vilFwxyByMember" method="post" class="breadcrumb form-search ">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
		</form:form>
		<h4>科技特派团个人协议列表：</h4>
		<sys:message content="${message}"/>
    	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>贫困村名称</th>		
				<th>贫困村联系人</th>
				<th>贫困村电话</th>		
				<th>特派团团名</th>
				<th>团员名称</th>
				<th>团员电话</th>	
				<th>服务时间</th>	
				<th class="sort-column create_date">填写时间</th>	
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="teamMember">
			<tr>
				<td>
					${teamMember.villageName}		
				</td>
				<td>
					${teamMember.deputy}
				</td>
				<td>
					${teamMember.deputyPhone}
					
				</td>
				<td>
					${teamMember.teamName}
				</td>
				<td>
					${teamMember.name}
				</td>
				<td>
					${teamMember.mobile}
				</td>
				<td>
					${teamMember.startTime}至${teamMember.endTime}
				</td>
				<td>
					<fmt:formatDate value="${teamMember.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>										
					<a href="${ctx}/dailywork/village/vilProtocol/MemberShfrom?id=${teamMember.id}"><font color=blue >审核</font> </a>					
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
  </body>
</html>
