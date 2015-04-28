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
    
    <title>My JSP 'showStoreWages.jsp' starting page</title>
    
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

.tableA th{
	border:1px solid #CBD8AC;/*细线条颜色*/
	height:22px;
}

.tableA td{
	border:1px solid #CBD8AC;/*细线条颜色*/
	height:22px;
}

.myTableTh{
		width:100%;
		background: url(image/wbg.gif);
	}
div{
		display: inline; 
	}
		a:link { 
		font-size: 9pt; 
		color: blue; 
		text-decoration: none; 
		font-family: ""宋体""; 
	}
	a:visited{
	font-size: 9pt;
	color: blue;
	
	font-family: ""宋体"";
}
	a:hover {
		color: red; 
		font-family: ""宋体"";
		text-decoration: underline;
		}
</style>
<script language="javascript" type="text/javascript" 
	src="js/jquery-1.4.2.js"></script>
	<script type="text/javascript" charset="gb2312" src="js/mybgcolor.js"></script>
  
  </head>
  
  <body>
  
  <c:choose>
  	<c:when test="${sessionScope.storeCountList != null}">
  			<table class="tableA">
  		<tr class="myTableTh">
  		<th colspan="15">
  		<b style="font-size:16px;">
					${requestScope.thisOperatingMonth.operatingMonthName}(${requestScope.startEndDate})员工销售总提表
				</b>
  			<div style="text-align: right;float:right;margin-top:-15px; ">
  				<c:url value="admin_showTable/admin_showTable_outStoreWages.action" var="outStoreWages">
	  			<c:param name="operatingMonthName" value="${requestScope.thisOperatingMonth.operatingMonthName}"></c:param>
	  			<c:param name="startEndDate" value="${requestScope.startEndDate}"></c:param>
	  		</c:url>	
	  			<a href="${outStoreWages}">导出execl表</a>	
  			</div>
  		</th>
  	</tr>	
  		<tr>
	  		<th>序列</th><th>门店编号</th><th>门店姓名</th>
	  		<th>实际销售总金额</th><th>计划销售总金额</th><th>完成比例</th>
	  		<th>一星店长提成</th><th>正店长</th><th>副店长</th>
	  		<th>个提合计</th><th>员工提成总额</th>
	  		<th>公共账号总提</th><th>门店总提</th>
  		</tr>
  		<c:forEach items="${sessionScope.storeCountList}" var="vo" varStatus="v">
  			<tr class="mybgcolor">
				<td>${v.count}</td>
				<td>${vo.braId}</td>
				<td>${vo.braName}</td>
				<td>${vo.saleCount}元</td>
				<td>${vo.thisPlanMoney}元</td>
				<td>${vo.percentStr}</td>
				<td>${vo.oneStarManagergrundbonusMoney}元</td>
				<td>${vo.positiveManagergrundbonusMoney}元</td>
				<td>${vo.deputyManagergrundbonusMoney}元</td>
				<td>${vo.storeAllWages}元</td>
				<td>${vo.usrSum}元</td>
				<td>${vo.pubSum}元</td>
				<td>${vo.allSum}元</td>  			
  			</tr>	
  		</c:forEach>
</table>
<table class="tableA">
				<tr>
			<th style="text-align:center;">总计</th>
			<th class="heji">实际销售总金额:${sessionScope.StoreWagesCount.saleCount}元</th>
			<th class="heji">计划销售总金额:${sessionScope.StoreWagesCount.thisPlanMoney}元</th>
			<th class="heji">平均完成比例:${sessionScope.StoreWagesCount.percentStr}元</th>
			<th class="heji">个提合计:${sessionScope.StoreWagesCount.storeAllWages}元</th>
			<th class="heji">员工提成总额:${sessionScope.StoreWagesCount.usrSum}元</th>
			<th class="heji">公共账号总提:${sessionScope.StoreWagesCount.pubSum}元</th>
			<th class="heji">门店总提:${sessionScope.StoreWagesCount.allSum}元</th>
				</tr>
	</table>
  	</c:when>
  	<c:otherwise>
  			${requestScope.thisOperatingMonth.operatingMonthName}员工销售总提表没有汇总,请等候！
  	</c:otherwise>
  </c:choose>
  
  
  
	

  </body>
</html>
