<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>三区人才</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			if($("#xpYear").val()==""||$("#xpYear").val()==null){
				$("#xpButton").attr({"disabled":"disabled"});
			}
		});
		function xpTpy(id){
			$("#threeAreaId").val(id);
			$("#xpTpy").modal();
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/threeSq/threeAreaXp">下派人员列表</a></li>
	</ul>
	<c:if test="${!empty message}">
		<div id ="div" class="alert alert-success alert-dismissible" role="alert">
			 <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			 ${message}
		</div>
	</c:if>
	 
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
								<th>操作/下派区域</th>
							</tr>
						</thead>
						<tbody class="tbody-job-list">
						<c:forEach items="${threeArea}" var="threeArea">
							<tr class="tr-length">
								<td>${threeArea.year}</td>
								<td>${threeArea.zoneName}</td>
								<td>${threeArea.name}</td>
								<td>${threeArea.sex}</td>
								<td>${threeArea.email}</td>
								<td>${threeArea.mobile}</td>
								<td>${threeArea.tpyQulification}</td>
								<td>${threeArea.tpyMajor}</td>
								<td>
								<c:if test="${!empty threeArea.xpZone}">
								<!--  <button type="button" class="btn btn-primary" data-toggle="modal" data-target=".bs-example-modal-sm">已下派</button>
								-->
								${threeArea.xpZoneName}
								</c:if>
								<c:if test="${empty threeArea.xpZone}">
								
								<button id="xpButton" type="button" class="btn btn-primary" data-toggle="modal" onclick="xpTpy('${threeArea.id}')"data-target=".bs-example-modal-sm">下派</button>
								
								</c:if>
								</td>
							</tr>
						</c:forEach>
						</tbody>
					</table>
</div>
<form action="${ctx}/threeSq/updateXpInfo" method="post">
<div class="modal fade" tabindex="-1" role="dialog" id="xpTpy" aria-labelledby="gridSystemModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="gridSystemModalLabel">三区人才下派</h4>
      </div>
      <div class="modal-body">
      <input type="hidden" value="" id="threeAreaId" name="id"/>
     <table>
     <tr>
     <td>下派区域：</td>
     <td><sys:treeselect id="area" name="area.id" value="${office.area.id}" labelName="area.name" labelValue="${office.area.name}"
					title="区域" url="/sys/area/treeData3" cssClass="required"/></td>
    </tr>
   <!--  <tr>
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
	</tr> -->
	<tr>
		<td>下派人:</td>
		<td>
		<input name="xpPerson" placeholder="下派人" value="${xpPerson}"  type="text"  readOnly/>
		</td>
	</tr>
	<tr>
		<td>下派年度:</td>
		<td>
		<input name="xpYear" placeholder="下派年度"  id="xpYear" value="${year}" type="text"  readOnly/>
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