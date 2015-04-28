<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'showFormattingConversionServiceFactoryBean.jsp' starting page</title>
    
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
    	usrName:${user.usrName}<br/>
 		usrPassWord:${user.usrPassWord}<br/>
 		birthday:${user.birthday}<br/>
 		formatBirthday:<fmt:formatDate value="${user.birthday}" pattern="yyyy-MM-dd"></fmt:formatDate>
 		<br/>
 		totalCount:${user.totalCount}<br/>
 		discount:${user.discount}<br/>
 		sumMoney:${user.sumMoney}<br/>
 		salary:${user.salary}<br/><br/>
 		<!-- formatNumber只能将String转换成number？在web有意思吗？到底怎么用? -->
 		 formatUserSWD:<fmt:formatNumber value="${user.usrPassWord}" pattern="#,###,##"></fmt:formatNumber> 
 		
  </body>
</html>
