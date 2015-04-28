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
    
    <title>My JSP 'delUsr.jsp' starting page</title>
    
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
  			<td>序号</td><td>名称</td><td>领导</td><td>操作</td>
  		</tr>
 
  	<c:forEach items="${requestScope.usrVoList}" var="vo" varStatus="v">
  		<tr>
  			<td>${v.count}</td>
  			<td>${vo.usrname}</td>
  			<td>${vo.leaderName}</td>
  			<td>
  				<c:url value="adminDel/delUsrSuccess.html" var="delUsr">
  					<c:param name="usrid" value="${vo.usrid}"></c:param>
  				</c:url>
  				<a href="${delUsr}">删除</a>
  			</td>
  		</tr>
  	</c:forEach>
  	 </table>
  </body>
</html>
