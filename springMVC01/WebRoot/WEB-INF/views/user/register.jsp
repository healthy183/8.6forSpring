<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'reginster.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  用于测试整合javaBean的form
	<form action="user/userShow.html" name="add" id="add" method="post">
		用户名:<input type="text" name="userName" value="梁健康"/><br/>
		真实名字:<input type="text" name="realName" value="梁健康1"/><br/>
		密码:<input type="password" name="passWord" value="梁健康2"/><br/>
		<input type="submit" value="提交"/>
	</form>  
	<hr/>
	
	用 户测试parameter的form
	<form action="user/testParam.html" method="post">
		<input name="showParam" value="参数值是abc"/>
		<input type="submit" value="提交"/>
	</form>
<hr/>
测试request.setAttribute和getAttribute
<form action="user/testAttribute.html" method="post">
		<input name="showAttribute" value="参数值是123"/>
		<input type="submit" value="提交"/>
	</form>
<hr/>
测试@ModelAttribute
<form action="user/showModelAttrSuccess.html" method="post">
	用户名:<input type="text" name="userName" value="梁健康"/>
	<input type="submit" value="提交"/>
</form><hr/>
测试ModelMap
<form action="user/showModelMapSuccess.html" method="post">
	用户名:<input type="text" name="userName" value="梁健康"/>
	<input type="submit" value="提交"/>
</form>
<hr/>
测试@SessionAttribute("modelUser")
<form action="model/showSessionAttrSuccess.html" method="post">
	用户名:<input type="text" name="userName" value="梁健康"/>
	<input type="submit" value="提交"/>
</form><hr/>

测试@PathVariable 的rest风格url
<form action="model/abc/showPathVariable.html" method="post">
	<input type="submit" value="提交"/>
</form>
<hr/>
测试webRequest request
<form action="model/showTestWebRequest.html" method="post" >
		<input type="submit" value="提交"/>
</form>

  </body>
</html>
