<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>贫困村基本信息</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	$(document).ready(function() {
		var scienceNeeds  = $("#scienceNeeds").val();
		$("#scienceNeed").val(scienceNeeds);
	});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<shiro:hasPermission name="sys:user:edit"><li class="active"><a href="${ctx}/VillageManage/villageInfo?id=${village.id}">修改贫困村信息</a></li></shiro:hasPermission>
	</ul>
	<form action="${ctx}/VillageManage/updateVillageInfo" modelAttribute="village" method="post"  class="form-horizontal">
	<div class="emi-box">
	<table  class="table  table-bordered">
		<tbody>
			<tr>
			<input type="hidden" name="id" value="${village.id}">
				<td><lable>村名:</lable></td>
				<td>
            	<input type="text" name="villageName" placeholder="村名" class="form-control" value="${village.villageName}" required >
            	<span class="help-inline"><font color="red">*</font> </span>
				</td>
				<td><lable>村书记:</lable></td>
				<td>
				<input type="text"  name="secretaryName" placeholder="村书记" class="form-control" value="${village.secretaryName}" required>
				<span class="help-inline"><font color="red">*</font> </span>
				</td>
				<td><lable>村书记号码：</lable></td>
				<td>
				<input type="text" name="secretaryPhone" placeholder="村书记号码" value="${village.secretaryPhone}" class="form-control" required>
				<span class="help-inline"><font color="red">*</font> </span>
				</td>
			</tr>
			<tr>
				<td><lable>联系人:</lable></td>
				<td>
				<input type="text" name="deputy" placeholder="联系人" value="${village.deputy}"  class="form-control" required>
				<span class="help-inline"><font color="red">*</font> </span>
				</td>
				<td></lable>联系人手机号码:</lable></td>
				<td>
				<input type="text"  name="deputyPhone" placeholder="手机号码" value="${village.deputyPhone}"  class="form-control" required>
				<span class="help-inline"><font color="red">*</font> </span>
				</td>
				<td><lable>户数:</lable></td>
				<td>
				  <input type="text" name="houseNumber" class="form-control" placeholder="户数" value="${village.houseNumber}"  required/>
				  <span class="help-inline"><font color="red">*</font> </span>
				</td>
			</tr>
			<tr>
				<td><lable>人口:</lable></td>
				<td>
				 <input type="text" name="population"  class="form-control" placeholder="人口" value="${village.population}"  required  />
				 <span class="help-inline"><font color="red">*</font> </span>
				</td>
				<td><lable>贫困户数:</lable></td>
				<td>
				 <input type="text" name="poorNumber" class="form-control" placeholder="贫困人口"  value="${village.poorNumber}" required />
				 <span class="help-inline"><font color="red">*</font> </span>
				</td>
				<td><lable>贫困人口:</lable></td>
				<td>
				 <input type="text" name="poorPopulation" class="form-control" placeholder="贫困人口" value="${village.poorPopulation}" required />
				 <span class="help-inline"><font color="red">*</font> </span>
				</td>
			</tr>
			 <tr>
				<td><lable>产业现状:</lable></td>
				<td>
				 <input type="text" name="estateInfo" class="form-control" placeholder="产业现状"  value="${village.estateInfo}"/>
				</td>
				<td><lable>科技需求:</lable></td>
				<td colspan="3">
				<textarea id="scienceNeed" name="scienceNeed" class="form-control" placeholder="科技需求"  rows="" cols="3" style="margin: 0px; width: 480px; height: 40px;" ></textarea>
				<input type="hidden" id="scienceNeeds" value ="${village.scienceNeed}">
				</td>
				<input id="zoneId" type="hidden" name="zoneId" class="form-control" value="${village.zoneId}" />
			</tr>
		</tbody>
	 </table>
	 
		<div class="form-actions table-bordered-bt">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>
			<input id="btnCancel" class="btn btn-default" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>	 
	 
	 
	 
	</div>
	</form>
	
</body>
</html>