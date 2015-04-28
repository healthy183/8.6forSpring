<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'showMonth.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

<style type="text/css">
	
	body{
		margin:0px;
	}

	*{ font-size:12px;
	   font-family:"宋体";
	 }
	
	#redId{
			background:transparent;
			border:0;
			color:red;
			width:100%;
			margin:0px;
		}
		
	form{
		margin:0px;
	}
	
	table{
		margin:0px;
		margin-left:10px;
	}
	
	span{
		margin:0px;
	}
	
	.myTable {
		width:98%;
		text-align:center;
		background-color: #CBD8AC;
		margin-bottom:8px;
		margin-top:8px;
	}
	.myTableTr{
		background-color:#FFFFFF;/**/
		font-size:14px;
		color: black;
	}
	.myTableTd{
		line-height: 14px;height: 14px;
	}
	
	.myTableSpan{
		font-size: 12px;color: #666600;
	}	
	
	.myTableTh{
		background: url(image/wbg.gif);
		/*background-color: #EEF4EA;*/
	}
		
</style>
 <script language="javascript" type="text/javascript" 
	src="js/jquery-1.4.2.js"></script>
<script type='text/javascript' src='/gialenWages/dwr/engine.js'></script>
<script type='text/javascript' src='/gialenWages/dwr/interface/dwrShowError.js'></script>
<script type='text/javascript' src='/gialenWages/dwr/util.js'></script>
<script language="javascript" charset="gb2312" 
 		type="text/javascript"src="js/validateMonth.js" ><%--  --%>
 </script> 
 <script language="javascript" charset="gb2312" >
 	var jq = jQuery.noConflict();
	jq(function(){
			jq("#upLoadForm").submit(function(){
				var fileVar = jq("#thisFile").val();
				var validateFileVar = fileVar.toLowerCase();
 				var lens = validateFileVar.length;
 				var extName = validateFileVar.substring((lens - 4), lens);
 				if (validateFileVar == "") {
 					jq("#redId").val("目录不能为空!");
 					return false;
 				} else if (extName != ".xls") {
 					jq("#redId").val("必须是以.xls结尾的execl文件");
 					return false;
 				}
					return true;
				}); 
		
});
</script> 
 
  </head>
  <body>

	<s:form action="admin_OperatingMonth_writeMonthSuccess.action"
		namespace="/admin_OperatingMonth" enctype="multipart/form-data"
			method="post" id="upLoadForm" theme="simple" target="main">
		
		<table class="myTable" cellpadding="3" cellspacing="1">
			<tr>
				<th class="myTableTh" align="left">
					 <s:hidden name="operatingMonthType" value="%{#parameters.operatingMonthType}"></s:hidden>
					 <!--  <input type="hidden" name="upfiles" id="hiddenFile"></input>   -->
					 <s:hidden name="uploadType" value="1"></s:hidden> 
					 
		<b style="font-size:16px;">更新${requestScope.thisYear}年营运月安排execl表:</b>			 
<s:file name="file" id="thisFile"></s:file>
				  	 <!--  <input id="button" type="button" value="确认提交"/>-->
				  	  <input type="submit" value="确认提交"/>
				</th>
			</tr>
			<tr class="myTableTr" align="left">
				<td class="myTableTd" align="left">
							<input id="redId" type="text" readonly="readonly"/>
					<span id="span"></span>
				</td>
			</tr>
				<c:if test="${requestScope.strList != null}">
				<c:forEach items="${requestScope.strList}" var="str" varStatus="s">
					<tr class="myTableTr" align="left">
						<td class="myTableTd" align="left">
							${str}
						</td>
					</tr>
				</c:forEach>
			</c:if>
			
		</table>
	</s:form>
  
  
  <c:if test="${requestScope.showWeekList != null}">
    <table class="myTable" cellpadding="2" cellspacing="1">
    	<tr>
			<th  class="myTableTh" colspan="6">
			<b style="font-size:16px;">${requestScope.thisYear}年营运月安排表</b>
			</th> 	
    	</tr>
    	<tr class="myTableTr">
			<td>季度*</td><td>周期*</td><td >天数*</td>
			<td>周代号*</td><td >日期*</td><td class="bgTd">节假日*</td>
		</tr>
		<s:iterator value="#request.showWeekList" status="status" var="s"><!-- status="" var="" -->
		<tr class="myTableTr">
			<s:if test="#status.index % 13 == 0">
				<td rowspan="13" class="myTableTd"><s:property value="operatingMonth.operatingMonth.operatingMonthName"/></td>
			</s:if>
			
			<s:if test="#status.index == 0 || #status.index == 4 || #status.index == 8 || #status.index == 13 ||
				#status.index == 17 || #status.index == 21 ||#status.index == 26 || #status.index == 30 ||
				#status.index == 34 || #status.index == 39 || #status.index == 43 || #status.index == 47">
			
			<td rowspan="${operatingMonth.operatingMonthSize}"><s:property value="operatingMonth.operatingMonthName"/></td>
			<td rowspan="${operatingMonth.operatingMonthSize}"><s:property value="operatingMonth.operatingMonthCount"/></td>
			</s:if>
			
			<td><s:property value="operatingMonthDate"/></td>
			<td><s:property value="operatingDate"/></td>
			<td><s:property value="operatingMonthBz"/></td>
		</tr>
		</s:iterator>
    </table>
  </c:if>  
  </body>
</html>
