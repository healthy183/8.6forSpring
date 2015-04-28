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
    
    <title>My JSP 'examineTreim.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript" src="js/jquery-1.7.2.js"></script>
<script type="text/javascript">
	$(function(){
		$(".ahrefs").click(function(){
			var id = $(this).attr("id");
			
			var rmidVar = $("#rmid"+id).val();
			var rmdescVar = $("#rmdesc"+id).val();
			
			alert(rmidVar+rmdescVar);
			$.post("${pageContext.request.contextPath}/adminTreim/examineTreimSuccessful.html",
					{rmid : rmidVar,rmdesc : rmdescVar},
						function(data){
							alert(data);
					});
		});
	});


</script>
  </head>
  
  <body>
   <table border="1">
  		<tr>
  			<th colspan="2">待${sessionScope.lgnUser.usrname}审批的申请表单</th>
  		</tr>
  		<c:forEach items="${requestScope.treimList}" var="vo" varStatus="v">
  			<tr>
  				<th>${vo.rmname}</th>
  				<th>
  					<c:if test="${vo.rmdesc == 0}">
  						待审批
  					</c:if>
  					<c:if test="${vo.rmdesc == 1}">
  						通过
  					</c:if>
  					<c:if test="${vo.rmdesc == 2}">
  						被拒
  					</c:if>
  				</th>
  			</tr>
  				<c:forEach items="${vo.TReimitms}" var="treimitm" varStatus="t">
						<tr>
							<td>${treimitm.rmitmname}</td>
							<td>${treimitm.rmitmcost}</td>
						</tr>	  				
  				</c:forEach>
  			<tr>
  				<td>
  					<input type="hidden" name="rmid" id="rmid${v.index}" value="${vo.rmid}"></input>
  					<select name="rmdesc" id="rmdesc${v.index}">
  						<option value="1">通过</option>
  						<option value="2">被拒</option>
  					</select>
  				</td>
  				<td>
  					<a href="javascript:void();" id="${v.index}" class="ahrefs">提交</a>
  				</td>
  			</tr>	
  		</c:forEach>
  	</table>
  </body>
</html>
