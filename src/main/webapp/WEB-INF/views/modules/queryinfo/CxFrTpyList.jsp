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
			$("#searchForm").attr("action","${ctx}/queryinfo/info/queryfrtpyList");
			$("#searchForm").submit();
	    	return false;
	    }
	    function corpList(){
					var officeId = $("#officeId").val();
					if(officeId==""){
					alert("请先输入所属部门");
					$("#officeName").focus();
					return;
					}else if($("#tpyCompany .es-list").length>0){
						$("#editable-select").remove();
		        		$(".es-list").remove();
		        		$("#tpyCompany").prepend("<select id='editable-select' name='tpyCompany'></select>");
					}
				$.ajax({  
					        type : "post",  
					        contentType : "application/json",  
					        url : "${ctx}/UserRegister/corpList?officeId="+officeId,  
					        dataType : "json",  
					        success : function(data) {
					        	var append1 ="<option value='";
					        	var append2 = "'>";
					        	var append3 = "</option>";
					    	if(data.corpList[0]!="查无数据"){
					        	for(var i=0;i<data.corpList.length;i++){
					        			var append = append1+data.corpList[i]+append2+data.corpList[i]+append3;
					        			$("#editable-select").empty();
					        			$("#editable-select").append(append);
					        		}
					        	}
					        		$("#editable-select").editableSelect({ 
					    			    effects: 'slide' 
					    			});
					        },  
					        error : function() {  
					          alert("error");  
					        }  
					      });  
				}	
	</script>
</head>
<body>
	<div id="importBox" class="hide">
		<form id="importForm" action="${ctx}/sys/user/import" method="post" enctype="multipart/form-data"
			class="form-search" style="padding-left:20px;text-align:center;" onsubmit="loading('正在导入，请稍等...');"><br/>
			<input id="uploadFile" name="file" type="file" style="width:330px"/><br/><br/>　　
			<input id="btnImportSubmit" class="btn btn-primary" type="submit" value="   导    入   "/>
			<a href="${ctx}/sys/user/import/template">下载模板</a>
		</form>
	</div>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/queryinfo/info/queryzrrtpyList">自然人特派员列表</a></li>
		<li class="active"><a href="${ctx}/queryinfo/info/queryfrtpyList">法人特派员列表</a></li>
		<li><a href="${ctx}/queryinfo/info/tpyviewtj">特派员图形统计</a></li>
	</ul>
		<form:form id="searchForm" modelAttribute="user" action="${ctx}/queryinfo/info/queryfrtpyList" method="post" class="breadcrumb form-search ">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
		<ul class="ul-form">			
			<li><label>姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：</label><form:input path="name" htmlEscape="false" maxlength="50" class="input-medium"/></li>
			<li><label>归属部门：</label><sys:treeselect id="office" name="office.id" value="${user.office.id}" labelName="office.name" labelValue="${user.office.name}"
								title="部门" url="/UserRegister/treeData?type=2" cssClass="required" notAllowSelectParent="true"/></li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" onclick="return page();"/></li>		
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><th>归属部门</th><th>姓名</th><th>法人代表</th><th>法人电话</th><th>联系人</th><th>联系人电话</th><th>审核状态</th><th>操作</th></tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="user">
			<tr>
				<td>${user.office.name}</td>
				<td>${user.name}</td>			
				<td>${user.corpLegRepName}</td>
				<td>${user.mobile}</td>
				<td>${user.corpCorName}</td>
				<td>${user.corpCorPhone}</td>					
				<td><c:choose>
						<c:when test="${user.checkFlag == '0'}">
							<font color=green >未审核</font> 
						</c:when>
						<c:when test="${user.checkFlag == '1'}">
							<font color=red >未通过</font> 
						</c:when>
						<c:when test="${user.checkFlag == '2'}">
							<font color=blue >通过</font> 
						</c:when>
				</c:choose></td>			
				<td>
    				<a href="${ctx}/queryinfo/info/querytpyInfo?id=${user.id}">查看详情</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>