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
					 
					qverQuotaMoney = document
							.getElementsByName("qverQuotaMoney");
					qverQuota = document.getElementsByName("QverQuota");

					ids = document.getElementsByName("ids");





 
					for (i = 0; i < qverQuota.length; i++) {
					
					
					if (isNaN(qverQuotaMoney[i].value)) {
								alert(i+" "+"超定额不正确");
								return false;
							}
					
					
						sum_qverQuota += qverQuota[i].value*1;
					}

					for (i = 0; i < qverQuotaMoney.length; i++) {

						sum_personal += qverQuotaMoney[i].value*1;

						var sql = "#" + ids[i].value + ","								
								+ qverQuotaMoney[i].value;

						RewardQverQuota += sql;

					}



   //alert( sum_qverQuota +":"+sum_personal);		

					if (sum_personal > sum_qverQuota) {

						alert("不能超出分配的总金额");

						return false;
					}

				

					planBrandRelationAction.Update_QverQuota(RewardQverQuota,
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

		<form action="admin_PlanBrandRelation_Update_QverQuota.action">
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
				<c:forEach items="${RewardQverQuota}" var="tables" varStatus="t">
					<tr class="mybgcolor">

						<td>  
						 ${tables.employee.personalId}
						</td>
						<td>  
					     ${tables.psnaccount.truename} 
						</td>
						<td>  
						 ${tables.psnaccount.cpcjobcode.names}  
						</td>
						<td><input name="qverQuotaMoney" id="qverQuotaMoney" value='${tables.qverQuotaMoney}'/>
						</td>
						 
						 
						 <td>
						 <input name="ids" id="ids" value='${tables.ids}'
							  type="hidden"/></td>
					</tr>
				</c:forEach>
				<tr class="mybgcolor">
					<td colspan="4"><input type="button" id="updtmiddleTable"
						class="updtmiddleTable" value="确认提交"></input></td>
				</tr>
			</table>
	</center>
	</form>
</body>
</html>
