<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.4.2.js"></script>	
	<title>Spring MVC - jQuery 整合教程</title>
</head>
<script type="text/javascript"> 
$(function() {
	 $("#demo1").click(function(){
		$.post("${pageContext.request.contextPath}/main/ajax/add.html",
					{inputNumber1:  $("#inputNumber1").val(),
				  	 inputNumber2:  $("#inputNumber2").val() 
				  	},
					function(data){							  													
						$("#sum").replaceWith('<span id="sum">'+ data + '</span>');							   
					});
	 });
	 
	  $("#demo2").click(function(){
	  	var userId = $("#userId").val();
		$.post("${pageContext.request.contextPath}/main/ajax/getUser/"+userId+".html",
					null,
					function(data){		
						var info = "姓名: " + data.name+",年龄: " + data.age + ",地址: " + data.address + ",性别: " + (data.sex == 1 ? "男" : "女")+",密码: " + data.password;					  													
						$("#info").html(info);							   
					});
	 });
	 
	  $("#demo3").click(function(){	  
		$.post("${pageContext.request.contextPath}/main/ajax/userList.html",
					null,
					function(data){	
						    var info = '';	
						     $.each(data,function(index,entity) { 
						       info += "姓名: " + entity.name+",年龄: " + entity.age + ",地址: " + entity.address + ",性别: " + (entity.sex == 1 ? "男" : "女")+",密码: " + entity.password+"<br>";   
						     });
						     $("#listInfo").html(info);
						  						    				   
					});
	 });	 
	 
	 $("#demo4").click(function(){	  
		$.post("${pageContext.request.contextPath}/main/ajax/userMap.html",
					null,
					function(map){	
					     var info = '';	
					     $.each(map,function(key,values) { 
					           info += "key="+key+"<br>";
					           $(values).each(function(){      
							       info += "姓名: " + this.name+",年龄: " + this.age + ",地址: " + this.address + ",性别: " + (this.sex == 1 ? "男" : "女")+",密码: " + this.password+"<br>";
							    });    
					             
					     });
					     $("#mapInfo").html(info);				   
					});
	 });	 
});
</script>


<body>
<h3>Spring MVC - jQuery 整合教程</h3>
<h4>AJAX version</h4>
<p>Demo 1 计算并返回值</p>
<div style="border: 1px solid #ccc; width: 250px;">
	Add Two Numbers: <br/>
	<input id="inputNumber1" type="text" size="5"> +
	<input id="inputNumber2" type="text" size="9">
	<input type="submit" id="demo1" value="Add" /> <br/>
	Sum: <br>
	<span id="sum">(Result will be shown here)</span>
</div>
<hr><br>
<p>Demo 2 获取一个对象</p>
<div style="border: 1px solid #ccc; width: 250px;">
	<select id="userId">		
		<c:forEach var="index" begin="1" end="5" step="1">
			<option value="${index}">${index}</option>
		</c:forEach>
	</select>
	<input type="submit" id="demo2" value="Get" /> <br/>	
	<span id="info">(Result will be shown here)</span>
</div>
<hr><br>
<p>Demo 3 返回List集合对象</p>
<div style="border: 1px solid #ccc; width: 250px;">	
	<input type="submit" id="demo3" value="Get List User" /> <br/>	
	<span id="listInfo">(Result will be shown here)</span>
</div>
<hr><br>
<p>Demo 4 返回Map集合对象</p>
<div style="border: 1px solid #ccc; width: 250px;">	
	<input type="submit" id="demo4" value="Get Map User" /> <br/>	
	<span id="mapInfo">(Result will be shown here)</span>
</div>
<hr><br>


<hr><br>

</body>
</html>
