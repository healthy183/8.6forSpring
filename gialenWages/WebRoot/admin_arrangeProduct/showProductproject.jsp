<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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

<title>My JSP 'showProductproject.jsp' starting page</title>

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
<script type="text/javascript">
	var jq = jQuery.noConflict();
	jq(function() {
		jq("#findInvalidProject")
				.click(
						function() {
							location.href = "admin_arrangeBrand_showInvalidProProject.action?"
									+ "operatingMonthType="
									+ "${param.operatingMonthType}&projectType="
									+ "${param.projectType}&"
									+ "operatingMonthId="
									+ jq("#operatingMonthId").val();
						});
	});
</script>
<script type="text/javascript">
	var jq = jQuery.noConflict();
	jq(function() {
		jq("#addProductProjectButton")
				.click(
						function() {
							location.href = "admin_arrangeBrand_addProduct_Project.action?operatingMonthType="
									+ "${param.operatingMonthType}&projectType="
									+ "${param.projectType}&"
									+ "operatingMonthId="
									+ jq("#operatingMonthId").val();

						});
	});
</script>
<script type="text/javascript">
	var jq = jQuery.noConflict();
	jq(function() {
		jq("#show_Plan_Money")
				.click(
						function() {
							location.href = "admin_arrangeBrand_show_Plan_Money.action?operatingMonthType="
									+ "${param.operatingMonthType}&"
									+ "operatingMonthId="
									+ jq("#operatingMonthId").val(); 

						});
	});
</script>






<script type="text/javascript">
	var jq = jQuery.noConflict();
	jq(function() {
		jq("#addPlanMoneyButton")
				.click(
						function() {
							location.href = "admin_arrangeBrand_addPlanMoney.action?operatingMonthType="
									+ "${param.operatingMonthType}&projectType="
									+ "${param.projectType}&planMoneyType=0&productProjectId=1"
									+ "&operatingMonthId="
									+ jq("#operatingMonthId").val();

						});
	});
</script>
<script type="text/javascript">
	var jq = jQuery.noConflict();
	jq(function() {
		jq("#operatingMonthId").change(
				function() {
					location.href = "admin_arrangeBrand_addProduct.action?"
							+ "operatingMonthType="
							+ "${param.operatingMonthType}&projectType="
							+ "${param.projectType}&" + "operatingMonthId="
							+ jq("#operatingMonthId").val();
				});
	});
</script>
	<script type="text/javascript" charset="gb2312" src="js/mybgcolor.js"></script>
</head>
<!--声明分页的初变量-->
<!-- 当前页 -->
<c:set var="page" value="0" />
<!-- 上一页-->
<c:set var="pagepre" value="0" />
<!-- 下一页-->
<c:set var="pageNext" value="0" />

<c:if test="${page==0}">
	<c:set var="pageNext" value="1" />
</c:if>
<c:if test="${param.pagenum!=null}">
	<c:set var="page" value="${pagenum}" />
	<!-- 上一页-->
	<c:set var="pagepre" value="${pagenum-1}" />
	<!-- 下一页-->
	<c:set var="pageNext" value="${pagenum+1}" />
</c:if>

<body>
	<input type="hidden" name="operatingMonthType"
		value="${param.operatingMonthType}"></input>
	<input type="hidden" name="projectType" value="${param.projectType}"></input>

	
	

	<table class="tableA">
		<tr>
			<th colspan="6">
			<select name="operatingMonthId" id="operatingMonthId">
					<option value="${requestScope.thisOperatingMonth.operatingMonthId}"
						selected="selected">${requestScope.thisOperatingMonth.operatingMonthName}</option>
					<c:forEach items="${requestScope.thisYearOperatingMonthList}"
						var="Month">
						<option value="${Month.operatingMonthId}">${Month.operatingMonthName}</option>
					</c:forEach>
			</select>品牌提成方案设置 
			<input type="button" id="addProductProjectButton" value="新增品牌方案"></input>
				 <input type="button" id="addPlanMoneyButton" value="公司预计定额"></input> 
				<input type="button" id="show_Plan_Money"value="查看定额"></input> 
				<input type="button" id="findInvalidProject"value="查看作废项目"></input>
			</th>
		</tr>
		<tr>
			<td>序号</td>
			<td>方案名称</td>
			<td>445月</td>
			<td>开始时间</td>
			<td>结束时间</td>

			<td>操作</td>
		</tr>
		<c:forEach items="${requestScope.projectList}" var="project"
			varStatus="s">
			<tr class="mybgcolor">
				<td>${s.count+pagenum*pageSize}</td>
				<td>${project.productProjectName}</td>
				<td>${project.operatingMonth.operatingMonthName}</td>
				<td>${project.operatingMonth.operatingStartDate}</td>
				<td>${project.operatingMonth.operatingEndDate}</td>



				<td><c:url
						value="admin_arrangeBrand/admin_arrangeBrand_showPlanBrand.action"
						var="queryProject">
						<c:param name="productProjectId"
							value="${project.productProjectId}"></c:param>
						<c:param name="productProjectName"
							value="${project.productProjectName}"></c:param>
						<c:param name="projectType" value="${param.projectType}"></c:param>
						<c:param name="operatingMonthType"
							value="${param.operatingMonthType}"></c:param>
					</c:url> <a href="${queryProject}">查看</a> <c:url
						value="admin_arrangeBrand/admin_arrangeBrand_updtProProjects.action"
						var="updtProProjects">
						<c:param name="operatingMonthId"
							value="${project.operatingMonth.operatingMonthId}"></c:param>
						<c:param name="productProjectId"
							value="${project.productProjectId}"></c:param>
						<c:param name="productProjectName"
							value="${project.productProjectName}"></c:param>
						<c:param name="projectType" value="${param.projectType}"></c:param>
						<c:param name="operatingMonthType"
							value="${param.operatingMonthType}"></c:param>
					</c:url> <a href="${updtProProjects}">修改</a> 
					
					
					
					
					
 <c:url value="admin_arrangeBrand/admin_arrangeBrand_addPlanMoney.action"
						var="addPlanMoney">
						<c:param name="productProjectId"
							value="${project.productProjectId}"></c:param>
						<c:param name="productProjectName"
							value="${project.productProjectName}"></c:param>
						<c:param name="planMoneyType" value="1"></c:param>
						<c:param name="operatingMonthId"
							value="${project.operatingMonth.operatingMonthId}"></c:param>
					</c:url> <a href="${addPlanMoney}">绑定定额</a>
			
			
			
			
			
		<c:url value="admin_arrangeBrand/admin_arrangeBrand_invalidProProjects.action" var="invalidProProjects">
						<c:param name="productProjectId" value="${project.productProjectId}"></c:param>
						<c:param name="productProjectName" value="${project.productProjectName}"></c:param>
						<c:param name="projectType" value="${param.projectType}"></c:param>
						<c:param name="operatingMonthType" value="${param.operatingMonthType}"></c:param>
					</c:url>
					<a  onclick="return confirm('您确定要作废该方案?');" href="${invalidProProjects}">作废</a>	
			
			
			
			
				</td>
			</tr>
		</c:forEach>

		<tr>
			<td colspan="8"><c:if test="${requestScope.totalPages >0}">
					<c:url
						value="admin_arrangeBrand/admin_arrangeBrand_addProduct.action"
						var="firstPage">
						<c:param name="operatingMonthType"
							value="${param.operatingMonthType}"></c:param>
						<c:param name="projectType" value="${param.projectType}"></c:param>
						<c:param name="operatingMonthId"
							value="${requestScope.thisOperatingMonth.operatingMonthId}"></c:param>
						<c:param name="pagenum" value="0"></c:param>
					</c:url>
					<a href="${firstPage}">首页</a>
				</c:if> <c:if test="${requestScope.totalPages ==0}">
						无对应记录!
					</c:if> <c:if test="${pagenum>0&&page>0}">
					<c:url
						value="admin_arrangeBrand/admin_arrangeBrand_addProduct.action"
						var="prevPage">
						<c:param name="operatingMonthType"
							value="${param.operatingMonthType}"></c:param>
						<c:param name="projectType" value="${param.projectType}"></c:param>
						<c:param name="operatingMonthId"
							value="${requestScope.thisOperatingMonth.operatingMonthId}"></c:param>
						<c:param name="pagenum" value="${pagepre}"></c:param>
					</c:url>
					<a href="${prevPage}">上一页</a>
				</c:if> <c:if test="${pagenum<(requestScope.totalPages-1)}">
					<c:url
						value="admin_arrangeBrand/admin_arrangeBrand_addProduct.action"
						var="nextPage">
						<c:param name="operatingMonthType"
							value="${param.operatingMonthType}"></c:param>
						<c:param name="projectType" value="${param.projectType}"></c:param>
						<c:param name="operatingMonthId"
							value="${requestScope.thisOperatingMonth.operatingMonthId}"></c:param>
						<c:param name="pagenum" value="${pageNext}"></c:param>
					</c:url>
					<a href="${nextPage}">下一页</a>
				</c:if> <c:if test="${requestScope.totalPages >0}">
					<c:url
						value="admin_arrangeBrand/admin_arrangeBrand_addProduct.action"
						var="lastPage">
						<c:param name="operatingMonthType"
							value="${param.operatingMonthType}"></c:param>
						<c:param name="projectType" value="${param.projectType}"></c:param>
						<c:param name="operatingMonthId"
							value="${requestScope.thisOperatingMonth.operatingMonthId}"></c:param>
						<c:param name="pagenum" value="${requestScope.totalPages-1}"></c:param>
					</c:url>
					<a href="${lastPage}">末页</a>
						(${requestScope.pagenum+1}/${requestScope.totalPages})
						</c:if>
			</td>
		</tr>
	</table>



</body>
</html>
