<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'css2.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
	li{ 	list-style:none;
			padding-left:20px;
			text-align:left;
			color:#555; 
			height:28px; 
			line-height:28px;
		}
	li a {color:#4C4C4C; float:right; font-size:14px;text-decoration:none;}
	div{
		display: inline; 
	}
</style>
  </head>
  
  <body>
  
  <table border="1" width="100%">
  	<tr>
  		<td>
  			<span style="text-align:center;">06月07日</span> 
  			<a href='#'  target='_blank'  style="text-align: right;">物超所值 七喜魔镜V001最新报价220元</a>
  		</td>
  	</tr>
  	<tr>
  		<th>
  		abc
  			<div style="text-align: right;float:right;margin-top:-20px; ">
  				<a href='#'  target='_blank'>物超所值 七喜魔镜V001最新报价220元</a>
  			</div>
  		</th>
  	</tr>
  	
  	
  </table>
  
  
  
  
  
  
  
  
  
  
  
  
  
  <!-- 
  
  	<table border="1">
  		<tr>
	  		<td>
		  		<ul>
					<li> 06月07日 <a href='#'  target='_blank'>物超所值 七喜魔镜V001最新报价220元</a></li>
					<li>06月07日<a href='#'  target='_blank'>1200W像素S60智能 索爱U1i跌至2690元</a></li>
				</ul> 
	  		</td>
  		</tr>
  		<tr>
	  		<td>
	  			<ul>
					<li> 06月07日 <a href='#'  target='_blank'>物超所值 七喜魔镜V001最新报价220元</a></li>
					<li>06月07日<a href='#'  target='_blank'>1200W像素S60智能 索爱U1i跌至2690元</a></li>
				</ul> 
	  		</td>
  		</tr>		
  	</table>
   -->
  </body>
</html>
