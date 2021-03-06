<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>推荐表填写</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		$(function(){
			$('button.btn-add-job').on('click',function(){
			$('tbody.tbody-job-list').append($('tbody.tbody-job-list tr:last').prop('outerHTML'));
			});
		});
		function removeJob(_this){
			if($('tr.tr-length').length<=1){
				return;
			}
		$(_this).parent().parent().remove();
		};
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/sys/user/tjTable">科技特派员推荐表</a></li>
	</ul>
	 <form action="${ctx}/sys/user/tjInfoSave" method="post" class="form-inline">
	 <table id="contentTable" class="table  table-bordered  ">
		<caption><h3>科技特派员推荐表</h3></caption>
		<tbody>
			<tr>
				<td>姓名:</td>
				<td>
            	<input type="text" value="${user.name}" class="form-control"  required readonly>
            	<span class="help-inline"><font color="red">*</font> </span>
				</td>
				<td>性别:</td>
				<td>
				<input type="text" value="${user.sex}" class="form-control"  disabled>
				<span class="help-inline"><font color="red">*</font> </span>
				</td>
				<td>出生年月:</td>
				<td>
				<input type="text" value="${user.tpyBirthDate}" class="form-control"  disabled>
				<span class="help-inline"><font color="red">*</font> </span>
				</td>
			</tr>
			<tr>
				<td>籍贯:</td>
				<td>
				<input type="text" value="${user.tpyLocation}" class="form-control" disabled>
				<span class="help-inline"><font color="red">*</font> </span>
				</td>
				<td>专业:</td>
				<td>
				<input type="text" value="${user.tpyMajor}" class="form-control" disabled>
				<span class="help-inline"><font color="red">*</font> </span>
				</td>
				<td>民族:</td>
				<td>
				 
				  <input type="text" name="tpyNation" class="form-control" placeholder="请输入民族" htmlEscape="false" />
				  <span class="help-inline"><font color="red">*</font> </span>
				</td>
			</tr>
			<tr>
				<td>政治面貌:</td>
				<td>
				<input type="text" name="tpyPoliticalStatus" class="form-control" placeholder="请输入政治面貌" htmlEscape="false" required  />
				<span class="help-inline"><font color="red">*</font> </span>
				</td>
				<td>健康状况:</td>
				<td>
				 <input type="text" name="tpyHealthCondition"  class="form-control" placeholder="请输入健康状况" htmlEscape="false" required  />
				 <span class="help-inline"><font color="red">*</font> </span>
				</td>
				<td>婚姻状况:</td>
				<td>
				 <input type="text" name="tpyMaritalStatus" class="form-control" placeholder="请输入婚姻状况" htmlEscape="false"  required />
				 <span class="help-inline"><font color="red">*</font> </span>
				</td>
			</tr>
			 <tr>
				<td>是否市际间调配:</td>
				<td>
				 <input type="text" name="tpyIsDeploy" class="form-control" placeholder="请输入调配情况" htmlEscape="false" required />
				 <span class="help-inline"><font color="red">*</font> </span>
				</td>
				<td>从何市调配:</td>
				<td>
				 <input type="text" name="tpySource" class="form-control" placeholder="请输入来源" htmlEscape="false" />
				</td>
				<td>拟分配的服务地点:</td>
				<td>
				 <input type="text" name="tpyServiceSite" class="form-control" placeholder="请输入服务地点" htmlEscape="false" required />
				 <span class="help-inline"><font color="red">*</font> </span>
				</td>
			</tr>
			<tr>
			<td>拟开展的服务内容</td>
			<th colspan="5">
			<textarea name="tpyServiceContent" style="width:700px;" class="form-control" rows="3"></textarea>
			<span class="help-inline"><font color="red">*</font> </span>
			</th>
			</tr>
			<tr>
			<td>奖惩情况:</td>
			<th colspan="5">
			<textarea name="tpyRewardStatus" style="width:700px;" class="form-control" rows="3"></textarea>
			<span class="help-inline"><font color="red">*</font></span>
			</th>
			</tr>
		</tbody>
	 </table>
	 <table class ="table table-bordered margin-top-0 emi-table">
	 <tr><h5>工作经历</h5></tr>
	 <tr>
	 <td><button type="button" class="btn btn-success btn-xs btn-add-job">新增工作经历</button></td>
	 <td>
	 <table class ="table table-bordered">
	 <thead>
	 <tr>
	 <th>单位名称</th>
	 <th>入职时间</th>
	 <th>离职时间</th>
	 <th>岗位</th>
	 <th>操作</th>
	 </tr>
	 </thead>
	 <tbody class="tbody-job-list">
	 <tr class="tr-length">
	 <td><input type="text"  name="tpyCorpName"  placeholder="单位名称" required /><span class="help-inline"><font color="red">*</font></span></td>
	 <td><input type="text" name="tpyWorkDate" placeholder="入职时间" id="d4311" class="Wdate" type="text" required onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd',isShowClear:false,maxDate:'#F{$dp.$D(\'d4312\')||\'%y-%M-%d\'}'})" /><span class="help-inline"><font color="red">*</font></span></td>
	 <td><input type="text" name="tpyLeaveDate" placeholder="离职时间"  id="d4312" class="Wdate" type="text" required onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd',isShowClear:false,minDate:'#F{$dp.$D(\'d4311\')}',maxDate:'%y-%M-%d'})"/><span class="help-inline"><font color="red">*</font></span></td>
	 <td><input type="text" name="tpyWork"  placeholder="岗位" required/><span class="help-inline"><font color="red">*</font></span></td>
	 <td><button type="button" class="btn btn-default btn-xs" onclick="removeJob(this)">删除</button></td>
	 </tr>
	 </tbody>
	 </table>
	 </td>
	 </tr>
	 </table>
	 <div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>
			<input id="btnCancel" class="btn btn-default" type="button" value="返 回" onclick="history.go(-1)"/>
	</div>
	 </form>
</body>
</html>