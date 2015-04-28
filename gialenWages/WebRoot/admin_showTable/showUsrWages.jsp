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
    
    <title>My JSP 'showUsrWages.jsp' starting page</title>
    
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
	.heji {
			background-color:"##E7E7E7";
			text-align:left;
		}	
	
</style>
<script language="javascript" type="text/javascript" 
	src="js/jquery-1.4.2.js"></script>
	<script type="text/javascript" charset="gb2312" src="js/mybgcolor.js"></script>
  
  </head>
  
  <body>
  	<c:choose>
  		<c:when test="${sessionScope.voList != null}">
  			<table class="tableA">
  		<tr class="myTableTh">
  		<th colspan="16">
  		<b style="font-size:16px;">
					${requestScope.thisOperatingMonth.operatingMonthName}(${requestScope.startEndDate})员工销售提成汇总表
				</b>
  			<div style="text-align: right;float:right;margin-top:-15px; ">
  				<c:url value="admin_showTable/admin_showTable_outUsrWages.action" var="outUsrWages">
	  				<c:param name="operatingMonthName" value="${requestScope.thisOperatingMonth.operatingMonthName}"></c:param>
	  				<c:param name="startEndDate" value="${requestScope.startEndDate}"></c:param>
	  			</c:url>
  				<a href="${outUsrWages}" style="float:right;text-decoration:none;">导出execl表</a>
  			</div>
  		</th>
  	</tr>	
  		
  		<tr>
			<th>序号</th><th>门店编号</th><th>门店名称</th>
			 <th>hr员工编号</th><th>佳讯员工编号</th>
			<th>员工姓名</th><th>职位</th><th>销售奖</th>
			<th>单品销售金额</th><th>单品提成</th>
			<th>品牌销售金额</th><th>品牌提成</th><th>总销售金额</th>
			<th>总提成</th><th>片区</th><th>大区</th>
    	</tr>
    	<c:forEach items="${sessionScope.voList}" var="vo" varStatus="v">
    		<tr class="mybgcolor">
				<td>${v.count}</td><td>${vo.braId}</td><td>${vo.braName}</td>
				<td>${vo.employeeid}</td><td>${vo.empId}</td>	    		
    			<td>${vo.empName}</td><td>${vo.jobNames}</td><td>${vo.jobWages}元</td>
    			<td>${vo.proSaleAmt}元</td><td>${vo.proSaleWages}元</td><td>${vo.braSaleCount}元</td>
    			<td>${vo.braSaleWages}元</td><td>${vo.saleAmt}元</td><td>${vo.saleWages}元</td>
    			<td>${vo.filmAreaName}</td><td>${vo.bigAreaName}</td>
    		</tr>
    	</c:forEach>
  	</table>
  	

	
	<table class="tableA">
				<tr>
			<th style="text-align:center;">总计</th>
			<th class="heji">销售奖汇总:${sessionScope.countVo.jobWages}元</th>
			<th class="heji">单品销售总金额:${sessionScope.countVo.proSaleAmt}元</th>
			<th class="heji">单品销售总提成:${sessionScope.countVo.proSaleWages}元</th>
			<th class="heji">品牌销售总金额:${sessionScope.countVo.braSaleCount}元</th>
			<th class="heji">品牌销售总提成:${sessionScope.countVo.braSaleWages}元</th>
			<th class="heji">销售总金额:${sessionScope.countVo.saleAmt}元</th>
			<th class="heji">销售总提成:${sessionScope.countVo.saleWages}元</th>
				</tr>
	</table>
	  	
  		</c:when>
  		<c:otherwise>
  	${requestScope.thisOperatingMonth.operatingMonthName}员工销售提成汇总表并未统计,请等候!
  		</c:otherwise>
  	</c:choose>
  	
  	
  
  
  </body>
</html>
  	