<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>

    <!-- 自然人上传照片 -->

	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
	<title>特派员（自然人）注册</title>
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/dyh/css/style.css">
	
	
	<script src="/kjtpypt/static/jquery/jquery-1.8.3.min.js" type="text/javascript"></script>
    <script src="/kjtpypt/static/jquery/Select.js" type="text/javascript"></script>
    <script src="/kjtpypt/static/bootstrap/2.3.1/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="/kjtpypt/static/jquery-select2/3.4/select2.min.js" type="text/javascript"></script>
    <script src="/kjtpypt/static/jquery-validation/1.11.0/jquery.validate.js" type="text/javascript"></script>
    <script src="/kjtpypt/static/jquery-jbox/2.3/jquery.jBox-2.3.min.js" type="text/javascript"></script>
    <script src="/kjtpypt/static/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
    <script src="/kjtpypt/static/common/mustache.min.js" type="text/javascript"></script>
    
    
    <!-- 20170906加注册页面样式 -->

    <script src="/kjtpypt/static/common/jeesite.js" type="text/javascript"></script>
    <!-- 上传图片 20171011-->
    <link href="${ctxStatic}/fileUpload/css/iconfont.css" rel="stylesheet" type="text/css"/>
    <link href="${ctxStatic}/fileUpload/css/fileUpload.css" rel="stylesheet" type="text/css">
    <script src="${ctxStatic}/fileUpload/js/fileUpload.js" type="text/javascript"></script>
    <link rel="stylesheet" href="${ctxStatic}/centermenu-master/css/animate.css">
    <link rel="stylesheet" href="${ctxStatic}/centermenu-master/css/toast.css">
    <link rel="stylesheet" href="${ctxStatic}/centermenu-master/css/centermenu.css">
    <script src="${ctxStatic}/centermenu-master/js/toast.js" type="text/javascript"></script>
    <script src="${ctxStatic}/centermenu-master/js/centermenu.js" type="text/javascript"></script>
	




    <style>
        body{
            height:100%;
            overflow-y: hidden;
        }
        .ui_button {
            padding:10px;
            width:100%;
            background: #01AAED;
            color: #fff;
        }
        .ui_button:hover {
           opacity: 0.88;
        }
        .zui-header{
            position: absolute;
            top: 0;
            width:100%;
            height: 46px;
            line-height: 46px;
            background-color:#18b4ed;
            color: #fff;
        }
        .zui-header .zicon-goback{
            position: absolute;
            left:0; 
        }
        .zui-header .title{
            font-size: 20px;
            text-align: center;
        }
        .zform-fieldset{
        	margin-top:56px;
            overflow-y: auto;
        }
        .button{
            display: block;
            padding: 12px;
            margin-bottom: 0;
            width:100%;
            color: #fff;
            font-size: 14px;
            font-weight: normal;
            line-height: 1.42857143;
            text-align: center;
            white-space: nowrap;
            vertical-align: middle;
            touch-action: manipulation;
            cursor: pointer;
            user-select: none;
            border: 1px solid transparent;
            border-radius: 4px;
            background-color: #337ab7;
            border-color: #2e6da4;
        }
        .button:active{
            color: #fff;
            background-color: #286090;
            border-color: #204d74;
        }
    </style> 
    
    
    
    <script type="text/javascript">
    
	    function mobileBind() {
	            var mobile=$("#loginName").val();
	            $("#mobile").val(mobile);
	        }
	
	        $(document).ready(function () {
	            var option = null;
	
	            $("#fileUploadContent").initUpload({
	                //"uploadUrl":"http://***/",//上传文件信息地址
	                "uploadUrl": "/kjtpypt/a/UserRegister/uploadImage",//上传文件信息地址
	                //"size":350,//文件大小限制，单位kb,默认不限制
	                //"maxFileNumber":3,//文件个数限制，为整数
	                //"filelSavePath":"",//文件上传地址，后台设置的根目录
	                //"beforeUpload":beforeUploadFun,//在上传前执行的函数
	                //"onUpload":onUploadFun，//在上传后执行的函数
	                autoCommit: false,//文件是否自动上传
	                "fileType": ['png', 'jpg']//文件类型限制，默认不限制，注意写的是文件后缀
	            });
	            //$(".uploadFileBt").hide();
	            //$(".cleanFileBt").hide();
	            initZyLb();
	            $("#inputForm").validate({
	                rules: {
	                    loginName: {remote: "/kjtpypt/a/UserRegister/checkLoginName?oldLoginName=" + encodeURIComponent('${user.loginName}')}
	                },
	                messages: {
	                    loginName: {remote: "用户登录名已存在"},
	                    confirmNewPassword: {equalTo: "输入与上面相同的密码"}
	                },
	                submitHandler: function (form) {
	                    loading('正在提交，请稍等...');
	                    form.submit();
	                },
	                errorContainer: "#messageBox",
	                errorPlacement: function (error, element) {
	                    $("#messageBox").text("输入有误，请先更正。");
	                    if (element.is(":checkbox") || element.is(":radio") || element.parent().is(".input-append")) {
	                        error.appendTo(element.parent().parent());
	                    } else {
	                        error.insertAfter(element);
	                    }
	                }
	            });
	        });
	
	
	
	        /*  function beforeUploadFun(opt){
	               $("#btnSubmit").attr({"disabled":"disabled"});
	           }
	         function onUploadFun(opt,data){
	               alert(data);
	               uploadTools.uploadError(opt);//显示上传错误
	           }		 */
	
	        //加载即可输入又可实现下拉的select
	        function corpList() {
	            var officeId = $("#officeId").val();
	            if (officeId == "") {
	                alert("请先输入所属部门");
	                $("#officeName").focus();
	                return;
	            } else if ($("#tpyCompany .es-list").length > 0) {
	                $("#editable-select").remove();
	                $(".es-list").remove();
	                $("#tpyCompany").prepend("<select id='editable-select' name='tpyCompany'></select>");
	            }
	            $.ajax({
	                type: "post",
	                contentType: "application/json",
	                url: "${ctx}/UserRegister/corpList?officeId=" + officeId,
	                dataType: "json",
	                success: function (data) {
	                    var append1 = "<option value='";
	                    var append2 = "'>";
                    var append3 = "</option>";
	                    if (data.corpList[0] != "查无数据") {
	                        for (var i = 0; i < data.corpList.length; i++) {
	                            var append = append1 + data.corpList[i] + append2 + data.corpList[i] + append3;
	                            $("#editable-select").empty();
	                            $("#editable-select").append(append);
	                        }
	                    }
	                    $("#editable-select").editableSelect({
	                        effects: 'slide'
	                    });
	                },
	                error: function () {
	                    alert("error");
	                }
	            });
	        }
	
	           function findZymc(id){
	        	   $.ajax({
	   				type:"post",
	   				async:"false",
	   				url:"${ctx}/UserRegister/registerSecondListNew",
	   				data:{'id':id},
	   				dataType:"json",
	   				success:function(msg){
	   					$("#tpyMajor").empty();	
	   					if(msg.majorList.length>0){
	   						for(var i=0;i<msg.majorList.length;i++){
	   							var partName = msg.majorList[i].name;
	   							var partId = msg.majorList[i].id; 
	   							var $option = $("<option>").attr({"value":partId}).text(partName);
	   							$("#tpyMajor").append($option);
	   						}
	   						$("#tpyMajor").change();
	   						
	   					}else{
	   						var $option = $("<option>").attr({"value":""}).text("");
	   						$("#tpyMajor").append($option);
	   						$("#tpyMajor").change();
	   					}
	   				},
	   				error:function(json){
	   					$.jBox.alert("网络异常");
	   				}
	   			});
	   		}
	        
	           
	           
	           //专业类别
	        function initZyLb() {
	            $("#yjxkdm").empty();
	            $.ajax({
	                type: "POST",
	                async: false,
	                url: "${ctx}/UserRegister/registerList",
	                //data: "id="+yjxkdm,
	                dataType: "json",
	                success: function (json) {
	                    $("<option value=''>--选择专业类别--</option>").appendTo("#yjxkdm");//添加下拉框的option
	                    for (var i = 0; i < json.majorList.length; i++) {
	                        $("<option value='" + json.majorList[i].id + "'>" + json.majorList[i].name + "</option>").appendTo("#yjxkdm");//添加下拉框的option
	                    }
	                }
	            });
	        }
	
	        function initZy() {
	            $("#tpyMajor").empty();
	            var yjxkdm = $("#yjxkdm").val();
	            if (yjxkdm == "") {
	                return;
	            } else {
	                $.ajax({
	                    type: "POST",
	                    async: false,
	                    url: "${ctx}/UserRegister/registerSecondList",
	                    data: "id=" + yjxkdm,
	                    dataType: "json",
	                    success: function (json) {
	                        $("<option value=''>--选择专业--</option>").appendTo("#tpyMajor");//添加下拉框的option
	                        for (var i = 0; i < json.majorList.length; i++) {
	                            $("<option value='" + json.majorList[i].name + "'>" + json.majorList[i].name + "</option>").appendTo("#tpyMajor");//添加下拉框的option
	                        }
	                    }
	                });
	            }
	        }
	
	
	        /* 号码校验*/
	        function phoneCheck1() {
	            var mobile = $.trim($('#mobile').val());
	            var num = /^\d*$/; //全数字
	            if (!num.exec(mobile)) {
	                alert("号码必须全为数字");
	                $("#mobile").focus();
	                return false; 	
	            }
	            if (mobile.length != 11) {
	                alert("号码长度不符,长度为11位");
	                $("#mobile").focus();
	                return false;
	            }
	        }
	
	
	        /*身份证校验*/
	        function idCardCheck() {
	            var idCard = $("#tpyIdcard").val();
	            var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
	            if (reg.test(idCard) === false) {
	                alert("身份证输入不合法");
	                $("#tpyIdcard").focus();
	                return false;
	            }
	        }
	
	        function registerSave() {
	            var yjxkdm = $("#yjxkdm").val();
	            if (yjxkdm == "" || yjxkdm == null) {
	                alert("请选择专业类别");
	                $("#yjxkdm").focus();
	                return false;
	            }
	            if ($("#tpyMajor").val() == "" || $("#tpyMajor").val() == null) {
	                alert("请选择专业");
	                $("#tpyMajor").focus();
	                return false;
	            }
	            uploadEvent.uploadFileEvent(option, 1);
	            $("#inputForm").attr("action","/kjtpypt/a/UserRegister/save1");
	        }

    </script>

</head>

<body>
    <div class="zui-header">
        <h1 class="title">特派员（自然人）注册</h1>
    </div>
    
    <div class="zui-mr10 zui-mb10 zui-ml10 pd10">
		<form:form id="inputForm" modelAttribute="user" method="post"  class="zform zform-radius">
            <fieldset class="zform-fieldset">
            <form:hidden path="id"/>
            <form:input path="company.id" htmlEscape="false" type="hidden"/>
            <sys:message content="${message}"/>
            <form:errors path="*"></form:errors>
            <div class="zform-control">
               
               <div class="control-field">
                   <label>登录名：</label>
                   <input id="oldLoginName" name="oldLoginName" type="hidden" value="${user.loginName}">
<!--                    <input type="password" class="field-text" placeholder="请输入用户名" data-required="true" data-descriptions="username" data-describedby="username-description"> -->
               	   <form:input path="loginName" id="loginName" placeholder="请输入您的手机号码" htmlEscape="false" maxlength="50"
                     class="field-text required mobile" onkeyup=" mobileBind()"/>
                    <em>*</em>
               </div>
           </div>
           
            <div class="zform-control">
             		<label>密码：</label>
                  <div class="control-field">
<!--                       <input type="password" id="pwd" class="field-text" placeholder="请输入密码" data-required="true" data-descriptions="password" data-describedby="password-description"  data-conditional="pwd"> -->
                	  <form:input id="newPassword" path="newPassword" type="password" placeholder="请输入密码" value="" maxlength="50" minlength="3"
                               class="field-text ${empty user.id?'required':''}"/><span class="help-inline"><font
                            color="red">*</font> </span>
                  </div> 
            </div>
            
            <div class="zform-control">
                   <label>确认密码：</label>
                  <div class="control-field">
<!--                       <input type="password" id="pwd" class="field-text" placeholder="请输入密码" data-required="true" data-descriptions="password" data-describedby="password-description"  data-conditional="pwd"> -->
                	  <input id="confirmNewPassword" name="confirmNewPassword" type="password" placeholder="请输入密码"
                               equalTo="#newPassword" class="field-text"/>
                      <span class="help-inline"><font color="red">*</font> </span>
                  </div> 
            </div>
            
            <div class="zform-control">
                   <label>姓名：</label>
                  <div class="control-field">
                	  <form:input path="name" htmlEscape="false" maxlength="50" class="field-text required"/>
                      <span class="help-inline"><font color="red">*</font> </span>
                  </div> 
            </div>
            
            <div class="zform-control">
                  <label>性别：</label>
                  <div class="control-field">
                	  <input type="radio" name="sex" value="0" id="checkbox1"/><label for="checkbox1">男</label>
                	  <input type="radio" name="sex" value="0" id="checkbox2"/><label for="checkbox2">女</label>
                	  
<%--                 	  <form:checkbox path="sex" items="${fns:getDictList('sex')}" itemLabel="label" itemValue="value" htmlEscape="false" class="required" /> --%>
<%--                 	  <form:select path="sex" > --%>
<%--                             <form:options items="${fns:getDictList('sex')}" itemLabel="label" itemValue="value" --%>
<%--                                           htmlEscape="false" class="field-text"/> --%>
<%--                         </form:select> --%>
                        <span class="help-inline"><font color="red">*</font> </span>
                  </div> 
            </div>
            
            <div class="zform-control">
                  <label>民族：</label>
                  <div class="control-field">
                	  <form:input path="tpyNation" htmlEscape="false" maxlength="50" class="field-text required"/>
                      <span class="help-inline"><font color="red">*</font> </span>
                  </div> 
            </div>
            
            
            <div class="zform-control">
                  <label>籍贯：</label>
                  <div class="control-field">
                	  <form:input path="tpyLocation" htmlEscape="false" maxlength="50" class="field-text required"/>
                      <span class="help-inline"><font color="red">*</font> </span>
                  </div> 
            </div>
            
            <div class="zform-control">
                  <label>所属区域：</label>
                  <div class="control-field">
                	  <sys:treeselect id="office" name="office.id" value="${user.office.id}" labelName="office.name"
                                        labelValue="${user.office.name}"
                                        title="部门" url="/UserRegister/treeData?type=2" cssClass="field-text required"
                                        notAllowSelectParent="true"/>
                      <span class="help-inline"><font color="red">*</font> </span>
                  </div> 
            </div>
            
            <div class="zform-control">
                  <label>工作单位名称：</label>
                  <div class="control-field">
                	   <form:input path="tpyCompany" htmlEscape="false" maxlength="50" class="field-text"/>
                      <span class="help-inline"><font color="red">*</font> </span>
                  </div> 
            </div>
             
             <div class="zform-control">
                  <label>职务：</label>
                  <div class="control-field">
                	   <form:input path="tpyPosition" htmlEscape="false" maxlength="50" class="field-text"/>
                      <span class="help-inline"><font color="red">*</font> </span>
                  </div> 
            </div>   
            
            <div class="zform-control">
                  <label>职称：</label>
                  <div class="control-field">
                	   <form:select path="tpyTitle">
                            <form:options items="${fns:getDictList('tpy_title')}" itemLabel="label" itemValue="value"
                                          htmlEscape="false" class="field-text required"/>
                        </form:select>
                      <span class="help-inline"><font color="red">*</font> </span>
                  </div> 
            </div>  
            
            <div class="zform-control">
                  <label>学历：</label>
                  <div class="control-field">
                	   <form:select path="tpyQulification">
                            <form:options items="${fns:getDictList('tpy_qulification')}" itemLabel="label"
                                          itemValue="value" htmlEscape="false" class="field-text required"/>
                        </form:select>
                      <span class="help-inline"><font color="red">*</font> </span>
                  </div> 
            </div>   
            
            <div class="zform-control">
                  <label>政治面貌：</label>
                  <div class="control-field">
                	   <form:select path="tpyPolitical">
                            <form:options items="${fns:getDictList('political')}" itemLabel="label" itemValue="value" htmlEscape="false" class="field-text"/>
                        </form:select>
                      <span class="help-inline"><font color="red">*</font> </span>
                  </div> 
            </div>     
            
             <div class="zform-control">
                  <label>邮箱：</label>
                  <div class="control-field">
                	    <form:input path="email" htmlEscape="false" maxlength="100" class="field-text required email"/>
                      <span class="help-inline"><font color="red">*</font> </span>
                  </div> 
            </div>  
            
             <div class="zform-control">
                  <label>手机：</label>
                  <div class="control-field">
                	   <form:input path="mobile" id="mobile" readonly="true" htmlEscape="false" maxlength="50" class="field-text required"/>
                      <span class="help-inline"><font color="red">*</font> </span>
                  </div> 
            </div>  
            
             <div class="zform-control">
                  <label>出生日期：</label>
                  <div class="control-field">
                	   <form:input path="tpyBirthDate" class="field-text Wdate required" htmlEscape="false" maxlength="40"
                                    onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd',isShowClear:false,maxDate:'%y-%M-%d'})"/>
                        <em>*</em>
                  </div> 
            </div>  
            
            
             <div class="zform-control">
                  <label>身份证/护照：</label>
                  <div class="control-field">
                	  <form:input path="tpyIdcard" id="tpyIdcard" htmlEscape="false" maxlength="20" class="field-text required"/>
                        <em>*</em>
                  </div> 
            </div>  
            
             <div class="zform-control">
                  <label>服务形式：</label>
                  <form:select path="tpyServiceMode" >
                          <form:options items="${fns:getDictList('service_mode')}" itemLabel="label" itemValue="value"
                                          htmlEscape="false" class="field-text"/>
                        </form:select>
                        <span class="help-inline"><font color="red">*</font> </span> 
            </div> 
            
             <div class="zform-control">
                  <label>人才类型：</label>
                  <form:select path="tpyTalentType">
                            <form:options items="${fns:getDictList('talent_type')}" itemLabel="label" itemValue="value"
                                          htmlEscape="false" class="field-text"/>
                    </form:select>
                        <span class="help-inline"><font color="red">*</font> </span> 
            </div>  
            
            <div class="zform-control">
                  <label>专业类别：</label>
                  <form:select path="tpyMajorType" id="tpyMajorType" class="field-text" onchange="findZymc(this.options[this.options.selectedIndex].value);">
                      <form:option value="" label=""/>
                      <form:options items="${fns:getMajorMenuList()}" itemLabel="name" itemValue="id"
                                          htmlEscape="false" />
                    </form:select>
                  
                  
<!--                   <select id="yjxkdm" name="tpyMajorType" class="ch-select required" onchange="initZy();"> 
                      <option value="" selected>--选择专业类别--</option>
<!--                   </select> -->
                  <span class="help-inline"><font color="red">*</font></span> 
            </div>  
            
             <div class="zform-control">
                  <label>专业名称：</label>
                   <form:select path="tpyMajor" id="tpyMajor" class="field-text">
                      <form:option value="" label=""/>
                    </form:select>
                  	
<!--                    <select name="tpyMajor"  class="ch-select required">
                       <option value="" selected>--选择专业名称--</option>
<!--                    </select> -->
                  <span class="help-inline"><font color="red">*</font></span> 
            </div>     
                     
             <div class="zform-control">
                  <label>专业擅长：</label>
                  <form:textarea path="tpySpecial" htmlEscape="false" rows="2" maxlength="100" style="width:753px"
                                       class="field-text required"/>
                  <span class="help-inline"><font color="red">*</font></span> 
            </div>   
            
            
             <div class="zform-control">
                  <label>拟服务地点：</label>
                 <form:textarea path="tpyNfwAddress" htmlEscape="false" rows="2" maxlength="10" style="width:753px"
                                class="field-text required"/>
                 <span class="help-inline"><font color="red">*</font> </span>
            </div>    
            
             <div class="zform-control">
                  <label>从事科技服务与创业经历：</label>
                  <form:textarea path="tpyExperience" htmlEscape="false" rows="2" maxlength="200" style="width:753px"
                                 class="field-text required"/>
                  <span class="help-inline"><font color="red">*</font> </span>
            </div>  
            
             <div class="zform-control">
                  <label>奖惩情况：</label>
                   <form:textarea path="tpyJcSituation" htmlEscape="false" rows="2" maxlength="100" style="width:753px"
                   			class="field-text required"/>
                   <span class="help-inline"><font color="red">*</font> </span>
            </div>  
            
             <div class="zform-control">
                  <label>选择图片：</label>
                    <button type="button" class="btn btn-primary" id="tit">选择图片</button>
                    <div id="fileUploadContent" class="fileUploadContent" class="required"></div>
                    <em>*注：头像上传</em>
                    
            </div>   
            
            <form:input path="photo" id="tjTableImage" htmlEscape="false" type="hidden" value=""/>
            <form:input path="loginFlag" value="1" htmlEscape="false" type="hidden"/>
            <form:input path="personFlag" value="0" htmlEscape="false" type="hidden"/>
            <form:input path="checkFlag" value="0" htmlEscape="false" type="hidden"/>
            <form:input path="roleIdList" value="3bb6453c699d49508b15529670ad9e9b" htmlEscape="false" type="hidden"/>
            
            
             <div class="zform-control">
					<input type="submit" value="提交" class="button">
             </div>
            
            <div class="btgroup form-actions">
                <input id="btnSubmit" type="submit" class="btn btn-primary" value="申报" onclick="registerSave()">
                <input id="btnCancel" type="button" class="btn btn-default" value="返回" onclick="history.go(-1)"/>
            </div>
			</fieldset>
        </form:form>
		
		
		
		
		
		
		
		

<script type="text/javascript" src="${ctx}/dyh/js/jquery-1.8.3.js"></script>
<script type="text/javascript">
$(function(){
    $('fieldset').css('height', ($(window).height() -56) + 'px');
   
})
</script>
</body>
</html>