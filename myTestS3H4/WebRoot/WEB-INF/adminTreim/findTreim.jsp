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
    
    <title>My JSP 'findTreim.jsp' starting page</title>
    
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
  			<th colspan="2">${sessionScope.lgnUser.usrname}的申请表单</th>
  		</tr>
  		<c:forEach items="${requestScope.treimList}" var="vo" varStatus="v">
  			<tr>
  				<th>${vo.rmname}</th>
  				<th>
  					<c:if test="${vo.rmdesc == 0}">
  						待审批
  					</c:if>
  					<c:if test="${vo.rmdesc == 1}">
  						通过
  					</c:if>
  					<c:if test="${vo.rmdesc == 2}">
  						被拒
  					</c:if>
  				</th>
  			</tr>
  				<c:forEach items="${vo.TReimitms}" var="treimitm" varStatus="t">
						<tr>
							<td>${treimitm.rmitmname}</td>
							<td>${treimitm.rmitmcost}</td>
						</tr>	  				
  				</c:forEach>
  		</c:forEach>
  	</table>
  
  </body>
</html>
