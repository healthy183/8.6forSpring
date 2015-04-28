<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@  taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

<link rel="stylesheet" href="css/powerFloat.css" type="text/css" />
<style type="text/css">
	*{ font-size:12px;font-family:"宋体"}

</style>
	 <script language="javascript" type="text/javascript" 
	src="js/jquery-1.4.2.js"></script>
<script language="javascript" type="text/javascript" 
	src="js/jquery-powerFloat.js"></script>
<script type="text/javascript">
	$(function() {
		var fnPosTri = function() {
			var oPosTri = $("#posTrigger1"), vPosTri = $.trim(oPosTri.val());
			if (vPosTri === "") {
				oPosTri.powerFloat({
					eventType : null,
					targetMode : "remind",
					target : "输入内容不能为空！",
					position : "1-4"
				}).focus();
			} else if (/\W/g.test(vPosTri)) {
				oPosTri.powerFloat({
					eventType : null,
					targetMode : "remind",
					target : "只能输入英文字符、数字和下划线",
					position : "1-4"
				}).focus();
			} else {
				$.powerFloat.hide();
			}
		};
		$("#trigger11").bind("click", fnPosTri);
		$("#posTrigger1").bind("blur", fnPosTri);
	});
</script>
<script type='text/javascript' src='/gialenWages/dwr/interface/dwrShowError.js'></script>
<script type='text/javascript' src='/gialenWages/dwr/engine.js'></script>
<script type='text/javascript' src='/gialenWages/dwr/util.js'></script>
<script  language="javascript"  type="text/javascript">

	var jq = jQuery.noConflict();
	jq(function(){
		alert("agg");
		jq("#testButton").click(function(){
			
			alert("gg");
			var file = dwr.util.getValue("testUdpate");  
			var file = dwr.util.getValue("myfile");  
		    alert(file.value);//不同浏览器在此处得到的值,不一定相同....IE9得到的是含全路径的文件名,firefox12得到的是文件名  
		    alert(file); 
			
		    dwrShowError.testUpdate(file, file.value, function(data){  
	            alert(data);  
	        });
		});
	});
	
</script>
<script type="text/javascript">
		
	var jq = jQuery.noConflict();
	jq(function(){
		alert("13");
		jq("#ids").click(function(){
			alert("abc");			
		});
	});
</script>

</head>
  
  <body>
 
  	
  
  
  
  
  
  <%--
  		<input type="file" id="testUdpate"></input>
    	<input type="button"  id="testButton" value="测试"></input>--%>
  
  		<!--  
  		<s:url action="main_lgn.action" namespace="/main" id="deptLgn">
  			<s:param name="personid" value="%{'001'}"></s:param>
  		</s:url>
    	<s:a href="%{deptLgn}">营运部登陆</s:a>-->
    	
    	
    	
    	<c:url value="main/main_lgnByOA.action" var="lgnByOAc">
    		<c:param name="userid" value="001"></c:param>
    	</c:url>
    	<a href="${lgnByOAc}">超意登陆</a>
    	
    	
    	<c:url value="main/main_lgnByOA.action" var="lgnByOAd">
    		<c:param name="userid" value="002"></c:param>
    	</c:url>
    	<a href="${lgnByOAd}">周刚登陆</a>
    	
    	
    	
    	<c:url value="main/main_lgnByOA.action" var="lgnByOAs">
    		<c:param name="userid" value="02002"></c:param>
    	</c:url>
    	<a href="${lgnByOAs}">棠景店登陆</a>
    
    	
    	<c:url value="main/main_lgnByOA.action" var="lgnByOAa">
    		<c:param name="userid" value="0102010201"></c:param>
    	</c:url>
    	<a href="${lgnByOAa}">白云片区1</a>
    	
    	
    	<c:url value="main/main_lgnByOA.action" var="lgnByOAb">
    		<c:param name="userid" value="01020102"></c:param>
    	</c:url>
    	<a href="${lgnByOAb}">白云区</a>
    	
    	
    	
    	
    	
    	<!-- admin_OperatingMonth_addMonthSuccess -->
    	<!--  
    	<input id="posTrigger1" type="text" /> 
    	<button id="trigger11">确定</button>
    	<br>
    	<s:form action="admin_OperatingMonth_upload.action" namespace="/admin_OperatingMonth"
    		enctype="multipart/form-data" method="post">
    		上传文件:<s:file name="upfiles"></s:file>
    		<s:submit value="提交"></s:submit>
    	</s:form>
    	-->
    	<hr>
  
    	
    	
  </body>
 </html>
