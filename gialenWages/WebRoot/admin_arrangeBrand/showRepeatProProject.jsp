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
    
    <title>My JSP 'showRepeatProProject.jsp' starting page</title>
    
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
	<script language="javascript" type="text/javascript" src="js/jquery-1.4.2.js"></script>
	<script type="text/javascript">
	var jq = jQuery.noConflict();
		jq(function(){
			jq("#choseCheck").click(function(){
				if(jq(this).attr("checked") == true){
					jq(".proId").attr("checked",true);
				}else{
					jq(".proId").attr("checked",false);
				}
			});
			
		});
	</script>
	<script type="text/javascript">
	var jq = jQuery.noConflict();
		jq(function(){
			jq("#button").click(function(){
				var checkedPro = jq(".proId:checked");
				if(checkedPro.length<1){
					alert("请选择要删除的单品记录!");
					return false;
				}else{
					var isSubmit = confirm("你确定要提交删除操作吗?");
					if(isSubmit == true){
						jq("#delRepeatProProject").submit();
					}else{
						return false;
					}
				}
			});			
		});
 </script>	
<script type="text/javascript" charset="gb2312" src="js/mybgcolor.js"></script>	
	 <script type="text/javascript" charset="gb2312" src="js/isReturn.js"></script>
  </head>
  <body>
 <form action="admin_arrangeBrand/admin_arrangeBrand_delRepeatProProject.action" id="delRepeatProProject">
  
  
  <input type="hidden" name="projectType" value="${param.projectType}"></input>
  <input type="hidden" name="operatingMonthType" value="${param.operatingMonthType}"></input>
  <input type="hidden" name="operatingMonthId" value="${param.operatingMonthId}"></input>
  	<table class="tableA">
  		<tr class="myTableTh"><th colspan="6">
  		<b style="font-size:16px;margin-top:30px;">请选择你要取消的记录!</b>
  		<div style="text-align: right;float:right;margin-top:-18px;">
  			<input type="button" id="button" value="提交"></input>
    			<input  id="returnButton" type="button" value= "返回" >
    	</div>		
    			</th>
    			
    		</tr>
  		<tr>
  			<th>单品编号</th><th>单品名称</th><th>单品项目</th>
  			<th>项目提点</th><th>类型</th>
  			<th>选择<input type="checkbox" id="choseCheck"></input></th>
  		</tr>
  		<c:forEach items="${requestScope.relationProject}" var="pro" varStatus="p">
  			<tr class="mybgcolor">
  				<td>${pro.product.proId}</td><td>${pro.product.proName}</td>
  				<td>${pro.productProject.productProjectName}</td>
  				<td>
	  				<c:choose>
	  					<c:when test="${pro.productProject.productProjectType == 0}">
	  						${pro.productProject.productProjectVal}%
	  					</c:when>
	  					<c:otherwise>
	  						${pro.productProject.productProjectVal}元
	  					</c:otherwise>
	  				</c:choose>
  				</td>
				<td>
	  				<c:choose>
	  					<c:when test="${pro.productProject.isAddBrandWages == 0}">
	  						不算入品牌提
	  					</c:when>
	  					<c:otherwise>
	  						算入品牌提
	  					</c:otherwise>
	  				</c:choose>
  				</td>
  				<td>
  					<input name="proId" class="proId" type="checkbox" value="${pro.proProjectRelationTableId}"></input>
  				</td>
  			</tr>
  		</c:forEach>
  	</table>	
  </form>	
  </body>
</html>
