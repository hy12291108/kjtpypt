<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>基本信息</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	$(document).ready(function() {
	
	}); 
	</script>

</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/queryinfo/info/villageList">贫困村列表</a></li>	
		<li class="active"><a href="${ctx}/queryinfo/info/serviceInformation?id=${village.id}">相关信息查看</a></li>
	</ul>
	<form action="" id="" method="post" class="form-horizontal">
	<div class="emi-box">
	<sys:message content="${message}"/>
	<c:if test="${!empty msg}">
	<div id ="div" class="alert alert-success alert-dismissible" role="alert">
	  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	 ${msg}
	</div>
	</c:if> 
	<tr><td colspan="6"><h4>贫困村信息</h4></td></tr>
	<table id="contentTable" class="table  table-bordered table-condensed">	
		<tr>
		<td>村名：</td>
		<td>${village.villageName}</td>
		<td>村支书：</td>
		<td>${village.secretaryName}</td>
		<td>村支书联系方式：</td>
		<td>${village.secretaryPhone}</td>
		</tr>
		<tr>
		<td>负责人：</td>
		<td>${village.deputy}</td>
		<td>负责人联系方式：</td>
		<td>${village.deputyPhone}</td>
		<td>户数：</td>
		<td>${village.houseNumber}</td>
		</tr>
		<tr>
		<td>人口：</td>
		<td>${village.population}</td>
		<td>贫困户数：</td>
		<td>${village.poorNumber}</td>
		<td>贫困人口：</td>
		<td>${village.poorPopulation}</td>
		</tr>
		<tr>
		<td>产业状况：</td>
		<td>${village.estateInfo}</td>
		<td>科技需求：</td>
		<td colspan="3">${village.scienceNeed}</td>
		</tr>
	</table>
	<tr><td colspan="6"><h4>特派团信息</h4></td></tr>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><th class="sort-column name">团队名称</th><th>服务方向</th><th>开始时间</th><th>结束时间</th><th>操作</th></tr></thead>
		<tbody>
		<c:forEach items="${village.serviceTeam}" var="serviceTeam">
		<tr>
		<td>${serviceTeam.teamName}</td>
		<td>${serviceTeam.serviceCyfx}</td>
		<td>${serviceTeam.startTime}</td>
		<td>${serviceTeam.endTime}</td>
		<td>
		<a href="${ctx}/queryinfo/info/teamMemberInfo?teamId=${serviceTeam.id}">查看</a>
		</td>
		</tr>
		</c:forEach>
		</tbody>
	</table>
	</div>
	<div class="table-bordered-bt">
	<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
	</div>
	</form>
</body>
</html>