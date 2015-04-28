<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib  uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   <base href="<%=basePath%>">
    <title>main.jsp</title>
	
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="this is my page">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    
    <!--<link rel="stylesheet" type="text/css" href="./styles.css">-->
	<link rel="stylesheet" type="text/css" href="css/base.css" />
	<link rel="stylesheet" type="text/css" href="css/main.css" />
  </head>
  
  <body leftmargin="8" topmargin='8'>
    <table width="98%" border="0" align="center"
     cellpadding="0" cellspacing="0">
  <tr>
    <td><div style='float:left'> <img height="14" src="image/book1.gif" width="20" />
    			&nbsp;欢迎使用娇兰佳人销售报表查询系统！</div>
      <div style='float:right;padding-right:8px;'>
        <!--  //保留接口  -->
      </div></td>
  </tr>
  <tr>
    <td height="1" background="image/sp_bg.gif" style='padding:0px'></td>
  </tr>
</table>
<table width="98%" align="center" border="0" cellpadding="3" cellspacing="1" 
	bgcolor="#CBD8AC" style="margin-bottom:8px;margin-top:8px;">
  <tr>
    <td background="image/wbg.gif" bgcolor="#EEF4EA" class='title'>
    	<span>消息</span>
    </td>
  </tr>
  <tr bgcolor="#FFFFFF">
    <td>&nbsp;通知:系统权限广告语!<a href="javascript:void;" style="color:red;"><!--  (关于等级)--></a></td>
  </tr>
</table>
<table width="98%" align="center" border="0" cellpadding="4" cellspacing="1"
 bgcolor="#CBD8AC" style="margin-bottom:8px">
  <tr bgcolor="#EEF4EA">
    <td colspan="2" background="image/wbg.gif" class='title'>快捷操作
    	<!-- <div style='float:left'><span></span></div></td>
    	<div style='float:right;padding-right:10px;background-color:#EEF4EA'></div>-->
   </td>
  </tr>
  <tr bgcolor="#FFFFFF">
    <td height="30" colspan="2" align="left" valign="bottom">
    		<img src="image/qc.gif" width="90" height="30" style="margin-rigth:100px;"/>
    		<%--
	    <table width="100%" border="0" cellspacing="1" cellpadding="1" align="left">
	        <tr>
	          <td width="15%" height="31" align="center">
	          
	          <td width="85%" valign="bottom"></td>
	        </tr>
	      </table>
 		--%>
 </td>
  </tr>
</table> <!-- bgcolor="#CBD8AC"金边 -->

<table width="98%" align="center" border="0" cellpadding="4" cellspacing="1"  
bgcolor="#CBD8AC" style="margin-bottom:8px">
  <tr bgcolor="#FFFFFF">
    <td colspan="2" background="image/wbg.gif" class='title'><span>系统基本信息</span></td>
  </tr>
  <tr bgcolor="#FFFFFF">
    <td width="25%" >您的级别：</td>
    <td width="75%" >${sessionScope.lgnUsr.usrLv}级&nbsp;</td>
  </tr>
  <tr bgcolor="#FFFFFF">
    <td>软件版本信息：</td>
    <td>gialen_2012_UTF8</td>
  </tr>
</table>
<table width="98%" align="center" border="0" cellpadding="4" 
	cellspacing="1" bgcolor="#CBD8AC">
  <tr bgcolor="#FFFFFF">
    <td colspan="2" background="image/wbg.gif" class='title'><span>使用帮助</span></td>
  </tr>
  <tr bgcolor="#FFFFFF">
    <td height="32">娇兰佳人官方网站：</td>
    <td ><a href="http://www.gialen.com/pages/newsbrief.aspx?catid=5|13|57" target="_blank"><p style="font-size: 12px;s">http://www.gialen.com</p></a></td>
  </tr>
  
</table>
  </body>
</html>
