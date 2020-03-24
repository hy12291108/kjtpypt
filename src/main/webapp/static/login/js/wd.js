
$(function(){
		   
	//按钮状态


	$(".btn-com-01").mouseover(function(){
		$(this).addClass("btn-com-up01");}).mouseout(function(){
		$(this).removeClass("btn-com-up01");});
	
	$(".btn-unimp-01").mouseover(function(){
		$(this).addClass("btn-unimp-up01");}).mouseout(function(){
		$(this).removeClass("btn-unimp-up01");});
	
	$(".btn-com-02").mouseover(function(){
		$(this).addClass("btn-com-up02");}).mouseout(function(){
		$(this).removeClass("btn-com-up02");});
	
	$(".btn-unimp-02").mouseover(function(){
		$(this).addClass("btn-unimp-up02");}).mouseout(function(){
		$(this).removeClass("btn-unimp-up02");});
	
	$(".btn-com-03").mouseover(function(){
		$(this).addClass("btn-com-up03");}).mouseout(function(){
		$(this).removeClass("btn-com-up03");});
	
	$(".btn-unimp-03").mouseover(function(){
		$(this).addClass("btn-unimp-up03");}).mouseout(function(){
		$(this).removeClass("btn-unimp-up03");});
	
	$(".btn-imp-01").mouseover(function(){
		$(this).addClass("btn-imp-up01");}).mouseout(function(){
		$(this).removeClass("btn-imp-up01");});
	
	$(".btn-imp-02").mouseover(function(){
		$(this).addClass("btn-imp-up02");}).mouseout(function(){
		$(this).removeClass("btn-imp-up02");});
	
	$(".btn-imp-03").mouseover(function(){
		$(this).addClass("btn-imp-up03");}).mouseout(function(){
		$(this).removeClass("btn-imp-up03");});
	
	$(".btn-com-04").mouseover(function(){
		$(this).addClass("btn-com-up04");}).mouseout(function(){
		$(this).removeClass("btn-com-up04");});
	
	$(".btn-unimp-04").mouseover(function(){
		$(this).addClass("btn-unimp-up04");}).mouseout(function(){
		$(this).removeClass("btn-unimp-up04");});
	
	$(".btn-com-05").mouseover(function(){
		$(this).addClass("btn-com-up05");}).mouseout(function(){
		$(this).removeClass("btn-com-up05");});
	
	$(".btn-com-06").mouseover(function(){
		$(this).addClass("btn-com-up06");}).mouseout(function(){
		$(this).removeClass("btn-com-up06");});


	//文本框输入状态


	$(".input").mouseover(function(){
		$(this).addClass("input-up");}).mouseout(function(){
		$(this).removeClass("input-up");});
	
	/** list **/
   $(".send-list tr:even").addClass("alt");
   //给class为stripeTb的表格的偶数行添加class值为alt
   
   $(".FrontOrders_showMyCart01-d1_c1 .tablelist tr:odd").addClass("tr-nos");
   //给class为stripeTb的表格的偶数行添加class值为alt

   (function() {
	$("a:contains(首页)").attr("href", "/index.html#index");
	var w = $("body#index");
	if(!!w.length) {
		var g = w.find("#guide");
		if(location.href.indexOf("#index") == -1) {
			var bg = g.find(".bg");
			var logo = g.find(".logo");
			var top_line = g.find(".top_line");
			var bottom_line = g.find(".bottom_line");
			var enter = g.find(".enter img");
			if(!+[1,]) {
				w.fadeIn();
				bg.css("top", "-100%").delay(500).animate({"top":0}, 1800);
				logo.css("top", "-100%").delay(1200).animate({"top":"30%"}, 1800);;
				top_line.css("left", "100%").delay(2200).animate({"left":0}, 800);;
				bottom_line.css("left", "-100%").delay(2200).animate({"left":0}, 800);;
				enter.css("top", "0").css("opacity", "0").delay(2500).animate({"top":13, "opacity":1}, 900);;
				g.find(".enter").click(function() {
					g.fadeOut();
					setTimeout(function() {
						w.css("overflow-y", "scroll");
					}, 300);
				});	
			} else {
				var d = (function() {
					commonAni = ["bounceIn", "bounceInLeft", "bounceInRight", "fadeInLeft", "fadeInRight"];
					var n = ~~(Math.random()*commonAni.length);
					return {
						css:function(target, t, delay) {
							target.addClass("fadeOut").css({
								"animation-delay":(delay||0) + "s",
								"-webkit-animation-delay":(delay||0) + "s",
							}).removeClass("fadeOut").addClass(commonAni[n]).css({
								"-webkit-animation-duration": t + "s",
								"animation-duration": t + "s",
								"-webkit-animation-fill-mode": "both",
								"animation-fill-mode": "both"
							});
							return this;
						}, hide:function(target, t, delay) {
							$.each(commonAni, function(i) {
								target.removeClass(commonAni[i]);
							});
							target.css({
								"animation-delay":(delay||0) + "s",
								"-webkit-animation-delay":(delay||0) + "s",
							}).addClass(commonAni[n].replace("In", "Out")).css({
								"-webkit-animation-duration": t + "s",
								"animation-duration": t + "s",
								"-webkit-animation-fill-mode": "both",
								"animation-fill-mode": "both"
							});
							return this;
						}
					}
				})();
				d.css(bg, 1, 0).css(logo, 1, 0.5).css(enter, 2, 1).css(top_line, 1, 1.5).css(bottom_line, 1, 1.5);
				/*
				g.find(".enter").click(function() {
					d.hide(bg, 1, 1.5).hide(logo, 1, 1).hide(enter, 0.8, 0.5).hide(top_line, 0.6, 0).hide(bottom_line, 0.6, 0);
					g.delay(2500).fadeOut();
					setTimeout(function() {
						w.css("overflow-y", "scroll");
						// location.href = "/index.html#index";
					}, 2700);
				});
				*/
				g.find(".enter").click(function() {
					g.fadeOut();
					w.css("overflow-y", "scroll");
				});
				w.fadeIn();
			}
			var doAni = false;
			enter.mouseenter(function() {
				if(!doAni) {
					doAni = true;
					top_line.stop(true, true).animate({"left":"100%"}, 500, function() {
						$(this).css("left", "-100%").animate({"left":"0%"}, 400);
					});
					bottom_line.stop(true, true).animate({"left":"-100%"}, 500, function() {
						$(this).css("left", "100%").animate({"left":"0%"}, 400);
					});
				}
			});
			enter.mouseout(function() {
				doAni = false;
			});
		} else {
			g.hide();
			w.css("overflow-y", "scroll").fadeIn();
		}
	}
   })();
   
   w();
   sub_nav();
})

function sub_nav() {
	var right = $("#box_nav li > a[href*=news_list]").siblings().find(".right").empty();
	$.get("/news_list/&newsCategoryId=4.html", function(e) {
		var s = $(e).find(".FrontNews_list01-d2_c2 .content");
		s.each(function(i) {
			var a = $(this).find('a').eq(0);
			var ah = a.attr("href");
			var title = a.attr("title");
			var p = $(this).find(".summary p");
			p.find("a").remove();
			p = p.text();
			if(p.length > 30) {
				p = p.substring(0, 60) + "...";
			}
			if(title.length > 20) {
				title = title.substring(0, 20) + "...";
			}
			right.append('<div class="sub_nav_news_list"><span>></span><a href="javascript:void(0)">'+title+'</a><div class="des">'+p+'<a href="'+ah+'">查看详细>></a></div></div>');
			
			if(s.length  == i + 1) {
				setTimeout(function() {
					$(".sub_nav_news_list span, .sub_nav_news_list a").click(function() {
						var b = $(this).siblings(".des");
						if(b.is(":visible")) {
							b.slideUp();
						} else {
							$(".sub_nav_news_list .des").slideUp();
							b.slideDown();
						}
					});
				}, 300);
			}
			$(".sub_nav_news_list .des").eq(0).slideDown();
		});
	});
}

function w() {
	if($("body#index").length > 0) {
		// index
		$("#elem-FrontSpecifies_show01-1462780851627 li").each(function() {
			$(this).append("<div class='bg'></div>");
		});
		
		// 新闻动态

		(function() {
			var w  = $("#elem-FrontSpecifies_show01-1462780490279 a");
			var d = $("#box_index_news .w_content .right .w_tabs .columnSpace");
			d.eq(0).show();
			w.mouseenter(function() {
				var index = w.index($(this));
				if(!$(this).hasClass("current")) {
					w.removeClass("current").eq(index).addClass("current");
					d.stop(true, true).hide().andSelf().eq(index).stop(true, true).show();
				}
			});
		})();
		
		// 集团企业
		(function() {
			var w  = $("#index_main3 div.w_container > .right .w_cate a");
			var d = $("#index_main3 div.w_container > .right .w_content .columnSpace");
			d.eq(0).show();
			w.mouseenter(function() {
				var index = w.index($(this));
				if(!$(this).hasClass("current")) {
					w.removeClass("current").eq(index).addClass("current");
					d.stop(true, true).hide().andSelf().eq(index).stop(true, true).show();
				}
			});
		})();
		
		// 产业发展等


		(function() {
			var w  = $("#index_main3 div.w_container > .left div.top .w_title a");
			var d = $("#index_main3 div.w_container > .left div.top .w_content .content_news .columnSpace");
			d.eq(0).show();
			w.mouseenter(function() {
				var index = w.index($(this));
				if(!$(this).hasClass("current")) {
					w.removeClass("current").eq(index).addClass("current");
					d.stop(true, true).hide().andSelf().eq(index).stop(true, true).show();
				}
			});
		})();
		
		// 企业文化等


		(function() {
			var w  = $("#index_main3 .left div.bottom div.left .w_title a");
			var d = $("#index_main3 .left div.bottom div.left .w_content .content_news .columnSpace");
			d.eq(0).show();
			w.mouseenter(function() {
				var index = w.index($(this));
				if(!$(this).hasClass("current")) {
					w.removeClass("current").eq(index).addClass("current");
					d.stop(true, true).hide().andSelf().eq(index).stop(true, true).show();
				}
			});
		})();
		
		//显示集团视频
		$("#index_main3 div.w_container .bottom .right .w_content .columnSpace").show();
		$("#index_main3 div.w_container .bottom .right .w_content div.movie").click(function() {
			$("#video").fadeIn().find(".close").click(function() {
				$("#video").fadeOut();
			});
			
		});
	
		(function() {
			var productsScroll = $("#FrontNews_list01-1462872279878");
			var productsIntervalCheck = setInterval(function() {
				var imgs = $("ul.newslist-01 li img", productsScroll);
				if(imgs.length > 0) {
					clearInterval(productsIntervalCheck);
					var activity =  $("ul.newslist-01", productsScroll).css("position", "relative");
					$("li.clearBoth", activity).remove();
					var lis = $("li.content", activity);
					activity.append(lis.clone());
					activity.append(lis.clone());
					var w = lis.eq(0).outerWidth();
					activity.css("width", w*lis.length*2+599);
					var i = 0, t;
					function scroll() {
						t = setInterval(function() {
							i++;
							activity.css("left", -i);
							if(i >= w*lis.length) {
								i=0;
							}
						}, 20);
					}
					function stop() {
						clearInterval(t);
					}
					
					scroll();
					activity.parent().hover(function() {
						stop();
					}, function() {
						scroll();
					});
				}
			},100);
		})();
		
		
		setTimeout(function() {
			// 特色展示
			$(".ent_feature .FrontNews_list01-d2_c1 .content").each(function() {
				$(this).append("<div class='bg'></div>");
			}).hover(function() {
				$(this).find(".bg").fadeIn().parent().find(".newstitle a").fadeIn().parents(".content").find(".newslist p").fadeIn();
			}, function() {
				$(this).find(".newslist p").fadeOut().parents(".content").find(".newstitle a").fadeOut().parents(".content").find(".bg").fadeOut();
			});
		}, 1000);
	} else {
	
		/**
			内页
		*/
		$("#sub_main #sub_main_left .w_cate a:not(.menu-text2)").each(function() {
			$(this).attr("title", $.trim($(this).text()));
			if(location.href.indexOf($(this).attr("href")) != -1) {
				$(this).addClass("current");
			}
			$(this).appendTo($(this).parents(".w_cate"));
		})
		
		
		if($("#elem-FrontNewsCategory_tree01-1462844490994").length > 0) {
			$("#sub_main #sub_main_left .w_cate").append('<a href="/download_list/&downloadcategoryid=10&isMode=false.html" target="_self" class="menu-text1">下载中心</a>');
		}
		
		// 修改产业动态链接


		if($("#elem-FrontComContent_list01-1462846753282").length > 0) {
			$("#sub_main #sub_main_left .w_cate a[title=产业动态]").attr("href", "/industry_list/&newsCategoryId=17.html");
		}
		// 修改投资动态链接


		if($("#elem-FrontComContent_list01-1462847545200").length > 0) {
			$("#sub_main #sub_main_left .w_cate a[title=投资动态]").attr("href", "/investment_list/&newsCategoryId=19.html");
		}
		// 修改文化活动链接

		if($("#elem-FrontComContent_list01-1462860617926").length > 0) {
			$("#sub_main #sub_main_left .w_cate a[title=文化活动]").attr("href", "/culture_list/&newsCategoryId=20.html");
		}
		
		// 修改通知公告链接
		if($("#elem-FrontNewsCategory_tree01-1462844490994").length > 0) {
			$("#sub_main #sub_main_left .w_cate a[href='/news_list/&newsCategoryId=5.html']").attr("href", "/news_list2/&newsCategoryId=5.html");
		}
		
		// 科研力量新闻列表链接修改
		/*
		(function() {
			var w = $("#elem-FrontNews_list01-1463384614927");
			if(!!w.length) {
				//省市、行业工程技术中心、工程实验室
				w.find("a[href='/scientific_list_2_detail/newsId=305.html']").attr("href", "/scientific_list_2_1/&newsCategoryId=40.html");
				//集团下属企业研发机构
				w.find("a[href='/scientific_list_2_detail/newsId=306.html']").attr("href", "/scientific_list_2_1/&newsCategoryId=41.html");
				//行业委员会


				w.find("a[href='/scientific_list_2_detail/newsId=308.html']").attr("href", "/scientific_list_2_1/&newsCategoryId=42.html");
			}
		})();
		*/
		
		// 修改科技服务链接
		if($("#elem-FrontComContent_list01-1462846753282").length > 0) {
			var a = $("#sub_main #sub_main_left .w_cate a[title=科技咨询服务]").attr("href", "/industry_s.html");
			if(!!a.length) {
				$("#sub_main_left .w_cate").append('<div class="sub_cate"><a href="/industry_s_list/&newsCategoryId=25.html">科技期刊</a><a href="/industry_s_list/&newsCategoryId=26.html">检测检验机构</a><a href="/industry_s_list/&newsCategoryId=27.html">行业科技服务机构</a><a href="/industry_s_list/&newsCategoryId=28.html">行业科技信息中心、网站</a><a href="/industry_s_list/&newsCategoryId=37.html">行业委员会</a></div>');
				var sub = $(".sub_cate");
				a.hover(function() {
					sub.show();
				}, function() {
					sub.delay(500).hide();
				});
				sub.hover(function() {
					sub.show();
				}, function() {
					sub.hide();
				});
			}
		}
		
		// 科技创新新闻分类高亮
		(function(w) {
			if(!!w.length) {
				var h = location.href;
				h = h.substring(h.indexOf("/industry_s_list/"));
				w.find("a[href='" + h +"']").addClass("w_current");
			}
		})($("#elem-FrontNewsCategory_tree01-1463455175610"));
		
		// 集团视频分类高亮
		(function(w) {
			if(!!w.length) {
				w.parent().find("a[title='集团视频']").attr("href", "/video.html");
			}
		})($("#elem-FrontComContent_list01-1462860617926"));
		
		//bread_links 
		(function() {
			var news = $("div[class*='FrontNews_list']");
			var bread = $(".FrontPublic_breadCrumb01-d1_c1 a:gt(0)");
			var s = $("#sub_main #sub_main_left .w_cate a");
			bread.each(function() {
				var h = $(this).attr("href");
				var t = $.trim($(this).text());
				if(t != "产业发展") {
					var c = s.filter("a[title='"+t+"']");
					if(!c.hasClass("current")) {
						c.addClass("current");
					}
					if(c.length) {
						var href = c.attr("href");
						var href2 = href.substring(1);
						if(href2.indexOf("/") != -1) {
							href2 = href2.substring(0, href2.indexOf("/"));
						} else {
							href2 = href2.substring(0, href2.indexOf("."));
						}
						if(href2 == "industry_s") {
							href2 = "industry_s_list";
						}
						$(this).attr("href", href);
						$(this).siblings().each(function() {
							var t = $.trim($(this).text());
							if(t != "首页") {
								var h = $(this).attr("href");
								h = h.substring(1);
								h = h.substring(h.indexOf("/"), h.length);
								$(this).attr("href", "/" + href2 + h);
							}
						});
					}
				} else {
					$(this).attr("href", "/industry/&i=19&comContentId=19.html");
				}
			});
		})();
		
		
		//面包屑字数过多


		(function() {
			var w = $("#sub_main #sub_main_right .w_bread .FrontPublic_breadCrumb01-d1_c1 DIV");
			var h = w.html();
			//console.log(h);
			var size = h.length;
			//console.log(size);
			var n = h.lastIndexOf("</a>");
			//console.log(h);
			var string1 = h.substring(0, n + 4);
			var a_num = string1.length;
			var string2 = h.substring(n + 4, h.length);
			//console.log(string2);
			if(size > 270) {
				h = string1 + string2.substring(0, 260 - a_num) + "...";
				//console.log(h);
				w.html(h);
			}
		})();
	}
	
	// nav
	(function() {
		var w = $("#elem-FrontSpecifies_show01-1462780132617 ul li");
		w.filter(":gt(6)").find(".sub").css("margin-left", "-250px");
	})();
	
	
	// tables
	(function() {
		var s = window.location.href.substring(location.href.lastIndexOf("/")+1);
		s = s.substring(0, s.lastIndexOf("."));
		if(location.href.indexOf("newsPageNo") != -1) {
			s = window.location.href.substring(location.href.lastIndexOf("/")+1);
			s = s.substring(s.indexOf("newsId=")+7, s.lastIndexOf("&"));
		}
		if("newsId=379, newsId=378, newsId=377, newsId=376, newsId=375, newsId=374".indexOf(s) != -1) {
			$(".FrontNews_detail01-d1_c1 table").each(function() {
				$(this).css({
					"border-bottom":"1px solid #95b0dd",
					"border-right":"1px solid #95b0dd"
				}).attr("border", 0);
				var th = $(this).find("tr").eq(0).find("td");
				var td = $(this).find("tr:gt(0)").filter(":odd").find("td");
				$(this).find("td").css({
					"text-align":"center",
					"border-left":"1px solid #95b0dd",
					"border-top":"1px solid #95b0dd"
				});
				th.css({
					"background-color":"#4472c5", 
					"color":"#fff"
				});
				td.css({
					"background-color":"#d9e3f3",
					"text-align":"center"
				});
			})
		}
	})();
	
	/*
	// 添加新闻详细来源
	(function() {
		if(location.href.indexOf("news_detail") != -1) {
			$('<span class="sources"><em>来源：</em><span id="news_sources">官网</span></span>').insertBefore($(".FrontNews_detail01-d1_c1 .message").children().eq(0));
			$(".FrontNews_detail01-d1_c1 .message").children().eq(1).appendTo($(".FrontNews_detail01-d1_c1 .message"));
		}
	})();
	*/
	
	(function() {
		if($("body[id*='scientific_list_']").length) {
			if(location.href.indexOf("_detail") != -1) {
				$("#sub_main #sub_main_right .w_bread .w_ad").hide();
			}
		}
	})();
	
	/*
	(function() {
		// 二级左侧跟随滚动
		var left = $("#sub_main #sub_main_left");
		$(window).scroll(function() {
			var t = $(window).scrollTop();
			if(t > 405) {
				left.stop(true, false).delay(100).animate({"margin-top":t - 405}, 700);
			} else {
				left.stop(true, true).animate({"margin-top":0}, 800);
			}
		});
	})();
	*/
	
}