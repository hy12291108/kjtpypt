<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>审批管理</title>
	<meta name="decorator" content="default"/>
	<link href="${ctxStatic}/fileUpload/css/iconfont.css" rel="stylesheet" type="text/css"/>
	<link href="${ctxStatic}/fileUpload/css/fileUpload.css" rel="stylesheet" type="text/css">
	<script src="${ctxStatic}/fileUpload/js/fileUpload.js" type="text/javascript"></script>
	<link rel="stylesheet" href="${ctxStatic}/centermenu-master/css/animate.css">
	<link rel="stylesheet" href="${ctxStatic}/centermenu-master/css/toast.css">	
	<link rel="stylesheet" href="${ctxStatic}/centermenu-master/css/centermenu.css">
	<script type="text/javascript">
		$(document).ready(function() {
			var option = null;
			$("#fileUploadContent").initUpload({
        		"uploadUrl":"/kjtpypt/a/dailywork/tpy/tpyDailyrecord/uploadVilImage",//上传文件信息地址==测试
        	//	"uploadUrl":"/a/dailywork/tpy/tpyDailyrecord/uploadVilImage",//上传文件信息地址==正式
        	//	"size":350,//文件大小限制，单位kb,默认不限制
        		"maxFileNumber":2,//文件个数限制，为整数
        		//"filelSavePath":"",//文件上传地址，后台设置的根目录
        		//"beforeUpload":beforeUploadFun,//在上传前执行的函数
        		//"onUpload":onUploadFun，//在上传后执行的函数
        		//autoCommit:true,//文件是否自动上传
       		    "fileType":['png','jpg','JPG','PNG']//文件类型限制，默认不限制，注意写的是文件后缀
    		});
  			//$(".uploadFileBt").hide();
  			//$(".cleanFileBt").hide();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
    function dailyRecordByFwdxSave(){
    	var fileNumber1 = uploadTools.getFileNumber(option);
    	if(fileNumber1>=1){
    		uploadEvent.uploadFileEvent(option,2);
    	}else{
    		$("#inputForm").attr("action","/kjtpypt/a/dailywork/tpy/tpyDailyrecord/save");//测试
    		//$("#inputForm").attr("action","/a/dailywork/tpy/tpyDailyrecord/save");//正式
        	$("#inputForm").submit();	
    	}       
    }
		function getVillage(){
			  var teamId=$("#teamId").val();
			 // alert(teamId);
			  if(teamId == ""){
			  	alert("请选择特派团");
			    return;
			  }else{
				  $.ajax({
					    type: "POST",
					    async: false,
					    url: "${ctx}/dailywork/tpy/tpyDailyrecord/getVillage",
					    data:{ //发送给数据库的数据
             				teamId: teamId
           				},
					    dataType: "json",
					    contentType: "application/x-www-form-urlencoded; charset=utf-8", 
					        success : function(data) {
					       // console.log(data);
					          $("#recHelpObj").val(data.villageName);
					          $("#recHelpobjid").val(data.villageId);  
					          $("#recTpyLocation").val(data.teamArea);     
					        }
					      });  
			  }
			}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/dailywork/tpy/tpyDailyrecord/">工作日志待审核列表</a></li>
		<li><a href="${ctx}/dailywork/tpy/tpyDailyrecord/yshlist">工作日志已审核列表</a></li>	
		<li class="active"><a href="${ctx}/dailywork/tpy/tpyDailyrecord/form">特派员日志添加(贫困村)</a></li>
	</ul>
		<form:form id="inputForm" modelAttribute="tpyDailyrecord" action="${ctx}/dailywork/tpy/tpyDailyrecord/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<fieldset>
			<legend>特派员日志(贫困村)填报信息</legend>
			<table class="table-form">							
				<tr>
					<td class="tit">填报人</td>
					<td colspan="2"><form:input path="recWriter" readonly="true" htmlEscape="false" maxlength="50" cssStyle="width:210px"/></td>
					<td class="tit">特派员类型</td>
					<td colspan="2"><form:input path="tpytype" readonly="true" htmlEscape="false" maxlength="50" cssStyle="width:210px"/></td>					
				</tr>
				<tr>
					<td class="tit">年度</td>
					<td colspan="2">
					<form:input path="Year" readonly="true" htmlEscape="false" maxlength="50" cssStyle="width:210px" onclick="WdatePicker({dateFmt:'yyyy'+'年',isShowClear:false});"/>
					</td>
					<td class="tit">服务对象类型</td>
					<td colspan="2"><form:input path="recHelpobjtype" readonly="true" htmlEscape="false" maxlength="50" cssStyle="width:210px"/></td>					
				</tr>
				<tr>
					<td class="tit">填报特派团</td>
					<td colspan="2">
					<form:select path="teamId" id="teamId"  class="required" cssStyle="width:210px" onchange="getVillage()">
					<form:option value="" label="--请选择特派团--"/>
					<c:forEach items="${tpyDailyrecord.teamMemberList}"  var="a2">	
					<form:option value="${a2.teamId}" label="${a2.teamName}"/>			
					</c:forEach>
					</form:select>				
					</td>		
					<td class="tit">填报贫困村</td>
					<td colspan="2">
					<form:input path="recHelpObj" id="recHelpObj" readonly="true" class="required" htmlEscape="false" maxlength="50" cssStyle="width:210px"/>
					<form:hidden path="recHelpobjid" id ="recHelpobjid"/>				
					</td>													
				</tr>
				<tr>
					<td class="tit">团长/团员</td>
					<td colspan="2"><form:select path="teamMemberType" class="required" cssStyle="width:210px">									
									<form:option value="团长">团长</form:option>  
              						<form:option value="团员">团员</form:option>              						 	
              						</form:select>
					</td>					
					<td class="tit">日志类型</td>
					<td colspan="2"><form:select path="dailyRecordType" class="required" cssStyle="width:210px">									
									<form:option value="团队日志(贫困村)">团队日志(贫困村)</form:option>  
              						<form:option value="个人日志(贫困村)">个人日志(贫困村)</form:option>              						 	
              						</form:select>
					</td>
				</tr>		
				<tr>
					<td class="tit">服务开始时间</td>
					<td colspan="2">
					<input id="recStartTime" name="recStartTime" type="text" readonly="readonly" maxlength="100" class="required" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
					<span class="help-inline"><font color="red">*</font> </span>
					</td>
					<td class="tit">服务结束时间</td>
					<td colspan="2">
					<input id="recEndTime" name="recEndTime" type="text" readonly="readonly" maxlength="100" class="required" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
					<span class="help-inline"><font color="red">*</font> </span>
					</td>
				</tr>
				<tr>
					<td class="tit">科技服务天数(天)</td>
					<td colspan="2"><form:input path="kjfwts" htmlEscape="false" maxlength="50" class="digits required"/></td>
					<td class="tit">集体经济增收(万元)</td>
					<td colspan="2"><form:input path="jtjjzc" htmlEscape="false" maxlength="50" class="number  required"/></td>
				</tr>
			
				<tr>
					<td class="tit">引进推广新品种新产品(个)</td>
					<td colspan="2"><form:input path="yjtgxpzxcp" htmlEscape="false" maxlength="50" class="digits required"/></td>
					<td class="tit">推广新技术(项)</td>
					<td colspan="2"><form:input path="tgxjs" htmlEscape="false" maxlength="50" class="digits required"/></td>
				</tr>
				<tr>
					<td class="tit">解决技术难题(个)</td>
					<td colspan="2"><form:input path="jjjsnt" htmlEscape="false" maxlength="50" class="digits required"/></td>
					<td class="tit">培训农民(人次)</td>
					<td colspan="2"><form:input path="pxnm" htmlEscape="false" maxlength="50" class="digits required"/></td>
				</tr>
				<tr>
					<td class="tit">创办领办培育企业或合作社(个)</td>
					<td colspan="2"><form:input path="cbqyhzs" htmlEscape="false" maxlength="50" class="digits required"/></td>
					<td class="tit">带动就业人数(人)</td>
					<td colspan="2"><form:input path="ddjyrc" htmlEscape="false" maxlength="50" class="digits required"/></td>
				</tr>
				<tr>
					<td class="tit">服务农村合作组织(个)</td>
					<td colspan="2"><form:input path="fwnchzzz" htmlEscape="false" maxlength="50" class="digits required"/></td>
					<td class="tit">建立科技示范基地(个)</td>
					<td colspan="2"><form:input path="jlkjsfjd" htmlEscape="false" maxlength="50" class="digits required"/></td>
				</tr>
				<tr>
					<td class="tit">培育科技示范户(户)</td>
					<td colspan="2"><form:input path="pykjsfh" htmlEscape="false" maxlength="50" class="digits required"/></td>
					<td class="tit">举办培训班(场次)</td>
					<td colspan="2"><form:input path="jbpxb" htmlEscape="false" maxlength="50" class="digits required"/></td>
				</tr>
				<tr>
					<td class="tit">培训人员(人次)</td>
					<td colspan="2"><form:input path="pxry" htmlEscape="false" maxlength="50" class="digits required"/></td>
					<td class="tit">培训贫困群众(人次)</td>
					<td colspan="2"><form:input path="pxpkqz" htmlEscape="false" maxlength="50" class="digits required"/></td>
				</tr>
				<tr>
					<td class="tit">实现产值(万元)</td>
					<td colspan="2"><form:input path="sxcz" htmlEscape="false" maxlength="50" class="number required"/></td>
					<td class="tit">带动增收(万元)</td>
					<td colspan="2"><form:input path="ddzs" htmlEscape="false" maxlength="50" class="number required"/></td>
				</tr>
				<tr>
					<td class="tit">创利税(万元)</td>
					<td colspan="2"><form:input path="cls" htmlEscape="false" maxlength="50" class="number required"/></td>
					<td class="tit">帮扶贫困村(个)</td>
					<td colspan="2"><form:input path="bfpkc" htmlEscape="false" maxlength="50" class="digits required"/></td>
				</tr>
				<tr>
					<td class="tit">贫困户(户)</td>
					<td colspan="2"><form:input path="pkh" htmlEscape="false" maxlength="50" class="digits required"/></td>
					<td class="tit">带动脱贫(户)</td>
					<td colspan="2"><form:input path="ddtp" htmlEscape="false" maxlength="50" class="digits required"/></td>
				</tr>
				<tr>
					<td class="tit">贫困人口(个)</td>
					<td colspan="2"><form:input path="pkrk" htmlEscape="false" maxlength="50" class="digits"/></td>
					<td class="tit">填报时间</td>
					<td colspan="2"><form:input path="recWrittenTime" readonly="true"  htmlEscape="false" maxlength="50" cssStyle="width:210px"/></td>
				</tr>
				<tr>
					<td class="tit">工作总结</td>
					<td colspan="5">
						<form:textarea path="recHelpContent" class="required" rows="5" cssStyle="width:1020px"/>
					</td>
				</tr>
				<tr>
					<td class="tit"><button type="button" class="btn btn-primary" id="tit">选择图片</button></td>
					<td colspan="5">
					 <div id="fileUploadContent" class="fileUploadContent" class="required"></div>   		
					</td>	
				</tr>						
			</table>
			<form:input path="recTpyLocation" id="recTpyLocation" type="text"/>
			<form:input path="drTableImage" id="drTableImage" htmlEscape="false" type="hidden"/>
		</fieldset>	
		<div class="form-actions">
			
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="提交" onclick="dailyRecordByFwdxSave()"/>		
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
		<c:if test="${not empty testAudit.id}">
			<act:histoicFlow procInsId="${tpyDailyrecord.id}" />
		</c:if>
	</form:form>
</body>
</html>
