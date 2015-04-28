<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'jqueryEachMap.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.4.2.js"></script>
<script type="text/javascript">
		
		$(function(){
			
			$("#button").click(function(){
				
			var map = {地名:["北京","天津","上海"],民族:["汉族","藏族","维吾尔族"]};
			var info = "";
			
			$.each(map,function(key,values){
				info +="<br>"+"key:"+key+"--";
				$(values).each(function(){
					info +="var:"+this;
				});
			});
			$("#span").replaceWith(info);//$("#span").append(info);//$("#span").html(info);
		});
			
			$("#button2").click(function(){
				$("#span2").append($("input").map(function(){
					return $(this).val();
				}).get().join(","));
			});
			
			
			
			
			
		});
</script>	 



</head>
  
  <body>
  <input id="button" type="button" value="测试jquery迭代map"/>
	<span id="span"></span><hr/>
<br/>
<!--  -->
map(callback)将当前匹配集合中元素通过‘callback’函数处理得到的一串新元素，进行组合成一个新列表(list)<br/>
$("#span2").append( $("input").map(function(){<br/>
对匹配input元素集合进行处理，处理后将返回值组合成新列表。<br/>
   <input id="button2" type="button" value="测试jquery迭代map(callback)"/><br/>		
	<span id="span2"></span><hr/><br/>
	
	<input type="button" id="button3" value="测试输出json List集合"/>
	<table id="button3Table">
		<tr>
			<td>姓名</td>
			<td>密码</td>
		</tr>
	</table>





  </body>
</html>
