<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>贫困村基本信息录入</title>
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
		$("#searchForm").attr("action","${ctx}/queryinfo/info/serviceTeamList");
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
		<li class="active"><a href="${ctx}/queryinfo/info/serviceTeamList">科技特派团列表列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="serviceTeam" action="${ctx}/queryinfo/info/serviceTeamList" method="post" class="breadcrumb form-search ">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
		<ul class="ul-form">
			<li><label>特派团名称:</label><form:input path="teamName" htmlEscape="false" maxlength="50" class="input-medium"/></li>
			<li><label>贫困村名：</label><form:input path="village.villageName" htmlEscape="false" maxlength="100" class="input-medium"/></li>
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
		<thead><tr><th>归属部门</th><th class="sort-column name">特派团</th><th>贫困村</th><th>团队组建时间</th><th>服务开始时间</th><th class="sort-column name">服务结束时间</th><th>操作</th></tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="serviceTeam">
			<tr>
				<td>${serviceTeam.office.name}</td>
				<td>${serviceTeam.teamName}</td>
				<td>${serviceTeam.village.villageName}</td>
				<td><fmt:formatDate value="${serviceTeam.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td>${serviceTeam.startTime}</td>
				<td>${serviceTeam.endTime}</td>	
				<td>			
    				<a href="${ctx}/queryinfo/info/teamMemberInfo?teamId=${serviceTeam.id}">查看</a>
    			</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>