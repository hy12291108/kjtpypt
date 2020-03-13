<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>三区人才申请</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
		 if($("#tpyInfoId").val()==null||$("#tpyInfoId").val()==""){
					//跳转首页
					if(self.frameElement && self.frameElement.tagName == "IFRAME" || $('#left').length > 0 || $('.jbox').length > 0){
						top.location = "${ctx}";
						}
			} 
		});
		function  onSubmit(){
		//alert("111");
			 if($("#year").val()==""||$("#year").val==null){
				 return false;
			 }
			 if($("#personalInfo").val()==""||$("#personalInfo").val==null){
			     alert("个人介绍不能为空！");
			     return false;
			 }
			// alert("222");
			$("#tpySqThreeArea").attr("action","${ctx}/threeSq/tpySqThreeArea");
			$("#tpySqThreeArea").submit();
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/threeSq/threeAreaSq">三区人才申请</a></li>
	</ul>
	<c:if test="${!empty message}">
		<div id ="div" class="alert alert-success alert-dismissible" role="alert">
			 <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			 ${message}
		</div>
	</c:if>
	 <form id="tpySqThreeArea" method="post" class="form-inline">
	 <input type="hidden" name="id" value="${threeArea.id}">
	 <input type="hidden" id="tpyInfoId" value="${tpyInfo.id}">
	 <div>
	 <table id="contentTable" class="table  table-bordered  ">
		<tbody>
			<tr>
				<td>姓名:</td>
				<td>
            	<input type="text" name="name" value="${user.name}" class="form-control"  readonly>
				</td>
				<td>性别:</td>
				<td>
				<input type="text" name="sex" value="${fns:getDictLabels(user.sex,'sex',user.sex)}" class="form-control"  readonly>
				</td>
				<td>出生年月:</td>
				<td>
				<input type="text" name="tpyBirthDate" value="${user.tpyBirthDate}" class="form-control"  readonly>
				</td>
			</tr>
			<tr>
				<td>民族:</td>
				<td>
				  <input type="text" name="tpyNation" class="form-control" value="${tpyInfo.tpyNation}" readonly/>
				</td>
				<td>E-mail:</td>
				<td>
				<input type="text" name="email" value="${user.email}" class="form-control" readonly>
				</td>
				<td>手机号码:</td>
				<td>
				<input type="text" name="mobile" value="${user.mobile}" class="form-control" readonly>
				</td>
			</tr>
			<tr>
				<td>专业:</td>
				<td>
				 <input type="text" name="tpyMajor"  class="form-control" value="${user.tpyMajor}" readonly />
				</td>
				<td>学历:</td>
				<td>
				 <input type="text" name="tpyQulification" class="form-control" value="${fns:getDictLabels(user.tpyQulification,'tpy_qulification',user.tpyQulification)}" readonly />
				</td>
				<td>政治面貌:</td>
				<td>
				<input type="text" name="tpyPoliticalStatus" class="form-control" value="${tpyInfo.tpyPoliticalStatus}" readonly/>
				</td>
			</tr>
			<tr>
				<td>专业特长:</td>
				<td colspan="3">
				<textarea name="tpySpecial" style="width:700px;" class="form-control"  rows="1" readonly>${user.tpySpecial}</textarea>
				</td>
				<td>年度:</td>
				<td>
				<!-- <input type="text" name="year" id="d243" onclick="WdatePicker({skin:'whyGreen',readOnly:true,dateFmt:'yyyy年'})" class="Wdate" required/>
				<span class="help-inline"><font color="red">*</font> </span> -->
				<input type="text" name="year" id="year" value="${threeAreaBaseData.year}" class="form-control" readonly/>
				</td>
			</tr>
			<tr>
				<td>个人介绍:</td>
				<th colspan="5">
				<textarea name="personalInfo" id="personalInfo"  style="width:700px;" class="form-control"  rows="5" required></textarea>
				<span class="help-inline"><font color="red">*</font> </span>
				</th>
			</tr>
		</tbody>
	 </table>
	</div>
	 <div>
		<h4 id="h5">工作经历</h4>
		<table class="table table-bordered">
						<thead>
							<tr>
								<th>单位名称</th>
								<th>入职时间</th>
								<th>离职时间</th>
								<th>岗位</th>
							</tr>
						</thead>
						<tbody class="tbody-job-list">
						<c:forEach items="${experience}" var="experience">
							<tr class="tr-length">
								<td>${experience.tpyCorpName}</td>
								<td>${experience.tpyWorkDate}</td>
								<td>${experience.tpyLeaveDate}</td>
								<td>${experience.tpyWork}</td>
							</tr>
						</c:forEach>
						</tbody>
					</table>
	</div>
	<div class="form-actions">	
	<input id="btnSubmit" class="btn btn-primary" type="submit" value="提交" onclick="onSubmit()"/>	
<%-- 			<button type="button" class="btn btn-default btn-xs" ><a href="${ctx}/UserSh/baseInfo">修改信息</a></button>
	 --%>
	</div>
</form>
</body>
</html>