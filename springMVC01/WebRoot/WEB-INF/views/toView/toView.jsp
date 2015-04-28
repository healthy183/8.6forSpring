<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title></title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">

	-->

  </head>
  
  <body>
    测试spring的form标签
    	<table>
    		<c:forEach items="${userList}" var="user">
    			<tr>
    			<td>${user.userName}</td>
    			<td>${user.passWord}</td>
    			<td>
					<fmt:formatDate value="${user.birthday}" pattern="yyyy-MM-dd"/>    					
    			</td></tr>
    		</c:forEach>
    	</table>
    
    <!-- 不会用 -->	
    <form:select name="passWord" path="userList"> 
    		<form:option value="abc">请选择</form:option>
    		<form:options items="${userList}" itemLabel="userName" itemValue="passWord"/>
    </form:select>
    <br/>
    <form:select  name="passWord" items="${userList}" 
    	path="userList" itemLabel="userName" 
    		itemValue="passWord"> </form:select> <br/>
    
    <select name="english">
    	<option value="abc">abc</option>
    	<option value="efg">efg</option>
    </select>
    
    
    <br/>
    	
    	<form:radiobuttons name="passWord" path="userList" 
    		items="${userList}"   delimiter="<br/>"
    			itemLabel="userName" itemValue="passWord"/>
    	
    	<br/>
    	<form:checkboxes name="passWord" path="userList"
    		 items="${userList}" itemLabel="userName" itemValue="passWord"/>
    		 
    	<hr/>
    	测试springMVC的excel视图 需要 poi-3.7.jar
    	<form action="testView/testExcel.html">
    		<input type="submit" value="马上测试"/>
    	</form>
    	<hr/>
    	
    	测试springMVC的pdf视图 需要
    	 com.springsource.com.lowagie.text-2.0.8.jar,iTextAsian.jar支持中文
    	<form action="testView/testPdf.html">
    		<input type="submit" value="马上测试"/>
    	</form><hr/>
    	
    	测试输出XML(不会 报错了,有空上网查)
    	<form action="testView/testOutXml.html">
    		<input type="submit" value="马上测试"/>
    	</form>
    	
    	测试输出json(不会 报错了,有空上网查)
    	<form action="testView/testOutJson.html">
    		<input type="submit" value="马上测试"/>
    	</form>
    		 
  </body>
</html>

