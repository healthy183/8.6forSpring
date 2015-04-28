<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%@ taglib uri="http://jsptags.com/tags/navigation/pager" prefix="pg" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'showInvalidProject.jsp' starting page</title>
    
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
	.myTableTh{
		width:100%;
		background: url(image/wbg.gif);
	}
	div{
		display: inline; 
		margin: 0px;
	}
	</style>
  <script language="javascript" type="text/javascript" 
	src="js/jquery-1.4.2.js"></script>
		<script type="text/javascript">
		 var jq = jQuery.noConflict();
		 jq(function(){
			 jq("#operatingMonthId").change(function(){
				 location.href ="admin_arrangeBrand_showInvalidProject.action?"+
					"operatingMonthType="+"${param.operatingMonthType}&projectType="+"${param.projectType}&"+
						"operatingMonthId="+jq("#operatingMonthId").val();
			 });
			 
			 jq("#addBrand").click(function(){
				 location.href ="admin_arrangeBrand_addBrand.action?"+
					"operatingMonthType="+"${param.operatingMonthType}&projectType="+"${param.projectType}&"+
						"operatingMonthId="+jq("#operatingMonthId").val();
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
  <input type="hidden" name="operatingMonthType" value="${param.operatingMonthType}"></input>
  <input type="hidden" name="projectType" value="${param.projectType}"></input>
  
  
   

   <table class="tableA">
   <tr class="myTableTh">
   	<th colspan="9">
   	<select name="operatingMonthId" id="operatingMonthId">
   		<option value="${requestScope.thisOperatingMonth.operatingMonthId}" selected="selected">${requestScope.thisOperatingMonth.operatingMonthName}</option>
  		<c:forEach items="${requestScope.thisYearOperatingMonthList}" var="Month">
  				<option value="${Month.operatingMonthId}">${Month.operatingMonthName}</option>
  		</c:forEach>
   </select><b style="font-size:16px;margin-top:30px;">的作废项目列表!</b>
   
   	<div style="text-align: right;float:right;margin-top:-18px;">
   		 <input type="button" id="addBrand"  value="返回"></input>
   	</div>
   	</th>
   </tr>
   		<tr>
   			<th>序号</th><th>方案名称</th>
			<th>445月</th><th>开始时间</th><th>结束时间</th>
			<th>提点</th><th>类型</th><th>状态</th>
			<th>操作</th>
   		</tr>
   		<c:forEach items="${requestScope.projectList}" var="project" varStatus="s">
   	<tr class="mybgcolor">
   				<td>${s.count+pagenum*pageSize}</td>
				<td>${project.productProjectName}</td>
				<td>${project.operatingMonth.operatingMonthName}</td>
				<td>${project.operatingMonth.operatingStartDate}</td>
				<td>${project.operatingMonth.operatingEndDate}</td>
				
				
				<c:if test="${project.productProjectType == 0}">
				<!-- 百分比提 -->
				<td>${project.productProjectVal}%</td>
				</c:if>
				<c:if test="${project.productProjectType == 1}">
					<td>${project.productProjectVal}元/件</td>
				</c:if>
				<c:if test="${project.isAddBrandWages == 0}">
					<td>不算入品牌提</td>
				</c:if>
				<c:if test="${project.isAddBrandWages ==1}">
					<td>算入品牌提</td>
				</c:if>	
				<c:if test="${project.projectStatus == 0}">
					<td>可用</td>				
				</c:if>
				<c:if test="${project.projectStatus == 1}">
					<td>作废</td>				
				</c:if>
				<td>
					
					<c:url value="admin_arrangeBrand/admin_arrangeBrand_restoreProject.action" var="restoreProject">
						<c:param name="productProjectId" value="${project.productProjectId}"></c:param>
						<c:param name="productProjectName" value="${project.productProjectName}"></c:param>
						<c:param name="projectType" value="${param.projectType}"></c:param>
						<c:param name="operatingMonthType" value="${param.operatingMonthType}"></c:param>
					</c:url>
					<a onclick="return confirm('您确定要恢复该方案?');" href="${restoreProject}">恢复</a>
				</td>		
			</tr>
   		</c:forEach>
   				<tr>
				<td colspan="9">
				
					<c:if test="${requestScope.totalPages >0}">
						<c:url	value="admin_arrangeBrand/admin_arrangeBrand_showInvalidProject.action" var="firstPage">
						<c:param name="operatingMonthType" value="${param.operatingMonthType}"></c:param>
						<c:param name="projectType" value="${param.projectType}"></c:param>
						<c:param name="operatingMonthId" value="${requestScope.thisOperatingMonth.operatingMonthId}"></c:param>
						<c:param name="pagenum" value="0"></c:param>
					</c:url>
					<a href="${firstPage}">首页</a>
					</c:if>
				
					<c:if test="${requestScope.totalPages ==0}">
						无对应记录!
					</c:if>
					
					<c:if test="${pagenum>0&&page>0}">
					<c:url	value="admin_arrangeBrand/admin_arrangeBrand_showInvalidProject.action" var="prevPage">
						<c:param name="operatingMonthType" value="${param.operatingMonthType}"></c:param>
						<c:param name="projectType" value="${param.projectType}"></c:param>
						<c:param name="operatingMonthId" value="${requestScope.thisOperatingMonth.operatingMonthId}"></c:param>
						<c:param name="pagenum" value="${pagepre}"></c:param>
					</c:url>
							<a href="${prevPage}">上一页</a>
					</c:if>	
					
					<c:if test="${pagenum<(requestScope.totalPages-1)}">
					<c:url	value="admin_arrangeBrand/admin_arrangeBrand_showInvalidProject.action" var="nextPage">
						<c:param name="operatingMonthType" value="${param.operatingMonthType}"></c:param>
						<c:param name="projectType" value="${param.projectType}"></c:param>
						<c:param name="operatingMonthId" value="${requestScope.thisOperatingMonth.operatingMonthId}"></c:param>
						<c:param name="pagenum" value="${pageNext}"></c:param>
					</c:url>
							<a href="${nextPage}">下一页</a>
					</c:if>
						
					
					
					<c:if test="${requestScope.totalPages >0}">
					<c:url	value="admin_arrangeBrand/admin_arrangeBrand_showInvalidProject.action" var="lastPage">
						<c:param name="operatingMonthType" value="${param.operatingMonthType}"></c:param>
						<c:param name="projectType" value="${param.projectType}"></c:param>
						<c:param name="operatingMonthId" value="${requestScope.thisOperatingMonth.operatingMonthId}"></c:param>
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
