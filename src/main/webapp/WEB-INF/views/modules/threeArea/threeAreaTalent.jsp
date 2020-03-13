<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>三区人才</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
		 /* if($("#tpyInfoId").val()==null||$("#tpyInfoId").val()==""){
					//跳转首页
					if(self.frameElement && self.frameElement.tagName == "IFRAME" || $('#left').length > 0 || $('.jbox').length > 0){
						top.location = "${ctx}";
						}
			}  */
		});
		function page(n,s){
			if(n) $("#pageNo").val(n);
			if(s) $("#pageSize").val(s);
			$("#searchForm").attr("action","${ctx}/threeSq/threeAreaTalent");
			$("#searchForm").submit();
	    	return false;
	    }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li ><a href="${ctx}/threeSq/threeAreaCheck">三区人才审核列表</a></li>
		<li class="active"><a href="${ctx}/threeSq/threeAreaTalent">审核通过三区人才</a></li>
		<li ><a href="${ctx}/threeSq/getFailTalent">审核未通过三区人才</a></li>
	</ul>
	<c:if test="${!empty message}">
		<div id ="div" class="alert alert-success alert-dismissible" role="alert">
			 <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			 ${message}
		</div>
	</c:if>
	<form:form id="searchForm" modelAttribute="threeArea" action="${ctx}/threeSq/threeAreaTalent" method="post" class="breadcrumb form-search ">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
		<ul class="ul-form">
			<li><label>姓&nbsp;&nbsp;名：</label><form:input path="name" htmlEscape="false" maxlength="50" class="input-medium"/></li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" onclick="return page();"/>
			<li class="clearfix"></li>
		</ul>
	</form:form> 
	 <%-- <input type="hidden" name="id" value="${threeArea.id}"> --%>
	 <div>
		<table class="table table-bordered">
						<thead>
							<tr>
								<th>年度</th>
								<th>管辖部门</th>
								<th>姓名</th>
								<th>性别</th>
								<th>Email</th>
								<th>手机号码</th>
								<th>学历</th>
								<th>专业</th>
								<!-- <th>操作</th> -->
							</tr>
						</thead>
						<tbody class="tbody-job-list">
						<c:forEach items="${page.list}" var="threeArea">
							<tr class="tr-length">
								<td>${threeArea.year}</td>
								<td>${threeArea.zoneName}</td>
								<td>${threeArea.name}</td>
								<td>${threeArea.sex}</td>
								<td>${threeArea.email}</td>
								<td>${threeArea.mobile}</td>
								<td>${threeArea.tpyQulification}</td>
								<td>${threeArea.tpyMajor}</td>
								<!-- <td>
								下派
								</td> -->
							</tr>
						</c:forEach>
						</tbody>
					</table>
					<div class="pagination">${page}</div>
	</div>
	<%-- <form action="${ctx}/UserSh/updateXp" method="get">
<div class="modal fade" tabindex="-1" role="dialog" id="xpTpy" aria-labelledby="gridSystemModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="gridSystemModalLabel">下派区域选择</h4>
      </div>
     
      <div class="modal-body">
      <input type="hidden" value="" id="userId" name="id">
   		<sys:treeselect id="area" name="area.id" value="${office.area.id}" labelName="area.name" labelValue="${office.area.name}"
					title="区域" url="/sys/area/treeData1" cssClass="required"/>
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
</form> --%>
	
	
</body>
</html>