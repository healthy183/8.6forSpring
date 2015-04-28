<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'jqueryEvent.jsp' starting page</title>
    
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
	//jq("#testBind").click(function(){});
	jq(function(){
		jq("#testBind").bind("click mouseleave",function(){//同时绑定两个事件触发同一个function
			alert("触发事件了！");
		});
		jq("#testBind2").bind({ //同一button部署不同事件触发不同function
			click:function(){alert("点击事件!")},
			mouseleave:function(){alert("mouseleave 事件!")}
		});
		jq("#testOne").one("click",function(){
			alert("一次性事件:one!");
		});
		jq("#testUnbind").click(function(){
			alert("取消testBind2的click事件");
			jq("#testBind2").unbind("click");
		});
		
		jq(".testLive").live({//可以用回调函数实现
			click:function(){alert("点击事件!")},
			mouseleave:function(){
				jq("p").append(
					"<input type='button' class='testLive' value='测试live()'>"
				);
			}
		});
		
		jq("#testDie").click(function(){
			jq("#testBind").die("click");				
		});
		
		function thisDie(){
			alert("thisDie function");
		}
		jq("#thisDie").click(thisDie);
		jq("#testDie").click(function(){
			jq("#thisDie").die("click",thisDie);
		});
	});
	
	
</script>	
  </head>
  
  <body>
		<input type="button" id="testBind" value="testBind()1"/>
		<input type="button" id="testBind2" value="testBind()2"/>
		<input type="button" id="testOne" value="testOne()"/>
		<input type="button" id="testUnbind" value="testUnbind()"/>
		<input type="button" class="testLive" id="testLive" value="testLive()"/><br/>
		
		<input type="button" id="thisDie" value="thisDie"/>
		<input type="button" id="testDie" value="测试die(唔识用)"/>
		
		
		<p></p>
  </body>
</html>
