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
    
    <title>My JSP 'addMyUsr.jsp' starting page</title>
    
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
	
	<form action="adminAdd/addUserAndRoleSuccess.html">
	
	<input type="submit" value="提交"></input><br>
  		用户名:<input name="usrname" value="XX哥"></input>
  		密码:<input name="usrpwd" value="123"></input><br>
	
		员工类型:		   
	<input name="usrtype" type="radio" value="0"></input>管理层
  	<input name="usrtype" type="radio" value="1" checked="checked"></input>普通员工
	
	<input type="hidden" name="leadId" value="${sessionScope.lgnUser.usrid}"></input>
	
	 选择角色:<br>
  	 <table border="1">
  	 <tr>
	  	 <c:forEach items="${requestScope.rolesList}" var="role" varStatus="r">
	  	 		<td>
	  	 			<input type="checkbox" name="roleIdCheck" value="${role.roleId}"></input>${role.roleName}
	  	 		</td>
	  	 </c:forEach>
	  	 <!-- 如果修改session中的对象的时候记得添加隐藏对象，激活springMVC绑定功能 
	  	 	<input type="hidden" name="hidden_" value="abc"></input>
	  	 -->
  	  	</tr>
  	  	</table>
	
	</form>	

  </body>
</html>
