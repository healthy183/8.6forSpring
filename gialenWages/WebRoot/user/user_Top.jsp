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
    
    <title>My JSP 'usr_top.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/layout.css">
-->
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/base.css" />
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css" />
	<style>
body { 
	padding:0px;
	margin:0px;
	font-size: 12px;
  	line-height: 1.5;
  	font-family: "宋体";
	bgColor:"#ffffff";
 }

	a:link { font-size: 9pt; color: #000000; text-decoration: none; font-family: ""宋体""; }
	a:visited{
	font-size: 9pt;
	color: #000000;
	
	font-family: ""宋体"";
}
	a:hover {
		color: red; 
		font-family: ""宋体"";
		text-decoration: underline;
		}
	a img { border-style:none; }

	.usr_table {
		float:right;
		text-align: right;
		font-size:12px;
	}

</style>



  </head>
  

  
  
  <body>
  
   <table width="100%" height="105" border="0" cellpadding="0" cellspacing="0" 
    background="image/topbgnew.gif">
    	<tr>
            <td  width="100%" valign="top" lign="right" background="image/top.gif">
            	<table class="usr_table" >
                	<tr>
                    	<td valign="top">
                    	 <s:url action="lgnout.action" namespace="/pub" id="lgnout"></s:url>
                        	您好:<span class="username"><s:property value="#session.lgnUsr.usrName"/></span>,欢迎使用本系统！                
                        [<a href="${pageContext.request.contextPath}/admin/main.jsp" target="main">首页</a>]
                        [<a href="${pageContext.request.contextPath}/admin/showMsg.jsp" target="main">修改资料</a>]
                        [<s:a onclick="return confirm('您确定要退出本系统吗？');" href="%{lgnout}" target="_top">注销退出</s:a>]&nbsp;
                        </td>
                    </tr>
                </table>
            </td>
      </tr>
    
    
    </table>
  
 
  <!--
  <div id="wfhleft">
			 <div style="float:left;">
			  		<div style="float:left;"><img src="${pageContext.request.contextPath}/usr/top_left.gif"/></div>
			 </div>		
  			<div style="float:right;" ><br/>
  				
  				欢迎你,<s:property value="#session.lgnUsr.usrName"/>
	  	<a href="${pageContext.request.contextPath}/admin/showMsg.jsp">修改密码</a>
	  		 
		    <s:url action="lgnout.action" namespace="/pub" id="lgnout"></s:url>
		    <s:a onclick="return confirm('您确定要退出本系统吗？');"  href="%{lgnout}">退出</s:a>
  			
  			</div>
	  </div>
  
  
  
   <div id="header">
	  	<img src="${pageContext.request.contextPath}/top.gif">	
	  	<div style="background:#CCED22;">
	  
	  <div style="float:left;background:#CCED22; margin-left:200px;"> 
	  
	 
	    
	  <s:url action="usr_jStorePlu.action" namespace="/usr" id="jStorePlu"></s:url> 
	  			&nbsp;<s:a href="%{jStorePlu}">单品销售汇总表(按门店)</s:a>
	  			
	  <s:url action="usr_jAP.action" namespace="/usr" id="jDPhz"></s:url>
	    		&nbsp;<s:a href="%{jDPhz}">单品销售汇总表</s:a>
	    
	   <s:url action="usr_jSB.action" namespace="/usr" id="jMDhz"></s:url>
	    		&nbsp;<s:a href="%{jMDhz}">门店品牌小类销售汇总表 </s:a>
	    				
	    	<s:if test="#session.lgnUsr.usrLv > 1">	
	  <s:url action="usr_showCou.action" namespace="/usr" id="showCou"></s:url>
	    	&nbsp;&nbsp;&nbsp;<s:a href="%{showCou}">单品日结库存表(按门店)</s:a>	
	 </s:if>
	 
	 	<s:if test="#session.lgnUsr.usrLv > 1">	
	  <s:url action="usr_showAreaCou.action" namespace="/usr" id="showAreaCou"></s:url>
	    	&nbsp;&nbsp;&nbsp;<s:a href="%{showAreaCou}">单品日结库存表(按区域)</s:a>	
	 </s:if>
	
	  	 
	 <s:url action="usr_addHZ" namespace="/usr" id="addHZ"></s:url>
	 <s:a href="%{addHZ}">批量添加  单品销售汇总表(按区域)</s:a>
	

	 <s:url action="usr_addMX" namespace="/usr" id="addMX" ></s:url>
	 <s:a href="%{addMX}">批量添加明细</s:a>  
	 
	   </div>
	  
	  
	  
	  <div style="float:right;background:#CCED22;">
	  
	  欢迎你:<s:property value="#session.lgnUsr.usrName"/> LV<s:property value="#session.lgnUsr.usrLv"/>
	  
	  		 <a href="${pageContext.request.contextPath}/usr/changePwd.jsp">修改密码</a>
	  		 
		    <s:url action="lgnout.action" namespace="/" id="lgnout"></s:url>
		    <s:a onclick="return confirm('您确定要退出本系统吗？');"  href="%{lgnout}">退出</s:a>
	  		</div>
	  </div>
	  
	  		
	  		
	  </div>
  --></body>
</html>
