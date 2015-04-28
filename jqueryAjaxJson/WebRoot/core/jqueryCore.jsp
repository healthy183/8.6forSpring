<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'jqueryCore.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
		.imgclass{
			font-size: 24px;
		}
	</style>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.2.js"></script>
<script type="text/javascript">
	$(function(){
		$("#imgButton").click(function(){
			$("img").each(function(){
				$(this).attr("src","${pageContext.request.contextPath}/core/Login.gif");
				//this.src= "${pageContext.request.contextPath}/core/Login.gif";
				$(this).toggleClass("imgclass");
			});
		});
		
		$("#returnButton").click(function(){
			alert("现场div数目是:"+$("div").size());
			alert("现场div数目是:"+$("div").length);
			
			$("div").each(function(i,value){
				if($(this).attr("id") == "stop"){
					alert("到了第"+(i+1)+"div就退出了!");
					return  false;
				}
			});			
		});
		
		$("#selectorButton").click(function(){
			$("#selectorSpan").append("选择器内容是:"+$("#selectorSpanselector").selector);
		});
		
		$("#contextButton").click(function(){
			$("#contextSpan").append("不明白返回的东西是:"+$("#contextSpan").context+"&&"
			          +$("#contextSpan",document.body).context.nodeName);
		});
		
		$("#get0").click(function(){
			$("#getSpan").append($("img").get(0));
			$("#getSpan").append($(".getSpan").get().reverse());
		});
		
		$("#indexButton").click(function(){
			//alert($("li").index("#foo"));//0  在li中下标是0
			//alert($("li").index($("li:gt(0)")));//1
			//alert($("#bra").index("li"));//1 在li中下标是1
			alert($("#bra").index());//2 在同辈中排行第2
		});
		
		$("#dataId").click(function(){
			$("#dataSpan").data("testData");//undefined
			$("#dataSpan").data("testData","ok");//设置值
			//alert($("#dataSpan").data("testData"));//弹出ok
			$("#dataSpan").data("testData","unok");
			//alert($("#dataSpan").data("testData"));
			$("#dataSpan").removeData("testData"); //remove值
			//alert($("#dataSpan").data("testData"));//undefined
			
			//1.43新用法
			$("#dataSpan").data("test",{f:1,g:2});
			alert($("#dataSpan").data("test").f);
			alert($("#dataSpan").data("test").g);
		});
	});
</script>

</head>
  <body>
  <input type="button" id="imgButton" value="imgButton"/><br/>
	<img src="${pageContext.request.contextPath}/core/Login.gif"></img><img></img><br/><br/>
	
	<input type="button" id="returnButton" value="returnButton"/><br/>
	<div></div>	
	<div id="stop"></div>
	<div></div>
	
	<input type="button" id="selectorButton" value="测试selector,返回选择器内容"/><br/><br/>
	<span id="selectorSpan"></span><br/>	
	
	<input type="button" id="contextButton" value="测试context(唔明)"/><br/><br/>
	<span id="contextSpan"></span><br/>
	
	<input type="button" id="get0" value="测试get(0)"/><br/>	
	<span id="getSpan"></span>
	<span class="getSpan">abc</span><span class="getSpan">def</span><br/>
	
	<input type="button" id="indexButton" value="indexButton"/>
	<ul>
		<span>wfh</span>
		<li id="foo">foo</li>
		<li id="bra">bra</li>
		<li id="baz">baz</li>
	</ul><br/>
	
	<input type="button" id="dataId" value="测试data(类似map)"/>
	<span id="dataSpan"></span>
	
	
  </body>
</html>
