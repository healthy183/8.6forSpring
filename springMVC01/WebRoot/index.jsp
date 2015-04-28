<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
  </head>
  <script type="text/javascript" src="js/jquery-1.9.0.min.js"></script>
  <script type="text/javascript">
  	$(function(){
  		
  		$("#textButton").click(function(){
  			$("#eventButton").click();
  		});
  		
  		
  		
  		/*
  		$("#eventButton").click(function(){
  			alert("测试成功!");	
  		});*/
  	});
  	function textGood(){
			alert("测试成功!");	
		} 
  </script> 
  <body>
  
  <a href="http://localhost:8080/springMVC01/user/register.html">基礎跳轉</a>
  
  
    <a href="grid/index.html">grid.jsp</a>
    
    <br>
    
    
    <input type="button"  id="textButton" value="测试" ></input>
    
     <input type="button"  id="eventButton" value="时间" onclick="textGood();"></input>
  
  </body>
</html>
