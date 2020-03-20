$().ready(function() {
	mkzh();
});

function mkzh() {
		    alert("111");
			if ("${category.name}"=="新闻公告" ){
			 alert("222");
				$("#xwgg").show();
				$("#tpyfc").hide();
				page(1);
			}else{
			alert("222");
				$("#xwgg").hide();
				$("#tpyfc").show();
				page(1);
			}
		}