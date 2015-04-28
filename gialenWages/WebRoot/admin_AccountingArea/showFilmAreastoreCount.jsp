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
    
    <title>My JSP 'showFilmAreastoreCount.jsp' starting page</title>
    
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
	
	#redId{
			background:transparent;
			border:0;
			color:red;
			width:100%;
		}
</style>
<style type="text/css">
	
	body{
		margin:0px;
	}

	*{ font-size:12px;
	   font-family:"宋体";
	 }
	
	#redId{
			background:transparent;
			border:0;
			color:red;
			width:100%;
			margin:0px;
		}
		
	form{
		margin:0px;
	}
	
	table{
		margin:0px;
		margin-left:10px;
	}
	
	span{
		margin:0px;
	}
	
	.myTable {
		width:98%;
		text-align:center;
		background-color: #CBD8AC;
		margin-bottom:8px;
		margin-top:8px;
	}
	
	.myTable tr{
		background-color:#FFFFFF;/**/
		font-size:14px;
		color: black;
	}
	.myTable td{
		line-height: 14px;height: 14px;
	}
	
	
	.myTableTr{
		background-color:#FFFFFF;/**/
		font-size:14px;
		color: black;
	}
	.myTableTd{
		line-height: 14px;height: 14px;
	}
	
	.myTableSpan{
		font-size: 12px;color: #666600;
	}	
	
	.myTableTh{
		background: url(image/wbg.gif);
		/*background-color: #EEF4EA;*/
	}
		
</style>
   <script language="javascript" type="text/javascript" 
	src="js/jquery-1.4.2.js"></script>
 <script type="text/javascript" charset="gb2312" src="js/mybgcolor.js">
 </script>
  </head>
  
  <body>
  	
    <table class="myTable" cellpadding="3" cellspacing="1">
    	<tr>
    		<td colspan="7">
    			<b style="font-size: 16px">片区长提成金额表</b>
    			<font style="text-align: right;">
    				<input type="button" value="返回" onclick="javascript:history.go(-1)"></input>
    			</font>
    		</td>
    	</tr>
    	<tr>
    		<th>序号</th>
    		<th>片区名称</th><th>445营运月</th><th>计划销售金额</th>
    		<th>实际销售金额</th><th>完成比例</th>
    		<th>片区长奖金</th>
    	</tr>
    	<c:forEach items="${requestScope.filmAreastoreCountList}"  var="storeCount" varStatus="s">
    	<tr class="mybgcolor">
    		<td>${s.count}</td>
    		<td>${storeCount.orgstdStruct.unitname}</td>
    		<td>${storeCount.operatingMonth.operatingMonthName}</td>
    		<td>${storeCount.planMoneyCount}元</td>
    		<td>${storeCount.saleCount}元</td>
    		<td>${storeCount.percentStr}</td>
    		<td>${storeCount.oneStarManagergrundbonusMoney}元</td>
    	</tr>
    	</c:forEach>
    	
    </table>
  </body>
</html>