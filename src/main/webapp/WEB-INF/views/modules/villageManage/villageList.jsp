<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>贫困村基本信息录入</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	$(document).ready(function() {
	/* 	$("#btnExport").click(function(){
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
		}); */
		var message = $("#input").val(); 
		//alert("message:"+message);
		if(message!=null&&message!=''){
			//alert(message);
			document.getElementById("div").style.display="";
		};
	});
	function page(n,s){
		if(n) $("#pageNo").val(n);
		if(s) $("#pageSize").val(s);
		$("#searchForm").attr("action","${ctx}/VillageManage/list");
		$("#searchForm").submit();
    	return false;
    }
	</script>
</head>
<body>
	<%--<div id="importBox" class="hide">
		 <form id="importForm" action="${ctx}/sys/user/import" method="post" enctype="multipart/form-data"
			class="form-search" style="padding-left:20px;text-align:center;" onsubmit="loading('正在导入，请稍等...');"><br/>
			<input id="uploadFile" name="file" type="file" style="width:330px"/><br/><br/>　　
			<input id="btnImportSubmit" class="btn btn-primary" type="submit" value="   导    入   "/>
			<a href="${ctx}/sys/user/import/template">下载模板</a>
		</form> 
	</div>--%>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/VillageManage/list">贫困村列表</a></li>
		<shiro:hasPermission name="sys:user:edit"><li><a href="${ctx}/VillageManage/addInfo">添加贫困村</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="village" action="${ctx}/VillageManage/list" method="post" class="breadcrumb form-search ">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
		<ul class="ul-form">
			<%-- <li><label>归属公司：</label><sys:treeselect id="company" name="company.id" value="${user.company.id}" labelName="company.name" labelValue="${user.company.name}" 
				title="公司" url="/sys/office/treeData?type=1" cssClass="input-small" allowClear="true"/></li> --%>
			<li><label>村&nbsp;名：</label><form:input path="villageName" htmlEscape="false" maxlength="100" class="input-medium"/></li>
		<%--	<li class="clearfix"></li>
		 	<li><label>归属部门：</label><sys:treeselect id="office" name="office.id" value="${user.office.id}" labelName="office.name" labelValue="${user.office.name}" 
				title="部门" url="/sys/office/treeData?type=2" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/></li>
			 --%>
			<li><label>村书记:</label><form:input path="secretaryName" htmlEscape="false" maxlength="50" class="input-medium"/></li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" onclick="return page();"/>
				<!-- <input id="btnExport" class="btn btn-primary" type="button" value="导出"/>
				<input id="btnImport" class="btn btn-primary" type="button" value="导入"/> --></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<div id ="div" class="alert alert-success alert-dismissible" style="display:none;" role="alert">
	 <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	 <c:if test="${!empty message}">
	 ${message}
	 </c:if> 
	</div> 
	<input id="input" type="hidden" value="${message}">
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><th class="sort-column villageName">村名</th><th>村书记</th><th>村书记号码</th><th class="sort-column deputy">联系人</th><th>联系人电话</th><th>户数（户）</th><th>人口（人）</th><th>贫困户数（户）</th><th>贫困人口（人）</th><shiro:hasPermission name="sys:user:edit"><th>操作</th></shiro:hasPermission></tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="village">
			<tr>
				<td>${village.villageName}</td>
				<td>${village.secretaryName}</td>
				<td>${village.secretaryPhone}</td>
				<td>${village.deputy}</td>
				<td>${village.deputyPhone}</td>
				<td>${village.houseNumber}</td>
				<td>${village.population}</td>
				<td>${village.poorNumber}</td>
				<td>${village.poorPopulation}</td>
				<shiro:hasPermission name="sys:user:edit"><td>
    				<a href="${ctx}/VillageManage/villageInfo?id=${village.id}">修改</a>
					<a href="${ctx}/VillageManage/delete?id=${village.id}" onclick="return confirmx('确认要删除该村信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	
	
	
</body>
</html>