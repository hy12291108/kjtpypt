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
			$("#searchForm").attr("action","${ctx}/dailywork/tpy/tpyAnnualreportInquiry/");
			$("#searchForm").submit();
	    	return false;
	    }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/dailywork/tpy/tpyAnnualreportInquiry/">特派员年度考核信息列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="tpyAnnualreport" action="${ctx}/dailywork/tpy/tpyAnnualreportInquiry/" method="post" class="breadcrumb form-search ">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
		<ul class="ul-form">
			<li><label>填写人：</label><form:input path="repTpyuser.name" htmlEscape="false" maxlength="50" class="input-medium"/></li>
			<li><label>归属区域：</label><sys:treeselect id="office" name="office.id" value="${user.office.id}" labelName="office.name" labelValue="${user.office.name}"
								title="部门" url="/UserRegister/treeData?type=2" cssClass="required" notAllowSelectParent="true"/></li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" onclick="return page();"/></li>			
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>所属区域</th>	
				<th>年度</th>				
				<th>特派员名称</th>
				<th>填写时间</th>
				<th>审批状态</th>
				<th>操作</th>				
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="tpyAnnualreport">
			<tr>
				<td>
					${tpyAnnualreport.office.name}
				</td>
				<td>
					${tpyAnnualreport.repTime}
				</td>
				<td>
					${tpyAnnualreport.repTpyuser.name}
				</td>
				<td>
				<fmt:formatDate value="${tpyAnnualreport.updateDate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					${tpyAnnualreport.repStatus=='inProcess'?'待审批':'已审批'}
				</td>
				<td>
					<a href="${ctx}/dailywork/tpy/tpyAnnualreportInquiry/view?id=${tpyAnnualreport.id}">查看</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>