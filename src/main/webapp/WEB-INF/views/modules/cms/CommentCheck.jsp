<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用户交流审核</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	$(document).ready(function() {
		var message = $("#input").val(); 
		if(message!=null&&message!=''){
			document.getElementById("div").style.display="";
		};
	}); 
	 /* function searchList(){
    	var name = $("#name").val();
    	var tpyMajor = $("#tpyMajor").val();
    	 $.ajax({
    		url:"${ctx}/VillageManage/findTpyList",
    		data:{"name":name,"tpyMajor":tpyMajor},
    		type:"post",
    		dataType:"json", 
    		success:function(data){
    			var items="" ;
	        	 for(var i=0;i<data.page.length;i++){
		            	var td1 = "<tr><td>"  + data.page[i].name;
		            	var td2 = "</td><td>" + data.page[i].tpyCompany;
		            	var td3 = "</td><td>" + data.page[i].tpyTitle;
		            	var td4 = "</td><td>" + data.page[i].tpyMajor; 
		            	var td5 = "</td><td>" + data.page[i].mobile;
		            	var td6 = "</td><td>" + data.page[i].email;
		            	var td7 =  "</td><td><label><input name='memberType' type='radio'  value='"+data.page[i].id+"'/>团长</label>";
		            	var td8 = "</td></tr>";
		            	items = items+td1+td2+td3+td4+td5+td6+td7+td8;
		            	$("#contentTable1 tbody").html("");
		    			$("#contentTable1 tbody").append(items);
	        	 }}
        	});    	
        } 
    function radioCheck(){
    	if($('input:radio[name="memberType"]:checked').val()!=""||$('input:radio[name="memberType"]:checked').val()!=null){
    		$('input:radio[name="memberType"]:checked').parent().parent().next().find('input:checkbox').attr('checked',true);
    	}
    } */
    
	/**
	 * 调用后台批量方法
	 */
	function checkBatch() {
		$("#insertBatch").attr("action","${ctx}/communication/Check");
		$("#insertBatch").submit(); 
	}
    function deleteBatch(){
    	$("#insertBatch").attr("action","${ctx}/communication/delete");
		$("#insertBatch").submit(); 
    }
    
    function selectBatch(){
    		//判断复选框有没有选中
    	if($('input:checkbox[name="selectAll"]').is(':checked')){  
            $('input:checkbox[name="commentId"]').each(function(){  
                //此处如果用attr，会出现第三次失效的情况  
                $(this).prop("checked",true);  
            });  
    	}else{  
        	$('input:checkbox[name="commentId"]').each(function(){  
                $(this).removeAttr("checked",false);  
            });  
	    }  
    }
    
    /* $('input[name="selectAll"]').click(function(){  
        alert(this.checked);  
        if($(this).is(':checked')){  
            $('input[name="commentId"]').each(function(){  
                //此处如果用attr，会出现第三次失效的情况  
                $(this).prop("checked",true);  
            });  
    	}else{  
        	$('input[name="commentId"]').each(function(){  
                $(this).removeAttr("checked",false);  
            });  
	    }  
	});   */
    
    
	</script>

</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/communication/InfoCheck">用户交流信息审核</a></li>
	</ul>
	<form  id="insertBatch" method="post">
	<sys:message content="${message}"/>
	<div class="breadcrumb form-search ">
		<ul class="ul-form">
			<!-- <li><label>姓名:</label><input id="name" name="name" maxlength="100" class="input-medium"/></li>
			 <li><label>标题:</label><input id="title" name="title"  maxlength="50" class="input-medium"/></li>-->
			<li class="btns">
			<!-- <input id="btnSubmit" class="btn btn-primary" type="button" value="查询" onclick="searchList()"/> -->
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="通过" onclick="return checkBatch();" />
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="删除" onclick="return deleteBatch();" />
			<!-- <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/> -->
			</li>
			<li class="clearfix"></li>
		</ul>
	</div>
	<table id="contentTable1" class="table table-bordered">
		<thead><tr><th><input  name="selectAll" type="checkbox"  onclick="return selectBatch();" /></th><th>标题</th><th>内容</th><!-- <th>操作</th> --></tr></thead>
		<tbody>
		 <c:forEach items="${communicationList}" var="communicationList">
			<tr>
				<td><input name="commentId" type="checkbox" value="${communicationList.id}"/></td>
				<td>${communicationList.title}</td>
				<td>${communicationList.content}</td>
			<!-- 	<td>
				删除 
				</td> -->
			</tr>
		</c:forEach>
		<c:forEach items="${communicationCommentList}" var="CommentList">
			<tr>
				<td><input name="commentId" type="checkbox" value="${CommentList.id}"/></td>
				<td>${CommentList.title}</td>
				<td>${CommentList.content}</td>
				<!-- <td>
				删除 
				</td> -->
			</tr>
		</c:forEach> 
		</tbody>
	</table>
	</form>
</body>
</html>