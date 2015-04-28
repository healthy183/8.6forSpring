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
    
    <title>My JSP 'showCountProductProjectsByAreaSuccess.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
 <script type="text/javascript">
    var	timeId;
    
    function loadPage(){
		//每五秒钟自动调用一次countDown方法
    	timeId = setInterval("countDown()",1000);
    }
   
   	</script> 
  </head>
  
  <body onload="loadPage();">
    <script type="text/javascript">
   		var sec = 5;
   		
   		function countDown(){
   			if(sec > 0 ){
   				document.getElementById("num").innerHTML = sec--;
   			}else{
   				clearInterval(timeId);
   				location.href
   					= "admin_countProductProject_showCountProductProjectsByArea.action?operatingMonthType="+
   							"${param.operatingMonthType}&unitcode="+"${param.unitcode}&operatingMonthId="+
   								"${param.operatingMonthId}";
   			}
   		} 
   </script>
  
   		  
   	<c:url value="admin_countProductProject/admin_countProductProject_showCountProductProjectsByArea.action" var="showCountProductProjectsByArea">
			<c:param name="operatingMonthType" value="${param.operatingMonthType}"></c:param>
			<c:param name="unitcode" value="${param.unitcode}"></c:param>
			<c:param name="operatingMonthId" value="${param.operatingMonthId}"></c:param>
	</c:url>	  
   		  
  	统计完成!
   <font id="num" size="5" >5</font>秒后自动跳会<a href="${showCountProductProjectsByArea}">返回</a>查看！
  
  
  
  
			
	
 </body>
</html>
