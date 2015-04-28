<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'jqueryTool.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.2.js"></script>
<script type="text/javascript">
	var jq = jQuery.noConflict();
	jq(function(){
		jq("#testEach").click(function(){
			jq.each({name:"j",pwd:"i"},function(n,v){
				alert(v);//弹出 j i
			});
		});
		jq("#testExtend").click(function(){
			var empty = {};
			var defaults = { validate: false, limit: 5, name: "foo" };
			var options = { validate: true, name: "bar" };//又后到前覆盖值
			var settings = jQuery.extend(empty, defaults, options);
			jq.each(empty,function(n,v){
				alert(v);
			});
		});	
		jq("#testGrep").click(function(){
	    <%--var a= jq.grep([0,1,2],function(n,i){
				return n>0;//返回下标大于0的元素
			}); --%>
			var a= jq.grep([0,1,2],function(n,i){
				return n>0;//返回下标不大于0的元素
			},true);
			jq.each(a,function(n,v){
				alert(v);
			});
		});
		jq("#testMap").click(function(){
			var a = jq.map([0,1,2],function(n,v){
				//return v>0?(v+1):null;
				return v+4;
			});
			jq.each(a,function(n,v){
				alert(n);										
			});
		});
		jq("#testToarray").click(function(){
			<%--(jq(":input").toArray()).each(function(){
				alert(jq(this).val());
			}); --%>
			alert(jq(":input").toArray());
		});
		jq("#testMerge").click(function(){//合并数组
			var jqmerge = jq.merge([0,1,2],[3,4,5]);
			alert(jqmerge);
		});
		jq("#testParentJson").click(function(){//格式化json?
			var obj = jQuery.parseJSON('{"name":"kang"}');
			alert(obj.name == "kang");
		});
	});
</script>
  </head>
  
  <body>
	<input id="testEach" type="button" value="testEach()"/>
	<input id="testExtend" type="button" value="testExtend()"/>
	<input id="testGrep" type="button" value="testGrep()"/>
	<input id="testMap" type="button" value="testMap(唔明)"/>
	<input id="testToarray"type="button" value="testToArray()"/>
	<input id="testMerge"type="button" value="testMerge()"/><br/>
	<input id="testParentJson" type="button"value="parentJson"/>

  </body>
</html>
