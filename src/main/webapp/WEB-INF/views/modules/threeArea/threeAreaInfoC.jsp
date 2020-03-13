<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>三区人才申请</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
		/*  if($("#tpyInfoId").val()==null||$("#tpyInfoId").val()==""){
					//跳转首页
					if(self.frameElement && self.frameElement.tagName == "IFRAME" || $('#left').length > 0 || $('.jbox').length > 0){
						top.location = "${ctx}";
						}
			}  */
		});
		/* $(function(){
			$('button.btn-add-job').on('click',function(){
			$('tbody.tbody-job-list').append($('tbody.tbody-job-list tr:last').prop('outerHTML'));
			});
		});
		function removeJob(_this){
			if($('tr.tr-length').length<=1){
				return;
			}
		$(_this).parent().parent().remove();
		}; */
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li ><a href="${ctx}/threeSq/threeAreaCheck">三区人才申请</a></li>
		<li class="active"><a href="${ctx}/threeSq/threeAreaInfoCheck?id=${threeArea.id}">个人信息</a></li>
	</ul>
	<c:if test="${!empty message}">
		<div id ="div" class="alert alert-success alert-dismissible" role="alert">
			 <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			 ${message}
		</div>
	</c:if>
	 <form action="${ctx}/threeSq/updatestatus"  method="post" class="form-inline">
	 <input type="hidden" name="id" value="${threeArea.id}">
	 <div>
	 <table id="contentTable" class="table  table-bordered  ">
		<tbody>
			<tr>
				<td>姓名:</td>
				<td>
            	<input type="text" name="name" value="${threeArea.name}" class="form-control"  readonly>
				</td>
				<td>性别:</td>
				<td>
				<input type="text" name="sex" value="${threeArea.sex}" class="form-control"  readonly>
				</td>
				<td>出生年月:</td>
				<td>
				<input type="text" name="tpyBirthDate" value="${threeArea.tpyBirthDate}" class="form-control"  readonly>
				</td>
			</tr>
			<tr>
				<td>民族:</td>
				<td>
				  <input type="text" name="tpyNation" class="form-control" value="${threeArea.tpyNation}" readonly/>
				</td>
				<td>E-mail:</td>
				<td>
				<input type="text" name="email" value="${threeArea.email}" class="form-control" readonly>
				</td>
				<td>手机号码:</td>
				<td>
				<input type="text" name="mobile" value="${threeArea.mobile}" class="form-control" readonly>
				</td>
			</tr>
			<tr>
				<td>专业:</td>
				<td>
				 <input type="text" name="tpyMajor"  class="form-control" value="${threeArea.tpyMajor}" readonly />
				</td>
				<td>学历:</td>
				<td>
				 <input type="text" name="tpyQulification" class="form-control" value="${threeArea.tpyQulification}" readonly />
				</td>
				<td>政治面貌:</td>
				<td>
				<input type="text" name="tpyPoliticalStatus" class="form-control" value="${threeArea.tpyPoliticalStatus}" readonly/>
				</td>
			</tr>
			<tr>
				<td>专业特长:</td>
				<td colspan="3">
				<textarea name="tpySpecial" style="width:700px;" class="form-control"  rows="1" readonly>${threeArea.tpySpecial}</textarea>
				</td>
				<td>年度:</td>
				<td >
				<input type="text" name="year" class="form-control" value="${threeArea.year}" readonly/>
				</td>
			</tr>
			<tr>
				<td>个人介绍:</td>
				<th colspan="5">
				<textarea name="personalInfo" style="width:700px;"   rows="5" readonly>${threeArea.personalInfo}</textarea>
				</th>
			</tr>
			<tr>
						<td><lable>审核结果:</lable></td>
						<td>
							<select name="status" class="selectpicker">
							  <option value="2">通过</option>
							  <option value="3">退回</option>
							</select>
							<span class="help-inline"><font color="red">*</font></span>
						</td>
						<td><lable>审核人:</lable></td>
						<td>
							<input name="checkPersonC" type="text" value="${threeArea.checkPersonC}" readonly>
						</td>
						<td><lable>审核时间:</lable></td>
						<td>
							<input name="checkTimeC" type="text" value="${threeArea.checkTimeC}" readonly>
						</td>
						
				</tr>
				<tr>
						<td>
						<lable>审核意见:</lable></td>
						<td  colspan="5">
							<textarea name="checkAdviceC" rows="3"  placeholder="审核意见" style="width:1024px" required></textarea>
							<span class="help-inline"><font color="red">*</font></span>
						</td>
				</tr>
		</tbody>
	 </table>
	</div>
	<div>
	<%-- <table  class="mytable">
				<tr>
						<td><lable>审核人:</lable></td>
						<td>
							<input name="checkPersonC" type="text" value="${threeArea.checkPersonC}" readonly>
						</td>
						<td><lable>审核时间:</lable></td>
						<td>
							<input name="checkTimeC" type="text" value="${threeArea.checkTimeC}" readonly>
						</td>
						<td><lable>审核结果:</lable></td>
						<td>
							<form:select path="checkFlag" class="input-xlarge">
							<form:options items="${fns:getDictList('check_flag')}" itemLabel="label" itemValue="value" htmlEscape="false" class="required"/>
							</form:select>
							<span class="help-inline"><font color="red">*</font></span>
							<select name="status" class="selectpicker">
							  <option value="2">通过</option>
							  <option value="3">退回</option>
							</select>
							<span class="help-inline"><font color="red">*</font></span>
						</td>
				</tr>
				<tr>
						<td>
						<lable>审核意见:</lable></td>
						<td  colspan="5">
							<textarea name="checkAdviceC" rows="3"  placeholder="审核意见" style="width:1024px" required></textarea>
							<span class="help-inline"><font color="red">*</font></span>
						</td>
				</tr>
			</table>
	</div> --%>
	 <%-- <div>
		<h5 id="h5">工作经历</h5>
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
	</div> --%>
	<div class="form-actions">
		<input id="btnSubmit" class="btn btn-primary" type="submit" value="保存" />&nbsp;
		<input id="btnCancel"  type="button" class="btn btn-default" value="返回" onclick="history.go(-1)"/>
	</div>
	</form>
</body>
</html>