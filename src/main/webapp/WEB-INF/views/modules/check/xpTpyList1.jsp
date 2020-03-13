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
			$("#searchForm").attr("action","${ctx}/UserSh/tpyXp");
			$("#searchForm").submit();
	    	return false;
	    }
		function xpTpy(id){
			$("#userId").val(id);
			$("#xpTpy").modal();
		}
	</script>
</head>
<body>

	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/UserSh/tpyXp?flag=1">特派员列表</a></li>
		<li ><a href="${ctx}/UserSh/tpyXp?flag=2">已下派特派员列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="user" action="${ctx}/UserSh/tpyXp" method="post" class="breadcrumb form-search ">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
		<ul class="ul-form">
		<%-- <li><label>登录名：</label><form:input path="loginName" htmlEscape="false" maxlength="50" class="input-medium"/></li>
		<li><label>姓&nbsp;&nbsp;&nbsp;名：</label><form:input path="name" htmlEscape="false" maxlength="50" class="input-medium"/></li>
		<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" onclick="return page();"/>
				<!-- <input id="btnExport" class="btn btn-primary" type="button" value="导出"/>
				<input id="btnImport" class="btn btn-primary" type="button" value="导入"/></li> -->
		<li class="clearfix"></li> --%>
		<li><label>登录名：</label><form:input path="loginName" htmlEscape="false" maxlength="50" class="input-medium"/></li>
		<li class="clearfix"></li>
		<li><label>姓&nbsp;&nbsp;&nbsp;名：</label><form:input path="name" htmlEscape="false" maxlength="50" class="input-medium"/></li>
		<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" onclick="return page();"/>
				<!-- <input id="btnExport" class="btn btn-primary" type="button" value="导出"/>
				<input id="btnImport" class="btn btn-primary" type="button" value="导入"/></li> -->
		<li class="clearfix"></li>
		</ul>
	</form:form>
	<%-- <sys:message content="${message}"/> --%>
	<c:if test="${!empty message}">
		<div id ="div" class="alert alert-success alert-dismissible" role="alert">
			 <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			 ${message}
		</div>
	</c:if>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><th>省份</th><th>部门</th><th class="sort-column login_name">登录名</th><th class="sort-column name">姓名</th><th>专业</th><th>座机</th><th>手机</th><th>状态</th></tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="user">
			<tr>
				<td>陕西省</td>
				<td>${user.office.name}</td>
				<td>${user.loginName}</td>
				<td>${user.name}</td>
				<td>${user.tpyMajor}</td>
				<td>${user.phone}</td>
				<td>${user.mobile}</td>
				<td>
    			<button type="button" class="btn btn-primary" data-toggle="modal" onclick="xpTpy('${user.id}')"data-target=".bs-example-modal-sm">下派</button>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
<form action="${ctx}/UserSh/updateXp" method="get">
<div class="modal fade" tabindex="-1" role="dialog" id="xpTpy" aria-labelledby="gridSystemModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="gridSystemModalLabel">下派区域选择</h4>
      </div>
      <div class="modal-body">
      <input type="hidden" value="" id="userId" name="id">
   		<%-- <sys:treeselect id="area" name="area.id" value="${office.area.id}" labelName="area.name" labelValue="${office.area.name}"
					title="区域" url="/sys/area/treeData1" cssClass="required"/> --%>
     <table>
     <tr>
     <td>下派区域：</td>
     <td><sys:treeselect id="area" name="area.id" value="${office.area.id}" labelName="area.name" labelValue="${office.area.name}"
					title="区域" url="/sys/area/treeData1" cssClass="required"/></td>
    </tr>
    <input type="hidden" value="${time}" name="time">
    <tr>
		<td>下派开始时间:</td>
		<td>
		<input name="xpStartTime" placeholder="开始时间" id="d4311" class="Wdate" type="text"  onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd',isShowClear:false,minDate:'%y-%M-%d'})" required/>
		<span class="help-inline"><font color="red">*</font> </span>
		</td>
	</tr>
	<tr>
		<td>下派结束时间:</td>
		<td>
		<input name="xpEndTime" placeholder="结束时间"  id="d4312" class="Wdate" type="text"  onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd',isShowClear:false,minDate:'#F{$dp.$D(\'d4311\')}'})" required/>
		<span class="help-inline"><font color="red">*</font> </span>
		</td>
	</tr>
    </table>
    </div>
      <div class="modal-footer">
      <button type="submit" class="btn btn-primary">保存</button>
      <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
      </div>
    </div>
  </div>
</div>
</form>
</body>
</html>