<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="decorator" content="default"/>
		<title></title>
		<link rel="stylesheet" href="${ctxStatic}/RFM/css/animsition.min.css" />
		<link rel="stylesheet" href="${ctxStatic}/RFM/css/drop-down.css" />
		<link rel="stylesheet" href="${ctxStatic}/RFM/css/common.css" />
		<link rel="stylesheet" href="${ctxStatic}/RFM/css/system.css" />
		<script src="${ctxStatic}/RFM/js/select-widget-min.js"></script>
		<script src="${ctxStatic}/RFM/js/jquery.animsition.min.js"></script>
		<script src="${ctxStatic}/RFM/js/macarons .js"></script>
		<script src="${ctxStatic}/RFM/js/common.js"></script>	
	</head>
	<body>		
		<script>
			var myChart;
			var myChart2;
			$(document).ready(function() {
  			  //初始化切换
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
			  
			   // 基于准备好的dom，初始化echarts实例
		        myChart = echarts.init(document.getElementById('main'),'macarons');
				myChart2 = echarts.init(document.getElementById('main2'),'macarons');
		        // 指定图表的配置项和数据
				var date = ['自然人特派员','法人特派员'];												
						$.ajax({  
					        type : "post",  
					        /* contentType : "application/json",   */
					        url : "${ctx}/queryinfo/info/tpyviewtj1", 
					        data : {"year":'2017'},
					        dataType : "json",  
					        //contentType: "application/x-www-form-urlencoded; charset=utf-8", 
					        success : function(data) {
					         //console.log(data);
					         //function my_data(data){
								//var data = [900,500];			
								//return data;
							//}
							var option = {
							    tooltip: {
							        trigger: 'axis',
							        
							    },					    
							    title: {
							        text: '特派员数量：',						        
							    },				    
							    toolbox: {
							        show : true,
							         feature : {
							            mark : {show: true},
							            dataView : {show: true, readOnly: true},
							            magicType : {show: true, type: ['line', 'bar']},
							            saveAsImage : {}
							        }
							    },
							    calculable : true,
							    xAxis: {
							        type : 'category',
							        boundaryGap : true,
							        data : date
							    },
							    yAxis: {
							        type: 'value',
							         boundaryGap : false,
							    },							   
							    series: [
							        {
							            name:'人数',
							            type:'bar',
							            barMaxWidth:60,//最大宽度
							            itemStyle:{
									            	normal:{
									            		borderWidth : 3,
									            		label : {show: true}
									            	}
									            },
							            data : data.array1
							        }
							    ]
							};
	
			 	var option2 = {
				    tooltip: {
				        trigger: 'item',
				        formatter: function(data){
				        	//console.log(data)
							 myChart.setOption({
						        title : {
							        text: '特派员数量' + data.value,
							    }
						    });
							return data.name + '</br>' + '人数：' + data.value + '</br>占比：' + data.percent + '%'+'</br>';
						}
				    },
				    toolbox: {
				        show : true,
				         feature : {
				            mark : {show: true},
				            dataView : {show: true, readOnly: false},
				            restore : {show: true},
				            saveAsImage : {show: true}
				        }
				    },
				    legend: {
				    	orient: 'horizontal', // 'vertical'
				    	icon:'pie',
				       // orient: 'vertical',
				        x: 'right',
				        y: 'bottom',
				        selectedMode:true,
				        data:['自然人特派员','法人特派员']
				    },
				    series: [
				        {
				            name:'各级别会员人数',
				            center:['50%','50%'],
				            type:'pie',
				            radius: ['50%', '65%'],
				            avoidLabelOverlap: false,
				            label: {
				                normal: {
				                    show: false,
				                    position: 'center',
				                },
				                emphasis: {
				                    show: true,
				                    textStyle: {
				                        fontSize: '20',
				                        fontWeight: 'bold'
				                    }
				                }
				            },
				            labelLine: {
				                normal: {
				                    show: true
				                }
				            },
				            data:[
				                {value:data.array1[0], name:'自然人特派员'},
								{value:data.array1[1], name:'法人特派员'}
				            ]
				        }
				    ]
				};	
				  // 使用刚指定的配置项和数据显示图表。
		       				 myChart.setOption(option);
		       				 myChart2.setOption(option2);
					        },  
					        error : function() {  
					          alert("统计出错！请联系技术人员");  
					        }  
					      });  		 		 	
			});
			var  selectTpyNum = function(){
				myChart.clear();
				myChart2.clear();	
				$.ajax({  
					        type : "post",  
					        /* contentType : "application/json",   */
					        url : "${ctx}/queryinfo/info/tpyviewtj3", 
					        data : {"year":$('#year').val()},
					        dataType : "json",  
					        //contentType: "application/x-www-form-urlencoded; charset=utf-8", 
					        success : function(data) {					       
							var option = {
							    tooltip: {
							        trigger: 'axis',
							        
							    },					    
							    title: {
							        text: '特派员数量：',						        
							    },				    
							    toolbox: {
							        show : true,
							         feature : {
							            mark : {show: true},
							            dataView : {show: true, readOnly: true},
							            saveAsImage : {}
							        }
							    },
							    calculable : true,
							    xAxis: {
							        type : 'category',
							        boundaryGap : true,
							        data : ['自然人特派员','法人特派员']
							    },
							    yAxis: {
							        type: 'value',
							         boundaryGap : false,
							    },							   
							    series: [
							        {
							            name:'人数',
							            type:'bar',
							            barMaxWidth:60,//最大宽度
							            itemStyle:{
									            	normal:{
									            		borderWidth : 3,
									            		label : {show: true}
									            	}
									            },
							            data : data.array2
							        },
							        {
							            name:'zr人数',
							            type:'bar',
							            barMaxWidth:60,//最大宽度
							            itemStyle:{
									            	normal:{
									            		borderWidth : 3,
									            		label : {show: true}
									            	}
									            },
							            data : data.array2
							        }
							    ]
							};
	
			 	var option2 = {
				    tooltip: {
				        trigger: 'item',
				        formatter: function(data){
				        	//console.log(data)
							 myChart.setOption({
						        title : {
							        text: '特派员数量' + data.value,
							    }
						    });
							return data.name + '</br>' + '人数：' + data.value + '</br>占比：' + data.percent + '%'+'</br>';
						}
				    },
				    toolbox: {
				        show : true,
				         feature : {
				            mark : {show: true},
				            dataView : {show: true, readOnly: false},
				            restore : {show: true},
				            saveAsImage : {show: true}
				        }
				    },
				    legend: {
				    	orient: 'horizontal', // 'vertical'
				    	icon:'pie',
				       // orient: 'vertical',
				        x: 'right',
				        y: 'bottom',
				        selectedMode:true,
				        data:['自然人特派员','法人特派员']
				    },
				    series: [
				        {
				            name:'各级别会员人数',
				            center:['50%','50%'],
				            type:'pie',
				            radius: ['50%', '65%'],
				            avoidLabelOverlap: false,
				            label: {
				                normal: {
				                    show: false,
				                    position: 'center',
				                },
				                emphasis: {
				                    show: true,
				                    textStyle: {
				                        fontSize: '20',
				                        fontWeight: 'bold'
				                    }
				                }
				            },
				            labelLine: {
				                normal: {
				                    show: true
				                }
				            },
				            data:[
				                {value:data.array2[0], name:'自然人特派员'},
								{value:data.array2[1], name:'法人特派员'}
				            ]
				        }
				    ]
				};	
				  // 使用刚指定的配置项和数据显示图表。
		       				 myChart.setOption(option);
		       				 myChart2.setOption(option2);
					        },  
					        error : function() {  
					          alert("统计出错！请联系技术人员");  
					        }  
					      });						
			}		
			
				
		</script>
		<div class="data_wrap" style="background: #efeff5; width: 100%; padding: 10px; overflow: hidden;">
			<input id="year" name="year" type="text" readonly="readonly" maxlength="100" class="required" onmouseover="WdatePicker({dateFmt:'yyyy'})"/>
		   	<input type="button" onclick="selectTpyNum()" value="确定"/>
		    <div class="animsition">
				<div style="background: white; solid #efeff5; width: 95%; overflow: hidden;">
					<div id="main" style="height:600px; width: 65%; float: left;"></div>
					<div id="main2" style="height:600px; width: 30%; float: left;"></div>					
				</div>
		    </div>
		</div>		
	</body>
</html>
