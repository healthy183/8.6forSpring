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
<script language="javascript" type="text/javascript"
	src="js/jquery-1.4.2.js"></script>
<script type='text/javascript' src='/gialenWages/dwr/engine.js'></script>
<script type='text/javascript'
	src='/gialenWages/dwr/interface/dwrShowError.js'></script>
<script type='text/javascript'
	src='/gialenWages/dwr/interface/dwrAddBrand.js'></script>
<script type='text/javascript' src='/gialenWages/dwr/util.js'></script>
<script language="javascript" charset="gb2312" type="text/javascript">
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

			//alert(realpath.value);

			// alert("realpath"+ realpath);
			dwrAddBrand.uploadExecl(realpath, function(fileString) {
				//alert(fileString);
				jq("#hiddenFile").val(fileString);
				// alert("error");
				dwrAddBrand.showPlanMoneyError(fileString, test);
				//dwrShowError.showPlanMoneyError(fileString);	
			});
		});

		function test(data) {
			if (data.length == 0) {
				jq("#upLoadForm").submit();
			} else if (data.length > 0) {
				var span = jq("#span");
				var num = data.length;
				span.html("");
				for ( var i = 0; i < num; i++) {
					var v = data[i];
					jq("<font>" + (i + 1) + "," + v + "</font><br>").appendTo(
							span);
				}
			}
		}
		;
	});
</script>

</head>
<body>
	<s:form action="admin_arrangeBrand_writePlanMoneySuccess.action"
		namespace="/admin_arrangeBrand" enctype="multipart/form-data"
		method="post" id="upLoadForm" theme="simple">
		<input type="hidden" name="productProjectId"
			value="${productProjectId}"></input>
		<input type="hidden" name="planMoneyType" value="${planMoneyType}"></input>
		<input type="hidden" name="operatingMonthType"
			value="${operatingMonthType}"></input>
		<input type="hidden" name="operatingMonthId"
			value="${operatingMonthId}"></input>

		<s:if test="%{projectType==1}">
	 
	 公司定额execl表:
	 
</s:if>

		<s:else>
			<s:property value="#productProjectName" />定额execl表: 
    	</s:else>

		<s:file name="upfile" id="thisFile"></s:file>
		<input type="hidden" name="upfiles" id="hiddenFile"></input>
		<s:hidden name="uploadType" value="1"></s:hidden>
		<input type="button" id="button" value="确认提交"></input>
		<s:textfield id="redId"></s:textfield>
	</s:form>
	<span id="span"></span>
	<center>
		<table border="1" width="100%">

			<tr>
				<td colspan="2"><b1>${productProjectName}</b1>
				</td>
			</tr>


			<tr>
				<td>销售门店</td>
				<td>定额</td>
			</tr>

			<c:forEach items="${planmoneylist}" var="table" varStatus="p">

				<tr>
					<td><input value='${table.branch.braName}' />
					</td>

					<td><input value='${table.planMoneyCount}' />
					</td>

				</tr>

			</c:forEach>

		</table>

	</center>
	<s:debug></s:debug>
</body>
</html>
