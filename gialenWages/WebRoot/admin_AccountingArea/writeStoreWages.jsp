<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'writeStoreWages.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
<style type="text/css">
	*{ font-size:12px;
	   font-family:"宋体";
	   }
	
	#redId{
			background:transparent;
			border:0;
			color:red;
			width:100%;
		}
</style>
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
	
	.myTable tr{
		background-color:#FFFFFF;/**/
		font-size:14px;
		color: black;
	}
	.myTable td{
		line-height: 14px;height: 14px;
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
 		type="text/javascript" ><%-- src="js/validateStoreWages.js"--%>
 		
 		var jq = jQuery.noConflict();
		jq(function() {
			
			jq("#button").click(function() {

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
				
				realpath = document.getElementById("thisFile");
				dwrShowError.uploadExecl(realpath,function(fileString){
 					jq("#hiddenFile").val(fileString); 
 					dwrShowError.showStoreError(fileString,test);	
 				});
				
			});	

			function test(data){
				if(data.length == 0){
					jq("#upLoadForm").submit();
				}else if(data.length > 0){
				var	span = jq("#span");
				 var num = data.length;
				 span.html("");
				 for(var i=0;i<num;i++){
					 var v  = data[i];
					jq("<font>"+(i+1)+","+v+"</font><br>").appendTo(span);
				 }							
				}					
			};		
		});	
 </script>
	<script type="text/javascript">
	
	var jq = jQuery.noConflict();
	jq(function(){
		jq("#operatingMonthId").change(function(){
 			location.href
				= "admin_thisMonthWages_writeStoreWages.action?operatingMonthType="+
					"${param.operatingMonthType}&operatingMonthPathMoneyType="+
						"${param.operatingMonthPathMoneyType}&operatingMonthId="+jq("#operatingMonthId").val();
 		});	
	});
	</script>
   <script type="text/javascript" charset="gb2312" src="js/validateExcel.js"></script>
  <script type="text/javascript" charset="gb2312" src="js/mybgcolor.js"></script>
 
  </head>
  
  
  <body>
  
    <s:form action="admin_thisMonthWages_writeStoreWagesSuccess.action" namespace="/admin_thisMonthWages"
    		enctype="multipart/form-data" method="post" id="upLoadForm" theme="simple">
    		
    	<table class="myTable" cellpadding="3" cellspacing="1">
    		<tr>
    			<th class="myTableTh" align="left">
					      <s:hidden name="operatingMonthPathMoneyType" value="1"></s:hidden>
    					  <s:hidden name="operatingMonthType" value="%{#parameters.operatingMonthType}"></s:hidden>
					  
					      请上传 <select name="operatingMonthId" id="operatingMonthId">
						<option
							value="${requestScope.thisOperatingMonth.operatingMonthId}"
							selected="selected">${requestScope.thisOperatingMonth.operatingMonthName}</option>
						<c:forEach items="${requestScope.thisYearOperatingMonthList}"
							var="Month">
							<option value="${Month.operatingMonthId}">${Month.operatingMonthName}</option>
						</c:forEach>
					</select>
			 的门店销售奖核算标准execl表! 
			 <s:file name="file" id="thisFile"></s:file>
    		<input type="submit" value="确认提交"></input>			
    			</th>
    		</tr>
    		<tr class="myTableTr" align="left">
					<td class="myTableTd" align="left">
						<input id="redId" type="text" readonly="readonly"/>
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
    	
    	<c:if test="${requestScope.complatePercentList != null }">
    	<table class="myTable" cellpadding="3" cellspacing="1">
    	<tr><th>档期预计额</th>
    	<s:iterator value="#request.complatePercentList">
    		<th><s:property value="complatePercentName"></s:property></th>
    	</s:iterator>
    	</tr>
    	<s:iterator value="#request.planMoneyList" var="planMoney">
    		<tr class="mybgcolor"><td><s:property value="operatingMonthPlanMoneyName"></s:property></td>
    			<s:iterator value="#planMoney.grundbonuses">
    				<td>
    					<s:property value="grundbonusMoney"></s:property>
    				</td>
    			</s:iterator>
    		</tr>
    	</s:iterator>
    </table>
    	</c:if>
    	
  </body>
</html>
