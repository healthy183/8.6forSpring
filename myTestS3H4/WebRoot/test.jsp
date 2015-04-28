<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'test.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript" src="js/jquery-1.7.2.js"></script>
<script type="text/javascript">
$(function(){
	$("#button").click(function(){
		var usrNameVal = $("#usrName").val();
		var usrPwdVal = $("#usrPwd").val();
		
		if(usrNameVal == ""){
			$("#span").html();
			$("#span").append("用户名不能为空!");
			return false;
		}
	
		if(usrPwdVal == ""){
			$("#span").html();
			$("#span").append("用户密码不能为空!");
			return false;
		}
		
		$.post("${pageContext.request.contextPath}/main/ajaxLgn.html",
			{usrName : usrNameVal,usrPwd : usrPwdVal},
				function(data){
					if(data =="ok"){
						alert("?搞咩飞机?");
						$("#myform").submit();	
						alert("?搞咩飞机2");
					}else{
						$("#span").html();
						$("#span").append("用户名称密码错误!");
						return false;
					}
		});
		});
	});
</script>
  </head>
  
  <body>
  	<form action="a.html" id="myform">
  	用户名字:<input id="usrName" name="usrname" value="梁健康"></input><br>
	用户密码:<input id="usrPwd" name="usrpwd" value="123"></input><br>
  		<input id="button" type="button" value="提交"></input>
  	</form>
  
  </body>
</html>
