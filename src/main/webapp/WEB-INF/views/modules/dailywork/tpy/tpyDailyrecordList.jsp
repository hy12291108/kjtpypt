<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>特派员工作日志管理</title>
	<meta name="decorator" content="default"/>
	<style>
	body,html{
		height: 100%;
		/*display: flex;
		align-items: center;
		justify-content: center;
		flex-direction: column;*/
	}

	.ul-centermenu{
		width: 200px;
		margin: 0 auto;
	}

	.ul-centermenu li{
		width: 100%;
		text-align: center;
		margin-bottom: 15px;
	}

</style>
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
			$("#searchForm").attr("action","${ctx}/dailywork/tpy/tpyDailyrecord/");
			$("#searchForm").submit();
	    	return false;
	    }
	    function centermenu7(){
		$('body').centermenu({
			hasLineBorder:true,
			duration:600,
			source:
			['<a href="${ctx}/dailywork/tpy/tpyDailyrecord/form">特派员日志添加(服务对象)</a>',
			'<a href="${ctx}/dailywork/tpy/tpyDailyrecord/vilform">特派员日志添加(贫困村)</a>',
			],
			liWidth:300,
			liHeight:50,
		});
	}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/dailywork/tpy/tpyDailyrecord/">工作日志待审核列表</a></li>
		<li><a href="${ctx}/dailywork/tpy/tpyDailyrecord/yshlist">工作日志已审核列表</a></li>	
		<shiro:hasPermission name="dailywork:tpy:tpyDailyrecord:edit"><li><a onclick="centermenu7()">日志添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="tpyDailyrecord" action="${ctx}/dailywork/tpy/tpyDailyrecord/" method="post" class="breadcrumb form-search ">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
		<ul class="ul-form">
			<li><label>帮扶对象：</label><form:input path="recHelpObj" htmlEscape="false" maxlength="50" class="input-medium"/></li>
			<li><label>开始时间：</label><form:input path="recStartTime" htmlEscape="false" maxlength="50" class="input-medium"/></li>
			<li><label>结束时间：</label><form:input path="recEndTime" htmlEscape="false" maxlength="50" class="input-medium"/></li>
			<li><label>填写时间：</label><form:input path="recWrittenTime" htmlEscape="false" maxlength="50" class="input-medium"/></li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" onclick="return page();"/></li>			
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th class="sort-column recWriter">填写人</th>
				<th class="sort-column recHelpObj">帮扶对象</th>
				<th class="sort-column recHelpobjtype">帮扶对象类型</th>
				<th class="sort-column recStartTime">帮扶开始时间</th>
				<th class="sort-column recEndTime">帮扶结束时间</th>
				<th class="sort-column createDate">填写时间</th>
				<th>日志类型</th>
				<th class="sort-column recStatus">审批状态</th>		
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="tpyDailyrecord">
			<tr>
     			<td>
					${tpyDailyrecord.recWriter}
				</td>
				<td>
					${tpyDailyrecord.recHelpObj}
				</td>
				<td>
					${tpyDailyrecord.recHelpobjtype}
				</td>
				<td>
					${tpyDailyrecord.recStartTime}
				</td>
				<td>
					${tpyDailyrecord.recEndTime}
				</td>
				<td>
					<fmt:formatDate value="${tpyDailyrecord.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>	
				</td>
				<td>
					${tpyDailyrecord.dailyRecordType}
				</td>
				<td><c:choose>
						<c:when test="${tpyDailyrecord.recStatus == 'no'}">
							<font color=green >未提交</font> 
						</c:when>
						<c:when test="${tpyDailyrecord.recStatus == 'inProcess'}">
							<font color=blue >审核中</font> 
						</c:when>
						<c:when test="${tpyDailyrecord.recStatus == 'pass'}">
							<font color=blue >已通过</font> 
						</c:when>
						<c:when test="${tpyDailyrecord.recStatus == 'return'}">
							<font color=red >未通过</font> 
						</c:when>
				</c:choose></td>
				<td><c:choose>
						<c:when test="${tpyDailyrecord.recStatus == 'no'}">
							<a href="${ctx}/dailywork/tpy/tpyDailyrecord/change?id=${tpyDailyrecord.id}">提交</a>
							 <a href="${ctx}/dailywork/tpy/tpyDailyrecord/update?id=${tpyDailyrecord.id}">修改</a> 
							<a href="${ctx}/dailywork/tpy/tpyDailyrecord/delete?id=${tpyDailyrecord.id}" onclick="return confirmx('确认要删除该特派员工作日志吗？', this.href)">删除</a> 
						</c:when>
						<c:when test="${tpyDailyrecord.recStatus == 'inProcess'}">
							<a href="${ctx}/dailywork/tpy/tpyDailyrecord/view?id=${tpyDailyrecord.id}&recHelpobjtype=${tpyDailyrecord.recHelpobjtype}">查看</a>
						</c:when>
						<c:when test="${tpyDailyrecord.recStatus == 'pass'}">
							<a href="${ctx}/dailywork/tpy/tpyDailyrecord/view?id=${tpyDailyrecord.id}&recHelpobjtype=${tpyDailyrecord.recHelpobjtype}">查看</a>
						</c:when>
						<c:when test="${tpyDailyrecord.recStatus == 'return'}">
							<a href="${ctx}/dailywork/tpy/tpyDailyrecord/update?id=${tpyDailyrecord.id}">修改</a>
						</c:when>
				</c:choose></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>