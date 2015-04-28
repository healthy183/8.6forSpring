<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'jqueryDocumen.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
		.wrap{
			font-size:24px;
		}
	</style>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.2.js"></script>
<script type="text/javascript">
	var jq = jQuery.noConflict();	
	jq(function(){
		jq("#testPrepend").click(function(){
			//jq("#prependSpan").prepend("<b>this is </b>"); //在被选元素里最前面添加
			jq(".p").prepend(jq(".prependClass")[0]);//将.prependClass的元素剪切到.p
		});
		
		jq("#testPrependTo").click(function(){//将#prependSpan的元素剪切到.prependClass
			jq("#prependSpan").prependTo(".prependClass");
		});
		jq("#testAfter").click(function(){ //在#AfterSpan后面添加<span>i am xiong!</span>
			//jq("#AfterSpan").after("<span>i am xiong!</span>");
			jq("#prependSpan").after(jq("#AfterSpan"));//将#AfterSpan剪切到#prependSpan后面
		});
		jq("#testinsertAfter").click(function(){//将#AfterSpan插入到#prependSpan后面
			jq("#AfterSpan").insertAfter(jq("#prependSpan"));
		});
		jq("#testBefore").click(function(){
			jq("#prependSpan").before(jq("#AfterSpan"));//#AfterSpan剪切到#prependSpan之前
		});
		jq("#testinsertBefore").click(function(){ //#AfterSpan剪切到#prependSpan之前
			jq("#AfterSpan").insertBefore("#prependSpan");
		});
		jq("#testWrap").click(function(){	   
			//jq("#wrapSpan").wrap("<b>abc</b>");//添加<b>abc</b>到#wrapSpan前，并包含#wrapSpan
			jq("#wrapSpan").wrap(function(){ //回调函数 作用同上
				return "<span class='"+jq(this).text()+"'></span>";
			});
		});
		jq("#testUnwrap").click(function(){
			jq("#wrapSpan").unwrap();//移除父节点
		});
		jq("#testWrapall").click(function(){ //为所有.wrapClass元素聚在一起,并添加父节点<div class='wrap'></div>
			jq(".wrapClass").wrapAll("<div class='wrap'></div>");
		});	
		jq("#testWrapInner").click(function(){ //为#wrapInner下所有元素添加<b>节点
			jq("#wrapInner").wrapInner("<b></b>");
		});
		jq("#testReplaceWith").click(function(){ //将.replaceWithClass替换成<b>abc</b>
			//jq(".replaceWithClass").replaceWith("<b>abc</b>");
			//.spanPlaceWith剪切并覆盖.replaceWithClass
			jq(".replaceWithClass").replaceWith(jq(".spanPlaceWith"));
		});
		jq("#testReplaceAll").click(function(){//.replaceWithClass剪切并覆盖.spanPlaceWith
			jq(".replaceWithClass").replaceAll(jq(".spanPlaceWith"));
		});
		jq("#testEmpty").click(function(){
			jq(".spanPlaceWith").empty();//清空.spanPlaceWith
		});
		jq("#testRemove").click(function(){ //删除带有.spanPlaceWith的span
			jq("span").remove(".spanPlaceWith");
		});
		jq("#testDetach").click(function(){//删除带有.spanPlaceWith的span 但不删除事件
			jq("span").detach(".spanPlaceWith");
		});
		jq("#testClone").click(function(){ //复制.replaceWithClass到.spanPlaceWith
			jq(".replaceWithClass").clone().prependTo(".spanPlaceWith");
		});
	});
</script>
  </head>
  
  <body>
	<input id="testPrepend" type="button" value="测试prepend()"/>
	<input id="testPrependTo"type="button" value="测试PrependTo()"/>
	<input id="testAfter"type="button" value="测试after()"/>
	<input id="testinsertAfter" type="button" value="测试insertAfter()"/>
	<input id="testBefore" type="button" value="测试before()"/>
	<input id="testinsertBefore" type="button" value="测试insertBefore()"/>
	<br/>
	<span id="prependSpan">abc,</span>
	<p class="p">i am kang!</p>
	<p class="p">i am liang!</p>
	<span class="prependClass" id="AfterSpan">hi,</span><br/>
	<span class="prependClass">hello,</span><br/>
	
	<input id="testWrap" type="button" value="测试wrap()"/>
	<input id="testUnwrap" type="button" value="测试Unwrap()"/>
	<input id="testWrapall" type="button" value="测试wrapAll()"/>
	<input id="testWrapInner" type="button" value="测试wrapInner()"/>	
	<br/>
	<span id="wrapSpan" class="wrapClass">wrap01</span>abc
	<span  class="wrapClass">wrap02</span><br/>
	<span id="wrapInner">test<span>wrapInner</span></span><br/>
	
	<input id="testReplaceWith" type="button" value="测试replaceWith()"/>
	<input id="testReplaceAll" type="button" value="测试replaceAll()"/>
	<input id="testEmpty" type="button" value="测试empty()"/>
	<input id="testRemove" type="button" value="测试remove()"/>
	<input id="testDetach"type="button" value="测试detach()"/>
	<input id="testClone" type="button" value="测试clone()"/>
	<span class="replaceWithClass">replaceWithClass</span>
	<span class="spanPlaceWith">PlaceWithspan</span>
  </body>
</html>
