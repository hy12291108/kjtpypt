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
			$("#searchForm").attr("action","${ctx}/dailywork/village/vilProtocol/yscvilmemberlist");
			$("#searchForm").submit();
	    	return false;
	    }
	</script>
</head>
<body>
		<ul class="nav nav-tabs">
		<li><a href="${ctx}/dailywork/village/vilProtocol/vilmemberlist">待上传个人协议列表</a></li>
		<li class="active"><a href="${ctx}/dailywork/village/vilProtocol/yscvilmemberlist">已上传个人协议列表</a></li>
		</ul>
		<form:form id="searchForm" modelAttribute="sqtpy" action="${ctx}/dailywork/village/vilProtocol/yscvilmemberlist" method="post" class="breadcrumb form-search ">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
		</form:form>
		<h4>已上传贫困村个人协议列表：</h4>
		<sys:message content="${message}"/>
    	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>贫困村</th>		
				<th>联系人</th>
				<th>贫困村电话</th>		
				<th>特派团</th>
				<th>团长/团员</th>	
				<th>服务时间</th>	
				<th class="sort-column create_date">填写时间</th>
				<th>审核状态</th>
				<th>协议操作</th>
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
					${teamMember.memberType}
				</td>
				<td>
					${teamMember.startTime}至${teamMember.endTime}
				</td>
				<td>
					<fmt:formatDate value="${teamMember.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
				<c:choose>				
						<c:when test="${teamMember.memberprotocolFlag == '1'}">	
							<font color=green >审核中</font> </a>					
						</c:when>
						<c:when test="${teamMember.memberprotocolFlag == '2'}">
							<font color=blue >已通过</font> </a>
						</c:when>
						<c:when test="${teamMember.memberprotocolFlag == '3'}">
							<font color=red >未通过</font> </a>
						</c:when>
						<c:when test="${teamMember.memberprotocolFlag == '0'}">
							<font color=blue >未提交</font> </a>
						</c:when>						
				</c:choose>
				</td>	
				<td>
				<!-- 修改查看方法，原方法（vilinfobyMember），新方法（vilinfobyMemberpersion） -->	
				<c:choose>			
						<c:when test="${teamMember.memberprotocolFlag == '1'}">	
							<a href="${ctx}/dailywork/village/vilProtocol/vilinfobyMemberpersion?id=${teamMember.id}"><font color=blue >查看</font> </a>					
						</c:when>
						<c:when test="${teamMember.memberprotocolFlag == '2'}">
							<a href="${ctx}/dailywork/village/vilProtocol/vilinfobyMemberpersion?id=${teamMember.id}"><font color=blue >查看</font> </a>
						</c:when>
						<c:when test="${teamMember.memberprotocolFlag == '3'}">
							<a href="${ctx}/dailywork/village/vilProtocol/addvil?userId=${teamMember.userId}&teamId=${teamMember.teamId}&villageId=${teamMember.villageId}&memberType1=0"><font color=red >添加</font> </a>
						</c:when>
						<c:otherwise>
							<font color=red >无权限</font> 
						</c:otherwise>						
					</c:choose>		
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
  </body>
</html>
