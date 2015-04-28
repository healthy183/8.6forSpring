<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'toConversionPage.jsp' starting page</title>
    
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
 	 测试WebBindingInitializer || ConversionServiceFactoryBean
    <form action="conversion/showWebBindingInitializer.html">
    	<input  type="text" name="user" value="healthy:梁健康:123"/>
    	<input type="submit" value="提交"/>
    </form>
    <hr/>
    
    测试initBinder
  <form action="conversion/showInitBinder.html">
  <input  type="text" name="user" value="healthy183:梁健康:123"/>
    	<input type="submit" value="提交"/>
  </form>  
  
     测试initBinder for Date
  <form action="conversion/showInitBinderDate.html">
  	<input type="text" name="myDate" value="2012-01-12"/>
  	<input type="submit" value="提交"/>
  </form>
  
  测试FormattingConversionServiceFactoryBean
  <form action="conversion/showFormattingConversionServiceFactoryBean.html">
  	<input type="text" name="usrName" value="梁健康"/>
  	<input type="text" name="usrPassWord" value="123"/>
  	<!-- 输入时候是javaBean对应的格式,就可以自动转换 否则：Unparseable Date -->
  	<input type="text" name="birthday" value="2012-12-12"/>
  	<input type="text" name="totalCount" value="2,123.12"/>
  	
  	<%----%>
  	<input type="text" name="discount" value="12%"/>
  	<input type="text" name="sumMoney" value="￥100000"/>
  	
  	<input type="text" name="salary" value="2,123.12"/>
  	
  	<input type="submit" value="提交"/>
  </form>
  <br>
  
  
   测试String to model转换器
  <form action="conversion/StringToModel.html">
  	<input type="text" name="phoneNum" value="020-6501353"/>
  	<input type="submit" value="提交"/>
  </form>
  
    
  </body>
</html>
