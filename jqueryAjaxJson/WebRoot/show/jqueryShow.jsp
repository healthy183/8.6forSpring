<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'jqueryShow.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.2.js"></script>
<script type="text/javascript">
	var jq = jQuery.noConflict();
	jq(function(){
		jq("#testToggle").click(function(){
			//jq("#toggleSpan").toggle(4000);//点击隐藏or显示 时间参数可选
			jq("#toggleSpan").toggle(4000,function(){
				alert("abc"); //点击隐藏or显示 之后触发回调函数
			});
		});
		jq("#testfadeOut").click(function(){
			jq("#toggleSpan").fadeOut("slow");//淡出
		});
		jq("#testfadeIn").click(function(){
			jq("#toggleSpan").fadeIn("slow");//淡入
		});
		jq("#testFadeto").click(function(){
			//jq("#toggleSpan").fadeTo();
			jq("#toggleSpan").fadeToggle("slow","linear");//同toggle有咩唔同
		});
		jq("#testAnimate").click(function(){
			jq("#toggleSpan").animate({
				left:"+50px"
			},1000);
		});
	});
</script>
  </head>
  
  <body>
	<input id="testToggle" type="button" value="测试toggle()"/>
	<input id="testfadeOut" type="button" value="测试fadeOut()"/>
	<input id="testfadeIn" type="button" value="测试fadeIn()"/>
	<input id="testFadeto" type="button" value="测试fadeTo(唔识用)"/>
	<input id="testAnimate" type="button" value="测试Animate(唔识用)"/>
	<br/>
	<span id="toggleSpan">toggleSpan</span>
  </body>
</html>
