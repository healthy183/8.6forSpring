<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'admin_left.jsp' starting page</title>


<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/menu.css" />
<script language='javascript'>var curopenItem = '1';</script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-1.4.2.js"></script>
	<script language="javascript" type="text/javascript">
	var jq = jQuery.noConflict();
	jq(function(){
		jq("#arrangeBrand").click(function(){
			var add = jq("#arrangeBrandUL li").css("display");
			if(add == "block"){
				jq("#arrangeBrandUL li").hide();
			}else{
				jq("#arrangeBrandUL li").show();
			}
		});
		jq(".dtClass").each(function(){
			jq(this).click(function(){
				var displayVar = jq(this).next().css("display");
				if(displayVar =="block"){
					jq(this).next().css("display","none");
				}else{
					jq(".dtClass").next().css("display","none");
					jq(this).next().show();
				}
			});
		});
	});
	</script>
<style type="text/css">
<!--
div {
	padding: 0px;
	margin: 0px;
}

body {
	font-size: 12px;
	scrollbar-base-color: #4D290F;
	scrollbar-arrow-color: #FFFFFF;
	scrollbar-shadow-color: #c1ea8b;
	padding: 0px;
	margin: auto;
	text-align: center;
	background-color: #DAEA65;
}

a {
	font-size: 9pt;
	color: #000000;
	text-decoration: none;
	font-family: "" 宋体 "";
}

a:visited {
	font-size: 9pt;
	color: #000000;
	font-family: "" 宋体 "";
}

a:hover {
	color: red;
	font-family: "" 宋体 "";
	text-decoration: underline;
}

a img {
	border-style: none;
}

dl.bitem {
	font-size: 12px;
	background-color: #DAEA65;
	width: 160px;
	/*上右下左*/
	margin: 0px 0px 5px 4px;
}

dl.bitem dt {
	background: url(image/menubg.jpg);
	height: 26px;
	line-height: 26px;
	text-align: center;
	cursor: pointer;
}

dl.bitem dd {
	list-style-type: none;
	margin: 0px;
	padding: 3px 3px 3px 3px;
	background-color: #fff;
}

.fllct {
	float: left;
	width: 90px;
}

.flrct {
	padding-top: 3px;
	float: left;
}

div.items {
	line-height: 22px;
	background: url(image/arr4.gif) no-repeat 10px 9px;
}

span.items {
	padding: 10px 0px 10px 22px;
	background: url(image/arr4.gif) no-repeat 10px 12px;
}

ul {
	margin: 0px;
	list-style-type: none;
	padding-top: 3px;
}

li {
	margin: 0px;
	height: 22px;
}

.sitemu li {
	padding: 0px 0px 0px 22px;
	line-height: 24px;
	background: url(image/arr4.gif) no-repeat 10px 9px; /**/
}
-->
</style>
</head>

<body target="main">

	<table width='99%' height="100%" border='0' cellspacing='0'
		cellpadding='0'>
		<tr>
			<td style='padding-left:3px;padding-top:8px' valign="top">
				
				<dl class='bitem'>
					<dt class="dtClass">
						<b>功能列表</b>
					</dt>
					<dd style='display:none' class='sitem' id='items4_1'>
						<ul class='sitemu'>
							<li>
								<c:url value="user_showTable/user_showTable_showUsrWages.action" var="showUsrWages">
									<c:param name="operatingMonthType" value="0"></c:param>
								</c:url>
									<a href="${showUsrWages}" target="main">员工销售提成汇总表 </a>							
							</li>
							<li>
							<c:url value="user_showTable/user_showTable_showStoreWages.action" var="showStoreWages">
									<c:param name="operatingMonthType" value="0"></c:param>
								</c:url>
								<a href="${showStoreWages}" target="main">夏季02期员工销售汇总表</a>
							</li>
						</ul>
					</dd>
				</dl>
				<dl class='bitem'>
					<dt class="dtClass">
						<b>系统帮助</b>
					</dt>
					<dd style='display:none' class='sitem' id='items4_1'>
						<ul class='sitemu'>
							<li><a
								href="http://www.gialen.com/pages/newsbrief.aspx?catid=5|13|57"
								target="_blank">访问官网</a></li>
							<li><a target="blank"
								href="tencent://message/?uin=892902951&amp;Site=点这里和我联系&amp;Menu=yes">在线客服</a>
							</li>
						</ul>
					</dd>
				</dl>
				
			</td>
		</tr>
	</table>
	 	
</body>
</html>
