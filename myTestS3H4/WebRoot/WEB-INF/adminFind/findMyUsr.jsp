<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="p" tagdir="/WEB-INF/tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'findMyUsr.jsp' starting page</title>
    
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
   <table border="1">
  		<tr>
  			<td>序号</td><td>名称</td><td>领导</td>
  		</tr>
 		<c:forEach items="${page.result}" var="vo" varStatus="v">
  		<tr>
  			<td>${v.count}</td>
  			<td>${vo.usrname}</td>
  			<td>${vo.leaderName}</td>
  		</tr>
  	</c:forEach>
  	<tr>
  		<td colspan="3">
  			<p:PageBar pageUrl="/adminFind/findMyUsr/${usrid}.html" pageAttrKey="page"/>
  		</td>
  	</tr>
  	 </table>
  </body>
</html>
