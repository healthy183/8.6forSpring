<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
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
* {
	font-size: 12px;
	font-family: "宋体";
}

#redId {
	background: transparent;
	border: 0;
	color: red;
	width: 100%;
}
</style>
<style type="text/css">
form {
	margin: 0px;
}

span {
	margin: 0px;
}

a:link {
	font-size: 9pt;
	color: blue;
	text-decoration: none;
	font-family: "" 宋体 "";
}

a:visited {
	font-size: 9pt;
	color: blue;
	font-family: "" 宋体 "";
}

a:hover {
	color: red;
	font-family: "" 宋体 "";
	text-decoration: underline;
}

.tableA {
	text-align: center;
	margin-bottom: 8px;
	margin-top: 8px;
	width: 98%;
	font: Georgia 11px;
	font-size: 12px;
	color: #333333;
	border-collapse: collapse; /*细线表格*/
}

.tableA th {
	border: 1px solid #CBD8AC; /*细线条颜色*/
	height: 22px;
}

.tableA td {
	border: 1px solid #CBD8AC; /*细线条颜色*/
	height: 22px;
}
</style>


<script language="javascript" type="text/javascript"
	src="js/jquery-1.4.2.js"></script>
<script type='text/javascript' src='/gialenWages/dwr/engine.js'></script>
<script type='text/javascript'
	src='/gialenWages/dwr/interface/dwrShowError.js'></script>
<script type='text/javascript'
	src='/gialenWages/dwr/interface/planBrandRelationAction.js'></script>
<script type='text/javascript' src='/gialenWages/dwr/util.js'></script>
<script type="text/javascript">
	var jq = jQuery.noConflict();
	jq(function() {
		jq("#updtmiddleTable").click(
				function() {

					var RewardQverQuota = "";
					var sum_personal = 0.0;
					var sum_qverQuota = 0.0;
					personalId = document.getElementsByName("PersonalId");
					tRUENAME = document.getElementsByName("TRUENAME");
					qverQuotaMoney = document
							.getElementsByName("qverQuotaMoney");
					qverQuota = document.getElementsByName("QverQuota");



					empId = document.getElementsByName("EmpId");

pERSONID = document.getElementsByName("PERSONID");
 
operatingMonthId = document.getElementsByName("operatingMonthId") ;

braId= document.getElementsByName("braId");



					for (i = 0; i < qverQuota.length; i++) {
					if (isNaN(qverQuotaMoney[i].value)) {
								alert(i+" "+"超定额不正确");
								return false;
							}
					
						sum_qverQuota += qverQuota[i].value*1;
					}

					for (i = 0; i < personalId.length; i++) {

						sum_personal += qverQuotaMoney[i].value*1;

						var sql = "#" + personalId[i].value + ","
								+tRUENAME[i].value + ","
								+empId[i].value + ","
								+qverQuotaMoney[i].value+ ","
								+pERSONID[i].value ;
					        	RewardQverQuota+=sql;

					}



    //alert( RewardQverQuota +":"+RewardQverQuota);		

					if (sum_personal > sum_qverQuota) {

						alert("不能超出分配的总金额");

						return false;
					}

				

					planBrandRelationAction.Insert_QverQuota(RewardQverQuota,operatingMonthId[0].value,braId[0].value,
							function(data) {

								var answer = confirm("操作成功!是否返回显示操作页面?");
								if (answer) {

									return false;
								} else {
									return false;
								}
							});
				});
	});
</script>
</head>
<body>

	<center>

		<form action="admin_PlanBrandRelation_Insert_QverQuota.action">
			<table class="tableA">




				<tr>
					<td>方案</td>
					<td>超定额</td>
				</tr>

				<c:forEach items="${ShowQverQuota}" var="table" varStatus="p">

					<tr class="mybgcolor">
						<td width="50%">${table[0]}</td>
						<td width="50%">${table[1]}</td>
						<input type="hidden" name="QverQuota" value='${table[1]}'
							id="QverQuota" />
					</tr>

				</c:forEach>

			</table>


			<table class="tableA">
				<c:forEach items="${SaleDailyMan}" var="tables" varStatus="t">
					<tr class="mybgcolor">


						<td>${tables[0]}<input name="PersonalId" id="PersonalId"
							value='${tables[0]}'  type="hidden" />
						</td>
						<td>${tables[1]}<input name="TRUENAME" id="TRUENAME" value='${tables[1]}'
							 type="hidden" />
						</td>
						<td>${tables[2]}<input name="NAMES" id="NAMES" value='${tables[2]}'
							  type="hidden"/>
						</td>
						<td><input name="qverQuotaMoney" id="qverQuotaMoney"
							value='0' />
						</td>
						<td>
						<input name="EmpId" id="EmpId"
							value='${tables[3]}' type="hidden"/></td>
							
							
							<input name="PERSONID" id="PERSONID"
							value='${tables[4]}' type="hidden"/></td>
					</tr>
				</c:forEach>
				<tr class="mybgcolor">
					<td colspan="5"><input type="button" id="updtmiddleTable"
						class="updtmiddleTable" value="确认提交"></input></td>
				</tr>
				<input id="operatingMonthId" type="hidden"
				value="${requestScope.operatingMonthId}"></input>
				<input id="braId" type="hidden"
				value="${requestScope.BraId}"></input>
			</table>
	</center>
	</form>
</body>
</html>
