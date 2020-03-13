<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>贫困村基本信息录入</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	$(document).ready(function() {
		var message = $("#input").val(); 
		if(message!=null&&message!=''){
			document.getElementById("div").style.display="";
		};
	}); 
	/* function page(n,s){
		if(n) $("#pageNo").val(n);
		if(s) $("#pageSize").val(s);
		$("#searchForm").attr("action","${ctx}/VillageManage/villageServiceTeam");
		$("#searchForm").submit();
    	return false;
    }  */
    function page(n,s){
    	var radioId = $('input:radio[name="memberType"]:checked').val();
    	alert("radioId:"+radioId);
    	//alert($('input:checkbox').attr('checked'));
    	var pageNo = n;
    	var pageSize = s;
    	var name = $("#name").val();
    	var tpyMajor = $("#tpyMajor").val();
    	//alert(${ctx}/VillageManage/findTpyList);
    	 $.ajax({
    		url:"${ctx}/VillageManage/findTpyList",
    		data:{"pageNo":pageNo,"pageSize":pageSize,"name":name,"tpyMajor":tpyMajor},
    		type:"post",
    		dataType:"json", 
    		success:function(data){
    			var items="" ;
	        	 for(var i=0;i<data.page.list.length;i++){
		            	var td1 = "<tr><td>"  + data.page.list[i].name;
		            	var td2 = "</td><td>" + data.page.list[i].tpyCompany;
		            	var td3 = "</td><td>" + data.page.list[i].tpyTitle;
		            	var td4 = "</td><td>" + data.page.list[i].tpyMajor; 
		            	var td5 = "</td><td>" + data.page.list[i].mobile;
		            	var td6 = "</td><td>" + data.page.list[i].email;
		            	var td7 ;
		            	/* if(data.page.list[i].id==radioId){
		            		alert(111);
		            	}else{
		            		td7 = "</td><td><label><input name='memberType' type='radio'  value='"+data.page.list[i].id+"'/>团长</label>";
		            	} */
		        		td7 = "</td><td><label><input name='memberType' type='radio'  value='"+data.page.list[i].id+"'/>团长</label>";
		            	
		            	//var td8 = "</td><td><input type='checkbox' name='userId' value='"+data.page.list[i].id+"'/></td></tr>";
		            	var td8 = "</td></tr>";
		            	items = items+td1+td2+td3+td4+td5+td6+td7+td8;
		            	$("#contentTable1 tbody").html("");
		    			$("#contentTable1 tbody").append(items);
		            }
			        	 $(".pagination").html("");
			        	 $(".pagination").append(data.page.html);
    			}
    	})    	
    } 
 /*    function radioCheck(){
    	if($('input:radio[name="memberType"]:checked').val()!=""||$('input:radio[name="memberType"]:checked').val()!=null){
    		$('input:radio[name="memberType"]:checked').parent().parent().next().find('input:checkbox').attr('checked',true);
    	}
    } */
    
	/**
	 * 调用后台批量方法
	 */
	function insertBatch() {
		/* if(typeof($('input:radio[name="memberType"]:checked').parent().parent().next().find('input:checkbox').attr('checked'))=="undefined"){
			alert("未选团长或所选团长未勾选！");
			return;
		} */
		if($('input:radio[name="memberType"]:checked').val()==""||$('input:radio[name="memberType"]:checked').val()==null){
			alert("未选团长！");
		}
		 if($("#teamName").val()==""||$("#teamName").val()==null){
			 $("#teamName").focus();
			return;
		}
		if($("#serviceCyfx").val()==""||$("#serviceCyfx").val()==null){
			$("#serviceCyfx").focus();
			return;
		}
		if($("#d4311").val()==""||$("#d4311").val()==null){
			$("#d4311").focus();
			return;
		}
		if($("#d4312").val()==""||$("#d4312").val()==null){
			$("#d4312").focus();
			return;
		} 
		//console.log($('input:radio[name="memberType"]:checked').parent().parent().next().find('input:checkbox').attr('checked'));
		$("#insertBatch").attr("action","${ctx}/VillageManage/insertBatch");
		$("#insertBatch").submit(); 
	}
	</script>

</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/VillageManage/villageServiceTeam?id=${village.id}">特派员团队组建</a></li>
	</ul>
	<form action="" id="insertBatch" method="post">
	<input name="Id" value="${village.id}" type="hidden">
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-bordered table-condensed">
		<tr>
		<td colspan="2"><h6>需求11：${village.zoneId}</h6></td>
		<td colspan="2">${village.scienceNeed}</td>
		</tr>
		<tr>
		<td>团队名称:</td>
		<td>
		<input type="text" id="teamName" name="teamName" placeholder="团队名称" required/>
		<span class="help-inline"><font color="red">*</font> </span>
		</td>
		<td>服务方向:</td>
		<td>
		<input type="text" id="serviceCyfx" name="serviceCyfx" placeholder="服务方向" required/>
		<span class="help-inline"><font color="red">*</font> </span>
		</td>
		</tr>
		<tr>
		<td>开始时间：</td>
		<td>
		<input name="startTime" placeholder="开始时间" id="d4311" class="Wdate" type="text" required onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" required/>
		<span class="help-inline"><font color="red">*</font> </span>
		</td>
		<td>结束时间：</td>
		<td>
		<input name="endTime" placeholder="结束时间"  id="d4312" class="Wdate" type="text" required onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" required/>
		<span class="help-inline"><font color="red">*</font> </span>
		</td>
		</tr>
	</table>
	<div class="breadcrumb form-search ">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<%-- <sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/> --%>
		<ul class="ul-form">
			<li><label>姓名:</label><input id="name" name="name" maxlength="100" class="input-medium"/></li>
			<li><label>专业:</label><input id="tpyMajor" name="tpyMajor"  maxlength="50" class="input-medium"/></li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="button" value="查询" onclick="return page(${page.pageNo},${page.pageSize});"/>
			<li class="clearfix"></li>
		</ul>
	</div>
	<table id="contentTable1" class="table table-striped table-bordered table-condensed">
		<thead><tr><th>姓名</th><th>单位</th><th>职称</th><th>专业</th><th>手机</th><th>email</th><th>成员类型</th><shiro:hasPermission name="sys:user:edit"><!-- <th>操作</th> --></shiro:hasPermission></tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="user">
			<tr>
				<td>${user.name}</td>
				<td>${user.tpyCompany}</td>
				<td>${user.tpyTitle}</td>
				<td>${user.tpyMajor}</td>
				<td>${user.mobile}</td>
				<td>${user.email}</td>
				<td>
				<label><input name="memberType" type="radio" value="${user.id}" onclick="radioCheck()"/>团长</label> 
				</td>
				<%-- <shiro:hasPermission name="sys:user:edit"><td>
    				<input type="checkbox" name="userId" value="${user.id}"/>
				</td></shiro:hasPermission> --%>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	<div class="form-actions table-bordered-bt">
	<input id="btnSubmit" class="btn btn-primary" type="submit" value="保存" onclick="return insertBatch();" />
	<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
	</div>
	</form>
	
</body>
</html>