<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'updtMyUsr.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="js/jquery-1.7.2.js"></script>
	<script type='text/javascript' src='/myTestS3H4/dwr/engine.js'></script>
    <script type='text/javascript' src='/myTestS3H4/dwr/interface/dwrMainController.js'></script>
    <script type='text/javascript' src='/myTestS3H4/dwr/util.js'></script>
    <script type="text/javascript">
    	var jq = jQuery.noConflict();
    	jq(function(){
			  jq(".submitUpdt").click(function(){
				 	
				// alert("?");
				  	var idVar =	jq(this).attr("id");
					var usridVar = jq("#usrid"+idVar).val();
					var usrnameVar = jq("#usrname"+idVar).val();
					var leadIdVar = jq("#leadId"+idVar).val();
					
					alert(usridVar+usrnameVar+ leadIdVar);
					dwrMainController.updtUsrSuccess
						(usridVar,usrnameVar,leadIdVar,function(data){
							
							alert(data);
					});
			  }); 		
    	});
    </script>
  </head>
  
  <body>
  //使用dwr3 annoation提交 
  		<table border="1">
  		<tr>
  			<td>序号</td><td>名称</td><td>领导</td><td>操作</td>
  		</tr>
 
  	<c:forEach items="${requestScope.usrVoList}" var="vo" varStatus="v">
  		<tr>
  			<td>${v.count}</td>
  			<td>
  				<input name="usrname" value="${vo.usrname}" id="usrname${v.count}"></input>
  				<input type="hidden" name="usrid" value="${vo.usrid}" id="usrid${v.count}"></input>
  				</td>
  			<td>
  		<select name="leadId" id="leadId${v.count}">
  		<c:if test="${vo.leadId != 0}">
  			<option value="${vo.leadId}">${vo.leaderName}</option>
  		</c:if>
  			<option value="0"></option>
  				<c:forEach items="${requestScope.leadList}" var="lead">
  					<c:if test="${lead.usrid != vo.leadId}">
							<option value="${lead.usrid}">${lead.usrname}</option>
  					</c:if>
  				</c:forEach>
  		</select>	
  			</td>
  			<td>
  				<a id="${v.count}" class="submitUpdt" href="javascript:void();">提交</a><!-- href="javascript:void();" -->
  			</td>
  		</tr>
  	</c:forEach>
  	 </table>
  
  
  </body>
</html>
