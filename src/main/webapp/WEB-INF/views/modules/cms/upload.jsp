<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 	<meta http-equiv="content-type" content="text/html;charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

	<title>附件上传</title>

    <link rel="stylesheet" href="${ctxStatic}/fileupload1/js/bootsrap/css/blueimp-gallery.min.css">
    <link rel="stylesheet" href="${ctxStatic}/fileupload1/js/bootsrap/css/bootstrap.min.css">   
  	<!--  CSS将文件输入字段设置为按钮，并调整引导进度条 -->
    <link rel="stylesheet" href="${ctxStatic}/fileupload1/css/jquery.fileupload.css">
    <link rel="stylesheet" href="${ctxStatic}/fileupload1/css/jquery.fileupload-ui.css">
  	<!-- 禁用JavaScript的浏览器的CSS调整 -->
   	<noscript>
        <link rel="stylesheet" href="${ctxStatic}/fileupload1/css/jquery.fileupload-noscript.css">
    </noscript>
    <noscript>
        <link rel="stylesheet" href="${ctxStatic}/fileupload1/css/jquery.fileupload-ui-noscript.css">
    </noscript>
	<script type="text/javascript">
		function deleteFile(id){
			var submit = function (v, h, f) {
			    if (v == true){
			    	$.post("${ctx}/jjbgAttachment/jjbgAttachment/deleteFile",{"id":id})
			    	var index=$("#"+id).index();
			    	$("#test tr:eq("+index+")").remove();  
			        jBox.tip("删除成功", 'info');
			    }else{
			    	return true;
			    }		    
			};
			$.jBox.confirm("确认要删除该短消息吗？", "系统提示", submit, { buttons: { '确定': true, '取消': false} }); 
		}
	</script>
    <style>
        body {
            font-family: "Arial", "Microsoft YaHei", "黑体", "宋体", sans-serif;
            background-color: #ffffff; 
            width:330px;
            overflow: hidden;
        }
        * {
            margin: 0;
        }
        html, body {
        	width:330px;
        	color:#555;
        }
        .navbar-custom {
            background-color: #4fb3eb;
        }
        .navbar-brand,
        .navbar-nav li a {
            line-height: 40px;
            height: 40px;
            padding-top: 0px;
            font-family: "Arial", "Microsoft YaHei", "黑体", "宋体", sans-serif;
        }
        .navbar-default .navbar-nav > li > a {
            color: #ffffff;
        }
        .navbar-default .navbar-nav > li > a:hover {
            color: #175A94;
        }
        .page-header {
            font-family: "Arial", "Microsoft YaHei", "黑体", "宋体", sans-serif;
        }
        hr {
            border-bottom: 1px solid #bbb;
        }
        .img_border {
            border: 1px solid #bbb;
        }
        @media screen and (min-width: 330px) {
            .container {
                width: 330px;
            }
        }
        @media screen and (min-width:330px) {
            .center_toaster {
                right: 35%;
                width: 30%;
            }
        }
        @media screen and (min-width: 330px) and (max-width: 330px) {
            .center_toaster {
                right: 25%;
                width: 50%;
            }
        }
        @media screen and (min-width: 330px ) and (max-width: 330px) {
            .center_toaster {
                right: 10%;
                width: 80%;
            }
        }
        .row a {
            text-decoration: none;
        }
        .row a:hover {
            text-decoration: none;
        }
        .addMaigin {
            margin-bottom: 30px;
        }
        .change_font {
            font-size: 1.5em;
        }
        .button_width {
            width: 4em;
        }
        .button_width2 {
            width: 4em;
        }
        .wrapper {
            min-height: 100%;
            height: auto !important;
            height: 100%;
            margin: 0 auto -6em;
        }
        .footer, {
            height: 4em;
        }
        .color_white {
            background: #ffffff;
        }
        #dropzone {
            text-align: center;
            font-weight: bold;
            vertical-align: middle;
            border: 2px dotted #A5A5C7;
            color: #DADCE3;
            background: #ffffff;
        }
        #dropzone.in {
            width: 600px;
            height: 200px;
            line-height: 200px;
            font-size: larger;
        }
        #dropzone.hover {
            background: lawngreen;
        }
        #dropzone.fade {
            -webkit-transition: all 0.3s ease-out;
            -moz-transition: all 0.3s ease-out;
            -ms-transition: all 0.3s ease-out;
            -o-transition: all 0.3s ease-out;
            transition: all 0.3s ease-out;
            opacity: 1;
        }
        #wj{
        	font-size:16px;
        	font-weight:bold;
        	color:white;
        	margin-left:100px;
        	font-family: "Arial", "Microsoft YaHei", "黑体", "宋体", sans-serif;
        }
        #but1{
        	background:#ffffff;
       		border: 1px solid #ffffff;
        }
        #but2{
       		background:#ffffff;
       		border: 1px solid #ffffff;
        }
        #but3{
       		background:#ffffff;
       		border: 1px solid #ffffff;
        }
          #but4{
       		background:#ffffff;
       		border: 1px solid #ffffff;
        }
        .widtn{
        	width: 45px;
        	overflow: hidden;
        	
        }
        span{
        font-size: 12px;
        }
        #files td{
        	height:5px;
        	padding: 3px;
        vertical-align: middle;
        } 
        .fade{
        	height: 30px;
        }
        
        .files tr{
        	height:  30px;
        }
    </style>
</head>
<body>
<div style="height: 10px"></div>
    <!-- Page Content -->
    <div class="container kv-main">
        <form id="fileupload" action="#" method="POST" enctype="multipart/form-data">
        <input type="hidden" id="files" name="files" value="" />
            <noscript><input type="hidden" name="redirect" value="#">
            </noscript>  
            <div class="row fileupload-buttonbar">
                 <div class="col-lg-10" style="font-size:12px;margin-left: -10px"">
                   <span class="btn btn-success fileinput-button" style="width:15%; height:30px;  text-align:center;">
                         <img alt=""  style="width: 14px;position:relative;left:-10px; top:-2px" src="${ctxStatic}/fileupload1/img/sc.png">
                        <span style="position:relative;left:-13px;top:-2px;font-size:12px;">添加</span>
                        <input type="file" name="file" multiple >
                    </span>
                    
                    <button type="submit" onclick="tpwjsc()" class="btn btn-primary start" style="width:15%; height:30px;text-align:center">
                        <img alt=""  style="width: 15px;  position:relative;left:-10px; top:-2px" src="${ctxStatic}/fileupload1/img/upload2.png">
                        <span style="position:relative;left:-13px; top:-2px;font-size:12px;">上传</span> 
                    </button> 
                  <span>&nbsp;(不支持视频文件)</span> 
            	</div>
            </div>
            <table role="presentation" class="table table-striped" id="files">
                <tbody class="files"></tbody>
            </table>
        </form>
    </div>
</div>

 <script id="template-upload" type="text/x-tmpl">
{% for (var i=0, file; file=o.files[i]; i++) { %}

    <tr class="template-upload fade">
        <td>
            <span class="preview"></span>
        </td>
        <td style="border:0px;">
            <p class="name" style="width:100%;margin-top:10px">{%=file.name.substring(0,14)%} 
			</p>
            <strong class="error text-danger"></strong>
        </td>
       
        <td style="float:right;border:0px; margin-right:10px">
            {% if (!i && !o.options.autoUpload) { %}
                <button id="but4" onclick="jysj()" class="btn btn-primary start widtn" >
					<img alt=""  style="width: 20px;height: 20px;" src="${ctxStatic}/fileupload1/img/upload.png">
                </button>
            {% } %}
            {% if (!i) { %}
                <button id="but2" class="btn btn-warning cancel widtn">
 				<img style="width: 20px;height: 20px;" src="${ctxStatic}/fileupload1/img/del1.png">
                    
                </button>
            {% } %}
        </td>
    </tr>
{% } %}
</script>
<script id="template-download" type="text/x-tmpl">
{% for (var i=0, file; file=o.files[i]; i++) { %}
    <tr class="template-download fade">
        <td>
            <span class="preview">
                {% if (file.thumbnailUrl) { %}
                    <a href="{%=file.url%}" title="{%=file.name%}" download="{%=file.name%}" data-gallery><img src="{%=file.thumbnailUrl%}"></a>
                {% } %}
            </span>
        </td>
        <td > 
            <p class="name" style="width:100%;margin-top:10px">
                {% if (file.url) { %}
                    <a id="fileId" href="${ctx}/cms/article/fileDownload?filePath={%=encodeURIComponent(file.url)%}&fileName={%=encodeURIComponent(file.name)%}" value="{%=file.url%}" title="{%=file.name%}" download="{%=file.name%}" {%=file.thumbnailUrl?'data-gallery':''%}>{%=file.name.substring(0,16)%}</a>
                {% } else { %}
                    <span>{%=file.name%}</span>
                {% } %}
            </p>
            {% if (file.error) { %}
                <div><span class="label label-danger">Error</span> {%=file.error%}</div>
            {% } %}
        </td>
       
        <td>
            {% if (file.deleteUrl) { %}
                <button class="btn btn-danger delete" data-type="{%=file.deleteType%}" data-url="{%=file.deleteUrl%}"{% if (file.deleteWithCredentials) { %} data-xhr-fields='{"withCredentials":true}'{% } %}>
                    <i class="glyphicon glyphicon-trash"></i>
                    <span>Delete</span>
                </button>
                <input type="checkbox" name="delete" value="1" class="toggle">
            {% } else { %}
                <button id="but3"  class="btn widtn btn-warning cancel">
					 <img style="width: 20px;height: 20px;" src="${ctxStatic}/fileupload1/img/del1.png">
                </button>
            {% } %}
        </td>
    </tr>
{% } %}
</script>

</body>

<script src="${ctxStatic}/fileupload1/js/bootsrap/js/jquery.min.js"></script>
<script src="${ctxStatic}/fileupload1/js/bootsrap/js/bootstrap.min.js"></script>
<script src="${ctxStatic}/fileupload1/js/bootsrap/js/tmpl.min.js"></script>
<script src="${ctxStatic}/fileupload1/js/bootsrap/js/load-image.all.min.js"></script>
<script src="${ctxStatic}/fileupload1/js/bootsrap/js/canvas-to-blob.min.js"></script>
<script src="${ctxStatic}/fileupload1/js/bootsrap/js/jquery.blueimp-gallery.min.js"></script>
<script src="${ctxStatic}/fileupload1/js/vendor/jquery.ui.widget.js"></script>
<script src="${ctxStatic}/fileupload1/js/jquery.iframe-transport.js"></script>
<script src="${ctxStatic}/fileupload1/js/jquery.fileupload.js"></script>
<script src="${ctxStatic}/fileupload1/js/jquery.fileupload-process.js"></script>
<script src="${ctxStatic}/fileupload1/js/jquery.fileupload-validate.js"></script>
<script src="${ctxStatic}/fileupload1/js/jquery.fileupload-ui.js"></script>

<script type="text/javascript">
    $('#fileupload').fileupload({
        // Uncomment the following to send cross-domain cookies:
        //xhrFields: {withCredentials: true},
        dataType: 'json',
        url: 'uploadFile',
        minFileSize: 1,
      /*acceptFileTypes:/\.(pdf)$/i, */
     
      	acceptFileTypes:/\.(doc|docx|txt|gd|sep|xls|xlsx|gd|sep|png|jpeg|jpg|gif|pdf|rar|zip)$/i,    
      /* 	acceptFileTypes:/\.(png|jpeg|jpg|gif)$/i,  */ 
      
        messages: {
       	acceptFileTypes: '类型不匹配!',
       	minFileSize:'文件为空!'
       	
       },
        dropZone: $('#dropzone')
    });
    $(document).bind('dragover', function (e) {
        var dropZone = $('#dropzone'),
                timeout = window.dropZoneTimeout;
        if (!timeout) {
            dropZone.addClass('in' );
        } else {
            clearTimeout(timeout);
        }
        var found = false,
                node = e.target;
        do {
            if (node === dropZone[0]) {
                found = true;
                break;
            }
            node = node.parentNode;
        } while (node != null);
        if (found) {
            dropZone.addClass('hover');
        } else {
            dropZone.removeClass('hover');
        }
        window.dropZoneTimeout = setTimeout(function () {
            window.dropZoneTimeout = null;
            dropZone.removeClass('in hover');
        }, 100);
    });
    
      function tpwjsc(){
	 	$("#but2").attr("style","display:none;");  
	 }
 	function jysj(){
	  	$("#but2").attr("style","display:none;");  
	 }
  
</script>
</html>