<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>贫困村基本信息录入</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	$(document).ready(function() {
		var message = $("#input").val(); 
		if(message!=null&&message!=''){
			document.getElementById("div").style.display="";
		};
	});
	function page(n,s){
		if(n) $("#pageNo").val(n);
		if(s) $("#pageSize").val(s);
		$("#searchForm").attr("action","${ctx}/queryinfo/info/villageList");
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
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/queryinfo/info/villageList">贫困村列表</a></li>
		<%-- <shiro:hasPermission name="sys:user:edit"><li><a href="${ctx}/queryinfo/info/addInfo">添加贫困村</a></li></shiro:hasPermission> --%>
	</ul>
	<form:form id="searchForm" modelAttribute="village" action="${ctx}/queryinfo/info/villageList" method="post" class="breadcrumb form-search ">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
		<ul class="ul-form">
			<li><label>村&nbsp;名：</label><form:input path="villageName" htmlEscape="false" maxlength="100" class="input-medium"/></li>
			<li><label>村书记:</label><form:input path="secretaryName" htmlEscape="false" maxlength="50" class="input-medium"/></li>
			<li><label>归属部门：</label><sys:treeselect id="office" name="office.id" value="${user.office.id}" labelName="office.name" labelValue="${user.office.name}"
								title="部门" url="/UserRegister/treeData?type=2" cssClass="required" notAllowSelectParent="true"/></li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" onclick="return page();"/>
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
		<thead><tr><th>归属部门</th><th class="sort-column name">村名</th><th>村书记</th><th>村书记号码</th><th class="sort-column name">联系人</th><th>联系人电话</th><shiro:hasPermission name="sys:user:edit"><th>操作</th></shiro:hasPermission></tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="village">
			<tr>
				<td>${village.office.name}</td>
				<td>${village.villageName}</td>
				<td>${village.secretaryName}</td>
				<td>${village.secretaryPhone}</td>
				<td>${village.deputy}</td>
				<td>${village.deputyPhone}</td>			
				<shiro:hasPermission name="sys:user:edit"><td>
    				<a href="${ctx}/queryinfo/info/serviceInformation?id=${village.id}">查看</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>