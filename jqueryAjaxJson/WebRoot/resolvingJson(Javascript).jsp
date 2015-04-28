<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>遍历json获得数据的4种方法</title>
    
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
		
		$("#test1").click(function(){
			var list1 = [1,2,3];
			//alert(list1[0]);
			
			var list2 = [{"name":"梁健康","xing":"lin"}];
			//alert(list2[0]["xing"]);
			//alert(list2[0].xing);
			
			var value = {
	"china":{
		"hangzhou":{"item":"1"},
		"shanghai":{"item":"2"},
		"chengdu":{"item":"3"}
	},
	"America":{
		"aa":{"item":"1"},
		"bb":{"item":"2"}    
	},
	"Spain":{
		"dd":{"item":"1"},
		"ee":{"item":"2"},
		"ff":{"item":"3"}    
	}
};

for(var countryObj in value)
{
	document.write(countryObj + ":<br />")
	//没用的for(var cityObj in value.countryObj)
	for(var cityObj in value[countryObj])
	{
		document.write('    ' + cityObj + "<br />");
		for(var itemObj in value[countryObj][cityObj])
		{
			document.write("      "+ itemObj + value[countryObj][cityObj][itemObj] +"<br/>")    
		}
	}    
} 
		});
		
		
		
		
	});	

</script>
  </head>
  
  <body>
   		var list1 = [1,3,4];<br/>
		alert(list1[1]);<br/>
		var list2 = [{"name":"leamiko","xing":"lin"}];<br/>
		alert(list2[0]["xing"])<br/>
		alert(list2[0].xing)<br/>
   		<input type="button" id="test1" value="测试1"/>
   		<HR/>	
   
  </body>
</html>
