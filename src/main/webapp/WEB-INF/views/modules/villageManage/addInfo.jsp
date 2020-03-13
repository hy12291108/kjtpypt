<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>贫困村基本信息录入</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/VillageManage/list">贫困村列表</a></li>
		<shiro:hasPermission name="sys:user:edit"><li class="active"><a href="${ctx}/VillageManage/addInfo">添加贫困村</a></li></shiro:hasPermission>
	</ul>
	<form action="${ctx}/VillageManage/saveInfo" modelAttribute="village" method="post"  class="form-horizontal">
	<div class="emi-box">
	<table  class="table  table-bordered table-condensed ">
		<tbody>
			<tr>
				<td>村名:</td>
				<td colspan="3">
					<input type="text" name="villageName" placeholder="村名" class="form-control"  required />
            	<span class="help-inline"><font color="red">*</font> </span>
				</td>
			</tr>
			<tr>
				<td>村书记:</td>
				<td>
				<input type="text"  name="secretaryName" placeholder="村书记" class="form-control"  required />
				<span class="help-inline"><font color="red">*</font> </span>
				</td>
				<td>村书记号码：</td>
				<td>
				<input type="text" name="secretaryPhone" placeholder="村书记号码" class="form-control" required />
				<span class="help-inline"><font color="red">*</font> </span>
				</td>
			</tr>
			<tr>
				<td>联系人:</td>
				<td>
				<input type="text" name="deputy" placeholder="联系人"  class="form-control" required />
				<span class="help-inline"><font color="red">*</font> </span>
				</td>
				<td>联系人手机号码:</td>
				<td>
				<input type="text"  name="deputyPhone" placeholder="手机号码" class="form-control" required />
				<span class="help-inline"><font color="red">*</font> </span>
				</td>
				
			</tr>
			<tr>
				<td>户数（户）:</td>
				<td>
				  <input type="text" name="houseNumber"  id="houseNumber" class="form-control" placeholder="户数"  onkeyup="if(!/^\d+$/.test(this.value)) tip.innerHTML='只能输入数字。'; else tip.innerHTML='';"  required/>
				  <span id="tip" class="help-inline"><font color="red">*</font> </span>
				</td>
				<td>人口（人）:</td>
				<td>
				 <input type="text" name="population"  class="form-control" placeholder="人口"  onkeyup="if(!/^\d+$/.test(this.value)) tip1.innerHTML ='只能输入数字。'; else tip1.innerHTML='';"   required/>
				 <span id="tip1" class="help-inline"><font color="red">*</font> </span>
				</td>
			</tr>
			<tr>
				<td>贫困户数（户）:</td>
				<td>
				 <input type="text" name="poorNumber" class="form-control" placeholder="贫困人口" onkeyup="if(!/^\d+$/.test(this.value)) tip2.innerHTML ='只能输入数字。'; else tip2.innerHTML='';"   required />
				 <span id="tip2" class="help-inline"><font color="red">*</font> </span>
				</td>
				<td>贫困人口（人）:</td>
				<td>
				 <input type="text" name="poorPopulation" class="form-control" placeholder="贫困人口" onkeyup="if(!/^\d+$/.test(this.value)) tip3.innerHTML ='只能输入数字。'; else tip3.innerHTML='';"  required />
				 <span id="tip3" class="help-inline"><font color="red">*</font> </span>
				</td>
			</tr>
			 <tr>
				<td>产业现状:</td>
				<td colspan="3">
				 	<textarea name="estateInfo" class="form-control" placeholder="产业现状" rows="" cols="5" style="margin: 0px; width: 900px; height: 40px;" ></textarea>
				</td>
				<input id="zoneId" type="hidden" name="zoneId" class="form-control"/>
			</tr>
			<tr>
				<td>科技需求:</td>
				<td colspan="3">
					<textarea name="scienceNeed" class="form-control" placeholder="科技需求" rows="" cols="3" style="margin: 0px; width: 900px; height: 40px;" ></textarea>
				</td>
			</tr>
		</tbody>
	 </table>
	</div>
		<div class="form-actions table-bordered-bt">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存" />
			<input id="btnCancel" class="btn btn-default" type="button" value="返 回" onclick="history.go(-1)" />
		</div>	
	</form>
	
</body>
</html>