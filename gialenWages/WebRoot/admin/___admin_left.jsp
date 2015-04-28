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
						<b>基础设置</b>
					</dt>
					<dd style='display:block' class='sitem' id='items1_1'>
						<ul class='sitemu'>
							<li id="li1"><s:url action="admin_OperatingMonth_writeMonth.action"
									namespace="/admin_OperatingMonth" id="OperatingMonth">
										<s:param name="operatingMonthType" value="{'0'}"></s:param>
									</s:url>
									<s:a href="%{OperatingMonth}"target="main">今年445安排填写/修改</s:a>
							</li>
							<li id="li1">
								<s:url action="admin_synchronizationStores_writeStoreMessage.action" 
										namespace="/admin_synchronizationStores" id="writeStoreMessage">
										<s:param name="operatingMonthType" value="%{'0'}"></s:param>
								</s:url>
								<s:a target="main" href="%{writeStoreMessage}">同步hr佳讯门店信息</s:a>
							</li>	
						</ul>
					</dd>
				</dl>

				<dl class='bitem'>
					<dt class="dtClass">
						<b>提成方案维护</b>
					</dt>
					<dd style='display:none' class='sitem' id='items2_1'>
						<ul class='sitemu'>
							<li id="li1">
							<s:url action="admin_thisMonthWages_writeAreaWages.action"
									namespace="/admin_thisMonthWages" id="writeAreaWages">
											<s:param name="operatingMonthPathMoneyType" value="%{'0'}"></s:param>
											<s:param name="operatingMonthType" value="%{'0'}"></s:param>
									</s:url>
							<s:a href="%{writeAreaWages}"target="main">填写本营运月区域销售奖核算标准表</s:a></li>
							
							<li id="li1">
							<s:url action="admin_thisMonthWages_writeStoreWages.action"
									namespace="/admin_thisMonthWages" id="writeStoreWages">
											<s:param name="operatingMonthPathMoneyType" value="%{'1'}"></s:param>
											<s:param name="operatingMonthType" value="%{'0'}"></s:param>
							</s:url>
									<s:a href="%{writeStoreWages}"target="main">填写本营运月门店销售奖核算标准表</s:a></li>
							
							<li id="li1">
								<s:url action="admin_thisMonthTotalCommission_writeStoreCommission.action" 
										namespace="/admin_thisMonthTotalCommission" id="writeStoreCommission">
											<s:param name="operatingMonthType" value="%{'0'}"></s:param>
											<s:param name="operatingMonthPathMoneyType" value="%{'2'}"></s:param>
										</s:url>
								<s:a target="main" href="%{writeStoreCommission}" >填写本营运月门店总提标准表 </s:a>
							</li>
						</ul>
					</dd>
				</dl>
				
				<dl class='bitem'>
					<dt class="dtClass">
						<b>销售目标管理</b>
					</dt>
					<dd style='display:none' class='sitem' id='items3_1'>
						<ul class='sitemu'>
							<li id="li1">
								<a id="arrangeBrand" href="javascript:void(0);">填写下营运月品牌奖励方案</a>
									<ul id="arrangeBrandUL">
										  
										  
										<li>
										<s:url action="admin_arrangeBrand_addProduct.action"
												 namespace="/admin_arrangeBrand" id="addProduct">
													 <s:param name="operatingMonthType" value="{'0'}"></s:param>
													 <s:param name="projectType" value="{'1'}"></s:param>
											</s:url>
											<s:a href="%{addProduct}" target="main">填写品牌提成方案</s:a>
											 
										</li>				  
										  
										  
										  
										<li>
											<s:url action="admin_arrangeBrand_addBrand.action"
												 namespace="/admin_arrangeBrand" id="addBrand">
													 <s:param name="operatingMonthType" value="{'0'}"></s:param>
													 <s:param name="projectType" value="{'0'}"></s:param>
											</s:url>
											<s:a href="%{addBrand}" target="main">填写单品提成方案</s:a>
										</li>
									</ul>
							</li>
						</ul>
					</dd>
				</dl>	

				<dl class='bitem'>
					<dt class="dtClass">
						<b>提成工资核算</b>
					</dt>
					<dd style='display:none' class='sitem' id='items4_1'>
						<ul class='sitemu'>
							<li id="li1">
							<s:url action="admin_AccountingArea_showLastMonthAreaWages.action"
									namespace="/admin_AccountingArea" id="showLastMonthAreaWages">
										 <s:param name="operatingMonthType" value="{'0'}"></s:param>
									</s:url>
									 <s:a href="%{showLastMonthAreaWages}" target="main">核算上月各区域提成</s:a></li>
							
							<!-- 
							<li id="li1">
							<c:url value="admin_countProductProject/admin_countProductProject_showCountProductProjects.action" var="showCountProductProjects">
								<c:param name="operatingMonthType" value="0"></c:param>
							</c:url>
							<a href="${showCountProductProjects}" target="main">核算单品提成</a>
							</li> -->
							<li>
								<c:url value="admin_countProductProject/admin_countProductProject_showCountProductProjectsByArea.action" var="showCountProductProjectsByArea">
									<c:param name="operatingMonthType" value="0"></c:param>
								</c:url>
								<a href="${showCountProductProjectsByArea}" target="main">核算单品提成</a>
							</li>
							<li id="li1"><s:url action="admin_jAdd.action"
									namespace="/admin" id="jAdd"></s:url> <s:a href="%{jAdd}"
									target="main">核算上月各门店提成</s:a>
							</li>
							
							
							
							
								<li>
										<s:url action="admin_PlanBrandRelation_Check_Brand_Product.action"
												 namespace="/admin_PlanBrandRelation" id="Check_Brand_Product">
													 <s:param name="operatingMonthType" value="{'0'}"></s:param>
													 <s:param name="projectType" value="{'1'}"></s:param>
											</s:url>
											<s:a href="%{Check_Brand_Product}" target="main">数据整理</s:a>
											 
											 
									</li>			 
											<li> 
											 <s:url action="admin_PlanBrandRelation_Branch_Product_Collect.action"
												 namespace="/admin_PlanBrandRelation" id="Branch_Product_Collect">
													 <s:param name="operatingMonthType" value="{'0'}"></s:param>
													 <s:param name="projectType" value="{'1'}"></s:param>
											</s:url>
											<s:a href="%{Branch_Product_Collect}" target="main">门店品牌汇总</s:a>									 
										</li>	
										
										
										
										<li> 
											 <s:url action="admin_PlanBrandRelation_IsAddBrandWages.action"
												 namespace="/admin_PlanBrandRelation" id="IsAddBrandWages">
												 
													 <s:param name="isAddBrandWages" value="{'1'}"></s:param>
											</s:url>
											<s:a href="%{IsAddBrandWages}" target="main">无单品任务的品牌</s:a>									 
										</li>		
										
										
										
										<li> 
											 <s:url action="admin_PlanBrandRelation_IsAddBrandWages.action"
												 namespace="/admin_PlanBrandRelation" id="IsAddBrandWages">
													 
													 <s:param name="isAddBrandWages" value="{'0'}"></s:param>
											</s:url>
											<s:a href="%{IsAddBrandWages}" target="main">有单品任务的品牌</s:a>									 
										</li>	
										
										
										
										
										<li> 
											 <s:url action="admin_PlanBrandRelation_CollectBrandWages.action"
												 namespace="/admin_PlanBrandRelation" id="CollectBrandWages">
													 
													 <s:param name="isAddBrandWages" value="{'0'}"></s:param>
											</s:url>
											<s:a href="%{CollectBrandWages}" target="main">统计品牌提成</s:a>									 
										</li>		
										
										
										<li> 
											 <s:url action="admin_PlanBrandRelation_ShowBrandWages.action"
												 namespace="/admin_PlanBrandRelation" id="ShowBrandWages">													 													 
											</s:url>
											<s:a href="%{ShowBrandWages}" target="main">显示门店定额</s:a>									 
										</li>		
											
										<li> 
											 <s:url action="admin_PlanBrandRelation_delete_table.action"
												 namespace="/admin_PlanBrandRelation" id="delete_table">													 													 
											</s:url>
											<s:a href="%{delete_table}" target="main">清除中间表数据</s:a>									 
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
