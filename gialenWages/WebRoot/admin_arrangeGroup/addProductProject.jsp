<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'addProductProject.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

<script language="javascript" type="text/javascript" src="js/jquery-1.4.2.js"></script>
<script type='text/javascript' src='/gialenWages/dwr/engine.js'></script>
<script type='text/javascript' src='/gialenWages/dwr/interface/dwrAddBrand.js'></script>
<script type='text/javascript' src='/gialenWages/dwr/util.js'></script>
<script type="text/javascript" charset="gb2312" src="js/brandThreeLinkage.js"></script>
<script type="text/javascript" charset="gb2312" src="js/classThreeLinkage.js"></script>
<script type="text/javascript" charset="gb2312" src="js/queryProductGroup.js"></script>
<script type="text/javascript" charset="gb2312" src="js/choseCheckbox.js"></script>
<script type="text/javascript" charset="gb2312" >
var jq = jQuery.noConflict();
jq(function(){
	jq("#updtmiddleTable").click(function(){
		var productProjectNameVar = jq("#productProjectName").val();
		var productProjectTypeVar =	jq("#productProjectType").val();
		var productProjectVal =	jq("#productProjectVal").val();
		var	isAddBrandWagesVar = jq("#isAddBrandWages").val();
		
		if(productProjectNameVar ==""){
			alert("单品方案名称不允许为空!");
			return false;
		}			
	
		if(productProjectVal ==""){
			alert("提点内容不允许为空!");
			return false;
		}if(isNaN(productProjectVal)){
			alert("提点内容必须是正数!");
			return false;
		}
		
		if(productProjectVal.substring(0,1)=="0"){
			if(productProjectVal.substring(0,2)!="0."){
				alert("提点内容必须是正数!");
				return false;
			}
		}else if(productProjectVal <= 0){
			alert("提点内容必须是正数!");
			return false;
		}		
		/* var proIdClassVar =	jq(".proIdClass:checked");
		if(proIdClassVar.length <= 1){
			alert("你没有选择单品!");
			return false;
		}		
		var proStr = "";
		jq.each(proIdClassVar,function(){
			proStr += jq(this).val()+",";
		}); */
		//alert("截取先:"+proStr);
		//proStr = proStr.substring(3,(proStr.length-1));//截取01，和最后的，
		//alert("截取后:"+proStr);
		var proid = document.getElementsByName("proid");
		var quantity = document.getElementsByName("quantity");
		var GruopSql="";
		jq("input[name=items]")
									.each(
											function() {
												if (jq(this).attr("checked")) {
		
		
			var Sql = "-" + proid[jq(this).val() - 1].value
				+ ","+ quantity[jq(this).val() - 1].value;
		
		GruopSql += Sql;

												}

											});
		
		
		var projectTypeVar = jq("#projectType").val();
		var operatingMonthIdVar = jq("#operatingMonthId").val();
		var productProjectIdVar = jq("#productProjectId").val();
		
		//alert(GruopSql);
		
		if(productProjectIdVar =="01"){
			dwrAddBrand.addProductProject_Group(operatingMonthIdVar,GruopSql,productProjectNameVar,productProjectTypeVar,
					productProjectVal,isAddBrandWagesVar,projectTypeVar,function(data){

				jq("#productProjectId").val(data);
				var answer = confirm("添加单品项目成功!是否返回项目清单页面?");
				if(answer){
					//return false; operatingMonthId
					location.href ="admin_arrangeBrand_addBrand.action?"+
					"operatingMonthType="+"${param.operatingMonthType}&projectType="+"${param.projectType}&"+
						"operatingMonthId="+jq("#operatingMonthId").val();
					
				}else{
					return false;	
				}
			});
		}else{
			//alert("执行修改");
			dwrAddBrand.updtProductProject(proStr,productProjectIdVar,
				productProjectNameVar,productProjectTypeVar,
					productProjectVal,isAddBrandWagesVar,function(){
				
				var answer = confirm("修改项目成功!是否返回项目清单页面?");
				if(answer){
					location.href ="admin_arrangeBrand_addBrand.action?"+
					"operatingMonthType="+"${param.operatingMonthType}&projectType="+"${param.projectType}&"+
						"operatingMonthId="+jq("#operatingMonthId").val();
					return false;
				}else{
					return false;	
				}
				
			});
			//alert("修改完成");
			return false;
		}
	});
});
</script>
<script type="text/javascript" src="js/mybgcolor.js">
	
</script>
 <style type="text/css">
 	table{
 		margin:0px;
 	}
 
	*{ font-size:12px;
	   font-family:"宋体";
	 }
	 li {
			float:left;
}

	a:link { 
		font-size: 9pt; 
		color: blue; 
		text-decoration: none; 
		font-family: ""宋体""; 
	}
	a:visited{
	font-size: 9pt;
	color: blue;
	
	font-family: ""宋体"";
}
	a:hover {
		color: red; 
		font-family: ""宋体"";
		text-decoration: underline;
		}

.tableA{
	text-align:center;
	margin-bottom:8px;
	margin-top:8px;
	width:99%;
	font:Georgia 11px;
	font-size:12px;
	color:#333333;
	border-collapse:collapse;/*细线表格*/
}
.tableA th{
	border:1px solid #CBD8AC;/*细线条颜色*/
	height:22px;
}
/*
 .tableA td{
	text-align:left;
	border:1px solid #CBD8AC;
	height:22px;
}*//*细线条颜色*/
	
	.mytds{
	text-align:left;
	border:1px solid #CBD8AC;/*细线条颜色*/
	height:22px;
	}

	.searchTable{
		margin-bottom:8px;
		margin-top:8px;
		width:99%;
		font:Georgia 11px;
		font-size:12px;
		color:#333333;
		border-collapse:collapse;/*细线表格*/
	}
	
	.searchTable td{
		border:1px solid #CBD8AC;/*细线条颜色*/
		height:22px;
	}
	
	.rowspanTd{
		margin:auto;
		text-align:left;
		border:5px solid #CBD8AC;/*细线条颜色*/
		height:22px;
	}
	
	.proAddTable{
	
		text-align:center;
		margin-bottom:8px;
		margin-top:-7px;
		width:99%;
		font:Georgia 11px;
		font-size:12px;
		color:#333333;
		border-collapse:collapse;/*细线表格*/
		border-top-style: 
	}
	
	.proAddTable td{
		border:1px solid #CBD8AC;/*细线条颜色*/
		height:22px;
	}
	
	#efg{
		display: none;
	}
	
	#efg td{
		border:1px solid #CBD8AC;/*细线条颜色*/
		height:22px;
	}
	
</style>
 
  </head>
  
  <body>
  <input type="hidden" name="operatingMonthType" value="${param.operatingMonthType}"></input>
  <input type="hidden" name="projectType" value="${param.projectType}"></input>

	<table width="100%" class="searchTable">
	<tr>
		<td colspan="4" align="center">新增组合项目</td>
	</tr>
					<tr>
						<td>单品编码:<input id="proIdKeyword" name="proIdKeyword" /></td>
						<td>单品条码:<input id="barcodeKeyword" name="barcodeKeyword"/></td>
						<td>单品关键字:<input id="proNameKeyWord" name="proNameKeyWord" /></td>
						<td rowspan="5" align="center"><input id="queryProduct" class="queryProduct" type="button" value="查询单品"/></td>
					</tr>
					<tr>
						<td colspan="3">
						品牌大类:
						<select id="bigBrand" class="brandClass">
								<option value="0"></option>
								<c:forEach items="${requestScope.productBigBrandList}" var="brand">
									<option value="${brand.brandId}">${brand.brandName}</option>
								</c:forEach>
						</select>
						品牌中类:<select id="middleBrand" class="brandClass">
							 	<option value="0"></option>
							 	<c:forEach items="${requestScope.productMiddleBrandList}" var="brand">
							 		<option value="${brand.brandId}">${brand.brandName}</option>
							 	</c:forEach>
						</select>
						品牌小类:<select id="smallBrand">
							 	<option value="0"></option>
							 	<c:forEach items="${requestScope.productSmallBrandList}" var="brand">
							 		<option value="${brand.brandId}">${brand.brandName}</option>
							 	</c:forEach>
						</select>	
						</td>
					</tr>
					<tr>
						<td colspan="3">
						类别大类:<select id="bigClass">
								<option value="0"></option>
								<c:forEach items="${requestScope.productBigClassList}" var="class">
									<option value="${class.classId}">${class.className}</option>
								</c:forEach>
						</select>
						类别中类:<select id="middleClass">
							 	<option value="0"></option>
							 	<c:forEach items="${requestScope.productMiddleClassList}" var="class">
									<option value="${class.classId}">${class.className}</option>
								</c:forEach>
						</select>
						类别小类:<select id="smallClass">
							 	<option value="0"></option>
							 	<c:forEach items="${requestScope.productSmallClassList}" var="class">
									<option value="${class.classId}">${class.className}</option>
								</c:forEach>
						</select>
						</td>
					</tr>
					<tr>
						<td colspan="3">商品类型:
							<input type="checkbox" class="ProFlag" name="ProFlag" value="0" checked="checked"/>正常商品
							<input type="checkbox" class="ProFlag" name="ProFlag" value="1" checked="checked"/>赠品
							<input type="checkbox" class="ProFlag" name="ProFlag" value="2" checked="checked"/>物料耗材
							<input type="checkbox" class="ProFlag" name="ProFlag" value="3" checked="checked"/>流通商品试用装
							<input type="checkbox" class="ProFlag" name="ProFlag" value="4" checked="checked"/>自有试用装
							<input type="checkbox" class="ProFlag" name="ProFlag" value="5" checked="checked"/>待摊物料
							<input type="checkbox" class="ProFlag" name="ProFlag" value="6" checked="checked"/>普通物料
						</td>
					</tr>
					<tr>
						<td colspan="3">单品状态:
							<input type="checkbox" name="Status" value="0" checked="checked"/>未使用
							<input type="checkbox" name="Status" value="1" checked="checked"/>新品
							<input type="checkbox" name="Status" value="2" checked="checked"/>正常
							<input type="checkbox" name="Status" value="3" checked="checked"/>季节性禁止采购
							<input type="checkbox" name="Status" value="4" checked="checked"/>停止采购 
							<input type="checkbox" name="Status" value="5" checked="checked"/>停止要货
							<input type="checkbox" name="Status" value="8" checked="checked"/>停止销售
							<input type="checkbox" name="Status" value="9" checked="checked"/>完全停用
						</td>
					</tr>
				</table>
	<table id="2" class="proAddTable">
					<tr class="titleClass">
					<td>
   				<input id="operatingMonthId"  type="hidden" value="${requestScope.thisoperatingMonth.operatingMonthId}"></input>
   				营运月:${requestScope.thisoperatingMonth.operatingMonthName}
					</td>
						<td>
							单品方案名称:<input id="productProjectName" ></input>
							<input id="projectType" type="hidden" value="0"></input>
						</td>
						<td>
							<select id="productProjectType">
								<option value="0">按百分比提成</option>
								<option value="1">按件提成</option>
							</select>
							<input id="productProjectVal" ></input>
							<select id="isAddBrandWages">
								<option value="0">不算入品牌提中</option>
								<option value="1">算入品牌提中</option>
							</select>
						</td>
						<td align="right">
							<input type="hidden" id="productProjectId" value="01"></input><!-- 当前单品项目id -->
							<input type="button" id="updtmiddleTable" class="updtmiddleTable"  value="提交"/>
						</td>
					</tr>
			</table>
	<table id="proTable" class="proAddTable">
	<tr id="efg">
			<td>0</td>
			<td><input type="checkbox" id="mustbeCheck" class="proIdClass"
				value="01" checked="checked">
			</td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
		</tr>
		<tr id="abc" class="titleClass">
			<td>序号</td>
			<td>选择<input id="allCheckbox" type="checkbox" checked="checked"></input>
			</td>
			<td>商品编号</td>
			<td>商品条码</td>
			<td>商品名称</td>
			<td>规格</td>
			<td>品牌小类</td>
			<td>品类小类</td>
			<td>商品类型</td>
			<td>商品状态</td>
			<td>组合数量</td>
		</tr>
		
	</table>
  </body>
</html>
