<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/modules/cms/front/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>陕西省科技特派员</title>

<!-- 201709021加服务平台页面样式 -->
<link href="/kjtpypt/static/skines/css/lrtk.css" type="text/css" rel="stylesheet" />
<script src="/kjtpypt/static/skines/js/jquery-1.8.3.min.js" type="text/javascript"></script>
<script src="/kjtpypt/static/skines/js/koala.min.1.5.js" type="text/javascript"></script>
<link href="/kjtpypt/static/skines/css/gundong.css" type="text/css" rel="stylesheet" />
<link href="/kjtpypt/static/skines/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
<link href="/kjtpypt/static/skines/css/css.css" type="text/css" rel="stylesheet" />
</head>
<script type="text/javascript">

var Speed_1 = 10; //速度(毫秒)
var Space_1 = 20; //每次移动(px)
//var PageWidth_1 = 246 * 6; 翻页宽度
var PageWidth_1 = 246 * 4; //翻页宽度
var interval_1 = 5000; //翻页间隔时间
var fill_1 = 0; //整体移位
var MoveLock_1 = false;
var MoveTimeObj_1;
var MoveWay_1="right";
var Comp_1 = 0;
var AutoPlayObj_1=null;
function GetObj(objName){if(document.getElementById){return eval('document.getElementById("'+objName+'")')}else{return eval('document.all.'+objName)}}
function AutoPlay_1(){clearInterval(AutoPlayObj_1);AutoPlayObj_1=setInterval('ISL_GoDown_1();ISL_StopDown_1();',interval_1)}
function ISL_GoUp_1(){if(MoveLock_1)return;clearInterval(AutoPlayObj_1);MoveLock_1=true;MoveWay_1="left";MoveTimeObj_1=setInterval('ISL_ScrUp_1();',Speed_1);}
function ISL_StopUp_1(){if(MoveWay_1 == "right"){return};clearInterval(MoveTimeObj_1);if((GetObj('ISL_Cont_1').scrollLeft-fill_1)%PageWidth_1!=0){Comp_1=fill_1-(GetObj('ISL_Cont_1').scrollLeft%PageWidth_1);CompScr_1()}else{MoveLock_1=false}
AutoPlay_1()}
function ISL_ScrUp_1(){if(GetObj('ISL_Cont_1').scrollLeft<=0){GetObj('ISL_Cont_1').scrollLeft=GetObj('ISL_Cont_1').scrollLeft+GetObj('List1_1').offsetWidth}
GetObj('ISL_Cont_1').scrollLeft-=Space_1}
function ISL_GoDown_1(){clearInterval(MoveTimeObj_1);if(MoveLock_1)return;clearInterval(AutoPlayObj_1);MoveLock_1=true;MoveWay_1="right";ISL_ScrDown_1();MoveTimeObj_1=setInterval('ISL_ScrDown_1()',Speed_1)}
function ISL_StopDown_1(){if(MoveWay_1 == "left"){return};clearInterval(MoveTimeObj_1);if(GetObj('ISL_Cont_1').scrollLeft%PageWidth_1-(fill_1>=0?fill_1:fill_1+1)!=0){Comp_1=PageWidth_1-GetObj('ISL_Cont_1').scrollLeft%PageWidth_1+fill_1;CompScr_1()}else{MoveLock_1=false}
AutoPlay_1()}
function ISL_ScrDown_1(){if(GetObj('ISL_Cont_1').scrollLeft>=GetObj('List1_1').scrollWidth){GetObj('ISL_Cont_1').scrollLeft=GetObj('ISL_Cont_1').scrollLeft-GetObj('List1_1').scrollWidth}
GetObj('ISL_Cont_1').scrollLeft+=Space_1}
function CompScr_1(){if(Comp_1==0){MoveLock_1=false;return}
var num,TempSpeed=Speed_1,TempSpace=Space_1;if(Math.abs(Comp_1)<PageWidth_1/2){TempSpace=Math.round(Math.abs(Comp_1/Space_1));if(TempSpace<1){TempSpace=1}}
if(Comp_1<0){if(Comp_1<-TempSpace){Comp_1+=TempSpace;num=TempSpace}else{num=-Comp_1;Comp_1=0}
GetObj('ISL_Cont_1').scrollLeft-=num;setTimeout('CompScr_1()',TempSpeed)}else{if(Comp_1>TempSpace){Comp_1-=TempSpace;num=TempSpace}else{num=Comp_1;Comp_1=0}
GetObj('ISL_Cont_1').scrollLeft+=num;setTimeout('CompScr_1()',TempSpeed)}}
function picrun_ini(){
GetObj("List2_1").innerHTML=GetObj("List1_1").innerHTML;
GetObj('ISL_Cont_1').scrollLeft=fill_1>=0?fill_1:GetObj('List1_1').scrollWidth-Math.abs(fill_1);
GetObj("ISL_Cont_1").onmouseover=function(){clearInterval(AutoPlayObj_1)}
GetObj("ISL_Cont_1").onmouseout=function(){AutoPlay_1()}
AutoPlay_1();
}
</script>
<style type="text/css">
*{margin:0;padding:0;list-style-type:none;}
a,img{border:0;}
body{font:12px/180% Arial, Helvetica, sans-serif, "新宋体";}

/* pop */
#pop{background:#fff;width:220px;border:1px solid #e0e0e0;font-size:12px;position:fixed;right:0px;bottom:0px;}
#popHead{line-height:32px;background:#f6f0f3;border-bottom:1px solid #e0e0e0;position:relative;font-size:12px;padding:0 0 0 10px;}
#popHead h2{font-size:14px;color:#438cce;line-height:32px;height:32px;margin: 0;}
#popHead #popClose{position:absolute;right:10px;top:1px;}
#popHead a#popClose:hover{color:#f00;cursor:pointer;}
#popContent{padding:5px 10px;}
#popTitle a{line-height:24px;font-size:14px;font-family:'微软雅黑';color:#333;font-weight:bold;text-decoration:none;}
#popTitle a:hover{color:#f60;}
#popIntro{text-indent:24px;line-height:160%;margin:5px 0;color:#666;}
#popMore{border-top:1px dotted #ccc;line-height:24px;margin:8px 0 0 0;}
#popMore a{color:#438cce;}
#popMore a:hover{color:#f00;}
</style>
<body>
<div class="header">
	<div class="top">
		<h1><img src="/kjtpypt/static/skines/images/logo.png" width="560" height="42" alt=""/><span>陕西省科技特派员服务与管理系统</span></h1>
		<ul class="login">
		<c:if test="${user.name!=''&&user.name!=null}">
		<li><a href="#" class="bt">${user.name}已登录</a></li>
		<li><a href="/kjtpypt/a/logout">退出</a></li>
		</c:if>
		<c:if test="${user.name==''||user.name==null}">
			<li><a href="/kjtpypt/a/login" class="bt">登录</a></li>
			<li>没有账户？<a href="/kjtpypt/a/UserRegister/registerClassify">注册/申报</a></li>
		</c:if>
		</ul>
	</div>
	<div class="navbg">
		<div class="nav">
			<ul>
				<li><a href="#" class="current">首  页</a></li>
				<li><a href="/kjtpypt/f/list-6.html">通知通告</a></li>
				<li><a href="/kjtpypt/f/list-2.html">动态新闻</a></li>
				<li><a href="/kjtpypt/f/list-10.html">特派员风采</a></li>
				<li><a href="/kjtpypt/f/list-1000.html">学习园地</a></li>
				<li><a href="/kjtpypt/a/communication/listAll">用户交流</a></li>
				<li><a href="/kjtpypt/f/list-999.html">专家咨询</a></li>
				<li><a href="/kjtpypt/a/communication/contactMe">联系我们</a></li>
			</ul>
		</div>
	</div>
</div>
<div class="container002">
		<div class="notice">
			<h2>通知通告</h2>
			<ul class="more">
				<li><a href="${ctx}/list-6${urlSuffix}">更多>></a></li>
			</ul>
			<ol>
				<c:forEach items="${fnc:getArticleList(site.id, 6, 2, '')}" var="article">
				<li><a href="${article.url}" style="color:${article.color}">${fns:abbr(article.title,45)}</a><span ><fmt:formatDate value="${article.updateDate}" pattern="yyyy-MM-dd"/></span></li>
				</c:forEach>
			</ol>
		</div>
		<div class="slx">
			<!-- 代码 开始 -->
			<div id="fsD1" class="focus_news">
				<div id="D1pic1" class="fPic">
				<c:forEach items="${fnc:getArticleList(site.id, 2, 3, 'image:1')}" var="article">
				<div class="fcon" style="display: none;">
						<a target="_blank" href="${article.url}"><img src="${article.image}" style="opacity: 1; "></a>
						<span class="shadow"><a target="_blank" href="${article.url}">${fns:abbr(article.title,20)}</a></span>
				</div>
				</c:forEach>
				</div>
				<div class="fbg">
				<div class="D1fBt" id="D1fBt">
					<a href="javascript:void(0)" hidefocus="true" target="_self" class=""><i>1</i></a>
					<a href="javascript:void(0)" hidefocus="true" target="_self" class=""><i>2</i></a>
					<a href="javascript:void(0)" hidefocus="true" target="_self" class="current"><i>3</i></a>
				</div>
				</div>
				<span class="prev"></span>
				<span class="next"></span>
			</div>
			<script type="text/javascript">
				Qfast.add('widgets', { path: "/kjtpypt/static/skines/js/terminator2.2.min.js", type: "js", requires: ['fx'] });
				Qfast(false, 'widgets', function () {
					K.tabs({
						id: 'fsD1',   //焦点图包裹id
						conId: "D1pic1",  //** 大图域包裹id
						tabId:"D1fBt",
						tabTn:"a",
						conCn: '.fcon', //** 大图域配置class
						auto: 1,   //自动播放 1或0
						effect: 'fade',   //效果配置
						eType: 'click', //** 鼠标事件
						pageBt:true,//是否有按钮切换页码
						bns: ['.prev', '.next'],//** 前后按钮配置class
						interval: 3000  //** 停顿时间
					})
				})
			</script>
			<!-- 代码 结束 -->
		</div>


	<div class="news">
		<h2><span>动态新闻</span></h2>
		<ul class="more">
			<li><a href="${ctx}/list-2${urlSuffix}">更多>></a></li>
		</ul>
		<div class="newsinfo">
			<ul>
				<c:forEach items="${fnc:getArticleList(site.id, 2, 5, '')}" var="article">
				<li><a href="${article.url}" style="color:${article.color}">${fns:abbr(article.title,60)}</a><span ><fmt:formatDate value="${article.updateDate}" pattern="yyyy-MM-dd"/></span></li>
				</c:forEach>
			</ul>
		</div>
	</div>

	<div class="fengcai">
		<h2><span>特派员风采</span></h2>

		<div class="info">
			<!-- picrotate_left start  -->
			<div class="blk_18"> <a class="LeftBotton" onmousedown="ISL_GoUp_1()" onmouseup="ISL_StopUp_1()" onmouseout="ISL_StopUp_1()" href="javascript:void(0);" target="_self"></a>
			  <div class="pcont" id="ISL_Cont_1">
				<div class="ScrCont">
				  <div id="List1_1">
				<c:forEach items="${fnc:getArticleList(site.id, 10, 9, '')}" var="article">
				<a class="pl" href="${article.url}" target="_blank"><img style="width:230px;height:165px" src="${article.image}" alt="#" width="230" height="145"/><span>${fns:abbr(article.title,40)}</span></a>
				<!--<a href="${article.url}" style="color:${article.color}">${fns:abbr(article.title,66)}</a><span ><fmt:formatDate value="${article.updateDate}" pattern="yyyy.MM.dd"/></span></li>-->
				</c:forEach>
					<!--<a class="pl" href="piclist.html" target="_blank"><img src="/kjtpypt/static/skines/images/pic001.jpg" alt="#" width="230" height="165"/><span>助力葛粉增产</span></a>
					<a class="pl" href="piclist.html" target="_blank"> <img src="/kjtpypt/static/skines/images/pic001.jpg" alt="#" width="230" height="165"/><span>助力葛粉增产</span></a>
					<a class="pl" href="piclist.html" target="_blank"><img src="/kjtpypt/static/skines/images/pic001.jpg" alt="#" width="230" height="165"/><span>助力葛粉增产</span></a>
					<a class="pl" href="piclist.html" target="_blank"><img src="/kjtpypt/static/skines/images/pic001.jpg" alt="#" width="230" height="165"/><span>助力葛粉增产</span></a>
					<a class="pl" href="piclist.html" target="_blank"><img src="/kjtpypt/static/skines/images/pic001.jpg" alt="#" width="230" height="165"/><span>助力葛粉增产</span></a>
					<a class="pl" href="piclist.html" target="_blank"><img src="/kjtpypt/static/skines/images/pic001.jpg" alt="#" width="230" height="165"/><span>助力葛粉增产</span></a>
					<a class="pl" href="piclist.html" target="_blank"><img src="/kjtpypt/static/skines/images/pic001.jpg" alt="#" width="230" height="165"/><span>助力葛粉增产</span></a>
					<a class="pl" href="piclist.html" target="_blank"><img src="/kjtpypt/static/skines/images/pic001.jpg" alt="#" width="230" height="165"/><span>助力葛粉增产</span></a>
					<a class="pl" href="piclist.html" target="_blank"><img src="/kjtpypt/static/skines/images/pic001.jpg" alt="#" width="230" height="165"/><span>助力葛粉增产</span></a>-->
				  </div>
				  <div id="List2_1"></div>
				</div>
			  </div>
			  <a class="RightBotton" onmousedown="ISL_GoDown_1()" onmouseup="ISL_StopDown_1()" onmouseout="ISL_StopDown_1()" href="javascript:void(0);" target="_self"></a> </div>
			<div class="c"></div>
			<script type="text/javascript">
			<!--
			picrun_ini()
			//-->
			</script>
			<!-- picrotate_left end -->
   		 </div>




	</div>

	<div class="yuandi">
		<h2><span>学习园地</span></h2>
		<ul class="more">
			<li><a href="${ctx}/list-1000${urlSuffix}">更多>></a></li>
		</ul>
		<div class="yuandiinfo">
			<ol>
				<c:forEach items="${fnc:getArticleListByXxyd(site.id, 1000, 1, '')}" var="article">
				<c:forEach items="${article.sysAttachmentList}" var="a1">
                	<!-- <li><a href="${article.url}"><video width="100%" height="255" controls="controls" src="http://127.0.0.1:8080/kjtpypt/a/dailywork/village/vilProtocol/showImage?filename=${a1.attName}&fileType=${a1.attFolder}"></video><span>${fns:abbr(article.title,25)}</span></a></li>
                	<li><a href="${article.url}"><video width="100%" height="255" controls="controls" src="http://10.0.248.53:8080/kjtpypt/a/dailywork/village/vilProtocol/showImage?filename=${a1.attName}&fileType=${a1.attFolder}"></video><span>${fns:abbr(article.title,25)}</span></a></li> -->
				    <li><a href="${article.url}"><video width="100%" height="255" controls="controls" src="http://www.sxkjtpy.cn/a/dailywork/village/vilProtocol/showImage?filename=${a1.attName}&fileType=${a1.attFolder}"></video><span>${fns:abbr(article.title,25)}</span></a></li>
				</c:forEach>
				<!-- <li><a href="${article.url}"><img src="${article.image}"/><span>${fns:abbr(article.title,25)}</span></a></li> -->
				</c:forEach>
			</ol>
			<ul>
				<c:forEach items="${fnc:getArticleList(site.id, 1000, 3, '')}" var="article">
				<li><a href="${article.url}" style="color:${article.color}">${fns:abbr(article.title,45)}</a><span ><fmt:formatDate value="${article.updateDate}" pattern="yyyy-MM-dd"/></span></li>
				</c:forEach>
			</ul>
		</div>
	</div>

	<div class="jiaoliu">
		<h2><span>用户交流</span></h2>
		<ul class="more">

			<li><a href="/kjtpypt/a/communication/saveIndex">发布</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="/kjtpypt/a/communication/listAll">更多>></a></li>
		</ul>
		<div class="jiaoliuinfo">
		<ul>
				<c:forEach items="${fnc:getCommunicationList(11)}" var="communication">
				<li><a href="/kjtpypt/a/communication/viewPage?id=${communication.id}" >${fns:abbr(communication.title,45)}</a><span ><fmt:formatDate value="${communication.createDate}" pattern="yyyy-MM-dd"/></span></li>
				</c:forEach>
		</ul>
		</div>
	</div>

	<div class="zixun">
		<h2><span>专家咨询</span></h2>
		<ul class="tw">
			<li><a href="/kjtpypt/a/cms/article/zjzxform?category.name=专家咨询&id=&category.id=999">我要咨询>></a></li>
		</ul>
		<ul class="more">
			<li><a href="/kjtpypt/f/list-999.html">更多>></a></li>
		</ul>
		<div class="zixuninfo">
			<ul>
				<!--  <li><a href="#">平台专家到安康开展科技服务...<img src="/kjtpypt/static/skines/images/video.gif" alt=""/></a><span>08-22</span></li>
				<li><a href="#">平台专家到榆林开展科技服务...<img src="/kjtpypt/static/skines/images/video.gif" alt=""/></a><span>08-22</span></li>
				<li><a href="#">平台专家到延安开展服务...<img src="/kjtpypt/static/skines/images/video.gif" alt=""/></a><span>08-22</span></li>
				<li><a href="#">平台专家到安康开展科技服务...<img src="/kjtpypt/static/skines/images/video.gif" alt=""/></a><span>08-22</span></li>
				<li><a href="#">平台专家到安康开展科技服务...<img src="/kjtpypt/static/skines/images/voice.gif" alt=""/></a><span>08-22</span></li>
				<li><a href="#">平台专家到延安开展服务...<img src="/kjtpypt/static/skines/images/voice.gif" alt=""/></a><span>08-22</span></li>-->
				<c:forEach items="${fnc:getArticleList(site.id, 999, 6, '')}" var="article">
					<c:if test="${fn:length(article.sysAttachmentList)!=0}">
						<li><a href="${article.url}" style="color:${article.color}">${fns:abbr(article.title,36)}<img src="/kjtpypt/static/skines/images/video.gif" alt=""/></a><span ><fmt:formatDate value="${article.updateDate}" pattern="yyyy-MM-dd"/></span></li>
					</c:if>
					<c:if test="${fn:length(article.sysAttachmentList)==0}">
						<li><a href="${article.url}" style="color:${article.color}">${fns:abbr(article.title,36)}</a><span ><fmt:formatDate value="${article.updateDate}" pattern="yyyy-MM-dd"/></span></li>
					</c:if>
				</c:forEach>
			</ul>
		</div>
	</div>
	<div class="xglj">
		<h2><span>相关链接|</span></h2>
		<div class="xgljinfos">
		<ul>
			<li class="li1">
				<a href="http://114.sstrc.gov.cn/"><font style="font-size:20px;color: #fff;">科&nbsp;技&nbsp;114</font></a>
			</li>
			<li class="li2">
				<a><font style="font-size:20px;color: #fff;">农业科技园区</font></a>
			</li>
			<li class="li3">
				<a><font style="font-size:20px;color: #fff;">实验示范站</font></a>
			</li>
			<li class="li4">
				<a><font style="font-size:20px;color: #fff;">星创天地</font></a>
			</li>
		</ul>
	 	  <!-- <div class="xgljinfo" style="width:25%;height: 50px;"></div>
	 	  <div class="xgljinfo1" style="width:25%;height: 50px;"></div>
		  <div class="xgljinfo2" style="width:25%;height: 50px;"></div>

 		  <div class="xgljinfo3" style="width:25%;height: 50px; "></div>-->
 		  </div>
	</div>
</div>
<div class="footer">陕西省科技特派员服务与管理系统 版权所有     Copyright © 2017 备案信息：陕ICP备***********&nbsp;&nbsp;&nbsp;&nbsp;<a   href="http://www.sxkjtpy.cn/a"><font  style="color: #fff;">进入管理系统>></font></a></div>
<!-- APP下载框 -->
<script type="text/javascript" src="/kjtpypt/static/skines/js/jquery.min.js"></script>
<script type="text/javascript" src="/kjtpypt/static/skines/js/yanue.pop.js"></script>
<script type="text/javascript" >
//记得加载jquery
//使用参数：1.标题，2.链接地址，3.内容简介
window.onload=function(){
	var pop=new Pop("App下载","","<span style='color:#438cce'>Android:</span><img src='/kjtpypt/static/skines/images/zhengshi.png' />");
	//var pop=new Pop("","","");
}
</script>
<div id="pop" style="display:none;">

	<div id="popHead"> <a id="popClose" title="关闭"><font color="#438cce">关闭</font></a>
		<h2>APP下载</h2>
	</div>
	<div id="popContent">
		<dl>
			<dd id="popIntro">这里是内容简介</dd>
		</dl>
		<p id="popMore"><font style='color:#438cce'>IOS:苹果商店搜索“陕西省科技特派员”下载。</font></p>
	</div>

</div>
</body>
</html>