<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="decorator" content="default"/>
		<title></title>
		<link rel="stylesheet" type="text/css" href="${ctxStatic}/RFM/css/font-awesome.css">
		<!-- <link rel="stylesheet" type="text/css" href="http://www.jq22.com/jquery/font-awesome.4.6.0.css"> -->
		<link rel="stylesheet" href="${ctxStatic}/RFM/css/animsition.min.css" />
		<link rel="stylesheet" href="${ctxStatic}/RFM/css/drop-down.css" />
		<link rel="stylesheet" href="${ctxStatic}/RFM/css/common.css" />
		<link rel="stylesheet" href="${ctxStatic}/RFM/css/xsfx.css" />
		<link rel="stylesheet" href="${ctxStatic}/RFM/jedate/skin/jedate.css" />
		<link rel="stylesheet" href="${ctxStatic}/RFM/css/jquery-labelauty.css" />
		<script src="http://www.jq22.com/jquery/jquery-1.10.2.js"></script>
		<script src="${ctxStatic}/RFM/js/jquery-labelauty.js"></script>
		<script src="${ctxStatic}/RFM/js/jquery.cityselect.js"></script>
		<script src="${ctxStatic}/RFM/jedate/jquery.jedate.min.js"></script>
		<script src="http://www.jq22.com/jquery/jquery-ui-1.11.0.js"></script>
		<script src="${ctxStatic}/RFM/js/select-widget-min.js"></script>
		<script src="${ctxStatic}/RFM/js/jquery.animsition.min.js"></script>
		<script src="http://echarts.baidu.com/build/dist/echarts.js"></script>
		<script src="${ctxStatic}/RFM/js/macarons .js"></script>
		<script src="${ctxStatic}/RFM/js/common.js"></script>
		<script src="${ctxStatic}/RFM/js/sq_data.js"></script>
		
	</head>
	<body>
		
		<script>
			var myChart;
			$(document).ready(function() {
				//插件初始化
				$(":checkbox").labelauty({
					checked_label: "",
					unchecked_label: "",
				});
				$(":radio").labelauty();
			  $(".animsition").animsition({
			  
			    inClass               :   'fade-in-right',
			    outClass              :   'fade-out',
			    inDuration            :    1500,
			    outDuration           :    800,
			    linkElement           :   '.animsition-link',
			    // e.g. linkElement   :   'a:not([target="_blank"]):not([href^=#])'
			    loading               :    true,
			    loadingParentElement  :   'body', //animsition wrapper element
			    loadingClass          :   'animsition-loading',
			    unSupportCss          : [ 'animation-duration',
			                              '-webkit-animation-duration',
			                              '-o-animation-duration'
			                            ],
			    //"unSupportCss" option allows you to disable the "animsition" in case the css property in the array is not supported by your browser.
			    //The default setting is to disable the "animsition" in a browser that does not support "animation-duration".
			    
			    overlay               :   false,
			    
			    overlayClass          :   'animsition-overlay-slide',
			    overlayParentElement  :   'body'
			  });
				var date = ['01月','02月','03月','04月','05月','06月','07月','08月','09月','10月','11月','12月','总计'];
				
			    // 路径配置
		        require.config({
		            paths: {
		                echarts: 'http://echarts.baidu.com/build/dist'
		            }
		        });
		        
		        // 使用
		        require(
		            [
		                'echarts',
		                'echarts/chart/bar',
		                'echarts/chart/line',
		                'echarts/chart/pie',
		                'echarts/chart/map',// 使用柱状图就加载bar模块，按需加载
		            ],
		            function (ec) {
		                // 基于准备好的dom，初始化echarts图表
		                myChart = ec.init(document.getElementById('main1'),'macarons');
		                var ecConfig = require('echarts/config');
		                $.ajax({  
					        type : "post",  
					        /* contentType : "application/json",   */
					        url : "${ctx}/queryinfo/info/tpytjview2", 
					        data : {"year":$('#year').val(),"bfsx":'jtjjzc'},
					        dataType : "json",  
					        //contentType: "application/x-www-form-urlencoded; charset=utf-8", 
					        success : function(data) {
						var option = {
							backgroundColor : 'white',
						    tooltip : {
						        trigger: 'axis'
						    },
						    grid :{
						    	y : 100,
						    	y2 : 80
						    },
						    toolbox: {
						        show : true,
						        y:32,
						        x :1200,
						        feature : {
						            //mark : {show: true},
						            dataView : {show: true, readOnly: false},
						            magicType : {show: true, type: ['line', 'bar','pie']},
						            restore : {show: true},
						            saveAsImage : {show: true}
						        }
						    },
						    calculable : true,
						    xAxis : [
						        {
						            type : 'category',
						            boundaryGap : false,
						            data : date
						        }
						    ],
						    yAxis : {
							        type: 'value'							        
							    },		
						    series : [						        						      
						        {
						            name:$("#year option:checked").text()+$("#bfsx option:checked").text(),
						            type:'line',
						            stack: '总量',
						            barMaxWidth : 25,
						            symbol:'emptyTriangle',
						            itemStyle: {
						                normal: {
						                	label : {show: true},
						                    lineStyle: {            // 系列级个性化折线样式，横向渐变描边
						                        width: 2,
						                        color: (function (){
						                            var zrColor = require('zrender/tool/color');
						                            return zrColor.getLinearGradient(
						                                0, 0, 1000, 0,
						                                [[0, 'rgba(143,100,229,0.8)'],[0.8, 'rgba(68,234,354,0.8)']]
						                            )
						                        })(),
						                        shadowColor : 'rgba(0,0,0,0.5)',
						                        shadowBlur: 10,
						                        shadowOffsetX: 4,
						                        shadowOffsetY: 4
						                    }
						                }
						               
						            },
						            data:data
						        }
						    ]
						};
                    

		                // 为echarts对象加载数据 
		                myChart.setOption(option); 
		               },  
					        error : function() {  
					          alert("统计出错！请联系技术人员");  
					        }  
					      });
			            }
			        );
			   
					//关闭提示
					$('.xsfx_tips  i').on('click',function(){
						$(this).parent().slideUp();
					})
			
			});
			var selectbfsx = function(){
				myChart.clear();
				$.ajax({  
					        type : "post",  
					        /* contentType : "application/json",   */
					        url : "${ctx}/queryinfo/info/tpytjview2", 
					        data : {"year":$('#year').val(),"bfsx":$('#bfsx').val()},
					        dataType : "json",  
					        //contentType: "application/x-www-form-urlencoded; charset=utf-8", 
					        success : function(data) {
						var option = {
							backgroundColor : 'white',
						    tooltip : {
						        trigger: 'axis'
						    },
						    grid :{
						    	y : 100,
						    	y2 : 80
						    },
						    toolbox: {
						        show : true,
						        y:32,
						        x :1200,
						        feature : {
						            //mark : {show: true},
						            dataView : {show: true, readOnly: false},
						            magicType : {show: true, type: ['line', 'bar']},
						            restore : {show: true},
						            saveAsImage : {show: true}
						        }
						    },
						    calculable : true,					   
						    xAxis : [
						        {
						            type : 'category',
						            boundaryGap : false,
						            data : ['01月','02月','03月','04月','05月','06月','07月','08月','09月','10月','11月','12月','总计']
						        }
						    ],
						    yAxis : {
							        type: 'value'							        
							    },		
						    series : [						        						      
						        {
						            name:$("#year option:checked").text()+$("#bfsx option:checked").text(),
						            type:'line',
						            stack: '总量',
						            barMaxWidth : 25,
						            symbol:'emptyTriangle',
						            itemStyle: {
						                normal: {
						                	label : {show: true},
						                    lineStyle: {            // 系列级个性化折线样式，横向渐变描边
						                        width: 2,
						                        color: (function (){
						                            var zrColor = require('zrender/tool/color');
						                            return zrColor.getLinearGradient(
						                                0, 0, 1000, 0,
						                                [[0, 'rgba(143,100,229,0.8)'],[0.8, 'rgba(68,234,354,0.8)']]
						                            )
						                        })(),
						                        shadowColor : 'rgba(0,0,0,0.5)',
						                        shadowBlur: 10,
						                        shadowOffsetX: 4,
						                        shadowOffsetY: 4
						                    }
						                }
						               
						            },
						            data:data
						        }
						    ]
						};
                    

		                // 为echarts对象加载数据 
		                myChart.setOption(option); 
		               },  
					        error : function() {  
					          alert("统计出错！请联系技术人员");  
					        }  
					      });
			}
		</script>
		<div class="data_wrap" style="background: #efeff5; width: 100%; padding: 10px;">
		    <div class="animsition" style="overflow: hidden;">
				<div class="xsfx_tips">
				</div>
				 <div class="my_duxs_time">					
					<div id="city_1" class="inline" style="margin-right: 30px;">	
						帮扶属性：					
						<select class="form-control" id="bfsx" style="width: 200px;">
						<option value="jtjjzc">集体经济增收 (单位:万元)</option>
						<option value="sxcz">实现产值 (单位:万元)</option>
						<option value="ddzs">带动增收 (单位:万元)</option>
						<option value="cls">创利税 (单位:万元)</option>
						<option value="yjtgxpzxcp">引进推广新品种新产品 (单位:个)</option>
						<option value="tgxjs">推广新技术 (单位:个)</option>
						<option value="jjjsnt">解决技术难题 (单位:个)</option>
						<option value="pxnm">培训农民 (单位:人次)</option>
						<option value="cbqyhzs">创办领办培育企业或合作社 (单位:个)</option>
						<option value="ddjyrc">带动就业 (单位:人次)</option>
						<option value="fwnchzzz">服务农村合作组织 (单位:个)</option>
						<option value="jlkjsfjd">建立科技示范基地 (单位:个)</option>
						<option value="pykjsfh">培育科技示范户 (单位:户)</option>
						<option value="jbpxb">举办培训班 (单位:班次)</option>
						<option value="pxry">培训人员 (单位:人次)</option>
						<option value="pxpkqz">培训贫困群众 (单位:人次)</option>
						<option value="bfpkc">帮扶贫困村 (单位:个)</option>
						<option value="pkh">贫困户 (单位:户)</option>
                      	<option value="ddtp">带动脱贫 (单位:户)</option>
                      	<option value="pkrk">贫困人口 (单位:个)</option>
                      	<option value="kjfwts">科技服务天数 (单位:天)</option>
              			</select>
              			年份：
              			<select class="form-control" id="year" style="width: 200px;">
              				<c:forEach var="item" items="${YearList}">  
                            <option value="${item.year}">${item.year}</option>  
                         	</c:forEach> 
              			</select>			
						<input type="button" onclick="selectbfsx()" value="确定"/><br><br>
						<label>${array}</label>
					</div>				
			    </div>
			    	<div id="main1" class="my_main2" style="width: 100%; height: 650px; float: left;"></div>
		    </div>   
		</div>	
	</body>
</html>
