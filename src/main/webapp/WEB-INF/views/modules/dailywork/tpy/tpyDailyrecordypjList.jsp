<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>特派员工作日志管理</title>
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
			$("#searchForm").attr("action","${ctx}/dailywork/tpy/tpyDailyrecord/ypjlist");
			$("#searchForm").submit();
	    	return false;
	    }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/dailywork/tpy/tpyDailyrecord/dpjlist">待评价列表</a></li>
		<li class="active"><a href="${ctx}/dailywork/tpy/tpyDailyrecord/ypjlist">已评价列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="tpyDailyrecord" action="${ctx}/dailywork/tpy/tpyDailyrecord/ypjlist" method="post" class="breadcrumb form-search ">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
		<ul class="ul-form">
			<li><label>填写人：</label><form:input path="recWriter" htmlEscape="false" maxlength="50" class="input-medium"/></li>
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
				<th class="sort-column recStartTime">帮扶开始时间</th>
				<th class="sort-column recEndTime">帮扶结束时间</th>
				<th class="sort-column createDate">填写时间</th>
				<th class="sort-column fwdxpjState">评价状态</th>
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
					${tpyDailyrecord.recStartTime}
				</td>
				<td>
					${tpyDailyrecord.recEndTime}
				</td>
				<td>
					<fmt:formatDate value="${tpyDailyrecord.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td><c:choose>
						<c:when test="${tpyDailyrecord.fwdxpjState == '0'}">
							<font color=blue >未评价</font> 
						</c:when>
						<c:when test="${tpyDailyrecord.fwdxpjState == '1'}">
							<font color=blue >已评价</font> 
						</c:when>
				</c:choose></td>
				<td><c:choose>
						<c:when test="${tpyDailyrecord.recStatus == 'no'}">
							<a href="${ctx}/dailywork/tpy/tpyDailyrecord/change?id=${tpyDailyrecord.id}">提交</a>
							<a href="${ctx}/dailywork/tpy/tpyDailyrecord/update?id=${tpyDailyrecord.id}">修改</a>
							<a href="${ctx}/dailywork/tpy/tpyDailyrecord/delete?id=${tpyDailyrecord.id}" onclick="return confirmx('确认要删除该特派员工作日志吗？', this.href)">删除</a> 
						</c:when>
						<c:when test="${tpyDailyrecord.recStatus == 'inProcess'}">
							<a href="${ctx}/dailywork/tpy/tpyDailyrecord/view?id=${tpyDailyrecord.id}">查看</a>
						</c:when>
						<c:when test="${tpyDailyrecord.recStatus == 'pass'}">
							<a href="${ctx}/dailywork/tpy/tpyDailyrecord/pjck?id=${tpyDailyrecord.id}">查看</a>
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