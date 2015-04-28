<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'toUpDownPage.jsp' starting page</title>
    
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
		测试上传:${msg}<br/>
		方法1：testUpload
		需要  org.springframework.web-sources-3.0.5.RELEASE.jar
		commons-fileupload-1.2.1.jar commons-io-2.3.jar
	<form action="upDown/testUpload.html"
		 enctype="multipart/form-data" method="post">
		<input type="file" name="file"/> 
		 <input type="submit" value="提交上传"/>
	</form><hr/>
	
	
	测试上传:${requestScope.msg}<br/>
	方法2：testUpload2
	<form action="upDown/testUpload2.html"
		enctype="multipart/form-data" method="post">
		<input type="file" name="file"/>
		<input type="submit" value="提交上传"/>	
	</form><hr/>
	
	多文件上传:${requestScope.msg}<br/>
	方法3：testUpload3 上传完后跳到下载web
	<form action="upDown/testUpload3.html"
		enctype="multipart/form-data" method="post">
		<input type="file" name="file"/>
		<input type="file" name="file2"/>
		<input type="file" name="file3"/>
		<input type="submit" value="提交上传"/>	
	</form><hr/>
	

	测试下载:失败! ${msg}<br/> //poi-3.7.jar的IOUtils 没有write closeQuietly方法
	<a href="upDown/testDownload.html">测试下载</a>


  </body>
</html>
