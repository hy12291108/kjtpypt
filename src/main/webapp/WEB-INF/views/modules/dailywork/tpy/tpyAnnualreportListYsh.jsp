<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>特派员年度考核管理</title>
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
			$("#searchForm").attr("action","${ctx}/dailywork/tpy/tpyAnnualreport/YshtpyAnnualreportList");
			$("#searchForm").submit();
	    	return false;
	    }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/dailywork/tpy/tpyAnnualreport/">年度考核(待审核)</a></li>
		<li class="active"><a href="${ctx}/dailywork/tpy/tpyAnnualreport/YshtpyAnnualreportList">年度考核(已审核)</a></li>
		<shiro:hasPermission name="dailywork:tpy:tpyAnnualreport:edit"><li><a href="${ctx}/dailywork/tpy/tpyAnnualreport/form">年度考核添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="tpyAnnualreport" action="${ctx}/dailywork/tpy/tpyAnnualreport/YshtpyAnnualreportList" method="post" class="breadcrumb form-search ">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
		<ul class="ul-form">
			<li><label>年度：</label><form:input path="repTime" htmlEscape="false" maxlength="50" class="input-medium"/></li>
			<li><label>填写时间：</label><form:input path="repWritenTime" htmlEscape="false" maxlength="50" class="input-medium"/></li>
			<li><label>审核人：</label><form:input path="repApproperson" htmlEscape="false" maxlength="50" class="input-medium"/></li>
			<li><label>审核时间：</label><form:input path="repApprotime" htmlEscape="false" maxlength="50" class="input-medium"/></li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" onclick="return page();"/></li>			
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th class="sort-column repTime">年度</th>
				<th>填写人</th>
				<th class="sort-column repWritenTime">填写时间</th>
				<th class="sort-column repApproperson">审核人</th>
				<th class="sort-column repStatus">审核状态</th>
				<th class="sort-column repApprotime">审核时间</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="tpyAnnualreport">
			<tr>
				<%-- <td><a href="${ctx}/dailywork/tpy/tpyAnnualreport/form?id=${tpyAnnualreport.id}">
					${tpyAnnualreport.repTpyid}
				</a></td> --%>
				<td>
					${tpyAnnualreport.repTime}
				</td>
				<td>
					${tpyAnnualreport.repTpyuser.name}
				</td>
				<td>
					${tpyAnnualreport.repWritenTime}
				</td>
				<td>
					${tpyAnnualreport.repApproperson}
				</td>
				<td><c:choose>
						<c:when test="${tpyAnnualreport.repStatus == 'no'}">
							<font color=green >未提交</font> 
						</c:when>
						<c:when test="${tpyAnnualreport.repStatus == 'inProcess'}">
							<font color=blue >审核中</font> 
						</c:when>
						<c:when test="${tpyAnnualreport.repStatus == 'pass'}">
							<font color=blue >已通过</font> 
						</c:when>
						<c:when test="${tpyAnnualreport.repStatus == 'return'}">
							<font color=red >未通过</font> 
						</c:when>
				</c:choose>
				</td>	
				<td>
					${tpyAnnualreport.repApprotime}
				</td>
				<shiro:hasPermission name="dailywork:tpy:tpyAnnualreport:edit"><td>
				<c:choose>
						<c:when test="${tpyAnnualreport.repStatus == 'inProcess'}">
							<a  href="${ctx}/dailywork/tpy/tpyAnnualreport/view?id=${tpyAnnualreport.id}">查看</a>						 
						</c:when>
						<c:when test="${tpyAnnualreport.repStatus == 'pass'}">
						<a  href="${ctx}/dailywork/tpy/tpyAnnualreport/view?id=${tpyAnnualreport.id}">查看</a>						
						</c:when>
						<c:when test="${tpyAnnualreport.repStatus == 'return'}">
							<a ${tpyAnnualreport.html} href="${ctx}/dailywork/tpy/tpyAnnualreport/update?id=${tpyAnnualreport.id}">修改</a>
						</c:when>
						<c:otherwise>
							<font color=red >不可操作</font> 
						</c:otherwise>
				</c:choose>
					<!-- <a  href="${ctx}/dailywork/tpy/tpyAnnualreport/view?id=${tpyAnnualreport.id}">查看</a>
    				<a ${tpyAnnualreport.html} href="${ctx}/dailywork/tpy/tpyAnnualreport/form?id=${tpyAnnualreport.id}">修改</a>
					<a ${tpyAnnualreport.html} href="${ctx}/dailywork/tpy/tpyAnnualreport/delete?id=${tpyAnnualreport.id}" onclick="return confirmx('确认要删除该特派员年度考核吗？', this.href)">删除</a>
				 -->
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>