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
    
    <title>My JSP 'showStoreCountList.jsp' starting page</title>
    
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
<script type="text/javascript" charset="gb2312" src="js/mybgcolor.js"></script>
  </head>
  
  <body>
  
  <br>
  <!--   -->
  &nbsp;&nbsp;门店店长总提金额表:  
  <c:url value="admin_AccountingArea/admin_AccountingArea_showLastMonthFilmAreaWages.action" var="showLastMonthFilmAreaWages">
  	<c:param name="operatingMonthType" value="${param.operatingMonthType}"></c:param>
  	<c:param name="operatingMonthId" value="${requestScope.operatingMonthId}"></c:param>
 	<c:param name="operatingMonthPathMoneyType" value="0"></c:param>
  </c:url>
 <a href="${showLastMonthFilmAreaWages}">查看片区长总提金额表</a>  
 
 <c:url value="admin_AccountingArea/admin_AccountingArea_showLastMonthBigAreaWages.action" var="showLastMonthBigAreaWages">
  	<c:param name="operatingMonthType" value="${param.operatingMonthType}"></c:param>
  	<c:param name="operatingMonthId" value="${requestScope.operatingMonthId}"></c:param>
  	<c:param name="operatingMonthPathMoneyType" value="0"></c:param>
  </c:url>
 <a href="${showLastMonthBigAreaWages}">查看大区长总提金额表</a>
 
  <table class="myTable" cellpadding="3" cellspacing="1">
  	<tr>
  		<th colspan="11">${requestScope.thisOperatingMonth.operatingMonthName}门店销售总提成表
  			<a href="">导出execl表</a>		
  		</th>
  	</tr>
  	<tr>
  		<th>序列</th><th>门店编号</th><th>门店名称</th><th>营运月</th>
  		<th>实际销售总金额</th><th>计划销售总金额</th>
  		<th>完成比例</th>
  		<th>一星店长提成</th><th>正店长</th><th>副店长</th> <!-- -->
  		<th>员工总提</th>
  	</tr>
	<c:forEach items="${requestScope.storeCountList}" var="count" varStatus="c">
		<tr class="mybgcolor">
			<td>${c.count}</td>
			
			<!-- 
			<c:if test="${count.corresponding.orgstdStruct.unitname == null}">
				<td>没有跟hr系统做对应,请尽快对应 </td>
			</c:if>
			<c:if test="${count.corresponding.orgstdStruct.unitname != null}">
				<td>${count.corresponding.orgstdStruct.unitname}</td>
			</c:if>--> 
			<td>
				${count.branch.braId}			
			</td>
			<td>${count.branch.braName}</td>
			<td>${count.operatingMonth.operatingMonthName}</td>
			<td>${count.saleCount}元</td>
			
			<td> 
			 <c:if test="${count.planMoney.planMoneyCount == null}">
			 			0.0元
			 </c:if>
			 <c:if test="${count.planMoney.planMoneyCount != null}">
			 			${count.planMoney.planMoneyCount}元
			 </c:if>
			</td>
			<td>${count.percentStr}</td>
		
			<td>${count.oneStarManagergrundbonusMoney}元</td>
			<td>${count.positiveManagergrundbonusMoney}元</td>
			<td>${count.deputyManagergrundbonusMoney}元</td>	<!--  -->
			<td>${count.storeAllWages}元</td>
		</tr>
	
	</c:forEach>
</table>
  </body>
</html>
