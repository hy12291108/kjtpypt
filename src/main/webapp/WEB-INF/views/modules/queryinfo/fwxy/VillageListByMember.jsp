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
			$("#searchForm").attr("action","${ctx}/queryinfo/info/villagelistByMember");
			$("#searchForm").submit();
	    	return false;
	    }
	</script>
</head>
<body>
		<ul class="nav nav-tabs">
		<li><a href="${ctx}/queryinfo/info/fwdxXyList">服务对象协议列表</a></li>
		<li><a href="${ctx}/queryinfo/info/villagelistByTeam">贫困村团队协议列表</a></li>
		<li class="active"><a href="${ctx}/queryinfo/info/villagelistByMember">贫困村个人协议列表</a></li>
		</ul>
		<form:form id="searchForm" modelAttribute="teamMember" action="${ctx}/queryinfo/info/villagelistByMember" method="post" class="breadcrumb form-search ">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
		<ul class="ul-form">
			<li><label>特派团名称:</label><form:input path="teamName" htmlEscape="false" maxlength="50" class="input-medium"/></li>
			<li><label>贫困村名：</label><form:input path="villageName" htmlEscape="false" maxlength="100" class="input-medium"/></li>
			<li><label>归属区域：</label><sys:treeselect id="office" name="office.id" value="${user.office.id}" labelName="office.name" labelValue="${user.office.name}"
								title="部门" url="/UserRegister/treeData?type=2" cssClass="required" notAllowSelectParent="true"/></li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" onclick="return page();"/>
			<li class="clearfix"></li>
		</ul>
		</form:form>
		<h4>科技特派团个人协议列表：</h4>
	<sys:message content="${message}"/>
    	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>所属区域</th>
				<th>贫困村名称</th>		
				<th>贫困村联系人</th>	
				<th>特派团团名</th>
				<th>团员名称</th>
				<th>服务时间</th>	
				<th class="sort-column create_date">填写时间</th>	
				<th>审核状态</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="teamMember">
			<tr>
				<td>
					${teamMember.office.name}		
				</td>
				<td>
					${teamMember.villageName}		
				</td>
				<td>
					${teamMember.deputy}
				</td>
				<td>
					${teamMember.teamName}
				</td>
				<td>
					${teamMember.name}
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
					<a href="${ctx}/dailywork/village/vilProtocol/MemberShInfo?id=${teamMember.id}"><font color=blue >查看</font> </a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
  </body>
</html>
