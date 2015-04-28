<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'ValidatePage.jsp' starting page</title>
    
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
    <form:form action="validate/validateUser.html" modelAttribute="validateUser">
    	
		<form:errors path="userName"></form:errors><br/> 	
		<form:input path="userName"></form:input><br/> 	
		    	
    	<form:errors path="password"></form:errors><br/> 	
    	<form:input path="password"/><br/>
    	
    	<form:errors path="birthday"></form:errors><br/> 	
    	<form:input path="birthday" ></form:input><br/>
    	
    	<form:errors path="salary"></form:errors><br/> 	
    	<form:input path="salary" /><br/>
    	
    	<input type="submit" value="提交"/>
    </form:form>
  </body>
</html>
