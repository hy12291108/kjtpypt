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
			$("#searchForm").attr("action","${ctx}/sqtpy/tpy/Xqdwlist");
			$("#searchForm").submit();
	    	return false;
        }
   /*     function sqbf(id){
        	alert("aaaa");
        }*/
        function sqbf(id){
						$.ajax({  
					        type : "post",  
					        url : "${ctx}/sqtpy/tpy/selectbfgx", 
					        data : {"xqdwid":id},
					        dataType : "json",  
					        contentType: "application/x-www-form-urlencoded; charset=utf-8", 
					        success : function(data) {
					        if(data == ""){
					        	window.location.href = '${ctx}/sqtpy/tpy/Sqxqdw?id='+id;
            					
        					}else{
        						alert("当前需求单位已申请过帮扶！");
        					}				          				         
					        },  
					        error : function() {  
					          	alert("错误，请反馈给管理员！");  
					        }  
					      });  
		}
	
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/sqtpy/tpy/Xqdwlist">待申请需求单位列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="user" action="${ctx}/sqtpy/tpy/Xqdwlist" method="post" class="breadcrumb form-search ">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
		<ul class="ul-form">
		</ul>
	</form:form>
		<sys:message content="${message}"/>
		<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th class="sort-column name" class="tit">需求单位</th>
				<th  class="tit">联系人</th>
				<th  class="tit">联系人电话</th>
				<th  class="tit">技术需求</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="user">
			<tr>
				<td>${user.name}</td>
				<td>${user.corpCorName}</td>
				<td>${user.corpCorPhone}</td>
				<td>${fns:abbr(user.corpNeeds,40)}</td>
				<!--  <td><a href="${ctx}/sqtpy/tpy/Sqxqdw?id=${user.id}"><font color=blue >申请帮扶</font> </a> </td>-->
				
<!--				<td><button  onclick="sqbf('${user.id}')">申请帮扶</button> </td>-->
				<td><button id="${user.id}"  onclick="sqbf(this.id)">申请帮扶</button> </td>
			</tr>
		</c:forEach>
		</tbody>		
	</table>
	<div class="pagination">${page}</div>
</body>
</html>