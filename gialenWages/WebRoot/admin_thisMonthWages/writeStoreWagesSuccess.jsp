<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'writeStoreWagesSuccess.jsp' starting page</title>
    
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
   				location.href= "admin_thisMonthWages_writeStoreWages.action?operatingMonthType="+
   					"${param.operatingMonthType}&operatingMonthPathMoneyType="+
   						"${param.operatingMonthPathMoneyType}&operatingMonthId="+
   							"${param.operatingMonthId}";
   			}
   		} 
   </script>
      <s:url action="admin_thisMonthWages_writeStoreWages.action" 
   		  namespace="/admin_thisMonthWages" id="writeStoreWages">
   		  	<s:param name="operatingMonthType" value="'%{#parameters.operatingMonthType}'"></s:param>
   		 	<s:param name="operatingMonthPathMoneyType" value="'%{#parameters.operatingMonthPathMoneyType}'"></s:param>
   			<s:param name="operatingMonthId" value="'%{#parameters.operatingMonthId}'"></s:param>
   		 </s:url>
  	 门店奖金方案提交完成！
   <font id="num" size="5" >5</font>秒后自动跳会<s:a href="%{writeStoreWages}">返回</s:a>查看！
  
   
  </body>
</html>