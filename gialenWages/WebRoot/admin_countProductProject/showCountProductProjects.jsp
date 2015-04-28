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
    
    <title>My JSP 'showCountProductProjects.jsp' starting page</title>
    
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
		border-collapse:collapse;
	}
	
	.myTable tr{
		background-color:#FFFFFF;/**/
		font-size:14px;
		color: black;
	}
	.myTable td{
		line-height: 14px;height: 14px;
		empty-cells : show;
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
<style type="text/css">
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

.tableA{
	text-align:center;
	margin-bottom:8px;
	margin-top:8px;
	width:98%;
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
  	 <c:forEach items="${request.allBigArea}" var="area" varStatus="a">
  			<td>
	  			<c:url value="admin_countProductProject/admin_countProductProject_showCountProductProjectsByAreaSuccess.action" var="showCountProductProjectsByAreaSuccess">
	  				<c:param name="operatingMonthType" value="${param.operatingMonthType}"></c:param>
	  				<c:param name="operatingMonthId" value="${requestScope.operatingMonthId}"></c:param>
	  				<c:param name="unitcode" value="${area.unitcode}"/>
	  			</c:url>
  				<a href="${showCountProductProjectsByAreaSuccess}">${area.unitname}</a>
  			</td>
  			<c:if test="${a.count mod 6 == 0}">
  					</tr><tr>
  			</c:if>
  </c:forEach>
  </table>
  
  <!-- 
  <c:if test="${(a.index+1) eq (a.count)}">
  			good
  			</c:if>
  	<c:choose>
  				<c:when test="${a.count mod 6 == 0}">
  						</tr><tr>
  				</c:when>	
  				<c:when test="${(a.index+1) eq (a.count)}">
  					
  					<td></td><td></td>
  						</tr>
  			    </c:when>	
  	</c:choose>
  
   -->
  
  
  
  <c:if test="${requestScope.parentLinkList != null}">
   <table class="myTable" cellpadding="3" cellspacing="1">
   <tr><th colspan="9">已经核算员工</th></tr>
	   	<tr>
	   		<th>序号</th><th>门店名称</th><th>员工名称</th><th>营运月</th><th>销售数量</th>
	   		<th>销售金额</th><th>奖金</th><th>片区</th><th>区域</th>
	   	</tr>
	   	
   <c:forEach items="${requestScope.parentLinkList}" var="parent" varStatus="p">
  		<tr class="mybgcolor">
  			<td>${p.count}</td>
  			<td>${parent.orgstdStruct.unitname}</td>
  			<td>${parent.psnaccount.truename}</td>
  			<td>${parent.operatingMonth.operatingMonthName}</td>
  			<td>${parent.saleNum}</td>
  			<td>${parent.saleCount}</td>
  			<td>${parent.saleWages}</td>
  			<td>${parent.orgstdStruct.orgstdStruct.unitname}</td>
  			<td>${parent.orgstdStruct.orgstdStruct.orgstdStruct.unitname}</td>
  		</tr> 		
   </c:forEach>
    </table>
  </c:if>
  
  

   
  </body>
</html>