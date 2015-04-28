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
    
    <title>My JSP 'showAreaWagesTable.jsp' starting page</title>
    
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
  <c:url value="admin_showTable/admin_showTable_outAreaWagesTable.action" var="outAreaWagesTable">
	  <c:param name="operatingMonthType" value="${param.operatingMonthType}"></c:param>
	  <c:param name="operatingMonthId" value="${requestScope.operatingMonthId}"></c:param>
	  <c:param name="operatingMonthName" value="${requestScope.thisOperatingMonth.operatingMonthName}"></c:param>
  	  <c:param name="startEndDate" value="${requestScope.startEndDate}"></c:param>	
  </c:url>
   	<table class="tableA">
    	<tr class="myTableTh">
    		<th colspan="10">
    		<b style="font-size:16px;">
    			${requestScope.thisOperatingMonth.operatingMonthName}(${requestScope.startEndDate})区长销售提成汇总表
				</b>
    		<div style="text-align: right;float:right;margin-top:-15px; ">
    			<a href="${outAreaWagesTable}">导出execl表 </a>
    		</div>
    		</th>
    	</tr>
    	<tr>
    		<td>序号</td><td>负责区域</td><td>hr员工编号</td>
			<td>佳讯员工编号</td><td>员工名称</td><td>职位</td>
			<td>计划销售金额</td><td>实际销售金额</td>
			<td>完成比例</td><td>提成</td>
    	</tr>
    	<c:forEach items="${requestScope.areastoreCountList}" var="vo" varStatus="v">
    		<tr class="mybgcolor">
    			<td>${v.count}</td>
    			<td>${vo.orgstdStruct.unitname}</td>
    			<%----%>
    			<td>${vo.orgstdStruct.psnaccount.employeeid}</td>
    			<td>${vo.employee.empId}</td>
    			<td>${vo.orgstdStruct.psnaccount.truename}</td>
    			<td>${vo.orgstdStruct.psnaccount.cpcjobcode.names}</td>
    			<td>${vo.planMoneyCount}元</td>
    		    <td>${vo.saleCount}元</td>
    		    <td>${vo.percentStr}</td>
    			<td>${vo.oneStarManagergrundbonusMoney}元</td>
    		</tr>
    	</c:forEach>
    </table>
    <table class="tableA">
				<tr>
			<th style="text-align:center;">总计</th>
			<th class="heji">计划销售金额:${sessionScope.StoreCountVo.planMoneyCountStr}元</th>
			<th class="heji">实际销售金额:${sessionScope.StoreCountVo.saleCountStr}元</th>
			<th class="heji">完成比例:${sessionScope.StoreCountVo.percentStr}</th>
			<th class="heji">提成:${sessionScope.StoreCountVo.oneStarManagergrundbonusMoneyStr}元</th>
				</tr>
	</table>
  </body>
  
</html>
<%--<tr>
    			<td>${v.count}</td><td>${vo.storeName}</td>
    			<td>${vo.employeeid}</td><td></td>
    			<td>${vo.truename}</td><td>${vo.names}</td>
    			<td>${vo.operatingMonthName}</td><td>${vo.thisPlanMoney}</td>
    			<td>${vo.saleCount}</td><td>${vo.percentStr}</td>
    			<td>${vo.storeAllWages}</td>
    		</tr>
    	--%>
