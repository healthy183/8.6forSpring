<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'jqueryAttr.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
	.liClass{
		font-size: 24px;
	}
	
	.myClass{
		color:red;	
	}
	</style>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.2.js"></script>
<script type="text/javascript">
	$(function(){
		$("#proId").click(function(){
			$("#proIdDIV :checkbox").prop({
				checked:true				
			})
			$("#proIdDIV :checkbox").prop({	
				disabled:true
			})
		});	
		
		$("#removePro").click(function(){
			var removeProDIV = $("#removeProDIV");		
			removeProDIV.prop("num","123");
			alert("添加:"+removeProDIV.prop("num"));
			removeProDIV.removeProp("num");
			alert("移除:"+removeProDIV.prop("num"));
		});
		
		$("#removeClass").click(function(){
			$("ul li").removeClass(function(){
				return $(this).prev().attr("class");
			});
		});
		
		var click = 0;
		$("#toggleClass").click(function(){
			//$("p").toggleClass("liClass"); //有对应class属性则添加,否则删除	
			//$("p").toggleClass("liClass",true);等同于addClass	
			$("p").toggleClass("liClass", click++ % 3 == 0);//3的倍数添加对应cssClass 否则删除
		});
		
		$("#returnToggleClass").click(function(){
			//$("ul li:last").prev()
			$("ul li:first").toggleClass(function(){
				if($(this).next().attr("class") == "liClass"){
					return "myClass";
				}else{
					return "liClass";
				}
			});
		});
		
		$("#testHtml").click(function(){
			alert("p入面有:"+$("p").html());	
			$("p").html("123");
			alert("p入面有:"+$("p").html());	
		});
	});
</script>
  </head>
  <body>
	<input id="proId" type="button" value="测试pro"/>
	<div id="proIdDIV">
		<input type="checkbox"/>A<input type="checkbox"/>B
	</div>
	
	<input id="removePro" type="button" value="测试removePro"/>
	<div id="removeProDIV"></div>
	
	<input id="removeClass" type="button" value="测试removeClass"/>	
	<ul>
		<li class="liClass">A</li>
		<li class="liClass">B</li>
	</ul>
	
	<input id="toggleClass" type="button" value="测试toggleClass"/><br/>
	<input id="returnToggleClass" type="button" value="测试returnToggleClass" />
	<p>abc</p>
	<input id="testHtml" type="button" value="测试html()"/>
	
  </body>
</html>
