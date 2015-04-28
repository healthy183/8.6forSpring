<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'welcome.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
 
  <%----%>
     <frameset rows="105,*" cols="*" frameborder="no" border="0" framespacing="0">
  <frame src="welcome/welTop.jsp" name="topFrame" scrolling="no">
	  <frameset cols="180,*" name="btFrame" frameborder="yes" border="0" framespacing="0">
		<frame src="welcome/welLeft.jsp" noresize name="menu" scrolling="no">
		<frame src="welcome/welMain.jsp" noresize name="main" scrolling="yes">
	  </frameset>
</frameset>
<noframes>
	<body>您的浏览器不支持框架！</body>
</noframes>
    
    
    
</html>
