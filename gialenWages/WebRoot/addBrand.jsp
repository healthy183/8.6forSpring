<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'addBrand.jsp' starting page</title>
    
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
  <input type="button" value="添加表格"/>
  	<table border="1">
  	<tr><td colspan="2" align="center">综合品牌填写表</td></tr>
  		<tr>
  			<td>
  				综合品牌名称:<input/>
  			</td>
  			<td align="right">
  				<input type="button" value="保存/修改">
  				<input type="button" value="删除">
  				<input type="button" value="添加更多">
  			</td>
  		</tr>
  		<tr>
	  		<td>
	  			单品关键字:<input/>
	  		</td>
	  		<td>
	  			类别:<select name="class">
	  					<option>无</option>
	  					<option>护肤</option>
	  			</select>
	  			<input type="button" value="查询单品"/>
	  		</td>
  		</tr>
  		<tr>
  			<td colspan="2">单品列表</td>
  		</tr>	
  		<tr>
  			<td colspan="2">
	  			<table border="1" width="100%">
	  				<tr>
	  					<td>关键字:</td>
	  					<td align="right">
	  						<input type="button" value="修改"/>
	  						<input type="button" value="删除"/>
	  					</td>
	  				</tr>
	  				<tr>
	  					<td colspan="2">
	  					</td>
	  				</tr>
	  			</table>
	  		</td>	
  		</tr>
  	</table>
  </body>
  <!-- 
  
  
  
   
  	<table border="1">
  		<tr>
  			<td>操作</td><td><input type="button" value="删除"></td>
  		</tr>
  		<tr>
  			<td>完成额<br>
  			品牌</td>
  			<td>
  				<input/>
	  				<select>
	  					<option>以上</option>
	  					<option>以下</option>
  					</select>
  			</td>
  		</tr>	
  		<tr>
  			<td>婷美</td><td><input/></td>
  		</tr>
  	</table>
  -->
</html>
