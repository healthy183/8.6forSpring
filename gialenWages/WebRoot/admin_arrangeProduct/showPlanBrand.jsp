<%@ page language="java" import="java.util.*"
	import="com.gialen.model.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'addProductProject.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
table {
	width: 100%;
}

li {
	float: left;
}

.tableA{
	text-align:center;
	margin-bottom:8px;
	margin-top:8px;
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
</style>


</head>

<body>

	<input style="float: right;" type="button" value="返回 " onclick="javascript:history.go(-1) ">
	<table class="tableA">
		<tr>
			<td>方案名称</td>
			<td>445月</td>
			<td>开始时间</td>
			<td>结束时间</td>
		</tr>
		<tr>
			<td>${requestScope.productProject.productProjectName}</td>
			<td>${requestScope.productProject.operatingMonth.operatingMonthName}</td>
			<td>${requestScope.productProject.operatingMonth.operatingStartDate}</td>
			<td>${requestScope.productProject.operatingMonth.operatingEndDate}</td>

		</tr>
	</table>
		<table id="proTable" class="tableA">
					<tr id="abc">
						<td>序号</td>
						<td>选择<input id="allCheckbox1" type="checkbox"
							checked="checked"></input>
						</td>
						<td>副任务</td>
						<td>品牌小类</td>
						<td>品牌代码</td>
						<td>是否有任务</td>
						<td>完成提点</td>

						<td>是否有完成定额</td>
						<td>定额</td>
						<td>完成定额提点</td>
						<!-- <td>未完成定额提点</td> -->
						<td>未完成提点</td>
					</tr>
					<c:forEach items="${Product_PlanBrandlist}" var="table"
						varStatus="p">
						<tr>
							<td>X${p.index+1}</td>
							<td><input type="checkbox" id="hjk_" value="${p.index+1}"
								class="proIdClass1" checked="checked" name="items1" />
							</td>
							<input type="hidden" id="QplanBrandmainPlan1" value="01"></input>
							<td><input id='QplanBrandsecondPlan1'
								value='${table.planBrandsecondPlan}' size='5' /></td>


							<td><input id='QparentPlanBrandId1'
								value='${table.productBrand.brandName}' size='10' />
							</td>

							<td><input id='QproductBrandId1' class='proIdClass3'
								value='${table.productBrand.id}' size='10' />
							</td>


							<td><select id="QplanBrandtaskType1">
									<c:if test="${table.planBrandtaskType == 1}">
										<option value="1">有任务</option>
									</c:if>
									<c:if test="${table.planBrandtaskType == 0}">
										<option value="0">无任务</option>
									</c:if>

									<option value="1">有任务</option>
									<option value="0">无任务</option>
							</select></td>
							<td><input id='QplanBrandfinishedPoint1'
								value='${table.planBrandfinishedPoint}' size='2' />
							</td>




							<td><select id="QplanBrandfinishedType1">

									<c:if test="${table.planBrandfinishedType == 1}">
										<option value="1">有完成定额</option>
									</c:if>
									<c:if test="${table.planBrandfinishedType == 0}">
										<option value="0">无完成定额</option>
									</c:if>

									<option value="1">有完成定额</option>
									<option value="0">无完成定额</option>
							</select></td>

							<td><input id='QplanBrandfinishedQuota1'
								value='${table.planBrandfinishedQuota}' size='2' />
							</td>


							<td><input id='QplanBrandfinishedPointQuota1'
								value='${table.planBrandfinishedPointQuota}' size='2' />
							</td>

							<td><input id='QplanBrandunFinishedPoint1'
								value='${table.planBrandunFinishedPoint}' size='2' />
							</td>
							<%-- <td><input id='QplanBrandunFinishedPointQuota1'
											value='${table.planBrandunFinishedPointQuota}' size='2' /></td> --%>



							<td><input id='QplanBrandType1' type='hidden'
								value='${table.planBrandType}' /></td>
					</c:forEach>

					<tr id="abc">
						<td>序号</td>
						<td>选择<input id="allCheckbox2" type="checkbox"
							checked="checked"></input>
						</td>

						<td>副任务</td>
						<td>商品名称</td>
						<td>商品编号</td>
						<td>是否有任务</td>
						<td>完成提点</td>

						<td>是否有完成定额</td>
						<td>定额</td>
						<td>完成定额提点</td>
						<td>未完成提点</td>
						<td>商品类型</td>
					</tr>

					<c:forEach items="${ProId_PlanBrandlist}" var="table" varStatus="t">
						<tr>
							<td>Y${t.index+1}</td>
							<td><input type="checkbox" id="mustbeCheck"
								class="proIdClass2" value="${p.index+1}" checked="checked"
								name="items2">
							</td>

							<input type="hidden" id="QplanBrandmainPlan2" value="01"></input>
							<td><input id='QplanBrandsecondPlan2'
								value='${table.planBrandsecondPlan}' size='5' /></td>




							<td><input id='QparentPlanBrandId2'
								value='${table.product.proName}' size='10' />
							</td>


							<td><input id='QProId2' class='proIdClass4'
								value='${table.product.proId}' readonly='true' size='10' /></td>




							<td><select id="QplanBrandtaskType2">

									<c:if test="${table.planBrandtaskType == 1}">
										<option value="1">有任务</option>
									</c:if>
									<c:if test="${table.planBrandtaskType == 0}">
										<option value="0">无任务</option>
									</c:if>

									<option value="1">有任务</option>
									<option value="0">无任务</option>
							</select></td>


							<td><input id='QplanBrandfinishedPoint2'
								value='${table.planBrandfinishedPoint}' size='2' />
							</td>

							<td><select id="QplanBrandfinishedType2">

									<c:if test="${table.planBrandfinishedType == 1}">
										<option value="1">有完成定额</option>
									</c:if>
									<c:if test="${table.planBrandfinishedType == 0}">
										<option value="0">无完成定额</option>
									</c:if>

									<option value="1">有完成定额</option>
									<option value="0">无完成定额</option>
							</select></td>



							<td><input id='QplanBrandfinishedQuota2'
								value='${table.planBrandfinishedQuota}' size='2' />
							</td>


							<td><input id='QplanBrandfinishedPointQuota2'
								value='${table.planBrandfinishedPointQuota}' size='2' />
							</td>

							<td><input id='QplanBrandunFinishedPoint2'
								value='${table.planBrandunFinishedPoint}' size='2' />
							</td>
							<%-- <td><input id='QplanBrandunFinishedPointQuota2'
											value='${table.planBrandunFinishedPointQuota}' size='2' /></td> --%>

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

							<td><input id='QplanBrandType2' type='hidden'
								value='${table.planBrandType}' /></td>
						</tr>
					</c:forEach>
				</table>
	
	
	<!--  
<form action="">
	<select name="aa">
		<option>00111</option>
	</select>
	<input type="file"></input>
	<input type="submit">
</form>
-->
</body>
</html>
