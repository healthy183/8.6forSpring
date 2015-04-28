<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'welTop.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
	ul {
	  margin:0px;
	  list-style-type: none;
	  padding-top:3px;
	}

	li {
	  margin:2px;
	  height:22px;
	}
	
	</style>
  </head>
  
  <body>
	<ul>
		<li>
			<c:url value="adminAdd/addUserAndRole.html" var="addUserAndRole"></c:url>
			<a href="${addUserAndRole}" target="main">新增员工&&角色</a>	
		</li>
		<li>
			<c:url value="adminAdd/addUsr.html" var="addUsr"></c:url>
			<a href="${addUsr}" target="main">新增员工</a>
		</li>
		<li>
			<c:url value="adminAdd/addMyUsr.html" var="addMyUsr"></c:url>
			<a href="${addMyUsr}" target="main">新增下属</a>
		</li>
		<li>
			<c:url value="adminDel/delUsr.html" var="delUsr"></c:url>
			<a href="${delUsr}" target="main">删除员工</a>
		</li>
		<li>
			<c:url value="adminDel/delMyUsr.html" var="delMyUsr"></c:url>
			<a href="${delMyUsr}" target="main">删除下属</a>
		</li>
		<li>
			<c:url value="adminUpdt/updtUsr.html" var="updtUsr"></c:url>
			<a href="${updtUsr}" target="main">修改员工</a>
		</li>
		<li>
			<c:url value="adminUpdt/updtMyUsr.html" var="updtMyUsr"></c:url>
			<a href="${updtMyUsr}" target="main">修改下属</a>
		</li>
		<li>
			<c:url value="adminFind/findUsr.html" var="findUsr"></c:url>
			<a href="${findUsr}" target="main">查询员工</a>
		</li>
		<li>
			<c:url value="adminFind/findMyUsr/${sessionScope.lgnUser.usrid}.html" var="findMyUsr"></c:url>
			<a href="${findMyUsr}" target="main">查询下属</a>
		</li>
		<li>
			<c:url value="adminTreim/findTreim.html" var="findTreim"></c:url>
			<a href="${findTreim}" target="main">查询报销</a>
		</li>
		<li>
			<c:url value="adminTreim/applyTreim.html" var="applyTreim"></c:url>
			<a href="${applyTreim}" target="main">申请报销</a>
		</li>
		<li>
			<c:url value="adminTreim/examineTreim.html" var="examineTreim"></c:url>
			<a href="${examineTreim}" target="main">审批报销</a>
		</li><br>
		===测试权限===
		 <c:forEach items="${sessionScope.sysRolesList}" var="roles" varStatus="r">
			<li>
				<c:url value="${roles.roleUrl}" var="role"></c:url>
				<a href="${role}" target="main">${roles.roleName}</a>
			</li>
		</c:forEach>
		
		
	</ul>

    
    
    
    
    
    
    
    
    
    
    
    
    
  </body>
</html>
