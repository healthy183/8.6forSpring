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
    
    <title>My JSP 'showCountProductProjectsByAreaNew.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">

	*{ font-size:12px;
	   font-family:"宋体";
	 }
	
	.tableA{
	text-align:center;
	width:99%;
	font:Georgia 11px;
	font-size:12px;
	color:#333333;
	border-collapse:collapse;/*细线表格*/
}
.tableA td{
	border:1px solid #CBD8AC;/*细线条颜色*/
	height:22px;
}
	 
</style>
<script language="javascript" type="text/javascript" 
	src="js/jquery-1.4.2.js"></script>
	<script type="text/javascript" charset="gb2312" src="js/mybgcolor.js"></script>
  
  </head>
  
  <body>
    <table class="tableA">
    <tr>
    	<td colspan="10">${requestScope.thisOperatingMonth.operatingMonthName}单品销售奖金核算表</td>
    </tr>
    	<tr>
    		<td>序号</td><td>门店编号</td>
    		<td>门店名称</td><td>员工编号</td><td>员工名称</td>
    		<td>销售总数量</td><td>销售总金额</td><td>总奖金</td>
    		<td>片区</td><td>区域</td>
    	</tr>
    	
    	<c:forEach items="${requestScope.saleVoList}" var="vale" varStatus="v">
    	<tr class="mybgcolor">
    		<td>${v.count}</td><td>${vale.braId}</td>
    		<td>${vale.braName}</td><td>${vale.personalId}</td><td>${vale.empName}</td>
    		<td>${vale.saleQty}件</td><td>${vale.saleAmt}元</td><td>${vale.saleWages}元</td>
    		<td>${vale.filmAreaName}</td><td>${vale.bigAreaName}</td>
    	</tr>
    	</c:forEach>
    </table>
  </body>
</html>
