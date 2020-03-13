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
			$("#searchForm").attr("action","${ctx}/sqtpy/tpy/Xqlist");
			$("#searchForm").submit();
	    	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/sqtpy/tpy/Xqlist">待审核列表</a></li>
		<li><a href="${ctx}/sqtpy/tpy/YshXqlist">已审核列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="sqtpy" action="${ctx}/sqtpy/tpy/Xqlist" method="post" class="breadcrumb form-search ">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
		<ul class="ul-form">
			<li><label>特派员姓名：</label><form:input path="tpyname" htmlEscape="false" maxlength="50" class="input-medium"/></li>
			<li><label>开始时间：</label><form:input path="starTime" htmlEscape="false" maxlength="50" class="input-medium"/></li>
			<li><label>结束时间：</label><form:input path="endTime" htmlEscape="false" maxlength="50" class="input-medium"/></li>
			<li><label>申请方式：</label><form:select path="ismajor"  htmlEscape="false" maxlength="50" class="input-medium">  
   										<form:option value="2">请选择</form:option>
   										<form:option value="0">按个人</form:option>
   										<form:option value="1">按专业</form:option>   										
										</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" onclick="return page();"/></li>			
		</ul>
	</form:form>
	<sys:message content="${message}"/>
		<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><th class="sort-column tpyname" class="tit">特派员姓名</th>
					<th class="sort-column starTime">服务开始时间</th>
					<th class="sort-column endTime">服务结束时间</th>
					<th class="sort-column create_date">填写时间</th>
					<th class="sort-column ismajor">申请方式</th>
					<th class="sort-column person_flag">特派员类型</th>
					<th class="sort-column state">状态</th>
					<th>操作</th></thead>
		<tbody>
		<c:forEach items="${page.list}" var="sqtpy">
			<tr>
				<td>${sqtpy.tpyname}</td>
				<td>${sqtpy.starTime}</td>
				<td>${sqtpy.endTime}</td>
				<td><fmt:formatDate value="${sqtpy.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td><c:choose>
						<c:when test="${sqtpy.ismajor == '0'}">
							<font color=blue >按个人</font> 
						</c:when>
						<c:when test="${sqtpy.ismajor == '1'}">
							<font color=red >按专业</font> 
						</c:when>
				</c:choose></td>
				<td><c:choose>
						<c:when test="${sqtpy.personFlag == '0'}">
							<font color=blue >自然人</font> 
						</c:when>
						<c:when test="${sqtpy.personFlag == '2'}">
							<font color=red >法人</font> 
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
							<a href="${ctx}/sqtpy/tpy/selectShInfo1?id=${sqtpy.id}"><font color=blue >查看</font> </a>
						</c:when>
						<c:when test="${sqtpy.state == '2'}">
							<a href="${ctx}/sqtpy/tpy/selectShInfo1?id=${sqtpy.id}"><font color=blue >查看</font> </a>
						</c:when>
						<c:when test="${sqtpy.state == '3'}">
							<a href="${ctx}/sqtpy/tpy/change?id=${sqtpy.id}&personFlag=${sqtpy.personFlag}"><font color=blue >修改</font> </a> 
						</c:when>
						<c:when test="${sqtpy.state == '4'}">
							<a href="${ctx}/sqtpy/tpy/selectShInfo1?id=${sqtpy.id}"><font color=blue >查看</font> </a>
						</c:when>
						<c:otherwise>
							<a href="${ctx}/sqtpy/tpy/updateTpyState?id=${sqtpy.id}&state=1"><font color=blue >提交</font> </a>
							<a href="${ctx}/sqtpy/tpy/change?id=${sqtpy.id}&personFlag=${sqtpy.personFlag}"><font color=blue >修改</font> </a>
							<a href="${ctx}/sqtpy/tpy/deleteTpySqInfo?id=${sqtpy.id}"><font color=blue >删除</font> </a> 
						</c:otherwise>
				</c:choose></td>
			</tr>
		</c:forEach>
		</tbody>		
	</table>
	<div class="pagination">${page}</div>
</body>
</html>