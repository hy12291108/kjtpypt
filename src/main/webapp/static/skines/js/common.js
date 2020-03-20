//全局
window.tui={};
//loading
tui.Loading=new Function();
tui.Loading.prototype={
	init:function(o){
		this.o=o;
		this.oImg=document.createElement('div');
		this.oImg.className='loading';
		this.oImg.style.display='none';
		this.oB=document.createElement('b');
		this.oB.innerHTML='loading';
		this.oImg.appendChild(this.oB);
		this.o.appendChild(this.oImg);
		if (this.oTop) {
			this.oImg.style.top=this.oTop+'px'
		};
	},
	funcShow:function(){
		var that=this;
		var i=0;
		this.oImg.style.display='block';
		this.oAuto=setInterval(loading,80);
		function loading(){
			i==11?i=0:i++;
			that.oB.style.top="-"+i*40+"px"
		};
	},
	funcStop:function(){
		clearInterval(this.oAuto);
		this.oImg.style.display='none';
	}
};
//alert
tui.Alert=function (text,title) {
	var o=document.getElementById('alert_mask');
	var out=document.getElementById('alert_out');
	out.innerHTML='';
	o.style.display='none';
	title=title || '提示';
	out.innerHTML='<div class="alert_inn"><h3><b>X</b>'+title+'</h3><h4>'+text+'</h4><p class="p_0"><a href="javascript:;" hidefocus="true" title="确定">确定</a></p>';
	var a=o.getElementsByTagName('a');
	var b=o.getElementsByTagName('b')[0];
	o.style.height=Math.max(document.body.offsetHeight,document.body.clientHeight,document.documentElement.clientHeight)+'px';
	out.style.top=(Math.max(document.body.scrollTop,document.documentElement.scrollTop)+200)+'px';
	b.onclick=a[0].onclick=function(){
		out.innerHTML='';
		o.style.display='none';
	};
	o.style.display='block';
};
//confirm
tui.Confirm=function (set) {
	var o=document.getElementById('alert_mask');
	var out=document.getElementById('alert_out');
	set.title=set.title || '提示';
	set.callBack=set.callBack || false;
	out.innerHTML='';
	o.style.display='none';
	out.innerHTML='<div class="alert_inn"><h3><b>X</b>'+set.title+'</h3><h4>'+set.text+'</h4><p class="p_0"><a href="javascript:;" hidefocus="true" title="确定">确定</a><a href="javascript:;" hidefocus="true" title="取消">取消</a></p>';
	var a=o.getElementsByTagName('a');
	var b=o.getElementsByTagName('b')[0];
	o.style.height=Math.max(document.body.offsetHeight,document.body.clientHeight,document.documentElement.clientHeight)+'px';
	out.style.top=(Math.max(document.body.scrollTop,document.documentElement.scrollTop)+200)+'px';
	b.onclick=a[1].onclick=function(){
		out.innerHTML='';
		o.style.display='none';
	};
	a[0].onclick=function(){
		out.innerHTML='';
		o.style.display='none';
		set.callBack();
	};
	o.style.display='block';
};
//addEvent
tui.addEvent=function(Elem, type, handle) {
	if (Elem.addEventListener) {
		Elem.addEventListener(type, handle, false);
	} else if (Elem.attachEvent) {
		Elem.attachEvent("on" + type, handle);
	};
};
//delEvent
tui.delEvent=function(Elem, type, handle) {
	if (Elem.removeEventListener) {
		Elem.removeEventListener(type, handle, false);
	} else if (Elem.detachEvent) {
		Elem.detachEvent("on" + type, handle);
	};
};
tui.getElemPos=function(obj){
	var pos = {"top":0, "left":0};
	if (obj.offsetParent){
		while (obj.offsetParent){
			pos.top += obj.offsetTop;
			pos.left += obj.offsetLeft;
			obj = obj.offsetParent;
		}
	}else if(obj.x){
		pos.left += obj.x;
	}else if(obj.x){
		pos.top += obj.y;
	}
	return {x:pos.left, y:pos.top};
};
//placeHolder
tui.placeHolder=function(input) {
	for (var i=0;i<input.length;i++) {
		input[i].onfocus=function(){
			var that=this;
			that.className='i_0';
			setTimeout(function(){that.select()},10);
		};
		input[i].onblur=function(){
			this.className='';
		};
	};
};
//useragent
tui.Sys=function (){
	var Sys={};
	var ua = navigator.userAgent.toLowerCase();
	var s;
	(s = ua.match(/msie ([\d.]+)/)) ? Sys.ie = s[1] :
	(s = ua.match(/firefox\/([\d.]+)/)) ? Sys.firefox = s[1] :
	(s = ua.match(/chrome\/([\d.]+)/)) ? Sys.chrome = s[1] :
	(s = ua.match(/opera.([\d.]+)/)) ? Sys.opera = s[1] :
	(s = ua.match(/version\/([\d.]+).*safari/)) ? Sys.safari = s[1] : 0;
	return Sys;
};
//正则
tui.regular=function(type,value){
	//正则表达式
	var regular={};
	//域名
	//regular['domain']=/^([a-zA-Z0-9]([a-zA-Z0-9\-]{0,61}[a-zA-Z0-9])?\.)+[a-zA-Z]{2,6}$/;
	var strRegex = "^((https|http|ftp|rtsp|mms)?://)" 
 		+ "?(([0-9a-z_!~*'().&=+$%-]+: )?[0-9a-z_!~*'().&=+$%-]+@)?" //ftp的user@ 
        + "(([0-9]{1,3}\.){3}[0-9]{1,3}" // IP形式的URL- 199.194.52.184 
        + "|" // 允许IP和DOMAIN（域名）
        + "([0-9a-z_!~*'()-]+\.)*" // 域名- www. 
        + "([0-9a-z][0-9a-z-]{0,61})?[0-9a-z]\." // 二级域名 
        + "[a-z]{2,6})" // first level domain- .com or .museum 
        + "(:[0-9]{1,4})?" // 端口- :80 
        + "((/?)|" // a slash isn't required if there is no file name 
        + "(/[0-9a-z_!~*'().;?:@&=+$,%#-]+)+/?)$";
	regular['domain']=new RegExp(strRegex); 
	//二级域名
	regular['subDomain']=/([0-9a-z][0-9a-z-]{0,61})?[0-9a-z]\./;
	//正整数
	regular['num']=/^[1-9]\d*$/;
	//中文 英文 数字
	regular['text']=/^([\u4E00-\uFA29]|[\uE7C7-\uE7F3]|[\w])*$/;
	//邮箱
	regular['email']=/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
	
	return regular[type].test(value);
};
function Sys(){
	var Sys={};
	var ua = navigator.userAgent.toLowerCase();
	var s;
	(s = ua.match(/msie ([\d.]+)/)) ? Sys.ie = s[1] :
	(s = ua.match(/firefox\/([\d.]+)/)) ? Sys.firefox = s[1] :
	(s = ua.match(/chrome\/([\d.]+)/)) ? Sys.chrome = s[1] :
	(s = ua.match(/opera.([\d.]+)/)) ? Sys.opera = s[1] :
	(s = ua.match(/version\/([\d.]+).*safari/)) ? Sys.safari = s[1] : 0;
	return Sys;
};
function addEvent(Elem, type, handle) {
	if (Elem.addEventListener) {
		Elem.addEventListener(type, handle, false);
	} else if (Elem.attachEvent) {
		Elem.attachEvent("on" + type, handle);
	};
};