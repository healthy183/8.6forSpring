<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'jqueryFilterChose.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style>
			body {
			text-align: right;	
		}
		.hid-1{
			display:none;
		}
		.hid-2{
			visibility: hidden;;
		}		
	</style>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.2.js"></script>
<script type="text/javascript"> 
	$(function(){
		$("#basicFilter").click(function(){ //id为thisDiv下的span第一个，最后一个
			$("#thisDiv span:first").css("color","#CC0000");
			$("#thisDiv span:last").css("color","#CC0000");
		});
		
		$("#notFilter").click(function(){
			//$("(.notFilter):not(#notFilterDIV)").css("color","#999966");//为什么无效?
			//$("div:not(.notFilter)").css("color","#00FF00");
			$("div:not(#notFilterDIV)").css("color","#00FF00");//为什么不可以覆盖上面事件的css颜色?
		});
		
		
		$("#evenOddFilter").click(function(){ //指定table奇偶hr的背景
			$("#evenOddTable tr:even").css("background","#EEE");
			$("#evenOddTable tr:odd").css("background","#DADADA");
		});
		
		$("#eqFilter").click(function(){
			$("#evenOddTable tr:eq(3)").css("background","#FF0000");	
		});
		
		$("#gtltFilter").click(function(){
			$("#evenOddTable tr:gt(2) ").css("background","#CCFF00");//小标大于2 青色
			$("#evenOddTable tr:lt(2)").css("background","#00CC00");//下标小于2 绿色
		});
		
		$("#hrFilter").click(function(){ //选择h1 h2等元素
			$(":header").css("background","#0099FF");
		});
		
		$("#containFilter").click(function(){
			$("dd:contains('jquery')").css("color","#33FF00");
			$(".dt:contains('技术')").css("color","#CC6600");//包含'技术'class为.dt的元素
			$(".thisdd:empty").html("web 开发"); //class为.dt并且为空的元素
		});
		
		$("#hasFilter").click(function(){//div子元素包含span的
			$("div:has(span)").css("background","#FFCC00");
		});
		
		$("#parentFilter").click(function(){ //ol所有li有内容或者包含子元素的li
			$("ol li:parent").css("background","#FFCC00");
		});
		
		$("#hiddenFilter").click(function(){//:hidden过滤选择器
			//jquery 1.3.2以后:hidden以后只匹配display:none <input type="hidden"/>的元素,因为他们不占用空间
			//visibility:hidden或opacity:0的元素等就不匹配了
			$("span:hidden").show(); 
			alert($("input:hidden").val());
		});
		
		$("#visibleFilter").click(function(){ //匹配所有可见的span
			$("span:visible").css("background","#EEADBB");
		});
	});

</script>
  </head>
  <body>
  <h1>过滤选择器</h1><hr/>
  <input type="button" id="basicFilter" value="基本过滤选择器"/>
  <div id="thisDiv"><p>0000</p>
  		<span>123</span><span>456</span><span>789</span>
  </div><hr/>
  
   <input type="button" id="notFilter" value="not过滤选择器"/>
  <div>123</div><!-- class="notFilter" -->
  <div class="notFilter" id="notFilterDIV">345</div>
  
  <input type="button" id="evenOddFilter" value="奇偶过滤选择器"/><br/>
  <input type="button" id="eqFilter" value="eq索引过滤选择器"/><br/>
  <input type="button" id="gtltFilter" value="gt大于lt小于过滤选择器"/>
  <table id="evenOddTable">
  	<tr><td>abc</td></tr>
  	<tr><td>def</td></tr>
  	<tr><td>ghi</td></tr>
  	<tr><td>jkl</td></tr>
  </table>
 <input type="button" id="hrFilter" value="hr过滤选择器"/><br/>
 
  <input type="button" id="containFilter" value="contains,empty内容过滤选择器"/>
  <dl>
  		<dt class="dt">技术</dt>
  			<dd class="thisdd">jquery javascript</dd>
  		<dt class="dt">类型</dt>
  			<dd class="thisdd"></dd>
  </dl>  
  
  <input type="button" id="hasFilter" value="has过滤选择器"/>
  <div>123</div>
  <div><span>345</span></div>
  
    <input type="button" id="parentFilter" value=":parent过滤选择器"/>
  	<ol>
  		<li>A</li><li></li><li><b>a</b></li><li></li>
  	</ol>
  	<input type="button" id="hiddenFilter" value=":hidden过滤选择器"/><br/>
  	<span class="hid-1">display:none</span><br/>
  	<span class="hid-2">visibility:hidden 占用空间的!取消br后可见</span><br/>
  	<span>:visible选择器</span><br/>
  	<input type="hidden" value="隐藏元素" />
  	<input type="button" id="visibleFilter" value=":visible过滤选择器"/>
  	
  </body>
</html>
