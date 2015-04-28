<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'css.jsp' starting page</title>
	<style>
li{ list-style:none;}
.mainPane {width:590px; float:left; margin-left:20px;}
.mainPane h3 {height:34px; line-height:34px; color:#0275C5; padding:15px; letter-spacing:3px;}
.mainPane li {padding-left:20px; text-align:right; color:#555; height:28px; line-height:28px;}
.mainPane li a {color:#4C4C4C; float:left; font-size:14px;text-decoration:none;}
.mainPane li a:hover {color:#FF0000; font-size:14px; text-decoration: underline;}
</style>
</head>
<body>
<div class="mainPane">
	<ul>
		<li><a href='#'  target='_blank'>物超所值 七喜魔镜V001最新报价220元</a>06月07日</li>
		<li><a href='#'  target='_blank'>1200W像素S60智能 索爱U1i跌至2690元</a>06月07日</li>
		<li><a href='#'  target='_blank'>经典三防手机 路虎Sonim S1小降219元</a>06月07日</li>
		<li><a href='#'  target='_blank'>时尚全键盘 沃达丰版黑莓9700售价稳定</a>06月07日</li>
		<li><a href='#'  target='_blank'>五彩靓丽翻盖机 联想P80超低价促销中</a>06月07日</li>
		<li><a href='#'  target='_blank'>OMS系统新机 LG GD888今首次上市开卖</a>06月07日</li>
		<li><a href='#'  target='_blank'>中国风“如意”屡跳水 多普达A8188小降</a>06月07日</li>
	</ul>      
</div>
</body>
</html>
