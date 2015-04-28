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
    
    <title>My JSP 'showProProjectRelationTable.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
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
  			
			<th colspan="9" align="center">
				项目信息
					<input type="button" value= "返回 "  onclick= "javascript:history.go(-1) ">
			</th>
   		</tr><!-- <span style="margin:0px;float:right;"></span>  -->
  		<tr>
	  		<td>方案名称</td>
			<td>445月</td><td>开始时间</td><td>结束时间</td>
			<td>提点</td><td>类型</td>
  		</tr>
  		<tr>
  				<td>${requestScope.productProject.productProjectName}</td>
				<td>${requestScope.productProject.operatingMonth.operatingMonthName}</td>
				<td>${requestScope.productProject.operatingMonth.operatingStartDate}</td>
				<td>${requestScope.productProject.operatingMonth.operatingEndDate}</td>
				<c:if test="${requestScope.productProject.productProjectType ==0}">
				<!-- 百分比提 -->
				<td>${requestScope.productProject.productProjectVal}%</td>
				</c:if>
				<c:if test="${requestScope.productProject.productProjectType ==1}">
					<td>${requestScope.productProject.productProjectVal}元/件</td>
				</c:if>
				<c:if test="${requestScope.productProject.isAddBrandWages ==0}">
					<td>不算入品牌提</td>
				</c:if>
				<c:if test="${requestScope.productProject.isAddBrandWages ==1}">
					<td>算入品牌提</td>
				</c:if>	
  		</tr>
  	</table>
  	
  	
   			<table class="tableA">
   				<tr>
   					<th colspan="9">
   						项目单品明细:
   					</th>
   				</tr>
   				<tr>
   					<td>序号</td><td>商品编号</td><td>商品条码</td><td>商品名称</td><td>规格</td>
					<td>品牌小类</td><td>品类小类</td><td>商品类型</td><td>商品状态</td>
   				</tr>
   				<c:forEach items="${ProProjectRelationTableList}" var="table" varStatus="t">
   				<tr  class="mybgcolor">
   					<td>${t.index+1}</td><td>${table.product.proId}</td><td>${table.product.barcode}</td>
   					<td>${table.product.proName}</td><td>${table.product.spec}</td>	
   					<td>${table.product.productClass.className}</td>
   					<td>${table.product.productBrand.brandName}</td>
   					
   					
   				<c:if test="${table.product.proFlag == 0}">
   						<td>正常商品</td>
   				</c:if>
   				<c:if test="${table.product.proFlag == 1}">
   						<td>赠品</td>
   				</c:if>
   				<c:if test="${table.product.proFlag == 2}">
   						<td>物料耗材</td>
   				</c:if>
   				<c:if test="${table.product.proFlag == 3}">
   						<td>流通商品试用装</td>
   				</c:if>
   				<c:if test="${table.product.proFlag == 4}">
   						<td>自有试用装</td>
   				</c:if>
   				<c:if test="${table.product.proFlag == 5}">
   						<td>待摊物料</td>
   				</c:if>
   				<c:if test="${table.product.proFlag == 6}">
   						<td>普通物料</td>
   				</c:if>
   					
		   			
		   		<c:if test="${table.product.status == 0}">
   						<td>未使用</td>
   				</c:if>
   				<c:if test="${table.product.status == 1}">
   						<td>新品</td>
   				</c:if>	
   				<c:if test="${table.product.status == 2}">
   						<td>正常</td>
   				</c:if>	
   				<c:if test="${table.product.status == 3}">
   						<td>季节性禁止采购</td>
   				</c:if>	
   				<c:if test="${table.product.status == 4}">
   						<td>停止采购</td>
   				</c:if>	
   				<c:if test="${table.product.status == 5}">
   						<td>停止要货</td>
   				</c:if>	
   				<c:if test="${table.product.status == 8}">
   						<td>停止销售</td>
   				</c:if>	
   				<c:if test="${table.product.status == 9}">
   						<td>完全停用</td>
   				</c:if>	
   				</tr>
   				</c:forEach>
   			</table>
  </body>
</html>
