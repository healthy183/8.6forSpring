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

<title>My JSP 'test.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
 
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
	.tableA {
		text-align: center;
		margin-bottom: 8px;
		margin-top: 8px;
		width: 98%;
		font: Georgia 11px;
		font-size: 12px;
		color: #333333;
		border-collapse: collapse; /*细线表格*/
	}
	.tableA th {
		border: 1px solid #CBD8AC; /*细线条颜色*/
		height: 22px;
	}
	
	.tableA td {
		border: 1px solid #CBD8AC; /*细线条颜色*/
		height: 22px;
	}
</style>
 <script language="javascript" type="text/javascript" 
	src="js/jquery-1.4.2.js"></script>
		<script type="text/javascript" charset="gb2312" src="js/mybgcolor.js"></script>
 
</head>

<body>
	<table class="tableA">
		<tr>
			<td>序号</td>
			<td>门店名称</td>
			<td>员工名称</td>
			<td>445</td>
			<td>销售金额</td>
		</tr>
		<c:forEach items="${rewardbrand_list}" var="rewardbrand" varStatus="s">
			<tr>
				<td width="50">${s.index}</td>
				<td width="150">${rewardbrand[0]}</td>
				<td width="150">${rewardbrand[1]}</td>
				<td width="150">${rewardbrand[2]}</td>
				<td width="80">${rewardbrand[3]}</td>
			</tr>
		</c:forEach>

	</table>
</body>
</html>
