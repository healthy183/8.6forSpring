<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'wel_User.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

 <style type="text/css">
	body
	{
	  scrollbar-base-color:#C0D586; /*	 设置的是滚动条可滑动区域的颜色 */
	  scrollbar-arrow-color:#FFFFFF; /* 上下按钮上三角箭头的颜色 */
	  scrollbar-shadow-color:DEEFC6; /* 立体滚动条阴影的颜色 */	
	}
	</style>
  </head>
  
 <frameset rows="105,*" cols="*" frameborder="no" border="0" framespacing="0">
  <frame src="user/user_Top.jsp" name="topFrame" scrolling="no">
	  <frameset cols="180,*" name="btFrame" frameborder="NO" border="0" framespacing="0">
		<frame src="user/user_left.jsp" noresize name="menu" scrolling="no">
		<frame src="user/main.jsp" noresize name="main" scrolling="yes">
	  </frameset>
</frameset>
<noframes>
	<body>您的浏览器不支持框架！</body>
</noframes>
</html>
