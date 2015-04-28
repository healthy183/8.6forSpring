<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'jqueryChose.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
		body {
			text-align: right;	
	}
	</style>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.2.js"></script>
<script type="text/javascript"> //基本选择器
	$(function(){  
		$("#idButton").click(function(){//id选择器
			$(this).css("background","#99CCFF");
		});
		
		$(".BasicClass").each(function(){
			$(this).click(function(){//class选择器
				$(".BasicClass").css("background","#99FF00");
			});
		});
		
		$("#idElement").click(function(){//将<P>里面的字体设置为12px
			$("P").css("font-size","12px");
		});
		
		$("#starElement").click(function(){ //*选择器 修改form下面所有的元素背景颜色
			$("form *").css("background","#9999FF");
		});
		
		$("#apposeElement").click(function(){//并列选择器 将<P>,<div>里面的字体设置为12px
			$("P,div").css("font-size","12px");
		});
	});

</script>
<script type="text/javascript">//层次选择器
	$(function(){
		
		$("#gradation").click(function(){ //div下面第一个span
			$("div > span").css("color","#FF0000");
		});
		
		$("#nextBrother").click(function(){//查找class为.item的元素的下一个属性并且是div的元素
			$(".item + div").css("color","#00FF66");//等价于
		//$(".item").next("div").css("color","#00FF66");
		});
		
		$("#allBrother").click(function(){ //查找class为item下为div的所有元素
			$(".item ~ div").css("color","#0033FF");//等价于
			//$(".item").nextAll("div").css("color","#0033FF");
		});
	});

</script>

</head>
  <body>
  <form>基本选择器<br/>
  	<input type="button" id="idButton"  value="id选择器"/><hr/>
  	
  	<input type="button" id="classButton" class="BasicClass" value="class选择器"/>
  	<input type="button"  class="BasicClass" value="class选择器2"/><hr/>
  	
  	<P><input type="button" id="idElement" value="element选择器"/>字体</P><hr/>
  	
  	<input type="button" id="starElement" value="*选择器"/><hr/>
  	
  	<input type="button" id="apposeElement" value="并列选择器"/><div>并列</div>
  	
</form><hr/>层次选择器<br/>
<input type="button" id="gradation" value="直系子元素选择器"/><hr/>
<div>
	<span>123</span>
	<p>
		<span>456</span>
	</p>
</div><hr/>
<input type="button" id="nextBrother" value="下一个兄弟元素选择器"/><hr/>
<p class="item"></p>
<p>0000</p>
<div>123</div><div>456</div>
<span class="item"></span>
<div>789</div><hr/>
	  
<input type="button" id="allBrother" value="所有兄弟元素选择器"/><hr/>	
<p class="item"></p>
<p>0000</p>
<div>123</div><div>456</div>
<div>789</div><hr/>  
   
  </body>
</html>
