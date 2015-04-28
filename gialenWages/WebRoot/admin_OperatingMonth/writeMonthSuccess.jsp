<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"  %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'addMonthSuccess.jsp' starting page</title>
    
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
  
  <body  onload="loadPage();">
  
  <script type="text/javascript">
   		
   		var sec = 5;
   		
   		function countDown(){
   			if(sec > 0 ){
   				document.getElementById("num").innerHTML = sec--;
   			}else{
   				clearInterval(timeId);
   				location.href
   					= "admin_OperatingMonth_updtMonthDate.action?operatingMonthType="+"${param.operatingMonthType}";
   			
   			}
   		} 
   </script>
   <s:url action="admin_OperatingMonth_updtMonthDate.action" 
   		  namespace="/admin_OperatingMonth" id="updtMonthDate">
   		  	<s:param name="operatingMonthType" value="'%{#parameters.operatingMonthType}'"></s:param>
   </s:url>
  	 营运月添加成功!
   <font id="num" size="5" >5</font>秒后自动跳会<s:a href="%{updtMonthDate}">返回</s:a>查看！
  
  
  </body>
</html>
